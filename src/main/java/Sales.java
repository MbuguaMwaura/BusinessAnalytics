import org.sql2o.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class Sales{
    private int id;
    private String name;
    private int amount;
    private  String description;

    public Sales(String name,int amount, String description) {
        this.name = name;
        this.amount = amount;
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


    public String getDescription() {
        return description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sales that = (Sales) o;
        return id == that.id &&
                amount == that.amount &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO sales ( name, amount, description ) VALUES (:name, :amount, :description )";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .addParameter("amount", this.amount)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Sales> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM sales";
            return connection.createQuery(sql).executeAndFetch(Sales.class);
        }
    }

    public static Sales find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM sales WHERE id=:id";
            Sales sales = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Sales.class);
            return sales;
        }
    }
    public void update(String name, int quantity, int amount, int vendor_id, String description){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE sales  SET name = :name, description=:description, amount=:amount , WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description",description)
                    .addParameter("amount", amount)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}