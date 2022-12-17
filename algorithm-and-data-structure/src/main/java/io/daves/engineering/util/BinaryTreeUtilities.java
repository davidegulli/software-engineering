package io.daves.engineering.util;

import io.daves.engineering.datastructure.trees.LinkedBinaryTree;
import io.daves.engineering.datastructure.trees.Position;
import io.daves.engineering.datastructure.trees.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeUtilities {

    public static Tree<Integer> buildSampleTree(Integer[] sampleDataset) {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();

        List<Position<Integer>> nodes = new ArrayList<>(25);
        nodes.add(tree.addRoot(sampleDataset[0]));

        for (int i = 1; i <= 18; i++) {
            Position<Integer> parent = i > 2 ? nodes.get((i - 1) / 2) : nodes.get(0);
            nodes.add(tree.addLeft(parent, sampleDataset[i]));

            i++;
            parent = i > 2 ? nodes.get((i - 2) / 2) : nodes.get(0);
            nodes.add(tree.addRight(parent, sampleDataset[i]));
        }

        return tree;
    }
}
