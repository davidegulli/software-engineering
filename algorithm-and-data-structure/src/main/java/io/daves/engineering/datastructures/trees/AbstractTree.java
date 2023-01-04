package io.daves.engineering.datastructures.trees;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractTree<E> implements Tree<E>{

    @Override
    public boolean isExternal(Position<E> position) {
        return numberOfChildren(position) == 0;
    }

    @Override
    public boolean isInternal(Position<E> position) {
        return numberOfChildren(position) > 0;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * The depth of a position is the level of the tree where the position is placed.
     *
     * The complexity of the method is O(d_p + 1), where d_p is the depth of the parent position.
     * The worst case the complexity is O(n), if all the internal nodes have only one child.
     *
     * @return An integer representing the position's depth.
     */
    @Override
    public int depth(Position<E> position) throws IllegalArgumentException {
        if(isRoot(position)) {
            return 0;
        } else {
            return depth(parent(position)) + 1;
        }
    }

    /**
     * The height of s tree is the max depth that an external position of the tree can have.
     *
     * The complexity of the method for the worst cae is O(n)
     *
     * @return An integer representing the height of the subtree passed as parameter
     */
    @Override
    public int height(Position<E> root) {
        int height = 0;
        for (Position<E> position : children(root)) {
            height = Math.max(height, (1 + height(position)));
        }

        return height;
    }

    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    private class ElementIterator implements Iterator<E> {

        Iterator<Position<E>> positionIterator = positions().iterator();

        @Override
        public boolean hasNext() {
            return positionIterator.hasNext();
        }

        @Override
        public E next() {
            return positionIterator.next().getElement();
        }

        @Override
        public void remove() {
            positionIterator.remove();
        }
    }

    @Override
    public Iterable<Position<E>> positions() {
        return preOrder();
    }

    @Override
    public Iterable<E> elements() {
        ArrayList<E> elements = new ArrayList<>();
        for (Position<E> position : positions()) {
            elements.add(position.getElement());
        }
        return elements;
    }

    /**
     * The complexity of the method is O(n)
     *
     * @return Return a preorder iterable list, for a tree T, it will visit the root as first position
     * and then all the children subtrees from left to right, for all the subtrees within T.
     */
    public Iterable<Position<E>> preOrder() {
        List<Position<E>> snapshot = new ArrayList<>();
        preorderSubtree(snapshot, root());
        return snapshot;
    }

    private void preorderSubtree(List<Position<E>> snapshot, Position<E> position) {
        if(position == null) {
            return;
        }

        snapshot.add(position);
        for(Position<E> child: children(position)) {
            preorderSubtree(snapshot, child);
        }
    }

    /**
     * The complexity of the method is O(n)
     *
     * @return Return a postOrder iterable list, for a tree T, for each subtree within T, it will visit all the
     * children subtrees from left to right belonging to the root and as last position it will visit the root.
     */
    public Iterable<Position<E>> postOrder() {
        List<Position<E>> snapshot = new ArrayList<>();
        postOrderSubtree(snapshot, root());
        return snapshot;
    }

    private void postOrderSubtree(List<Position<E>> snapshot, Position<E> position) {
        if(position == null) {
            return;
        }

        for(Position<E> child: children(position)) {
            postOrderSubtree(snapshot, child);
        }
        snapshot.add(position);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        printInOrder(root(), 0, result);
        return result.toString();
    }

    private void printInOrder(Position<E> position, Integer depth, StringBuilder printStream) {
        printStream.append(spaces(depth)).append(position.getElement()).append("\n");
        for (Position<E> child : children(position)) {
            printInOrder(child, (depth + 1), printStream);
        }
    }

    private String spaces(Integer factor) {
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < factor; i++) {
            spaces.append("  ");
        }
        return spaces.toString();
    }
}
