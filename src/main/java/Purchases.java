import org.sql2o.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class Purchases{
    private int id;
    private String name;
    private int amount;
    private int vendor_id;
    private  String description;

    public Purchases(String name,int amount, int vendor_id, String description) {
        this.name = name;
        this.amount = amount;
        this.vendor_id = vendor_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }


    public int getAmount() {
        return amount;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public String getDescription() {
        return description;
    }

    public Vendor getVendor(int id){
        return Vendor.find(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
      Purchases that = (Purchases) o;
        return id == that.id &&
                amount == that.amount &&
                vendor_id == that.vendor_id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO purchases ( name, amount, vendor_id, description ) VALUES (:name, :amount, :vendor_id, :description )";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .addParameter("vendor_id", this.vendor_id)
                    .addParameter("amount", this.amount)
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
    public void update(String name, int quantity, int amount, int vendor_id, String description){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE purchases  SET name = :name, description=:description, amount=:amount, vendor_id=:vendor_id , WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description",description)
                    .addParameter("vendor_id",vendor_id)
                    .addParameter("amount", amount)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}