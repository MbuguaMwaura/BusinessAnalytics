import org.sql2o.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Income{
    private int id;
    private String name;
    private String description;
    private int amount;
    private Timestamp date;
    private int account_id;

    public Income(String name, String description, int amount,int account_id) {
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
            String sql = "SELECT amount FROM income";
            List<Integer> incomes = connection.createQuery(sql).executeAndFetch(Integer.class);
            int sum = 0;
            for (int d : incomes)
                sum += d;
            return sum;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return id == income.id &&
                amount == income.amount &&
                account_id == income.account_id &&
                Objects.equals(name, income.name) &&
                Objects.equals(description, income.description);
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO income (name, description, amount, date, account_id) VALUES (:name , :description, :amount, now(), :account_id)";
            this.id = (int) connection.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("description", this.description)
                    .addParameter("amount", this.amount)
                    .addParameter("account_id", this.account_id)
                    .executeUpdate()
                    .getKey();
        }
    }

    public static List<Income> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM income";
            return connection.createQuery(sql).executeAndFetch(Income.class);
        }
    }

    public static Income find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM income WHERE id=:id";
            Income income = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Income.class);
            return income;
        }
    }
    public void update(String name, String description, int amount,int account_id){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE income  SET name = :name, description=:description, amount=:amount, account_id=:account_id WHERE id = :id";
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

