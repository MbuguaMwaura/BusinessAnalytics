import org.sql2o.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class ProductSale{
    private int id;
    private Timestamp date;
    private String name;
    private int quantity;
    private int price;
    private int customer_id;
    private  String description;

    public ProductSale(String name, int quantity, int price, int customer_id, String description) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.customer_id = customer_id;
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

    public int getCustomer_id() {
        return customer_id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSale that = (ProductSale) o;
        return id == that.id &&
                quantity == that.quantity &&
                price == that.price &&
                customer_id == that.customer_id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO product_sale (date, name, quantity, price, customer_id, description ) VALUES (now(), :name, :quantity, :price, :customer_id, :description )";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .addParameter("quantity", this.quantity)
                    .addParameter("customer_id", this.customer_id)
                    .addParameter("price", this.price)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<ProductSale> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM product_sale";
            return connection.createQuery(sql).executeAndFetch(ProductSale.class);
        }
    }

    public static ProductSale find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM product_sale WHERE id=:id";
            ProductSale productSale = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(ProductSale.class);
            return productSale;
        }
    }
    public void update(String name, int quantity, int price, int customer_id, String description){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE product_sale  SET name = :name, description=:description, price=:price, customer_id=:customer_id , quantity = :quantity WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description",description)
                    .addParameter("quantity", quantity)
                    .addParameter("customer_id",customer_id)
                    .addParameter("price", price)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}