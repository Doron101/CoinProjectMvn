import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;

public abstract class Coin implements ICalcualte {
    public abstract double getValue();

    public abstract double calculate(double d);

}
