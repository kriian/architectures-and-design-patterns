package ru.hehnev.decorator;

public class GoldChain extends Decorator{
    public GoldChain(Scene scene) {
        super(scene);
    }

    @Override
    public void operation() {
        super.operation();
        System.out.println("Золотая цеп на дубе...");
    }
}
