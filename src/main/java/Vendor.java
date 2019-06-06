import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class Vendor{

    private int id;
    private String name;
    private String email;
    private int number;

    public Vendor(String name, String email, int number){
        this.name = name;
        this.email = email;
        this.number = number;

    }

    public int getNumber() {
        return number;
    }

    public String getEmail(){
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vendor vendor = (Vendor) o;
        return id == vendor.id &&
                Objects.equals(name, vendor.name) &&
                Objects.equals(email, vendor.email) &&
                number == vendor.number;
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO vendor (name, email, number) VALUES (:name, :email, :number)";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("number", this.number)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Vendor> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM vendor";
            return connection.createQuery(sql).executeAndFetch(Vendor.class);
        }
    }

    public static Vendor find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM vendor WHERE id=:id";
            Vendor vendor = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Vendor.class);
            return vendor;
        }
    }
    public void update(String name, String email, int number){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE vendor  SET name = :name , email = :email , number = :number WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("email", email)
                    .addParameter("number", number)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}