package ControlWork;

// Класс третьего задания
public class Triangle {
    private double ASide;
    private double BSide;
    private double CSide;

    public Triangle(double ASide, double BSide, double CSide) {
        this.ASide = ASide;
        this.BSide = BSide;
        this.CSide = CSide;
    }

    public double getASide() {
        return ASide;
    }

    public void setASide(double ASide) {
        this.ASide = ASide;
    }

    public double getBSide() {
        return BSide;
    }

    public void setBSide(double BSide) {
        this.BSide = BSide;
    }

    public double getCSide() {
        return CSide;
    }

    public void setCSide(double CSide) {
        this.CSide = CSide;
    }

    private boolean isTriangleCorrect() {
        if (((ASide + BSide) > CSide)
                && ((ASide + CSide) > BSide)
                && ((BSide + CSide) > ASide)) {
            System.out.println("Такой треугольник существует!");
            return true;
        } else {
            System.out.println("Такого труегольника не бывает!");
            return false;
        }
    }

    public void getPerimeter() {
        if (isTriangleCorrect()) {
            double P = ASide + BSide + CSide;
            System.out.println("Периметр треугольника = " + P);
            System.out.println();
        }
    }

    public void getArea() {
        if (isTriangleCorrect()) {
            double PP = (ASide + BSide + CSide) / 2;
            double S = Math.sqrt(PP * (PP - ASide) * (PP - BSide) * (PP - CSide));
            System.out.println("Площадь треугольника = " + S);
            System.out.println();
        }
    }
}
