public class Volvo240 extends Car {

    private static final double trimFactor = 1.25;

    public Volvo240() {
        super(4, 100, "black");
    }

    public String getName() {
        return "Volvo240";
    }

    protected double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }
}
