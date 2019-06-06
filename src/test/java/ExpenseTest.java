import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sql2o.*;

public class ExpenseTest{

    @Rule
    public DataBaseRule dataBaseRule = new DataBaseRule();

    @Test
    public void expenses_instantiatesCorrectly(){
        Expense expense = new Expense("Expense", "expensive", 1021, 1);
        assertTrue(expense instanceof Expense);
        assertEquals("Expense", expense.getName());
    }

    @Test
    public void save_correctlySaveInstancesInDB(){
        Expense expense = new Expense("Expense", "expensive", 1021, 1);
        expense.save();
        Expense expense1 = new Expense("Expensetwo", "expensive", 1011, 2);
        expense1.save();
        assertEquals(true, Expense.all().get(0).equals(expense));
        assertTrue(Expense.all().get(1).equals(expense1));
    }

    @Test
    public void find_retrievesSavedInstance(){
        Expense expense = new Expense("Expense", "expensive", 1021, 1);
        expense.save();
        assertEquals("Expense", Expense.find(expense.getId()).getName());
    }
    @Test
    public void update_updatesSavedInstance(){
        Expense expense = new Expense("Expense", "expensive", 1021, 1);
        expense.save();
        expense.update("Expensetwo", "expensive", 1011, 2);
        assertEquals("Expensetwo", Expense.find(expense.getId()).getName());
    }
}