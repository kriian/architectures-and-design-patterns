package ru.hehnev.composite;

import java.util.ArrayList;
import java.util.List;

public class BagComposite implements BagComponent {

    private final List<BagComponent> childBags = new ArrayList<>();

    public void add(BagComponent bagComponent) {
        childBags.add(bagComponent);
    }

    public void remove(BagComponent bagComponent) {
        childBags.remove(bagComponent);
    }

    @Override
    public void show() {
        childBags.forEach(BagComponent::show);
    }
}
