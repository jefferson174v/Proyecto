public class Electrodomesticos extends Modelo {
    public Electrodomesticos(String name, int usodiario, double volteos, double consumomensual) {
        super(name, usodiario, volteos, consumomensual);
    }
    @Override
    public String toString() {
        return getName() + "---" + getUsodiario() + "---" + getVolteos() + "---" + getConsumomensual();
    }
}