package com.solomonherron;

import java.util.Iterator;

/**
 *
 * -Preconditions
 *      -0 < degree < 10
 *      -N terms of same degree
 *      -Will not be instandard form
 *      -All coefficients and expo are Integers
 *      -Exponents can be negative
 *      -'|' represents Integral symbol
 *      -If definite  a | b where a <b or b<a
 *      -There will always be a space before and after expression
 *      -Expression may include spaces
 *      -There will always be a space between a trig function and the inner coeffient
 *      -If there is no coefficient, it is assumed to be 1
 * -Postconditions
 *      -Definite integrals will include value 3 decimal places
 *      -Exponents represented with ^
 *      -Fractions must be simplified & enclosed in parenthesis
 *      -spaces before and after every operator
 *      ( + or - )
 *      -Trig antiderivatives will be listed at the end of the expression in the order encountered
 *      -There will always be a space between a trig function and the inner coeffient
 *      -Indefinite format:
 *      <Expression><space><+><space><Capital C><newline>
 *      <Expression><comma><space><upperboud><pipe><lowerboun><space><=><space><value><newline>
 *
 */
public class Main {

    public static LinkedList<Integral> listOfIntegrals;

    public static void main(String[] args) {
    }

    /**
     * Gets file name and imports file data into a linked list
     *
     * @return a linked list where each node holds a line of file input
     */
    public static LinkedList<String> getFileData(){ return new LinkedList<String>();}

    /**
     * An intermediary function meant to make parsing integral information easier and more readable
     * @param filedata a list of strings in which each string holds a single integral with spaces in it.
     * @return A linked list in which each node contains 'cleaned' integral.
     *         All spaces must be removed from the expression, and dx removed, leaving only strings of the format ( a|b 4x^3-x+8 OR | -x^3+5x-1  )
     */
    public static LinkedList<String> cleanInput(LinkedList<String> filedata){return new LinkedList<String>();}

    /**
     * A simple function that loops through list of expressions and creates integrals
     * @param filedata A 'cleaned' expression in the form: a|b 4x^3-x+8 -OR- | -x^3+5x-1.
     * @return a LinkedList of Integral objects. This function will call the Integral constructor and pass in the cleaned expression
     */
    public static LinkedList<Integral> populateIntegralList(LinkedList<String> filedata){return new LinkedList<Integral>();}

    /**
     * Loops through list of integrals and prints each Antiderivate / value
     * @param integrals A list of integrals
     */
    public static void displayResults(LinkedList<Integral> integrals){}



    //================================================================================================
    //================================================================================================
    //================================================================================================
    //===============================================================================================
    static class Integral implements Comparable<Integral> {
        private BinTree<Integer> treeOfTerms;
        private LinkedList<Character> operators;
        private int upperBound;
        private int lowerBound;
        private Double value;
        private boolean isDefinite;
        private int constant;

        public Integral(String expression){

            //a|b 4x^3-x+8

            //split expression into bounds and expression

            //setbounds() , set isDefinite

            //splitTerms(String expression)

            //push operators to queue

            //create term obj

            //push terms to treeofterms, set constant
        }

        /**
         * Calculates the value of a definite integral
         * @return the value of the integral
         */
        public Double calculuateValue(){
            value = 0.0;
            return value;
        }

        /**
         * This function contains the meat of the logic of this program.
         * TODO define test cases for every type of term, negative coefficients
         * @param expression A polynomial in the form: 4x^3-x+8
         * @return A tree containing all the terms in the polynomial. I could assign the tree to the integral within this function but I
         * want to return it to the constructor for consistency
         */
        private BinTree<Term> splitTerms(String expression){
            BinTree<Term> tree = new BinTree<>();
            LinkedList<Character> operatorQueue = new LinkedList<>();

            return tree;
        }
        @Override
        public int compareTo(Integral o) {
            return 0;
        }
    }

    static class LinkedList<G extends Comparable<G>> implements Iterable<G>{
        private Node<G> next;
        private G data;

        @Override
        public Iterator<G> iterator() {
            return null;
        }
    }
}
