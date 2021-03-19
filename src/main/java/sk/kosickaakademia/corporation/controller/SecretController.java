package sk.kosickaakademia.corporation.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sk.kosickaakademia.corporation.log.Log;
import sk.kosickaakademia.corporation.util.Util;

@RestController
public class SecretController {
    private final String PASSWORD = "Kosice2021";
    Map<String,String>
    Log log = new Log();
    @GetMapping(path = "/secret")
    public String secret(){
        return"secret";
    }
    @PostMapping(path = "/login")
    public ResponseEntity<String> login (@RequestBody String auth){
        JSONObject object =null;
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
                map.pit(login,token);
                log.print("User logged");
                JSONObject obj = new JSONObject();
                obj.put("login",login);
                obj.put("token","Bearer"+token);
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

}
