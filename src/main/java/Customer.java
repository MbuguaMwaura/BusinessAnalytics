import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Customer{

    private int id;
    private String name;
    private String email;
    private int number;

    public Customer(String name, String email, int number){
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
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(name, customer.name) &&
                Objects.equals(email, customer.email) &&
                number == customer.number;
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO customer (name, email, number) VALUES (:name, :email, :number)";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("email", this.email)
                    .addParameter("number", this.number)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Customer> all(){
        try(org.sql2o.Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM customer";
            return connection.createQuery(sql).executeAndFetch(Customer.class);
        }
    }

    public static Customer find(int id){
        try(org.sql2o.Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM customer WHERE id=:id";
            Customer customer = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Customer.class);
            return customer;
        }
    }
    public void update(String name, String email, int number){
        try(org.sql2o.Connection connection = DB.sql2o.open()){
            String sql = "UPDATE customer  SET name = :name , email = :email , number = :number WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("email", email)
                    .addParameter("number", number)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}