package com.leanit.subway.common.bean;

/**
 * Created by admin on 2016/11/3.
 */
public class Position {
    double x;
    double y;
    String sense;

    public Position(String[] split) {
        if (null != split && split.length == 3) {
            sense = split[0];
            x = Double.parseDouble(split[1]);
            y = Double.parseDouble(split[2]);
        }
    }

    public Position() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getSense() {
        return sense;
    }

    public void setSense(String sense) {
        this.sense = sense;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", sense='" + sense + '\'' +
                '}';
    }
}
