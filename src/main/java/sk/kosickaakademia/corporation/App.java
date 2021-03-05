package sk.kosickaakademia.corporation;

import sk.kosickaakademia.corporation.database.Database;
import sk.kosickaakademia.corporation.entity.User;
import sk.kosickaakademia.corporation.util.Util;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        Database db = new Database();
        db.getConnection();
        //db.insertNewUser(new User (" Rudolf, Mrkva, 40, 0 "));
        //List<User> list = db.getUsersByAge(40,60);
        List<User> list = db.getUsersByAge();
        String text = new Util().getJson(list);
        System.out.println(text);

        //for(User u:list)
            //System.out.println(new Util().getJson(u));

    }
}

