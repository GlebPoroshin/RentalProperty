package com.gleb.rentservice.enteties;

public enum PropertyStatus {
    AVAILABLE("Available", 0),
    RENTED("Rented", 1),
    AWAITING_PAYMENT("Awaiting Payment", 2);

    private final String status;
    private final int code;

    PropertyStatus(String status, int code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
