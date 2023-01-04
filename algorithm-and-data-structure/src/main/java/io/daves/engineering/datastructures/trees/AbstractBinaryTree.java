package io.daves.engineering.datastructures.trees;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree <E> implements BinaryTree<E> {

    @Override
    public Position<E> sibling(Position<E> position) throws IllegalArgumentException {
        Position<E> parent = parent(position);

        if (parent == null) {
            return null;
        }

        return (position == left(parent)) ? right(parent) : left(parent);
    }

    @Override
    public int numberOfChildren(Position<E> position) {
        int numberOfChildren = 0;

        if (left(position) != null) {
            numberOfChildren++;
        }

        if (right(position) != null) {
            numberOfChildren++;
        }

        return numberOfChildren;
    }

    @Override
    public Iterable<Position<E>> children(Position<E> position) throws IllegalArgumentException {
        List<Position<E>> snapshot = new ArrayList<>();

        if (left(position) != null) {
            snapshot.add(left(position));
        }

        if (right(position) != null) {
            snapshot.add(right(position));
        }

        return snapshot;
    }
}
