package io.daves.engineering.datastructure.trees;

import java.util.ArrayList;

public abstract class AbstractTree<E> implements Tree<E>{

    Position<E> root;

    public AbstractTree(Position<E> root) {
        this.root = root;
    }

    public Position<E> getRoot() {
        return root;
    }

    @Override
    public boolean isExternal(Position<E> position) {
        return numberOfChildren(position) == 0;
    }

    @Override
    public boolean isInternal(Position<E> position) {
        return numberOfChildren(position) > 0;
    }

    @Override
    public int size() {
        return 0;
        //return positions();
    }

    @Override
    public Iterable<Position<E>> positions() {
        ArrayList<Position<E>> allPositions = new ArrayList<>();

        preorder(root, allPositions);
        return allPositions;
    }

    /**
     * It will have to be improved at the moment its complexity is O(n^2)
     * @return Iterable<E>
     */
    @Override
    public Iterable<E> elements() {
        ArrayList<E> result = new ArrayList<>();
        for (Position<E> position : positions()) {
            result.add(position.getElement());
        }

        return result;
    }

    private void preorder(Position<E> position, ArrayList<Position<E>> positions) {
        if(position == null) {
            return;
        }

        for(Position<E> child : children(position)) {
            preorder(child, positions);
        }

        positions.add(position);
    }

}
