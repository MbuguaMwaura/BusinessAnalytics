import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

public class IncomeTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void incomes_instantiatesCorrectly(){
        Income income = new Income("Income", "expensive", 1021, 1);
        assertTrue(income instanceof Income);
        assertEquals("Income", income.getName());
    }

    @Test
    public void save_correctlySaveInstancesInDB(){
        Income income = new Income("Income", "expensive", 1021, 1);
        income.save();
        Income income1 = new Income("Incometwo", "expensive", 1011, 2);
        income1.save();
        assertEquals(true, Income.all().get(0).equals(income));
        assertTrue(Income.all().get(1).equals(income1));
    }

    @Test
    public void find_retrievesSavedInstance(){
        Income income = new Income("Income", "expensive", 1021, 1);
        income.save();
        assertEquals("Income", Income.find(income.getId()).getName());
    }
    @Test
    public void update_updatesSavedInstance(){
        Income income = new Income("Income", "expensive", 1021, 1);
        income.save();
        income.update("Incometwo", "expensive", 1011, 2);
        assertEquals("Incometwo", Income.find(income.getId()).getName());
    }
}