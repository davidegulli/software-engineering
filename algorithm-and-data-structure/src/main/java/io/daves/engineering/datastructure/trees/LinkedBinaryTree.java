package io.daves.engineering.datastructure.trees;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    public Node<E> createNode(E element, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<>(element, parent, left, right);
    }

    private Node<E> root;
    private int size;

    protected Node<E> validate(Position<E> position) throws IllegalArgumentException {
        if(!(position instanceof Node)) {
            throw new IllegalArgumentException();
        }

        Node<E> node = (Node<E>) position;
        if(node == node.getParent()) {
            throw new IllegalArgumentException();
        }

        return node;
    }

    public int size() {
        return size;
    }

    public Node<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return node.getParent();
    }

    @Override
    public Position<E> left(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> position) throws IllegalArgumentException {
        Node<E> node = validate(position);
        return node.getRight();
    }

    public Position<E> addRoot(E element) {
        if(!isEmpty()) {
            throw new IllegalArgumentException();
        }

        size = 1;
        root = createNode(element, null, null, null);
        return root;
    }

    public Position<E> addLeft(Position<E> parent, E element) throws IllegalArgumentException {
        Node<E> parentNode = validate(parent);

        if(left(parent) != null) {
            throw new IllegalArgumentException();
        }

        Node<E> leftChild = createNode(element, parentNode, null, null);
        parentNode.setLeft(leftChild);
        size++;

        return leftChild;
    }

    public Position<E> addRight(Position<E> parent, E element) throws IllegalArgumentException {
        Node<E> parentNode = validate(parent);

        if(right(parent) != null) {
            throw new IllegalArgumentException();
        }

        Node<E> rightChild = createNode(element, parentNode, null, null);
        parentNode.setRight(rightChild);
        size++;

        return rightChild;
    }

    public E set(Position<E> position, E element) throws IllegalArgumentException {
        Node<E> node = validate(position);
        E oldElement = node.getElement();
        node.setElement(element);

        return oldElement;
    }

    @Override
    public boolean isRoot(Position<E> position) {
        return root == position;
    }

    protected static class Node<E> implements Position<E> {

        private E element;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        protected Node(E element, Node<E> parent, Node<E> left, Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

}
