package ru.hehnev.decorator;

public class Main {
    public static void main(String[] args) {
        Scene scene = new GoldChain(new OakDecorator(new SceneImpl()));
        scene.operation();
    }
}
