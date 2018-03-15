package ds.ac.kr.nuguseyo;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Owner on 2018-03-15.
 */

public class ValidateRequest extends StringRequest {

    final static private String URL = "http://minjik95.cafe24.com/UserValidate.php";
    private Map<String, String> parameters;

    public ValidateRequest(String userID, Response.Listener listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);

    }

    public Map<String, String> getParams() {
        return parameters;
    }
}
