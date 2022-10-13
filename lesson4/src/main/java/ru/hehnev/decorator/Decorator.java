package ru.hehnev.decorator;

public abstract class Decorator implements Scene {
    private final Scene scene;

    public Decorator(Scene scene) {
        this.scene = scene;
    }

    @Override
    public void operation() {
        scene.operation();
    }
}
