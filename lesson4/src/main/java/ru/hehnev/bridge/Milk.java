package ru.hehnev.bridge;

public abstract class Milk {
    protected final Manufacturer manufacturer;

    public Milk(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public abstract void showDetails();
}
