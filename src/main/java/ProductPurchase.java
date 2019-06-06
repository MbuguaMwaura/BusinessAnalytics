import org.sql2o.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class ProductPurchase{
    private int id;
    private Timestamp date;
    private String name;
    private int quantity;
    private int price;
    private int vendor_id;
    private  String description;

    public ProductPurchase(String name, int quantity, int price, int vendor_id, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.vendor_id = vendor_id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Timestamp getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
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
        ProductPurchase that = (ProductPurchase) o;
        return id == that.id &&
                quantity == that.quantity &&
                price == that.price &&
                vendor_id == that.vendor_id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO product_purchase (date, name, quantity, price, vendor_id, description ) VALUES (now(), :name, :quantity, :price, :vendor_id, :description )";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .addParameter("quantity", this.quantity)
                    .addParameter("vendor_id", this.vendor_id)
                    .addParameter("price", this.price)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<ProductPurchase> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM product_purchase";
            return connection.createQuery(sql).executeAndFetch(ProductPurchase.class);
        }
    }

    public static ProductPurchase find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM product_purchase WHERE id=:id";
            ProductPurchase productPurchase = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(ProductPurchase.class);
            return productPurchase;
        }
    }
    public void update(String name, int quantity, int price, int vendor_id, String description){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE product_purchase  SET name = :name, description=:description, price=:price, vendor_id=:vendor_id , quantity = :quantity WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description",description)
                    .addParameter("quantity", quantity)
                    .addParameter("vendor_id",vendor_id)
                    .addParameter("price", price)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}