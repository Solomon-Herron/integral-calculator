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


public class BinTree<G extends Comparable<G>> implements Comparable<BinTree<G>>{

    class Node<G extends Comparable<G>> implements Comparable<Node<G>>{
        Node left;
        Node right;
        G data;

        Node(G data){
            this.data = data;
        }
        Node(){}

        @Override
        public int compareTo(Node<G> node){
            return 0;
        }
    }

    private Node root;

    public BinTree(G data){
        Node<G> node = new Node<>(data);
        root = node;

    }
    public BinTree(){
        root = null;
    }

    //====================BST operations
    public Node<G> insert(G data, Node<G> node){
        return new Node<G>();
    }

    public Node<G> search(G data){
        return new Node<>();
    }

    public Node<G> delete(G data){
        return new Node<G>();
    }

    //=================Getters and setters
    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    @Override
    public int compareTo(BinTree<G> tree){
        return 0;
    }



}
