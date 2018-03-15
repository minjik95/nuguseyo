package ds.ac.kr.nuguseyo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 2018-03-15.
 */

public class SignUpRequest extends StringRequest {

    final static private String URL = "http://minjik95.cafe24.com/UserRegister.php";
    private Map<String, String> parameters;

    public SignUpRequest(String userID, String userPassword, String userMajor, String userEmail, Response.Listener listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userMajor", userMajor);
        parameters.put("userEmail", userEmail);

    }

    public Map<String, String>getParams() {
        return parameters;
    }
}
