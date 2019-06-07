import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

import java.util.Arrays;
import java.util.Date;

public class ReceiptTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void receipts_instantiatesCorrectly(){
        Receipt receipt = new Receipt("A receipt", 7, 19, true, 1, 2, 1);
        assertTrue(receipt instanceof Receipt);
        assertEquals("A receipt", receipt.getDescription());
    }


    @Test
    public void save_correctlySaveInstancesInDB(){
        Receipt receipt = new Receipt("A receipt", 8, 12, false, 1, 1, 2);
        receipt.save();
        Receipt receipt1 = new Receipt("Another receipt", 7, 19, true, 1, 2, 1);
        receipt1.save();
        Receipt[] receipts = {receipt,receipt1};
        assertTrue(Receipt.all().containsAll(Arrays.asList(receipts)));
    }

    @Test
    public void find_retrievesSavedInstance(){
        Receipt receipt = new Receipt("A receipt", 7, 19, true, 1, 2, 1);
        receipt.save();
        assertEquals("A receipt", Receipt.find(receipt.getId()).getDescription());
    }
    @Test
    public void update_updatesSavedInstance(){
        Receipt receipt = new Receipt("A receipt", 7, 19, true, 1, 2, 1);
        receipt.save();
        receipt.update("Another receipt", 7, 19, true, 1, 2, 1);
        assertEquals("Another receipt", Receipt.find(receipt.getId()).getDescription());
    }

    @Test
    public void total_calculatesTheTotal(){
        Receipt receipt = new Receipt("A receipt", 8, 12, false, 1, 1, 2);
        receipt.save();
        Receipt receipt1 = new Receipt("Another receipt", 7, 19, true, 1, 2, 1);
        receipt1.save();
        assertEquals(229, Receipt.total());
    }
}