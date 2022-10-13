package ru.hehnev.composite;

public class Pen implements BagComponent{
    @Override
    public void show() {
        System.out.println("Pen...");
    }
}
