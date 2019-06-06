import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

public class VendorTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void vendors_instantiatesCorrectly(){
        Vendor vendor = new Vendor("Jack", "Pays",1);
        assertTrue(vendor instanceof Vendor);
        assertEquals("Jack", vendor.getName());
    }

    @Test
    public void save_correctlySaveInstancesInDB(){
        Vendor vendor = new Vendor("Jack", "Pays",1);
        vendor.save();
        Vendor vendor1 = new Vendor("Jacky", "Doesn't Pay",1);
        vendor1.save();
        assertEquals(true, Vendor.all().get(0).equals(vendor));
        assertTrue(Vendor.all().get(1).equals(vendor1));
    }

    @Test
    public void find_retrievesSavedInstance(){
        Vendor vendor = new Vendor("Jack", "Pays",1);
        vendor.save();
        assertEquals("Jack", Vendor.find(vendor.getId()).getName());
    }
    @Test
    public void update_updatesSavedInstance(){
        Vendor vendor = new Vendor("Jack", "Pays", 1);
        vendor.save();
        vendor.update("Jacky", "Doesn't Pay",1);
        assertEquals("Jacky", Vendor.find(vendor.getId()).getName());
    }
}