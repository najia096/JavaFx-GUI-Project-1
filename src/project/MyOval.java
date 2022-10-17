/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class MyOval extends MyShape {

    private final MyPoint center;
    private double width;
    private double height;

    MyOval(double x, double y, double width, double height, Color color) {
// (x,y) is top left corner
        super(x, y, color);
        this.width = width;
        this.height = height;
        this.center = new MyPoint(x + width / 2, y + height / 2);
    }

    public double getArea() {
        return Math.PI * width * height * 0.5;
    }

    public double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((width + height) / 2);
    }

    public MyPoint getCenter() {
        return center;
    }

    public double getRadius(double angle) {
        double semiW = width / 2;
        double semiH = height / 2;
        double temp = Math.pow(semiW, 2) * Math.pow(Math.sin(Math.toRadians(angle)), 2);
        temp += Math.pow(semiH, 2) * Math.pow(Math.cos(Math.toRadians(angle)), 2);
        return (semiH * semiW) / (Math.sqrt(temp));
    }

    public void setAxes(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void setCenter(double x, double y) {
        this.center.setX(x);
        this.center.setY(y);
    }

    @Override
    public String toString() {
        return String.format("----- Oval Properties -----\n%15s (%.2f,%.2f)\n%15s %.2f\n%15s %.2f\n%15s %.2f\n%15s %.2f\n%15s " + super.getColor(),
                "Center:", center.getX(), center.getY(), "Area:", getArea(), "Perimeter:", getPerimeter(), "Width:", width, "Height:", height, "Color:");
    }

    public MyRectangle getMyBoundingRectangle() {
        return new MyRectangle(super.getX(), super.getY(), width, height, super.getColor());
    }

    public ArrayList<MyPoint> getMyArea() {
        ArrayList<MyPoint> set = new ArrayList<>();
        double horizonLen = width / 2;
        double verticalLen = height / 2;
        double dx, dy;
        for (double x = getX(); x <= getX() + width; ++x) {
            for (double y = getY(); y <= getY() + height; ++y) {
                dx = Math.abs(x - center.getX());
                dy = Math.abs(y - center.getY());
                if (Math.pow((dx) / horizonLen, 2)
                        + Math.pow((dy) / verticalLen, 2) <= 1) {
// Accounts for 4 quadrants of oval
                    set.add(new MyPoint(x, y));
                    set.add(new MyPoint(x - 2 * dx, y - 2 * dy));
                    set.add(new MyPoint(x - 2 * dx, y));
                    set.add(new MyPoint(x, y - 2 * dy));
                }
            }
        }
        return set;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(super.getColor());
        gc.setStroke(super.getColor());
        gc.strokeOval(super.getX(), super.getY(), width, height);
        gc.fillOval(super.getX(), super.getY(), width, height);
    }
}
