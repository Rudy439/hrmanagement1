package sk.kosickaakademia.corporation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    { SpringApplication.run(App.class,args);

        /*System.out.println( "Hello World!" );
        Database db = new Database();
        db.getConnection();
        //db.insertNewUser(new User (" Rudolf, Mrkva, 40, 0 "));
        //List<User> list = db.getUsersByAge(40,60);
        List<User> list = db.getUsersByAge();
        String text = new Util().getJson(list);
        System.out.println(text);

        //for(User u:list)
            //System.out.println(new Util().getJson(u));*/

    }
}

