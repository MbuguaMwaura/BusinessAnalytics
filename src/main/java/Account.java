import org.sql2o.*;

import java.util.List;
import java.util.Objects;

public class Account{

    private int id;
    private String name;

    public Account(String name){
        this.name = name;
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
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(name, account.name);
    }

    public void save(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "INSERT INTO account (name) VALUES (:name)";
          this.id = (int) connection.createQuery(sql, true)
                  .addParameter("name", this.name)
                  .executeUpdate()
                  .getKey();
        }
    }

    public static List<Account> all(){
        try(Connection connection = DB.sql2o.open()){
            String sql = "SELECT * FROM account";
            return connection.createQuery(sql).executeAndFetch(Account.class);
        }
    }

    public static Account find(int id){
        try(Connection connection = DB.sql2o.open()) {
            String sql = "SELECT * FROM account WHERE id=:id";
            Account account = connection.createQuery(sql)
                    .addParameter("id",id)
                    .executeAndFetchFirst(Account.class);
            return account;
        }
    }
    public void update(String name){
        try(Connection connection = DB.sql2o.open()){
            String sql = "UPDATE account  SET name = :name WHERE id = :id";
            connection.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }

}