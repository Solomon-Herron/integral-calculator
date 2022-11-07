package com.solomonherron;
/*
Abstractly speaking, a specification of a function has several parts:
    -a function signature, giving the name, parameter types, and return type

    -a requires clause, describing additional restrictions on the parameters
        -narrowing a parameter type (e.g. x is an integer >= 0 to say that a number parameter x must actually be a nonnegative integer)
        -interactions between parameters (e.g., val occurs exactly once in arr)

    -an effects clause, describing the return value, exceptions, and other effects of the function
        -how the return value relates to the inputs
        -which exceptions are thrown, and when
        -whether and how objects are mutated

    JavaDoc Function specification. @para == preconditions, @returns == postconditions
    /**
     * Find a value in an array.
     * @param arr array to search, requires that val occurs exactly once
     *            in arr
     * @param val value to search for
     * @returns index i such that arr[i] = val



     psuedocode spec:
     isPalindrome(word: string): boolean
        requires:
        word contains only alphanumeric characters
        effects:
        returns true if and only if word is a palindrome

     JavaDoc spec
     /**
     * Check if a word is a palindrome.
     * A palindrome is a sequence of characters
     * that reads the same forwards and backwards.
     * @param string word
     * @requires word contains only alphanumeric characters
     * @effects returns true if and only if word is a palindrome
     * @returns boolean

 */


public class BinTree<G extends Comparable<G>, K extends Comparable<K>> implements Comparable<BinTree<G, K>>{
    private Node<G> root;
    private Main.LinkedList<K> operatorsQueue;
    private int constant;

    public BinTree(G data){
        root = new Node<>(data);

    }
    public BinTree(){
        root = null;
    }

    //====================BST operations
    /**
     * @param newData Data to be entered into the tree
     * @param node recursive traversal node
     */
    public void insert(Node<G> node, G newData){
        if(root == null){
            root = new Node<G>(newData);
            return;
        }

        Node<G> left = node.getLeft();
        Node<G> right = node.getRight();
        G nodeData = node.getData();

        if (right != null & nodeData.compareTo(newData) < 0) insert(right, newData);
        else if (left != null & nodeData.compareTo(newData) > 0) insert(left, newData);
        else if (right == null & nodeData.compareTo(newData) < 0) node.setRight(new Node<G>(newData));
        else if (left == null & nodeData.compareTo(newData) > 0) node.setLeft(new Node<G>(newData));
    }

    /**
     * @param data Data to be found
     * @return Whether the data was found or not
     */
    public boolean search(Node<G> node, G data){
        boolean found;
        if(node == null) return false;
        if(node.getData().compareTo(data) == 0) return true;
        if(node.getData().compareTo(data) < 0) found = search(node.getRight(), data);
        else found = search(node.getLeft(), data);
        return found;
    }

    /**
     *
     * @param data Data to be deleted
     * @return  Whether the data was deleted
     */
    public boolean delete(G data){
        return true;
    }

    public String preorderTraversal(Node<G> node, String nodes){
        if(node == null) return nodes;
        nodes += node.getData();
        nodes = preorderTraversal(node.getLeft(), nodes);
        nodes = preorderTraversal(node.getRight(), nodes);
        return nodes;
    }
    public void postorderTraversalPrintTree(Node<G> node){

    }
    public void inoderTraversalPrintTree(Node<G> node){

    }

    public String reverseInoderTraversalPrintTree(Node<G> node, String nodes){
        if(node == null) return nodes;
        nodes = reverseInoderTraversalPrintTree(node.getRight(), nodes);
        nodes += node.getData();
        //if(operatorsQueue.getHead() != null) nodes += operatorsQueue.pop();
        nodes = reverseInoderTraversalPrintTree(node.getLeft(), nodes);
        //nodes += constant;
        return nodes;
    }






    //=================Getters and setters
    public Node<G> getRoot() {
        return root;
    }

    public void setRoot(Node<G> root) {
        this.root = root;
    }

    public Main.LinkedList<K> getOperatorsQueue() {
        return  operatorsQueue;
    }

    public void setOperatorsQueue(Main.LinkedList<K> operatorsQueue) {
        this.operatorsQueue = operatorsQueue;
    }

    public int getConstant() {
        return constant;
    }

    public void setConstant(int constant) {
        this.constant = constant;
    }

    @Override
    public int compareTo(BinTree<G, K> o) {
        return 0;
    }

    public Main.LinkedList<G> getListReverseInoderTraversal(Node<G> node, Main.LinkedList<G> list) {
        if(node == null) return list;
        list = getListReverseInoderTraversal(node.getRight(), list);
        list.append(node.getData());
        list = getListReverseInoderTraversal(node.getLeft(), list);
        return list;

    }
}
