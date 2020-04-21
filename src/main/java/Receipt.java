import org.sql2o.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Receipt{
    private int id;
    private String description;
    private Timestamp date;
    private int quantity;
    private int price;
    private boolean paid;
    private int customer_id;
    private int product_id;
    private int payment_id;

    public Receipt(String description, int quantity, int price, boolean paid, int customer_id, int product_id, int payment_id) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.paid = paid;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.payment_id = payment_id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public boolean isPaid() {
        return paid;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public int getPurchase_id() {
        return product_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public String getFormattedDate(){
        return DateFormat.getDateTimeInstance().format(date);
    }

    public static int receiptamount(int id) {
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT quantity FROM receipt WHERE id=:id";
            String sqlTwo = "SELECT price FROM receipt WHERE id=:id";
            Integer quantity = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Integer.class);
            Integer price = connection.createQuery(sqlTwo)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Integer.class);


            return quantity*price;
        }
    }

    public static int unpaid() {
        try (Connection connection = DB.sql2o.open()) {
            String sql = "SELECT id FROM receipt where paid = false";
            List<Integer> ids = connection.createQuery(sql).executeAndFetch(Integer.class);

            int sum = 0;
            for (int id : ids)
                sum += receiptamount(id);
            return sum;
        }
    }

    public static int total() {
        try (Connection connection = DB.sql2o.open()) {
            String sql = "SELECT id FROM receipt";
            List<Integer> ids = connection.createQuery(sql).executeAndFetch(Integer.class);

            int sum = 0;
            for (int id : ids)
                sum += receiptamount(id);
            return sum;
        }
    }




    public Customer getCustomer(int id) {
        return Customer.find(id);
    }
    public Sales getSales(int id) {
        return Sales.find(id);
    }

    public Account getAccount(int id){
        return Account.find(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return id == receipt.id &&
                quantity == receipt.quantity &&
                price == receipt.price &&
                paid == receipt.paid &&
                customer_id == receipt.customer_id &&
                product_id == receipt.product_id &&
                payment_id == receipt.payment_id &&
                Objects.equals(description, receipt.description);
    }


    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO receipt (date, description, quantity, price, paid, customer_id, product_id, payment_id) VALUES (now(),:description,:quantity, :price, :paid, :customer_id, :product_id, :payment_id)";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("description", this.description)
                    .addParameter("quantity", this.quantity)
                    .addParameter("price", this.price)
                    .addParameter("paid", this.paid)
                    .addParameter("customer_id", this.customer_id)
                    .addParameter("product_id", this.product_id)
                    .addParameter("payment_id", this.payment_id)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Receipt> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM receipt";
            return connection.createQuery(sql).executeAndFetch(Receipt.class);
        }
    }

//    public static int getUnpaid() {
//        try (Connection connection = DB.sql2o.open()) {
//            String sql = "SELECT * FROM receipt WHERE paid = false";
//            List<Integer> unpaid = connection.createQuery(sql).executeAndFetch(Integer.class);
//
//            int sum = 0;
//            if(unpaid.size() != 0) {
//
//
//                for (int id : unpaid)
//                    sum += id;
//            }
//                return sum;
//
//        }
//    }

    public static Receipt find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM receipt WHERE id=:id";
            Receipt receipt = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Receipt.class);
            return receipt;
        }
    }
    public void update(String description, int quantity, int price, boolean paid, int customer_id, int product_id, int payment_id){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE receipt  SET description = :description , quantity = :quantity, price=:price, paid=:paid, customer_id=:customer_id, product_id=:product_id, payment_id=:payment_id WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("quantity", quantity)
                    .addParameter("price", price)
                    .addParameter("paid", paid)
                    .addParameter("customer_id", customer_id)
                    .addParameter("product_id", product_id)
                    .addParameter("payment_id", payment_id)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}