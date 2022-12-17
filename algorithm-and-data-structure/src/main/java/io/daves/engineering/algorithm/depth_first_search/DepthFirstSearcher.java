package io.daves.engineering.algorithm.depth_first_search;

import io.daves.engineering.datastructure.trees.Position;
import io.daves.engineering.datastructure.trees.Tree;
import io.daves.engineering.util.BinaryTreeUtilities;

public class DepthFirstSearcher {

    private static Integer[] sampleDataset = { 3, 12, 5, 8, 19, 6, 9, 4, 13, 18, 15, 7, 9, 11, 17, 21, 14, 2, 1, 20};

    public static void main(String[] args) {
        Tree<Integer> sampleTree = BinaryTreeUtilities.buildSampleTree(sampleDataset);
        System.out.println(sampleTree);

        DepthFirstSearcher searcher = new DepthFirstSearcher();
        searcher.depthFirstSearch(sampleTree, 15); // The correct depth is: 3
        searcher.depthFirstSearch(sampleTree, 21); // The correct depth is: 4
        searcher.depthFirstSearch(sampleTree, 3); // The correct depth is: 0
    }

    private void depthFirstSearch(Tree<Integer> sampleTree, Integer numberToSearch) {
        Position<Integer> root = sampleTree.root();
        Position<Integer> foundPosition = null;

        if(root.getElement() == numberToSearch) {
            foundPosition = root;
        } else {
            foundPosition = subtreeInorderSearchingTraversal(root, sampleTree, numberToSearch);
        }

        System.out.println(
                "The element: " + numberToSearch + " has been found at depth: " + sampleTree.depth(foundPosition));
    }

    private Position<Integer> subtreeInorderSearchingTraversal(
            Position<Integer> position, Tree<Integer> tree, Integer numberToSearch) {

        if (position.getElement() == numberToSearch) {
            return position;
        }

        Position<Integer> result = null;

        for(Position<Integer> child : tree.children(position)) {
            result = subtreeInorderSearchingTraversal(child, tree, numberToSearch);

            if (result != null) {
                break;
            }
        }

        return result;
    }

}
