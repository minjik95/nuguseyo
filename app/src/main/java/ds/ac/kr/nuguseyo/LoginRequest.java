package ds.ac.kr.nuguseyo;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://minjik95.cafe24.com/UserLogin1.php";
    private Map<String, String> parameters;

    public LoginRequest(String userID, String userPassword, Response.Listener listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);

        Log.d("parameters","" + parameters);

    }

    public Map<String, String>getParams() {
        return parameters;
    }
}
