package Test_demo;

import Model.User;
import dao.impl.UserDaoImpl;

import java.util.List;

public class UserDaoTest {

    public static void main(String[] arge){
        UserDaoImpl dao = new UserDaoImpl();
        User userByID = dao.findUserByID(10);
        System.out.println(userByID);

        List<User> l = dao.findUserByName("王");
        for (User u:l
             ) {
            System.out.println(u);
        }


    }
}
