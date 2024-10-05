package com.nrv.room_service.model.enums;

/**
 * Enum representing types of room availablility. It also contains PricePerNight of
 * that particular room.
 *
 * @author Nirav Parekh
 */
public enum Type {
    SINGLE(100.0),
    DOUBLE(150.0),
    SUITE(200.0),
    FAMILY(250.0),
    DELUXE(300.0);

    private final double pricePerNight;

    Type(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}
