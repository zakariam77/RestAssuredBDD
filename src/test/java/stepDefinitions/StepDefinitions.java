package stepDefinitions;

import POJO.AddPlace;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIResources;
import resources.JsonPathUtil;
import resources.ReusableCode;
import resources.TestDataBuild;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions  extends ReusableCode {
    AddPlace addPlacePayload;
    Response response;
    RequestSpecification requestSpecification;
    static String place_id;

    @Given("Add Place Payload {string} {string} {string}")

    public void add_place_payload(String name, String language, String address) throws FileNotFoundException {
        addPlacePayload = TestDataBuild.addPlacePayload(name, language, address);

        requestSpecification = given().spec(requestBase()).body(addPlacePayload);
    }

    @When("user calls {string} with {string} http request")
    public void user_calls_with_post_http_request(String resource, String httpMethod) {
       APIResources resourceAPI =  APIResources.valueOf(resource);
        switch (httpMethod){
            case "POST" : response = requestSpecification.when().post(resourceAPI.getResource());
            break;
            case "GET" : response = requestSpecification.when().get(resourceAPI.getResource());
            break;
        }
    }

    @Then("the api call get success with status code {int}")
    public void the_api_call_get_success_with_status_code(int statusCode) {
        assertEquals(statusCode, response.getStatusCode() );
    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectedValue) {

        assertEquals(expectedValue, JsonPathUtil.jsonPathValue(response, key));
    }

    @Then("verify place_id to maps name {string} using {string} using {string}")
    public void verify_place_id_to_maps_name_using_using(String expectedName, String resource, String httpMethod) throws FileNotFoundException {

        place_id = JsonPathUtil.jsonPathValue(response, "place_id");

        requestSpecification = given().spec(requestBase()).queryParam("place_id", "nn");

        user_calls_with_post_http_request(resource, httpMethod);

        assertEquals(expectedName, JsonPathUtil.jsonPathValue(response, "name"));

    }

    //

    @Given("Delete place payload")
    public void delete_place_payload() throws FileNotFoundException {
        requestSpecification = given().spec(requestBase()).body(TestDataBuild.getDelPayload(place_id));
    }


}
