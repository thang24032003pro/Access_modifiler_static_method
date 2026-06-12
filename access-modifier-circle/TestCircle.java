class Circle {
    private double radius = 1.0;
    private String color = "red";

    public Circle() {}

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}

public class TestCircle {
    public static void main(String[] args) {
        Circle c1 = new Circle();
        System.out.println("Circle 1 - Radius: " + c1.getRadius() + " | Area: " + c1.getArea());

        Circle c2 = new Circle(5.0);
        System.out.println("Circle 2 - Radius: " + c2.getRadius() + " | Area: " + c2.getArea());
    }
}
