package test;

import com.solomonherron.Main;
import com.solomonherron.Term;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TermTest {

    //

    @Test
    void testIt(){
        Main.LinkedList<Term> listOfTerms = new Main.LinkedList<>();
        Random random = new Random();

        for(int i = 0; i < 11; i++){
            String str = "";
            int coefficient = random.nextInt(20) - 10;
            int exponent = random.nextInt(20) - 10;
            str += coefficient + "x^" + exponent;
            listOfTerms.append(new Term(str));
        }

        listOfTerms.append(new Term("-6x^5"));
        listOfTerms.append(new Term("-10x^-2"));
        listOfTerms.append(new Term("4"));
        listOfTerms.append(new Term("-4x^-2"));

        for(Term term : listOfTerms){
            System.out.println(term.toString());
            term.calculateAntiDerivative();

        }
    }
    @Test
    void testToString() {
    }

    @Test
    void compareTo() {
    }

    @Test
    void testGCDalgo(){
        // quotient = coefficient / exponent;
        int quotient = 126, decimalPLace = 100; //decimalPlace = (.xxx) ? 1000: 100;
        int remainderNumerator, remainderDenominator;
        boolean done = false;
        int gcd = 1;
        for(int i = 1; i <= quotient && i <= decimalPLace; i++){
            if(quotient % i == 0 & decimalPLace % i == 0){
                gcd = i;
            }
        }

        quotient = quotient/gcd;
        decimalPLace = decimalPLace/gcd;

        int constant;
        for(constant = 0; done; constant++ ){
            quotient = quotient - decimalPLace;

        }


        System.out.print(gcd);
    }

    @Test
    void testCompareTo() {
    }
}