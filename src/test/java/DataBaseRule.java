import org.sql2o.*;
import org.junit.rules.ExternalResource;

public class DataBaseRule extends ExternalResource{

    @Override
    protected void before(){
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/accounts_test", "mbugua", "4545");
    }

    @Override
    protected void after(){
        try(Connection con = DB.sql2o.open()){
            String sqlaccount = "DELETE FROM account *; ";
            String sqlbill = "DELETE FROM bill *;";
            String sqlcustomer = "DELETE FROM customer *;";
            String sqlexpense = "DELETE FROM expense *;";
            String sqlincome = "DELETE FROM income *;";
            String sqlpayment = "DELETE FROM payment *;";
            String sqlpropurchase = "DELETE FROM product_purchase *;";
            String sqlprosale = "DELETE FROM product_sale *;";
            String sqlreceipt = "DELETE FROM receipt *;";
            String sqlvendor = "DELETE FROM vendor *;";
            con.createQuery(sqlpayment).executeUpdate();
            con.createQuery(sqlpropurchase).executeUpdate();
            con.createQuery(sqlprosale).executeUpdate();
            con.createQuery(sqlreceipt).executeUpdate();
            con.createQuery(sqlvendor).executeUpdate();
            con.createQuery(sqlbill).executeUpdate();
            con.createQuery(sqlexpense).executeUpdate();
            con.createQuery(sqlcustomer).executeUpdate();
            con.createQuery(sqlincome).executeUpdate();
            con.createQuery(sqlaccount).executeUpdate();
        }
    }
}