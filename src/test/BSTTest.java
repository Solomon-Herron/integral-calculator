package test;

import com.solomonherron.BinTree;
import com.solomonherron.Main;
import com.solomonherron.Term;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    public static BinTree<Term, Character> tree = new BinTree<>();

    @org.junit.jupiter.api.Test
    void insert() {
        Term term1 = new Term(2, 0, "2x^3");
        Term term2 = new Term(4, 4, "2x^3");
        Term term3 = new Term(3, -10, "2x^3");
        Term term4 = new Term(6, 10, "2x^3");
        Term term5 = new Term(3, -2, "2x^3");
        Term term6 = new Term(3, 7, "2x^3");


        tree.insert(tree.getRoot(), term1);
        tree.insert(tree.getRoot(), term2);
        tree.insert(tree.getRoot(), term3);
        tree.insert(tree.getRoot(), term4);
        tree.insert(tree.getRoot(), term5);
        tree.insert(tree.getRoot(), term6);
    }

    @org.junit.jupiter.api.Test
    void search() {
        buildTree();
        Term negativeDegreefalse = new Term(1, -1, "x^10");
        Term negativeDegreetrue = new Term(1, -2, "x^10");
        Term zeroDegree = new Term(1, 0, "x^10");
        Term postiveDegreefalse = new Term(1, 5, "x^10");
        Term postiveDegreetrue = new Term(1, 7, "x^10");
        Term maxDegree = new Term(1, 10, "x^10");
        assertTrue(tree.search(tree.getRoot(), negativeDegreetrue));
        assertTrue(tree.search(tree.getRoot(), postiveDegreetrue));
        assertFalse(tree.search(tree.getRoot(), negativeDegreefalse));
        assertFalse(tree.search(tree.getRoot(), postiveDegreefalse));
        assertTrue(tree.search(tree.getRoot(), zeroDegree));
        assertTrue(tree.search(tree.getRoot(), maxDegree));
    }

    @org.junit.jupiter.api.Test
    void delete() {
    }

    @org.junit.jupiter.api.Test
    void preorderTraversal() {
        buildTree();
        System.out.print(tree.reverseInoderTraversalPrintTree(tree.getRoot(), ""));

    }

    @org.junit.jupiter.api.Test
    void postorderTraversal() {
    }

    @org.junit.jupiter.api.Test
    void inorderTraversal() {
    }

    void buildTree(){
        Term term1 = new Term("-37x^0");
        Term term2 = new Term("-2x^9");
        Term term3 = new Term("-324x^4");
        Term term4 = new Term("-324x^10");
        Term term5 = new Term("-324x^-7");


        tree.insert(tree.getRoot(), term1);
        tree.insert(tree.getRoot(), term2);
        tree.insert(tree.getRoot(), term3);
        tree.insert(tree.getRoot(), term4);
        tree.insert(tree.getRoot(), term5);
    }

    BinTree<Term, Character> getTree(){
        buildTree();
        return tree;
    }

    @Test
    void testList(){
        buildTree();
        Main.Integral integral =  new Main.Integral("1|3,6x+3x^2-7");
        Main.LinkedList<Term> terms = new Main.LinkedList<>();
        terms = integral.getTreeOfTerms().getListReverseInoderTraversal(integral.getTreeOfTerms().getRoot(), terms);
        System.out.print(terms);
    }
}