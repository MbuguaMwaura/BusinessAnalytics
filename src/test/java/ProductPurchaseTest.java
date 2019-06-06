import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ProductPurchaseTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void productPurchases_instantiatesCorrectly(){
        ProductPurchase productPurchase = new ProductPurchase("Mbuzi", 12, 22, 1, "Fat");
        assertTrue(productPurchase instanceof ProductPurchase);
        assertEquals("Mbuzi", productPurchase.getName());
    }

    @Test
    public void save_correctlySaveInstancesInDB(){
        ProductPurchase productPurchase = new ProductPurchase("Mbuzi", 12, 22, 1, "Fat");
        productPurchase.save();
        ProductPurchase productPurchase1 = new ProductPurchase("Ngombe", 1, 221, 1, "Fat");
        productPurchase1.save();
        assertEquals(true, ProductPurchase.all().get(0).equals(productPurchase));
        assertTrue(ProductPurchase.all().get(1).equals(productPurchase1));
    }

    @Test
    public void find_retrievesSavedInstance(){
        ProductPurchase productPurchase = new ProductPurchase("Mbuzi", 12, 22, 1, "Fat");
        productPurchase.save();
        assertEquals("Mbuzi", ProductPurchase.find(productPurchase.getId()).getName());
    }
    @Test
    public void update_updatesSavedInstance(){
        ProductPurchase productPurchase = new ProductPurchase("Mbuzi", 12, 22, 1, "Fat");
        productPurchase.save();
        productPurchase.update("Ngombe", 1, 221, 1, "Fat");
        assertEquals("Ngombe", ProductPurchase.find(productPurchase.getId()).getName());
    }
}