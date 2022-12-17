package io.daves.engineering.algorithm.breadth_first_search;

import io.daves.engineering.datastructure.trees.Position;
import io.daves.engineering.datastructure.trees.Tree;
import io.daves.engineering.util.BinaryTreeUtilities;

import java.util.ArrayDeque;
import java.util.Deque;

public class BreadthFirstSearcher {

    private static Integer[] sampleDataset = { 3, 12, 5, 8, 19, 6, 9, 4, 13, 18, 15, 7, 9, 11, 17, 21, 14, 2, 1, 20 };

    public static void main(String[] args) {
        Tree<Integer> sampleTree = BinaryTreeUtilities.buildSampleTree(sampleDataset);
        System.out.println(sampleTree);

        BreadthFirstSearcher searcher = new BreadthFirstSearcher();
        searcher.breadthFirstSearch(sampleTree, 15); // The correct depth is: 3
        searcher.breadthFirstSearch(sampleTree, 21); // The correct depth is: 4
        searcher.breadthFirstSearch(sampleTree, 3); // The correct depth is: 0
    }

    private void breadthFirstSearch(Tree<Integer> sampleTree, Integer numberToSearch) {
        Position<Integer> root = sampleTree.root();
        Position<Integer> foundPosition = null;

        if(root.getElement() == numberToSearch) {
            foundPosition = root;
        } else {
            foundPosition = breadthFirstSearchingTraversal(root, sampleTree, numberToSearch);
        }

        System.out.println(
                "The element: " + numberToSearch + " has been found at depth: " + sampleTree.depth(foundPosition));
    }

    private Position<Integer> breadthFirstSearchingTraversal(
            Position<Integer> position, Tree<Integer> tree, Integer numberToSearch) {

        Position<Integer> result = null;

        Deque<Position<Integer>> queue = new ArrayDeque<>();
        queue.add(position);

        while (!queue.isEmpty()) {
            result = queue.remove();

            if (result.getElement() == numberToSearch) {
                break;
            }

            for(Position<Integer> child : tree.children(result)) {
                queue.add(child);
            }
        }

        return result;
    }
}
