package stepDefinitions;

import io.cucumber.java.Before;

import java.io.FileNotFoundException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeDeletePlace() throws FileNotFoundException {

        // run @Addplace before to get place_id
        StepDefinitions definitions = new StepDefinitions();
        if(StepDefinitions.place_id == null) {

            definitions.add_place_payload("John", "english", "wick");
            definitions.user_calls_with_post_http_request("AddPlaceAPI", "POST");
            definitions.verify_place_id_to_maps_name_using_using("John", "GetPlaceAPI", "GET");


        }

    }
}
