package mitelski_238006.bmi;

public abstract class BMI {
    double mass;
    double height;

    BMI(double mass, double height) {
        this.mass = mass;
        this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    abstract public double calculateBmi();
    abstract protected boolean dataAreValid();
}
