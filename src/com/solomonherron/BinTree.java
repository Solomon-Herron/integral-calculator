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
    private Node<G> root;

    public BinTree(G data){
        root = new Node<>(data);

    }
    public BinTree(){
        root = null;
    }

    //====================BST operations
    /**
     * @param data Data to be entered into the tree
     * @param direction move left or right
     */
    public void insert(G data, Node<G> direction){}

    /**
     * @param data Data to be found
     * @return Whether the data was found or not
     */
    public boolean search(G data){
        return true;
    }

    /**
     *
     * @param data Data to be deleted
     * @return  Whether the data was deleted
     */
    public boolean delete(G data){
        return true;
    }

    //=================Getters and setters
    public Node<G> getRoot() {
        return root;
    }

    public void setRoot(Node<G> root) {
        this.root = root;
    }

    @Override
    public int compareTo(BinTree<G> tree){
        return 0;
    }



}
