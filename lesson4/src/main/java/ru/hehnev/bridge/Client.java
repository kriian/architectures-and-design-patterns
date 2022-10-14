package ru.hehnev.bridge;

public class Client {
    public static void main(String[] args) {
        Milk milk = new BakedMilk(new Piskarevskoye());
        milk.showDetails();
    }
}
