import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CustomerTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void customers_instantiatesCorrectly(){
        Customer customer = new Customer("Jack", "Pays",1);
        assertTrue(customer instanceof Customer);
        assertEquals("Jack", customer.getName());
    }

    @Test
    public void save_correctlySaveInstancesInDB(){
        Customer customer = new Customer("Jack", "Pays",1);
        customer.save();
        Customer customer1 = new Customer("Jacky", "Doesn't Pay",1);
        customer1.save();
        assertEquals(true, Customer.all().get(0).equals(customer));
        assertTrue(Customer.all().get(1).equals(customer1));
    }

    @Test
    public void find_retrievesSavedInstance(){
        Customer customer = new Customer("Jack", "Pays",1);
        customer.save();
        assertEquals("Jack", Customer.find(customer.getId()).getName());
    }
    @Test
    public void update_updatesSavedInstance(){
        Customer customer = new Customer("Jack", "Pays", 1);
        customer.save();
        customer.update("Jacky", "Doesn't Pay",1);
        assertEquals("Jacky", Customer.find(customer.getId()).getName());
    }
}