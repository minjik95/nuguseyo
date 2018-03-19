package ds.ac.kr.nuguseyo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
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

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> listItems;
    public String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        listItems = new ArrayList<>();

//        for(int i = 0; i < 5; i++) {
//            Item items = new Item(true, "123", "http://img.theqoo.net/img/IpAiy.gif");
//            listItems.add(i, items);
//        }

        Item item1 = new Item(true, "125",
                "http://res.heraldm.com/content/image/2015/12/15/20151215000161_0.jpg");
        listItems.add(item1);

        Item item2 = new Item(true, "125",
                "http://img.tenasia.hankyung.com/webwp_kr/wp-content/uploads/2017/11/2017110720122915751.jpg");
        listItems.add(item2);

        Item item3 = new Item(true, "125",
                "http://img.insight.co.kr/static/2017/12/24/700/ta07wn8fb2n2n7581oc9.jpg");
        listItems.add(item3);

        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        ItemAdapter itemAdapter = new ItemAdapter(this, listItems);
        recyclerView.setAdapter(itemAdapter);
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
                //Log.d("photoFile", "photoFile은 " + photoFile);
            } catch (IOException e) {
                //Log.d("error", "error는 " + e.getMessage());
            }

            if(photoFile != null) {
                Uri photoUri = FileProvider.getUriForFile(this,
                                                        "ds.ac.kr.nuguseyo.fileprovider",
                                                        photoFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(cameraIntent, 10);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_hhmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 10) {
            Item item4 = new Item(true, "125", mCurrentPhotoPath);
            listItems.add(item4);
        }

    }
}
