package ru.hehnev.composite;

public class Client {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Copybook copybook = new Copybook();

        BagComposite bigBag = new BagComposite();
        BagComposite smallBag = new BagComposite();

        smallBag.add(pen);
        smallBag.add(copybook);

        bigBag.add(smallBag);

        bigBag.show();
    }
}
