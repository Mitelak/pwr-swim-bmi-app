package mitelski_238006.bmi;


public class AnalyzeBMI {
    static final double SEVERELY_UNDERWEIGHT = 16;
    static final double UNDERWEIGHT = 18.5;
    static final double NORMAL = 25;
    static final double OVERWEIGHT = 30;

    public enum BMICategory {
       BAD, TILT, GOOD
    }

    double bmi;

    AnalyzeBMI (double bmi) {
        this.bmi = bmi;
    }

    public BMICategory getCategory () {
        if (bmi < SEVERELY_UNDERWEIGHT)
            return BMICategory.BAD;

        if (bmi < UNDERWEIGHT)
            return BMICategory.TILT;

        if (bmi < NORMAL)
            return BMICategory.GOOD;

        if (bmi < OVERWEIGHT)
            return BMICategory.TILT;

        return BMICategory.BAD;
    }

}
