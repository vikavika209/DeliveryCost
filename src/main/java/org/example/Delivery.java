package org.example;

public class Delivery {

    public double TotalDeliveryCost(double distance,
                                  double width,
                                  double length,
                                  double height,
                                  Fragility fragility,
                                  Workload workload) throws DeliveryProhibitedException {

        if (distance <= 0 || width <= 0 || length <= 0 || height <= 0){
            throw new IllegalArgumentException("Каждый из введенных параметров должен быть больше 0");
        }

        double sizes = width * length * height;
        double distanceCost;
        double sizeCost;
        double fragilityCost;
        double workloadCost;

        if (distance > 30) {
            distanceCost = 300;
        } else if (distance > 10) {
            distanceCost = 200;
        } else if (distance > 2) {
            distanceCost = 100;
        } else {
            distanceCost = 50;
        }

        if (sizes > 1000) {
            sizeCost = 200;
        } else {
            sizeCost = 100;
        }

        if (fragility == Fragility.YES) {
            fragilityCost = 300;
        } else {
            fragilityCost = 0;
        }

        if (workload == Workload.VERY_HIGH) {
            workloadCost = 1.6;
        }
        if (workload == Workload.HIGH) {
            workloadCost = 1.4;
        }
        if (workload == Workload.INCREASED) {
            workloadCost = 1.2;
        } else {
            workloadCost = 1;
        }

        if (distance > 30 && fragility == Fragility.YES) {
            throw new DeliveryProhibitedException("Доставка хрупкого груза на расстояние более 30 км невозможна");
        }

        double totalCost = (distanceCost + sizeCost + fragilityCost) * workloadCost;

        if (totalCost >= 400) {
            return totalCost;
        } else {
            return 400;
        }
    }
}
