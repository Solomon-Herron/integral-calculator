package com.solomonherron;

public class Node<G extends Comparable<G>> implements Comparable<Node<G>> {
    private Node<G> left;
    private Node<G> right;
    private Node<G> next;
    private G data;

    Node(G data){
        this.data = data;
    }
    Node(){}

    //Getters setters

    public Node<G> getNext() {
        return next;
    }

    public void setNext(Node<G> next) {
        this.next = next;
    }

    public Node<G> getLeft() {
        return left;
    }

    public void setLeft(Node<G> left) {
        this.left = left;
    }

    public Node<G> getRight() {
        return right;
    }

    public void setRight(Node<G> right) {
        this.right = right;
    }

    public G getData() {
        return data;
    }

    public void setData(G data) {
        this.data = data;
    }

    @Override
    public int compareTo(Node<G> o) {
        return 0;
    }

    @Override
    public String toString(){
        return "" + data;
    }
}
