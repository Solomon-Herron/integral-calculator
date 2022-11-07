package com.solomonherron;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

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

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<String> filedata = getFileData();
        LinkedList<Integral> integralList = getIntegrals(filedata);
        displayResults(integralList);
    }

    /**
     * Gets file name and imports file data into a linked list
     * <p>
     *This function is built based on the specifications of Zybooks
     * <p>
     * -precondition: All files that are input from Zybooks exist and follow the same format.
     * @return
     * a linked list where each node holds a line of file input
     */
    public static LinkedList<String> getFileData() throws FileNotFoundException {
        LinkedList<String> filedata = new LinkedList<>();
        //Scanner scnner = new Scanner(System.in);
        FileInputStream instream = new FileInputStream("testfiles/manyints.txt");
        Scanner filescnner = new Scanner(instream);

        while(filescnner.hasNext()){
            filedata.append(filescnner.nextLine());
        }

        return cleanInput(filedata);
    }

    /**
     * An intermediary function meant to make parsing integral information easier and more readable
     * @param filedata a list of strings in which each string holds a single integral with spaces in it.
     * @return A linked list in which each node contains 'cleaned' integral.
     *         All spaces must be removed from the expression, and dx removed, leaving only strings of the format ( a|b,4x^3-x+8 OR |-x^3+5x-1  )
     *         <p>
     *         *IF* integral contains a trig function, a space will be left between trig function and g(x)
     *
     *
     */
    public static LinkedList<String> cleanInput(LinkedList<String> filedata){
        LinkedList<String> cleanedFileData = new LinkedList<>();
        String strbuilder = "";
        boolean boundsRead = false;

        for(String str : filedata)
        {
          for (int i = 0; i < str.length(); i++) {
              if(str.charAt(i) == 'd') break;
              if(str.charAt(i) != ' '){
                  strbuilder += str.charAt(i);                       // Case: If the character is not a S
              } else if(i < 4 & strbuilder.length() > 1){                                      // Case: first instance of ' ' in string indicates end of Bounds. Separate with comma.
                  strbuilder += ",";
              } else if(str.charAt(i-1) == 'n' || str.charAt(i-1) == 's' ){
                  // Case: trig function.
                  strbuilder += " ";
              }
          }

          cleanedFileData.append(strbuilder);
          strbuilder = "";
        }

        return cleanedFileData;
    }

    /**
     * A simple function that loops through list of expressions and creates integrals
     * @param filedata A 'cleaned' expression with NO spaces, in the form: a|b,4x^3-x+8 -OR- |-x^3+5x-1.
     * @return a LinkedList of Integral objects. This function will call the Integral constructor and pass in the cleaned expression
     */
    public static LinkedList<Integral> getIntegrals(LinkedList<String> filedata){
        LinkedList<Integral> listOfIntegrals = new LinkedList<>();


        for(String rawIntegral: filedata){
            listOfIntegrals.append(new Integral(rawIntegral));
            System.out.print("What the fuck");
        }

        return listOfIntegrals;
    }

    /**
     * Loops through list of integrals and prints each Antiderivate / value
     * @param integrals A list of integrals
     */
    public static void displayResults(LinkedList<Integral> integrals){

        for(Integral integral : integrals){
            System.out.print("What the fuck");
            System.out.print("pp" + integrals + "\n pp" );
        }

    }



    //================================================================================================
    //================================================================================================
    //================================================================================================
    //===============================================================================================
    public static class Integral implements Comparable<Integral> {
        private BinTree<Term, Character> treeOfTerms;
        private LinkedList<Character> operatorsQueue;
        private int[] bounds;
        private Double value;
        private boolean isDefinite;
        private int constant;
        private String stringRepresentation;


        public Integral(){}
        /**
         *
         * @param rawIntegral A string with ONLY two valid forms: a|b,4x^3-x+8 OR |4x^3-x+8
         */
        public Integral(String rawIntegral){
            // indefinite integral |4x^3-x+8
            if(rawIntegral.charAt(0) == '|'){
                isDefinite = false;
                rawIntegral = rawIntegral.substring(1); // removes pipe from rawintegral
                treeOfTerms = splitTermsAndPushOperators(rawIntegral);
            }
            //  definite integral -a|-b-4x^-3-x+8
            else
            {
                isDefinite = true;
                bounds = new int[]{Integer.parseInt(rawIntegral.substring(0, rawIntegral.indexOf('|'))), Integer.parseInt(rawIntegral.substring(rawIntegral.indexOf('|')+1, rawIntegral.indexOf(',') ))};
                rawIntegral = rawIntegral.substring(rawIntegral.indexOf(",")+1); // removes pipe & bounds from rawintegral
                treeOfTerms = splitTermsAndPushOperators(rawIntegral);
            }
        }

        /**
         * This function contains the meat of the logic of this program.
         * TODO define test cases for every type of term, negative coefficients & exponents, coeifficients & exponents of 0, postives.
         * @param expression A polynomial in the form: -4x^-3-x+8
         * @return A tree containing all the terms in the polynomial. This function also pushes all operators to a Queue. I could assign the tree to the integral within this function but I
         * want to return it to the constructor for consistency
         */
        public BinTree<Term, Character> splitTermsAndPushOperators(String expression){
            BinTree<Term, Character> tree = new BinTree<>();
            operatorsQueue = new LinkedList<>();

            int stringStart = 0;
            int i = (expression.charAt(0) == '-') ? 1 : 0;
            for(; i < expression.length(); i++)
            {   //if character is an operator
                if(expression.charAt(i) == '-' || expression.charAt(i) == '+'){//the condition "expression.charAt(i-1) != '^'" is to eliminate cases  in which there is a negative exponent
                    if(expression.charAt(i-1) != '^')
                    {
                        if(stringStart > 0 && expression.charAt(stringStart-1) == '-'){
                            Term term = new Term(expression.substring(stringStart-1, i));
                            tree.insert(tree.getRoot(), term);
                            operatorsQueue.append(expression.charAt(i));
                            stringStart = i + 1;
                        } else {
                            Term term = new Term(expression.substring(stringStart, i));
                            tree.insert(tree.getRoot(), term);
                            operatorsQueue.append(expression.charAt(i));
                            stringStart = i + 1;
                        }
                    }
                }
            }
            //get the last term
            Term term = new Term(expression.substring(stringStart));
            tree.insert(tree.getRoot(), term);
            return tree;
        }

        public double evaluateIntegralAtBound(int bound){
            LinkedList<Term> integral = treeOfTerms.getListReverseInoderTraversal(treeOfTerms.getRoot(), new LinkedList<Term>());
            double sum = 0;

            for(Term term : integral){
                sum += Math.pow(bound, term.getAntiExponent()) * term.getAntiCoefficient();
            }
            return sum;
        }

        /**
         * Calculates the value of a definite integral
         * @return the value of the integral
         */
        public Double calculuateValue(){
            value = 0.0;
            return value;
        }

        public BinTree<Term, Character> getTreeOfTerms() {
            return treeOfTerms;
        }

        public void setTreeOfTerms(BinTree<Term, Character> treeOfTerms) {
            this.treeOfTerms = treeOfTerms;
        }

        public LinkedList<Character> getOperatorsQueue() {
            return operatorsQueue;
        }

        public void setOperatorsQueue(LinkedList<Character> operatorsQueue) {
            this.operatorsQueue = operatorsQueue;
        }

        public int[] getBounds() {
            return bounds;
        }

        public void setBounds(int[] bounds) {
            this.bounds = bounds;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }

        public boolean isDefinite() {
            return isDefinite;
        }

        public void setDefinite(boolean definite) {
            isDefinite = definite;
        }

        public int getConstant() {
            return constant;
        }

        public void setConstant(int constant) {
            this.constant = constant;
        }

        public String getStringRepresentation() {
            return stringRepresentation;
        }

        public void setStringRepresentation(String stringRepresentation) {
            this.stringRepresentation = stringRepresentation;
        }
        @Override
        public String toString(){
            Main.LinkedList<Term> terms = new Main.LinkedList<>();
            terms = treeOfTerms.getListReverseInoderTraversal(treeOfTerms.getRoot(), terms);
            Node<Term> node = terms.head;
            while(node.getNext() != null && node != null){
                stringRepresentation += node.getData().toString() + (node.getNext().getData().isNegative ? " - " : " + ");
            }
            stringRepresentation += node.getData().toString()  + " + C";
            stringRepresentation = " bruh";
            return stringRepresentation;
        }

        @Override
        public int compareTo(Integral o) {
            return 0;
        }
    }

    public static class LinkedList<G extends Comparable<G>> implements Iterable<G> {
        private Node<G> head;
        private Node<G> tail;
        private int size = 0;

        ///List operations

        public void clear(){
            head = null;
            size = 0;
        }

        public void append(G data) {
            Node<G> node = new Node<G>(data);
            if(head == null){
                head = node;
            } else {
                tail.setNext(node);
            }
            tail = node;
            this.size++;
        }


        public void prepend(G data) {
            Node<G> node = new Node<G>(data);
            if (this.head != null) {
                node.setNext(this.head);
            }
            this.head = node;
        }

        public G pop(){
            Node<G> node = new Node<G>(head.getData());
            head = head.getNext();
            return node.getData();
        }

        /**
         * Deletes every occurrence data
         * @param data data to be deleted
         * @return boolean if an item was deleted. Deletes every occurrence of the data
         */
        public boolean delete(G data){
            boolean deletionPerformed = false;
            if(head == null) return false;
            if(head.getNext() == null && head.getData() == data){
                head = null;
                return true;
            }

            Node<G> followNode = head;
            Node<G> traversalNode = head.getNext();

            while(traversalNode != null && traversalNode.getNext() != null){
                if(traversalNode.getData().compareTo(data) == 0){
                    followNode.setNext(null);
                    deletionPerformed = true;
                } else {
                    traversalNode = traversalNode.getNext();
                    followNode = followNode.getNext();
                }
            }

            return deletionPerformed;
        }

        public void remove(Node<G> prevNode, Node<G> nodeToRemove){
            prevNode.setNext(nodeToRemove.getNext());
        }


        public void reverseList() {
            //create stack named reversedList
            LinkedList<G> reversedList = new LinkedList<>();
            while(head != null){
                reversedList.prepend(head.getData());
                head = head.getNext();
            }
            head = reversedList.head;
        }

        public int getSize() {
            return this.size;
        }




        //================ancillary list functions
        /**
         *     sort() currently only sorts alphabetically...
         *     todo learn how to use 'Comparable' interface so that sort can handle any sort-by query.
         *     I could accomplish this by putting every plyr's stats into parrallel Comparable[] arrays and taking an sortCategory that corresponds to a specific stat category
         *     as a parameter to sort(), This would allow me to generify the sort function to sort a given stat category be taking an integer parameter
         *     and use node.data[i] to compare two nodes. However arrays are not allowed in this project...
         *
         *     sortCategory corresponds t the chosen category in p.statList.
         *     I am aware of the memory overhead that I will incur from carrying this variable back and
         *     forth through the jvm stack. I can remove this overhead by creating a static 'sortcategory' reference variable
         */
        public void sort(Node<G> head){
            this.head = split(head);
        }

        public Node<G> split(Node<G> head){
            //No node(s) in list
            if(head == null || head.getNext() == null) return head;

            Node<G> middleNode = getMiddleNode(head);    //midnode becomes tail of left list.
            Node<G> middleNodePlus1 = middleNode.getNext();   //midnode+1 will become head of right list
            middleNode.setNext(null);                     //cut list in half by setting middle.next to null

            //recursively split and sort
            Node<G> left = split(head);
            Node<G> right = split(middleNodePlus1);

            return mergeSort(left, right);
        }



        //The statList will only be used for indexing stats. Because I will always know the datatype
        //of each plyr stat, and I am limiting the scope of this list to expanding sorting functionality
        //I believe this is a typesafe operation, so I am ok with suppressing the cast warning
        @SuppressWarnings("unchecked")
        private Node<G> mergeSort(Node<G> a, Node<G> b){
            Node<G> sortedNodes = null;
            if(a == null) return b;
            if(b == null) return a;

            if(a.getData().compareTo(b.getData()) <= 0){
                sortedNodes = a;
                sortedNodes.setNext(mergeSort(a.getNext(), b));
            } else {
                sortedNodes = b;
                sortedNodes.setNext(mergeSort(a, b.getNext()));
            }
            return  sortedNodes;
        }

        private Node<G> getMiddleNode(Node<G> head) {
            if(head == null) return head;

            //create two pointers to traverse the list, one moving twice as fast as the other
            //by the time the fast node reaches the end the slower node will be at the middle of the list.
            Node<G> rabbitNode = head;
            Node<G> turdleNode = head;

            while(rabbitNode.getNext() != null && rabbitNode.getNext().getNext() != null){
                turdleNode = turdleNode.getNext();
                rabbitNode = rabbitNode.getNext().getNext();
            }
            return turdleNode;
        }



        public <T extends Comparable<T>> void search(Node<T> node, T data) {

        }

        @Override
        public String toString(){
            String result = "";
            result = print(head, result);
            return result;
        }

        public String print(Node<G> node, String result){
            result += node.getData() + "\n";
            if(node.getNext() != null){
                node = node.getNext();
                result = print(node, result);
            }
            return result;
        }

        @Override
        public Iterator<G> iterator() {
            return new ListIterator();
        }

        private class ListIterator implements Iterator<G>{
            private Node<G> current = head;
            public boolean hasNext(){ return current != null; }
            public void remove() {}
            public G next(){
                G data =  current.getData();
                current = current.getNext();
                return data;
            }

        }


        // ===Getters and Setters

        public Node<G> getHead() {
            return head;
        }

        public void setHead(Node<G> head) {
            this.head = head;
        }


    }

}
