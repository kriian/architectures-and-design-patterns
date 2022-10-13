package ru.hehnev.facade;

public class Car {
    private final CarEngine carEngine;
    private final CarPowerSupply carPowerSupply;
    private final FuelPump fuelPump;

    public Car() {
        carEngine = new CarEngine();
        carPowerSupply = new CarPowerSupply();
        fuelPump = new FuelPump();
    }

    public void turnIgnitionKey() {
        carPowerSupply.power();
        fuelPump.giveFuel();
        carEngine.workEngine();
        System.out.println("Автомобиль завелся!!!");
    }
}
