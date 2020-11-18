import java.io.Serializable;
public class ILS extends Coin  implements Serializable {
    static final double value = 1 / USD.value; // instead of 0.28
    @Override
    public double getValue() {
        return value;
    };
     @Override
     public  double calculate(double d){
        return d / getValue();
    };
}
