import org.sql2o.*;
import java.util.*;

public class User {
    private String username;
    private String email;
    private String password;
    private int id;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getUsername(){
        return username;
    }

    public User(String username,String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public void register(){
        try(Connection con = DB.sql2o.open()){
            String sql = "INSERT INTO users (username,email,password) VALUES (:username,:email,:password)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("username", this.username)
                    .addParameter("email", this.email)
                    .addParameter("password", this.password)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<User> all(){
        String sql = "SELECT * FROM users";
        try (Connection con = DB.sql2o.open()){
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    public static List<String> allEmails(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT email FROM users";
            return con.createQuery(sql).executeAndFetch(String.class);
        }
    }


    public String getUserPassword(){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT password FROM users WHERE email = :email";
            return con.createQuery(sql)
                    .addParameter("email", this.email)
                    .executeAndFetch(String.class).toString()
                    .replace("[","").replace("]","");
        }
    }
}
