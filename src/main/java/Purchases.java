import org.sql2o.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class Purchases{
    private int id;
    private String name;
    private int price;
    private int vendor_id;
    private  String description;

    public Purchases(String name,int price, int vendor_id, String description) {
        this.name = name;
        this.price = price;
        this.vendor_id = vendor_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }


    public int getPrice() {
        return price;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
      Purchases that = (Purchases) o;
        return id == that.id &&
                price == that.price &&
                vendor_id == that.vendor_id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO purchases ( name, price, vendor_id, description ) VALUES (:name, :price, :vendor_id, :description )";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .addParameter("vendor_id", this.vendor_id)
                    .addParameter("price", this.price)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Purchases> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM purchases";
            return connection.createQuery(sql).executeAndFetch(Purchases.class);
        }
    }

    public static Purchases find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM purchases WHERE id=:id";
            Purchases purchases = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Purchases.class);
            return purchases;
        }
    }
    public void update(String name, int quantity, int price, int vendor_id, String description){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE purchases  SET name = :name, description=:description, price=:price, vendor_id=:vendor_id , WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description",description)
                    .addParameter("vendor_id",vendor_id)
                    .addParameter("price", price)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}