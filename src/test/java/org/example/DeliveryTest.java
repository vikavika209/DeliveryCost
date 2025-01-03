package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryTest {
    private Delivery delivery;

    @BeforeEach
    void setUp() {
        delivery = new Delivery();
    }

    @Tag("all-tests")
    @ParameterizedTest
    @CsvSource({
            "5.0, 10.0, 10.0, 10.0, YES, INCREASED",
    })
    void totalDeliveryCost(double distance,
                           double width,
                           double length,
                           double height,
                           Fragility fragility,
                           Workload workload) throws DeliveryProhibitedException {
        double res = delivery.TotalDeliveryCost(distance, width, length, height, fragility, workload);
        assertEquals(600, res);
    }

    @Tag("all-tests")
    @ParameterizedTest
    @CsvSource({
            "-5.0, 10.0, 10.0, 10.0, YES, INCREASED",
            "5.0, -10.0, 10.0, 10.0, YES, INCREASED",
            "-5.0, 10.0, 0.0, 10.0, YES, INCREASED",
    })
    void totalDeliveryCostException(double distance,
                           double width,
                           double length,
                           double height,
                           Fragility fragility,
                           Workload workload) throws DeliveryProhibitedException {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> delivery.TotalDeliveryCost(distance, width, length, height, fragility, workload)),
                () -> assertThrows(RuntimeException.class, () -> delivery.TotalDeliveryCost(distance, width, length, height, fragility, workload))
        );    }

    @Tag("all-tests")
    @ParameterizedTest
    @CsvSource({
            "50.0, 10.0, 10.0, 10.0, YES, INCREASED",
    })
    void DeliveryProhibitedExceptionTest(double distance,
                                    double width,
                                    double length,
                                    double height,
                                    Fragility fragility,
                                    Workload workload) throws DeliveryProhibitedException {
        assertAll(
                () -> assertThrows(DeliveryProhibitedException.class, () -> delivery.TotalDeliveryCost(distance, width, length, height, fragility, workload)),
                () -> assertThrows(Exception.class, () -> delivery.TotalDeliveryCost(distance, width, length, height, fragility, workload))
        );
    }
    @Tag("all-tests")
    @ParameterizedTest
    @CsvSource({
            "0.1, 0.1, 0.1, 0.1, NO, NORMAL",
    })
    void totalDeliveryCostMinTest(double distance,
                           double width,
                           double length,
                           double height,
                           Fragility fragility,
                           Workload workload) throws DeliveryProhibitedException {
        double res = delivery.TotalDeliveryCost(distance, width, length, height, fragility, workload);
        assertEquals(400, res);
    }
}