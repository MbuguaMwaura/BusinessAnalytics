import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ProductSaleTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void productSales_instantiatesCorrectly(){
        ProductSale productSale = new ProductSale("Mbuzi", 12, 22, 1, "Fat");
        assertTrue(productSale instanceof ProductSale);
        assertEquals("Mbuzi", productSale.getName());
    }

    @Test
    public void save_correctlySaveInstancesInDB(){
        ProductSale productSale = new ProductSale("Mbuzi", 12, 22, 1, "Fat");
        productSale.save();
        ProductSale productSale1 = new ProductSale("Ngombe", 1, 221, 1, "Fat");
        productSale1.save();
        assertEquals(true, ProductSale.all().get(0).equals(productSale));
        assertTrue(ProductSale.all().get(1).equals(productSale1));
    }

    @Test
    public void find_retrievesSavedInstance(){
        ProductSale productSale = new ProductSale("Mbuzi", 12, 22, 1, "Fat");
        productSale.save();
        assertEquals("Mbuzi", ProductSale.find(productSale.getId()).getName());
    }
    @Test
    public void update_updatesSavedInstance(){
        ProductSale productSale = new ProductSale("Mbuzi", 12, 22, 1, "Fat");
        productSale.save();
        productSale.update("Ngombe", 1, 221, 1, "Fat");
        assertEquals("Ngombe", ProductSale.find(productSale.getId()).getName());
    }
}