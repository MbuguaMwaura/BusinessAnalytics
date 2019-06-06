import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

import java.util.Arrays;
import java.util.Date;

public class BillTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void bills_instantiatesCorrectly(){
        Bill bill = new Bill("A bill", 7, 19, true, 1, 2, 1);
        assertTrue(bill instanceof Bill);
        assertEquals("A bill", bill.getDescription());
    }


    @Test
    public void save_correctlySaveInstancesInDB(){
        Bill bill = new Bill("A bill", 8, 12, false, 1, 1, 2);
        bill.save();
        Bill bill1 = new Bill("Another bill", 7, 19, true, 1, 2, 1);
        bill1.save();
        Bill[] bills = {bill,bill1};
        assertTrue(Bill.all().containsAll(Arrays.asList(bills)));
    }

    @Test
    public void find_retrievesSavedInstance(){
        Bill bill = new Bill("A bill", 7, 19, true, 1, 2, 1);
        bill.save();
        assertEquals("A bill", Bill.find(bill.getId()).getDescription());
    }
    @Test
    public void update_updatesSavedInstance(){
        Bill bill = new Bill("A bill", 7, 19, true, 1, 2, 1);
        bill.save();
        bill.update("Another bill", 7, 19, true, 1, 2, 1);
        assertEquals("Another bill", Bill.find(bill.getId()).getDescription());
    }
}