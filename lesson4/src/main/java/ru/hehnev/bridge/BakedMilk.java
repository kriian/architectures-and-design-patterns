package ru.hehnev.bridge;

public class BakedMilk extends Milk{

    public BakedMilk(Manufacturer manufacturer) {
        super(manufacturer);
    }

    @Override
    public void showDetails() {
        System.out.println("Baked milk...");
        manufacturer.setManufacturer();
    }
}
