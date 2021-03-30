package sk.kosickaakademia.corporation.user;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Login {
    private static Map<String, Date> blocked = new HashMap<>(); //blokovaná; // premenná, zoznam použivateľov,ktorí sú časovo blokovaný
    private static Map<String,Integer>attempt = new HashMap<>();  //ďalší pokus
    private final String PSWRD = "Kosice2021!";  //heslo


    public Login() {                   //konštruktor prazdny
        blocked=new HashMap<>();
        attempt=new HashMap<>();
    }
    public String loginUser(String userName, String password) {
        //1. je user blokovany?
        if (blockedUsers(userName)) {
            return "Blocked user";
        }
        if (!(password.equals(PSWRD))) { //ak je heslo nespravne pripočíta zlý pokus

            if (attempt.get(userName) == null) {  //pokus prazdny
                attempt.put(userName, 1);
            } else {
                attempt.put(userName, attempt.get(userName) + 1);   //pripočítanie jedneho pokusu
            }

            if (attempt.get(userName) == 3) { //počita až do troch
                attempt.put(userName, attempt.get(userName) - 3); //zmaže pokusy o pripojenie
                Date date = new Date(); //zaznamena čas kedy kedy na stalo blokovanie
                blocked.put(userName, date);
                System.out.println(date.getTime());
                return "Blocked user";
            }
            return "wrong password"; //zle heslo
        }
        deleteAttempt(userName); //zavolá metodu na vymazanie pokusov nižšie
        return "Good password";
    }



    private boolean blockedUsers(String userName) {
        if (blocked.get(userName) == null) {
            return false;
        }
        Long dateNow = new Date().getTime();                //zistí aktualny čas
        if (blocked.get(userName).getTime() + 60000 > dateNow) { //počká minutu
            return true;
        } else {
            blocked.remove(userName);
            return false;
        }
    }
        private void deleteAttempt(String userName) { //výmaz zlých pokusov
            for(Map.Entry<String, Integer> entry : attempt.entrySet()) {
                if(entry.getValue().equals(userName)) {
                    attempt.remove(entry.getKey());  //vymaz ho z attemp
                }
            }

        


            //ak ano, neuplynul cas blokacie
            //ak uplynul...odblokuj+ over heslo
            //ak cas neuplynul, potom vrat blocked

            // je heslo spravne
            //ak ano generuj a vrat token + vymaz mu zle pokusy ak nejake ma
            //ak je heslo zle, pridaj alebo zvys pocet zlych pokusov
            // ak uz mam 3 zle pokusy, zistim aktualny cas,pridaj 1 min a zapis do blocked + vymaz ho z attemp

    }
}
