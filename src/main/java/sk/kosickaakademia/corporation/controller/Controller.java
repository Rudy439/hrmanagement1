package sk.kosickaakademia.corporation.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.kosickaakademia.corporation.database.Database;
import sk.kosickaakademia.corporation.entity.User;
import sk.kosickaakademia.corporation.enumerator.Gender;
import sk.kosickaakademia.corporation.log.Log;
import sk.kosickaakademia.corporation.util.Util;

import java.util.List;

@RestController

public class Controller {
    Log log = new Log();

    @PostMapping("/user/new")
    public ResponseEntity<String> insertNewUser (@RequestBody String data) {
        try{
            JSONObject obj = (JSONObject) new JSONParser().parse(data);
            String fname = ((String)obj.get ("fname")).trim();
            String lname = ((String)obj.get ("lname")).trim();
            String gender = (String)obj.get ("gender");
            Integer age = Integer.parseInt((String)obj.get ("age"));
            if(fname==null || lname==null || lname.trim().length()==0 || fname.length()==0 || age<1){
                log.error ("Missing Lname or Fname or incorrect age. ");
                JSONObject object = new JSONObject();
                object.put ("error" , "error") ;

                return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
            }
            Gender g;
            if(gender==null) {
                g = Gender.OTHER;
            }else if (gender.equalsIgnoreCase("male")){
            g=Gender.MALE;
            }else if (gender.equalsIgnoreCase("female")) {
                g = Gender.FEMALE;
            }else g=Gender.OTHER;

            User user = new User(fname,lname,age,g.getValue());
            new Database().insertNewUser(user);




        } catch (ParseException e) {
            log.error("Cannot insert data in/user/new controller");
            JSONObject obj = new JSONObject();
            obj.put("error","Cannot insert data in/user/new controller");


            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body(obj.toJSONString());
        }
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(null);
    }
    /*@GetMapping("/user")
    public ResponseEntity<String> getAllUsers(){
        List<User> list = new Database().getAllUsers();
        String json = new Util.getJson(list)*/

    @GetMapping(path = "/user/age")
    public ResponseEntity<String> getUserAge(@RequestParam(value ="from") int from,
        @RequestParam(value = "to") int to){
        Util util = new Util();
        String response = util.getJson(new Database().getUsersByAge(from,to));
        return ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON).body(null);
    }
    @GetMapping(path = "/users/age")
    public ResponseEntity<String> getUsersAge(@RequestParam(value ="from") int from, @RequestParam(value = "to") int to){
        if(from>to || from<1 ) {

            return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON).body("null");
        }
       List<User> list = new Database().getUsersByAge(from, to);
        for(User user:list)
            System.out.println(user);
        String json = new Util().getJson(list);
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(json);

}}
