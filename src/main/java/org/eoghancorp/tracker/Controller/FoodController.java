package org.eoghancorp.tracker.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eoghancorp.tracker.Controller.DbController;
import org.eoghancorp.tracker.Models.FoodResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;


@RestController
@RequestMapping("/food")
public class FoodController {
    private static DbController db;

    public FoodController() {
        try {
            db = new DbController();

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("")
    public FoodResponse getFoods(@RequestParam String searchvalue) {
        FoodResponse apiResponse = GetFoodResponse(searchvalue);

        return apiResponse;
    }


    @GetMapping("/add")
    public boolean addFood(@RequestParam String userId, @RequestParam String foodId) {

        System.out.println("USERID: \t" + userId);
        System.out.println("FOODID: \t" + foodId);

        try {
            return db.addFoodToUser(userId, foodId);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }


    static FoodResponse GetFoodResponse(String searchCriteria) {
        String creds = "3491a2842fc9958c7fadbdd189297fdc";
        String appId = "51c5235c";

        String url = String.format("https://api.edamam.com/api/food-database/v2/parser?app_id=%s&app_key=%s&ingr=%s", appId, creds, searchCriteria);

        System.out.println(url);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            FoodResponse res = mapper.readValue(response.getBody(), FoodResponse.class);

            res.LoopHints(res);

            return res;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        return null;

    }



}