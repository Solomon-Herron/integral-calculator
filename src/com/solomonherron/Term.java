package com.solomonherron;

public class Term implements Comparable<Term> {
    private int coefficient;
    private int degree;
    private String stringRepresentation;

    public Term(int coefficient, int degree, String stringRepresentation){
        this.coefficient = coefficient;
        this.degree = degree;
        this.stringRepresentation = stringRepresentation;
    }

    @Override
    public int compareTo(Term o) {
        if(this.degree < o.degree) return -1;
        if(this.degree > o.degree) return 1;
        if(this.degree == o.degree) return 0;
        return 0;
    }
}
