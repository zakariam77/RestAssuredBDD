package resources;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonPathUtil {
    public static String  jsonPathValue(Response response, String key){

        JsonPath jsonPath = new JsonPath(response.asString());
        return jsonPath.get(key);

    }
}
