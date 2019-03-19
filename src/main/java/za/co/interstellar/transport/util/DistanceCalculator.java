/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.interstellar.transport.util;

import java.awt.geom.Point2D;

/**
 *
 * @author F4829689
 */
public class DistanceCalculator {

    public double calculateDistanceBetweenPointsWithPoint2D(
            double x1,
            double y1,
            double x2,
            double y2) {

        return Point2D.distance(x1, y1, x2, y2);
    }

    public double calculateDistanceBetweenPointsWithHypot(
            double x1,
            double y1,
            double x2,
            double y2) {

        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);

        return Math.hypot(ac, cb);
    }

    public double calculateDistanceBetweenPoints(
            double x1,
            double y1,
            double x2,
            double y2) {
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }
}
