package org.example;

public class DeliveryProhibitedException extends Exception {
    public DeliveryProhibitedException(String message) {
        super(message);
    }
}
