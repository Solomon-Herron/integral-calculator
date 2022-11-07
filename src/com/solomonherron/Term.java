package com.solomonherron;

import java.math.BigDecimal;
import java.math.MathContext;

public class Term implements Comparable<Term> {
    private int coefficient;
    private int exponent;
    private String stringRepresentation;
    private String fractionalCoeffictient = "";
    private double antiCoefficient;
    private int antiExponent;
    private String antiStringRepresentation;
    private int zero = -11;
    public boolean isNegative = false;

    public Term(int coefficient, int exponent, String stringRepresentation){
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.stringRepresentation = stringRepresentation;
    }

    public Term(String expression) {
        //-4x^-3  x^-2  3x x 3  only 5 representations of a valid term
        stringRepresentation = expression;
        if(expression.indexOf('x') == -1){                                              //no x variable in term
            coefficient = Integer.parseInt(expression);
            exponent = zero;
        } else if(expression.indexOf('x') > 0 && !(Character.isDigit(expression.charAt(expression.indexOf('x')-1))) & expression.indexOf('^') == -1) {        //No coefficient no exponent
            if(expression.charAt(expression.indexOf('x')-1) == '-') coefficient = -1;
            else coefficient = 1;
            exponent = 1;
        } else if(expression.indexOf('x') > 0 && !(Character.isDigit(expression.charAt(expression.indexOf('x')-1))) & expression.indexOf('^') != -1) {        //No coefficient w/ exponent
            if(expression.charAt(expression.indexOf('x')-1) == '-') coefficient = -1;
            else coefficient = 1;
            exponent = Integer.parseInt(expression.substring(expression.indexOf('^') + 1));
        } else if( expression.charAt(0) == 'x' & expression.indexOf('^') == -1) {        //No coefficient no exponent
            coefficient = 1;
            exponent = 1;
        } else if(expression.charAt(0) == 'x' & expression.indexOf('^') != -1) {        //No coefficient w/ exponent
            coefficient = 1;
            exponent = Integer.parseInt(expression.substring(expression.indexOf('^') + 1));
        }else if(expression.charAt(0) != 'x' & expression.indexOf('^') == -1) {        //coefficient w/ no exponent
            coefficient = Integer.parseInt(expression.substring(0, expression.indexOf('x')));
            exponent = 1;
        } else {                                                                        //Both coefficient and exponent
            coefficient = Integer.parseInt(expression.substring(0, expression.indexOf('x')));
            exponent = Integer.parseInt(expression.substring(expression.indexOf('^') + 1));
        }

        calculateAntiDerivative();
    }

    /**
     * calculate the antiderivative of a term
     *
     */
    public void calculateAntiDerivative(){
        antiExponent = exponent + 1;
        if (exponent == zero) antiExponent = 1;
        int originalAntiExponent = antiExponent;
        if(antiExponent == 0) return;

        int gcd = 1;
        boolean reduced = false;
        while(!reduced) {
            gcd = 1;
            for (int i = 1; i <= Math.abs(coefficient) && i <= antiExponent; i++) {
                if (coefficient % i == 0 & antiExponent % i == 0) {
                    gcd = i;
                }
            }
            coefficient = coefficient/gcd;
            antiExponent = antiExponent/gcd;

            if(gcd == 1) reduced = true;
        }
        if (coefficient % antiExponent == 0) coefficient = coefficient / antiExponent;
        if ((coefficient / antiExponent) < 0) isNegative = true;
        fractionalCoeffictient =  ((coefficient % antiExponent == 0) ? coefficient+"" :  "(" + Math.abs(coefficient)+ "/" +Math.abs(antiExponent)+ ")");
}

    @Override
    public String toString(){
        if(fractionalCoeffictient == "")
        {
            if (coefficient == 1) {
                if (antiExponent == 1) stringRepresentation = "x";
                else stringRepresentation = "x^" + antiExponent;
            } else if (coefficient == -1) {
                if (antiExponent == 1) stringRepresentation = "-x";
                else stringRepresentation = "-x^" + antiExponent;
            } else if (antiExponent == 1) {
                stringRepresentation = coefficient + "x";
            } else {
                stringRepresentation = coefficient + "x^" + antiExponent;
            }
        } else if (antiExponent == 1) {
            stringRepresentation = fractionalCoeffictient + "x";
        }  else {
            stringRepresentation = fractionalCoeffictient + "x^" + antiCoefficient;
        }

        return stringRepresentation;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public String getStringRepresentation() {
        return stringRepresentation;
    }

    public void setStringRepresentation(String stringRepresentation) {
        this.stringRepresentation = stringRepresentation;
    }

    public String getFractionalCoeffictient() {
        return fractionalCoeffictient;
    }

    public void setFractionalCoeffictient(String fractionalCoeffictient) {
        this.fractionalCoeffictient = fractionalCoeffictient;
    }

    public double getAntiCoefficient() {
        return antiCoefficient;
    }

    public void setAntiCoefficient(double antiCoefficient) {
        this.antiCoefficient = antiCoefficient;
    }

    public int getAntiExponent() {
        return antiExponent;
    }

    public void setAntiExponent(int antiExponent) {
        this.antiExponent = antiExponent;
    }

    public String getAntiStringRepresentation() {
        return antiStringRepresentation;
    }

    public void setAntiStringRepresentation(String antiStringRepresentation) {
        this.antiStringRepresentation = antiStringRepresentation;
    }

    public int getZero() {
        return zero;
    }

    public void setZero(int zero) {
        this.zero = zero;
    }

    @Override
    public int compareTo(Term o) {
        if(this.exponent < o.exponent) return -1;
        if(this.exponent > o.exponent) return 1;
        if(this.exponent == o.exponent) return 0;
        return 0;
    }

}


/*
antiExponent = exponent + 1;
        if(antiExponent == 0) return;
        antiCoefficient = (double) coefficient / antiExponent;                                                         //decimal value of the coefficient of the anti derivative.
        BigDecimal decimalValue = new BigDecimal(antiCoefficient); //a BigDecimal variable userd soley for finding the number of decimal places to perform decimal to fraction conversion
        decimalValue = decimalValue.round(new MathContext(4));

        int numDigitsAfterDecimal = decimalValue.scale();                                                 //Gets the number of digits after decimal 0.xxx
        int dec2fracScaler = (int) Math.pow(10, numDigitsAfterDecimal);                                   //divide decimal value by 10, 100, 1000, etc
        double numerator = decimalValue.doubleValue() * dec2fracScaler;
        double denominator = dec2fracScaler;

        int gcd = 1;
        boolean reduced = false;
        while(!reduced) {
            gcd = 1;
            for (int i = 1; i <= Math.abs(numerator) && i <= denominator; i++) {
                if (numerator % i == 0 & denominator % i == 0) {
                    gcd = i;
                }
            }
            numerator = numerator/gcd;
            denominator = denominator/gcd;
            if(gcd == 1) reduced = true;
        }



        int sign = (numerator < 0) ? -1 : 1;
        int constant = 0;
        for(;; constant++){
            if(sign > 0) {
                if ((numerator - denominator) < 0) break;
                numerator = numerator - denominator;
            } else {
                if((numerator + denominator) > 0) break;
                numerator = numerator + denominator;
            }
        }

        if(sign < 0){
            constant = constant - (constant*2);
            numerator = numerator + (numerator*-2);
        }


        if(denominator != 1) fractionalCoeffictient = ((constant == 0) ? "" :  constant) + ((numerator == 0) ? "" : "(" +(int)numerator+ "/" +(int)denominator+ ")");
        else fractionalCoeffictient = antiCoefficient + "";

        System.out.println(fractionalCoeffictient);
 */