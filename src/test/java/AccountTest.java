import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AccountTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void accounts_instantiatesCorrectly(){
        Account account = new Account("Cash in Hand");
        assertTrue(account instanceof Account);
        assertEquals("Cash in Hand", account.getName());
    }

    @Test
    public void save_correctlySaveInstancesInDB(){
        Account account = new Account("Bank");
        account.save();
        Account account1 = new Account("Cash");
        account1.save();
        assertEquals(true, Account.all().get(0).equals(account));
        assertTrue(Account.all().get(1).equals(account1));
    }

    @Test
    public void find_retrievesSavedInstance(){
        Account account = new Account("Bank");
        account.save();
        assertEquals("Bank", Account.find(account.getId()).getName());
    }
    @Test
    public void update_updatesSavedInstance(){
        Account account = new Account("Bank");
        account.save();
        account.update("Cash");
        assertEquals("Cash", Account.find(account.getId()).getName());
    }
}