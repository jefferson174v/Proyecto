public abstract class Modelo {
    private String name;
    private int usodiario;
    private double volteos;
    private double consumomensual;

    public Modelo(String name, int usodiario, double volteos, double consumomensual) {
        this.name = name;
        this.usodiario = usodiario;
        this.volteos = volteos;
        this.consumomensual = consumomensual;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUsodiario() {
        return usodiario;
    }

    public void setUsodiario(int usodiario) {
        this.usodiario = usodiario;
    }

    public double getVolteos() {
        return volteos;
    }

    public void setVolteos(double volteos) {
        this.volteos = volteos;
    }

    public double getConsumomensual() {
        return consumomensual;
    }

    public void setConsumomensual(double consumomensual) {
        this.consumomensual = consumomensual;
    }

    public void actualizarConsumo() {
        this.consumomensual = (volteos / 1000.0) * usodiario * 30;
    }

    @Override
    public String toString() {
        return name + "---" + usodiario + "---" + volteos + "---" + consumomensual;
    }
}