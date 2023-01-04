package io.daves.engineering.datastructures.trees;

import java.util.Iterator;

public interface Tree<E> extends Iterable<E> {

    Position<E> root();

    int depth(Position<E> position);

    int height(Position<E> position);

    Position<E> parent(Position<E> position) throws IllegalArgumentException;

    Iterable<Position<E>> children(Position<E> position) throws IllegalArgumentException;

    int numberOfChildren(Position<E> position);

    boolean isInternal(Position<E> position);

    boolean isExternal(Position<E> position);

    boolean isRoot(Position<E> position);

    int size();

    boolean isEmpty();

    @Override
    Iterator<E> iterator();

    Iterable<Position<E>> positions();

    Iterable<E> elements();
 }
