package sk.kosickaakademia.corporation.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import sk.kosickaakademia.corporation.entity.User;
import sk.kosickaakademia.corporation.enumerator.Gender;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Util {
    public String getJson(List<User> list) {
        if(list==null || list.isEmpty()) return "{}";
        JSONObject object = new JSONObject();
        object.put("datatime", getCurrentDateTime());
        object.put("size", list.size());
        JSONArray jsonArray = new JSONArray();
        for(User us : list ) {
         JSONObject userJson = new JSONObject();
         userJson.put("id", us.getId() );
         userJson.put ("id", us.getFname() );
         userJson.put("lname", us.getLname() );
         userJson.put("age", us.getAge() );
         userJson.put("gender", us.getGender().toString() );
         jsonArray.add(userJson);
        }
        object.put("Users",jsonArray);
        return object.toJSONString();
    }
    public String getJson(User user) {
        if(user==null) return "{}";
        JSONObject object = new JSONObject();
        object.put("datatime",getCurrentDateTime() );
        object.put("size",1);
        JSONArray jsonArray=new JSONArray();
        JSONObject userJson = new JSONObject();
        userJson.put("id",user.getId() );
        userJson.put("fname",user.getFname() );
        userJson.put("lname",user.getLname() );
        userJson.put("age",user.getAge() );
        userJson.put("gender",user.getGender().toString() );
        jsonArray.add(userJson);
        object.put("users",jsonArray);
        return object.toJSONString();
    }
    public String getCurrentDateTime(){
        String pattern = "yyyy/MM/dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format (new Date());
        return date; //datum a čas zobrazí vo forme "2021-O3-05 09:35:13"
    }
    public String normalizeName(String name){
        //PETER ->Peter      jozEf ->Jozef
        if(name ==null || name.equals(""))
        return " ";
        name=name.trim();
        return Character.toUpperCase(name.charAt(0))+name.substring(1).toLowerCase();
    }
    public String getOverview(List<User> list) {
        int count = list.size();
        int male = 0;
        int female = 0;
        int sumage = 0;
        //ternárny operátor
        int min = count>0? list.get(0).getAge():0;
        int max = count>0? list.get(0).getAge():0;
        for(User u : list){
            if(u.getGender() == Gender.MALE) male++;
            else if (u.getGender() == Gender.FEMALE) female++;
            sumage+=u.getAge();
            if(min>u.getAge())
                min=u.getAge();
            if(max<u.getAge())
                max=u.getAge();
        }
        double avg=(double) sumage/count;
        JSONObject object = new JSONObject();
        object.put("count",count);
        object.put("min",min);
        object.put("max",max);
        object.put("countMale",male);
        object.put("countFemale",female);
        object.put("averageAge",avg);
        return object.toJSONString();
    }

}
