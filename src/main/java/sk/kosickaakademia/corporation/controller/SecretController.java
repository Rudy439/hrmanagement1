package sk.kosickaakademia.corporation.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kosickaakademia.corporation.log.Log;
import sk.kosickaakademia.corporation.util.Util;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SecretController {
    private final String PASSWORD = "Kosice2021";
    Map<String, String> map = new HashMap<>();
    Log log = new Log();

    @GetMapping(path = "/secret")
    public String secret(@RequestHeader("token") String header) {
        System.out.println(header);
        String token = header.substring(7);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(token)) {
                return "secret";
            }
        }
        return "401";
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody String auth) {
        JSONObject object = null;
        try {
            object = (JSONObject) new JSONParser().parse(auth);
            String login = ((String) object.get("login"));
            String password = ((String) object.get("password"));
            System.out.println(login + " " + password);
            if (login == null || password == null) {
                log.error("Mistake in login or password");
                return ResponseEntity.status(400).body("");
            }
            if (password.equals(PASSWORD)) {
                String token = new Util().generateToken();
                map.put(login, token);
                log.print("User logged");
                JSONObject obj = new JSONObject();
                obj.put("login", login);
                obj.put("token", "Bearer" + token);
                return ResponseEntity.status(200).body("");
            } else {
                log.error("Wrong password");
                return ResponseEntity.status(401).body("");
            }


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(path ="/user/logout", method = RequestMethod.DELETE)
    public ResponseEntity<String> logout(@RequestBody String auth) {
        JSONObject obj = null;

        try {
            obj = (JSONObject) new JSONParser().parse(auth);
            String login = ((String) obj.get("login"));

            return ResponseEntity.status(200).body("Logout Successfully!");

        } catch (ParseException e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Logout Unsuccessfully");
        }

    }
}
