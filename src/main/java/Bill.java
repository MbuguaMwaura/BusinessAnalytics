import org.sql2o.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.*;
import java.lang.*;
import java.io.Console;

public class Bill{
    private int id;
    private String description;
    private Timestamp date;
    private int quantity;
    private int price;
    private boolean paid;
    private int vendor_id;
    private int purchase_id;
    private int payment_id;

    Console ob = System.console();


    public Bill(String description, int quantity, int price, boolean paid, int vendor_id, int purchase_id, int payment_id) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.paid = paid;
        this.vendor_id = vendor_id;
        this.purchase_id = purchase_id;
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

    public int getVendor_id() {
        return vendor_id;
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public String getFormattedDate(){
        return DateFormat.getDateTimeInstance().format(date);
    }

    public Account getAccount(int id){
        return Account.find(id);
    }

    public Vendor getVendor(int id) {
        return Vendor.find(id);
    }
    public Purchases getProduct(int id) {
        return Purchases.find(id);
    }





    public static List<Bill> price(){
        try(org.sql2o.Connection connection = DB.sql2o.open()){
            String sql = "SELECT price FROM bill";
            return connection.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Bill.class);
        }
    }


    public static int billamount(int id) {
        try(org.sql2o.Connection connection = DB.sql2o.open()) {
            String sql = "SELECT quantity FROM bill WHERE id=:id";
            String sqlTwo = "SELECT price FROM bill WHERE id=:id";
            Integer quantity = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Integer.class);
            Integer price = connection.createQuery(sqlTwo)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Integer.class);


            return quantity*price;
        }
    }

    public static int total() {
        try (org.sql2o.Connection connection = DB.sql2o.open()) {
            String sql = "SELECT id FROM bill";
            List<Integer> ids = connection.createQuery(sql).executeAndFetch(Integer.class);

            int sum = 0;
            for (int id : ids)
                sum += billamount(id);
            return sum;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return id == bill.id &&
                quantity == bill.quantity &&
                price == bill.price &&
                paid == bill.paid &&
                vendor_id == bill.vendor_id &&
                purchase_id == bill.purchase_id &&
                payment_id == bill.payment_id &&
                Objects.equals(description, bill.description);
    }


    public void save(){
        try(org.sql2o.Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO bill (date, description, quantity, price, paid, vendor_id, purchase_id, payment_id) VALUES (now(),:description,:quantity, :price, :paid, :vendor_id, :purchase_id, :payment_id)";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("description", this.description)
                    .addParameter("quantity", this.quantity)
                    .addParameter("price", this.price)
                    .addParameter("paid", this.paid)
                    .addParameter("vendor_id", this.vendor_id)
                    .addParameter("purchase_id", this.purchase_id)
                    .addParameter("payment_id", this.payment_id)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Bill> all(){
        try(org.sql2o.Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM bill";
            return connection.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(Bill.class);
        }
    }


    public static int unpaid() {
        try (org.sql2o.Connection connection = DB.sql2o.open()) {
            String sql = "SELECT id FROM bill where paid = false";
            List<Integer> ids = connection.createQuery(sql).executeAndFetch(Integer.class);

            int sum = 0;
            for (int id : ids)
                sum += billamount(id);
            return sum;
        }
    }

    public static Bill find(int id){
        try(org.sql2o.Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM bill WHERE id=:id";
            Bill bill = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Bill.class);
            return bill;
        }
    }
    public void update(String description, int quantity, int price, boolean paid, int vendor_id, int purchase_id, int payment_id){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE bill  SET description = :description , quantity = :quantity, price=:price, paid=:paid, vendor_id=:vendor_id, purchase_id=:purchase_id, payment_id=:payment_id WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("description", description)
                    .addParameter("quantity", quantity)
                    .addParameter("price", price)
                    .addParameter("paid", paid)
                    .addParameter("vendor_id", vendor_id)
                    .addParameter("purchase_id", purchase_id)
                    .addParameter("payment_id", payment_id)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}