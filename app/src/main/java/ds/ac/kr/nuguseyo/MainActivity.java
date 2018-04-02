package ds.ac.kr.nuguseyo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public static ItemAdapter itemAdapter;

    Uri photoUri;

    public static ArrayList<Item> listItems;

    String timeStamp;
    String imagePath;

    String userID;

    public static JSONArray array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        userID = intent.getExtras().getString("userID");
        Log.d("userID는 ", "" + userID);

        recyclerView = findViewById(R.id.rv_list);

        listItems = new ArrayList<>();
        imagePath = "http://minjik95.cafe24.com/";

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        itemAdapter = new ItemAdapter(this, listItems);
        recyclerView.setAdapter(itemAdapter);

        LoadDataFromServer();

        requirePermission();

        FloatingActionButton fabButton = findViewById(R.id.fab_button);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean camera = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
                boolean write = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

                if(camera && write) {
                    takePicture();
                } else {
                    Toast.makeText(MainActivity.this, "카메라 권한 및 쓰기 권한이 없습니다.", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void requirePermission() {
        String[] permissions = new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ArrayList<String> listPermissionsNeeded = new ArrayList<>();

        for(String permission: permissions) {

            if(ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                //권한 허가가 안됐을 경우 권한 추가
                listPermissionsNeeded.add(permission);
            }

            if(!listPermissionsNeeded.isEmpty()) {
                //권한 요청
                ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
            }
        }
    }

    private void takePicture() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                Log.d("error", "error는 " + e.getMessage());
            }

            if(photoFile != null) {
                photoUri = FileProvider.getUriForFile(this,
                                                        "ds.ac.kr.nuguseyo.fileprovider",
                                                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(cameraIntent, 10);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
        String imageFileName = timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }

    /**

     photoUri : content://ds.ac.kr.nuguseyo.fileprovider/my_images/20180320_103606_396953548.png
     mCurrentPhotoPath : /storage/emulated/0/Android/data/ds.ac.kr.nuguseyo/files/Pictures/20180325_025234_2093980949.png

     **/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10 && resultCode == Activity.RESULT_OK) {

            Intent startIntent = new Intent(MainActivity.this, PostActivity.class);

            startIntent.setData(photoUri);
            startIntent.putExtra("imageName", timeStamp + ".png");
            startIntent.putExtra("userID", userID);

            startActivity(startIntent);
        }

    }

    private void LoadDataFromServer() {

        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = new FormBody.Builder()
                        .add("userID", userID)
                        .build();

                Request request = new Request.Builder()
                        .url("http://minjik95.cafe24.com/MyData.php")
                        .post(requestBody)
                        .build();

                try {
                    okhttp3.Response response = client.newCall(request).execute();

                    array = new JSONArray(response.body().string());

                    for(int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);

                        Item items = new Item(object.getInt("id"), true, object.getString("userID"), object.getString("path"), object.getString("content"));

                        listItems.add(items);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    Log.d("End of posts","End of posts");
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                itemAdapter.notifyDataSetChanged();
            }
        };

        task.execute();
    }

}
