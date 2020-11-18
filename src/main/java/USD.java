import java.io.Serializable;

public class USD extends Coin implements Serializable {
    static final double value = 3.52;
    @Override
    public double getValue() {
        return value;
    };
    public double calculate(double d){
        return d / getValue();
    };

}
