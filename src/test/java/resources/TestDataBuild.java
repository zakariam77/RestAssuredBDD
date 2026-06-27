package resources;

import POJO.AddPlace;
import POJO.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public static AddPlace addPlacePayload(String name, String lang, String address){

        AddPlace addPlace = new AddPlace();
        addPlace.setLocation(new Location(-38.383494, 33.427362));
        addPlace.setAddress(address);
        addPlace.setAccuracy(50);
        addPlace.setName(name);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("http://google.com");
        addPlace.setLanguage(lang);
        addPlace.setTypes(new ArrayList<>(List.of("shoe park","shop")));

        return addPlace;
    }
    public static String getDelPayload(String placeID){

        return "{\n" +
                "    \"place_id\": \"" + placeID + "\"\n" +
                "}";
    }
}
