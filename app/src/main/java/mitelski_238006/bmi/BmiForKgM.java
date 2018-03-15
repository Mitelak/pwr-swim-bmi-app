package mitelski_238006.bmi;

public class BmiForKgM extends BMI {

    BmiForKgM(double mass, double height) {
        super(mass, height);
    }

    @Override
    public double calculateBmi() {
        if (dataAreValid()) {
            return getMass() / (getHeight()*getHeight());
        } else {
            throw new IllegalArgumentException("Invalid data");
        }
    }

    @Override
    protected boolean dataAreValid() {
        return getHeight() > 0 && getMass() > 0;
    }
}
