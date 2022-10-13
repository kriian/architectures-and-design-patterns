package ru.hehnev.decorator;

public class OakDecorator extends Decorator{
    public OakDecorator(Scene scene) {
        super(scene);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("Дуб...");
    }
}
