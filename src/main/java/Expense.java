import org.sql2o.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Expense{
    private int id;
    private String name;
    private String description;
    private int amount;
    private Timestamp date;
    private int account_id;

    public Expense(String name, String description, int amount,int account_id) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.account_id = account_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getAccount_id() {
        return account_id;
    }

    public String getFormattedDate(){
        return DateFormat.getDateTimeInstance().format(date);
    }

    public Account getAccount(int id){
        return Account.find(id);
    }

    public static int total() {
        try (Connection connection = DB.sql2o.open()) {
            String sql = "SELECT amount FROM expense";
            List<Integer> expenses = connection.createQuery(sql).executeAndFetch(Integer.class);
            int sum = 0;
            for (int d : expenses)
                sum += d;
            return sum;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return id == expense.id &&
                amount == expense.amount &&
                account_id == expense.account_id &&
                Objects.equals(name, expense.name) &&
                Objects.equals(description, expense.description);
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO expense (name, description, amount, date, account_id) VALUES (:name , :description, :amount, now(), :account_id)";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .addParameter("amount", this.amount)
                    .addParameter("account_id", this.account_id)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Expense> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM expense";
            return connection.createQuery(sql).executeAndFetch(Expense.class);
        }
    }

    public static Expense find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM expense WHERE id=:id";
            Expense expense = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Expense.class);
            return expense;
        }
    }
    public void update(String name, String description, int amount,int account_id){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE expense  SET name = :name, description=:description, amount=:amount, account_id=:account_id WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("description", description)
                    .addParameter("amount", amount)
                    .addParameter("account_id", account_id)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}

