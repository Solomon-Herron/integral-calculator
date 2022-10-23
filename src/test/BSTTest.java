package test;

import com.solomonherron.BinTree;
import com.solomonherron.Term;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {

    @org.junit.jupiter.api.Test
    void insert() {
        Term term1 = new Term(2, 5, "2x^3");
        Term term2 = new Term(4, 4, "2x^3");
        Term term3 = new Term(3, 0, "2x^3");
        Term term4 = new Term(6, 10, "2x^3");
        Term term5 = new Term(3, 6, "2x^3");
        Term term6 = new Term(3, 7, "2x^3");
        BinTree<Term> tree = new BinTree<>();

        tree.insert(tree.getRoot(), term1);
        tree.insert(tree.getRoot(), term2);
        tree.insert(tree.getRoot(), term3);
        tree.insert(tree.getRoot(), term4);
        tree.insert(tree.getRoot(), term5);
        tree.insert(tree.getRoot(), term6);

    }

    @org.junit.jupiter.api.Test
    void search() {
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void preorderTraversal() {
    }

    @org.junit.jupiter.api.Test
    void postorderTraversal() {
    }

    @org.junit.jupiter.api.Test
    void inorderTraversal() {
    }
}