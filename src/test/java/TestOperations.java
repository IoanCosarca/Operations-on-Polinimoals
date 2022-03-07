import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestOperations {
    @DisplayName("Addition")
    @Test
    public void testAdd()
    {
        CalcPolinomModel model = new CalcPolinomModel();
        model.Add("3*X^2-X+1","X-2",1);
        String r = model.toString(model.RPolin);
        assertEquals("3.00*X^2-1.00", r);
    }

    @DisplayName("Subtract")
    @Test
    public void testSubtract()
    {
        CalcPolinomModel model = new CalcPolinomModel();
        model.Add("3*X^2-X+1","X-2",-1);
        String r = model.toString(model.RPolin);
        assertEquals("3.00*X^2-2.00*X+3.00", r);
    }

    @DisplayName("Multiply")
    @Test
    public void testMultiply()
    {
        CalcPolinomModel model = new CalcPolinomModel();
        model.Multiply("3*X^2-X+1","X-2");
        String r = model.toString(model.RPolin);
        assertEquals("3.00*X^3-7.00*X^2+3.00*X-2.00", r);
    }

    @DisplayName("Divide")
    @Test
    public void testDivide()
    {
        CalcPolinomModel model = new CalcPolinomModel();
        model.Divide("3*X^2-X+1","X-2");
        String r = model.toString(model.RPolin);
        assertEquals("3.00*X+5.00", r);
    }

    @DisplayName("Derivative")
    @Test
    public void testDerivative()
    {
        CalcPolinomModel model = new CalcPolinomModel();
        model.Derivative("3*X^2-X+1");
        String r = model.toString(model.RPolin);
        assertEquals("6.00*X-1.00", r);
    }

    @DisplayName("Integration")
    @Test
    public void testIntegration()
    {
        CalcPolinomModel model = new CalcPolinomModel();
        model.Integration("3*X^2-X+1");
        String r = model.toString(model.RPolin);
        assertEquals("X^3-0.50*X^2+X", r);
    }

}