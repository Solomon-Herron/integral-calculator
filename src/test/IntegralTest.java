package test;

import com.solomonherron.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class IntegralTest {

    // <a|b> <space> <3x^2> <-> <9x> <+> <2>
    public static HashMap<String, String> testCases = new HashMap<>();
    public static HashMap<String, String> expectedIntegral = new HashMap<>();

    //TODO Untested members: value
    @Test
    public void Integral(){
        buildTestCaseMap();
        String expected, actual;
        Main.Integral integral = new Main.Integral();


        integral  = new Main.Integral(testCases.get("standardDefinite"));

        System.out.print(integral);
        /*
        assertEquals("standardDefinite bounds failure", 1, integral.getBounds()[0]);
        assertEquals("standardDefinite bounds failure", 3, integral.getBounds()[1]);
        assertEquals("standardDefinite operator failure", "+\n-\n", integral.getOperatorsQueue().toString());
        assertEquals("standardDefinite tree failure", "6x", integral.getTreeOfTerms().getRoot().toString());
        assertEquals("standardDefinite tree failure", "3x^2", integral.getTreeOfTerms().getRoot().getRight().toString());
        assertEquals("standardDefinite tree failure", "7", integral.getTreeOfTerms().getRoot().getLeft().toString());

        integral  = new Main.Integral(testCases.get("standardIndefinite"));

        assertEquals("standardIndefinite operator failure", "+\n+\n", integral.getOperatorsQueue().toString());
        assertEquals("standardIndefinite tree failure", "x", integral.getTreeOfTerms().getRoot().toString());
        assertEquals("standardIndefinite tree failure", "4x^2", integral.getTreeOfTerms().getRoot().getRight().toString());
        assertEquals("standardIndefinite tree failure", "3", integral.getTreeOfTerms().getRoot().getLeft().toString());

        integral  = new Main.Integral(testCases.get("noConstant"));
        assertEquals("noConstant bounds failure", 1, integral.getBounds()[0]);
        assertEquals("noConstant bounds failure", 3, integral.getBounds()[1]);
        assertEquals("noConstant operator failure", "+\n-\n", integral.getOperatorsQueue().toString());
        assertEquals("noConstant tree failure", "4x^2", integral.getTreeOfTerms().getRoot().toString());
        assertEquals("noConstant tree failure", "2x^3", integral.getTreeOfTerms().getRoot().getRight().toString());
        assertEquals("noConstant tree failure", "x", integral.getTreeOfTerms().getRoot().getLeft().toString());

        integral  = new Main.Integral(testCases.get("Max Degree"));

        assertEquals("Max Degree bounds failure", 1, integral.getBounds()[0]);
        assertEquals("Max Degree bounds failure", 3, integral.getBounds()[1]);
        assertEquals("Max Degree operator failure", "+\n-\n", integral.getOperatorsQueue().toString());
        assertEquals("Max Degree tree failure", "4x^2", integral.getTreeOfTerms().getRoot().toString());
        assertEquals("Max Degree tree failure", "2x^3", integral.getTreeOfTerms().getRoot().getRight().toString());
        assertEquals("Max Degree tree failure", "x", integral.getTreeOfTerms().getRoot().getLeft().toString());

        testCases.put("standardDefinite",      "1|3 6x+3x^2-7");
        testCases.put("standardIndefinite",    "| x+4x^2+3");
        testCases.put("noConstant",            "1|3 4x^2+2x^3-x");
        testCases.put("a GreaterThan b",         "5|1 3x^2+6x-76x^4-7");
        testCases.put("Max Degree",             "| 4x^10+x+3"); //fill this out with all terms
        testCases.put("Degree Zero",            "| 3");
        testCases.put("Terms With Same Degree",   "1|33x^2-6x^2+6x+7");
        testCases.put("All Negative",           "-1|-3-3x^-2-6x^-2+6x+7");
        testCases.put("Negative Exponent",      "1|3 3x^2-6x^-2+7");
        testCases.put("Negative Coefficients",  "1|3 -6x^3+-2x^2--3x^4-7"); // ask in piazza if this is a real possibility
        testCases.put("No Coefficients",        "1|3 x^2-x^-2+7");
        testCases.put("Trig Integral",          "| 3x^4+2sin 10x–6x^2+2");
        testCases.put("multipleTrigFunctions", "| -cos 4x-3x^4+2sin 10x–6x^2+2");
         */

    }

    @Test
    public void splitTermsAndPushOperators(){
        Main.Integral int1 = new Main.Integral();
        BinTree<Term, Character> tree1 = int1.splitTermsAndPushOperators("-4x^-3-x+8");
        assertEquals("-4x^-3", tree1.getRoot().toString());
        assertEquals("-x", tree1.getRoot().getRight().toString());
        assertEquals("8", tree1.getRoot().getLeft().toString());
        //assertEquals("-\n+\n", int1.getOperatorsQueue().toString());
        int1.setTreeOfTerms(tree1);
        System.out.print(int1.evaluateIntegralAtBound(2));

        Main.Integral int2 = new Main.Integral();
        BinTree<Term, Character> tree2 = int2.splitTermsAndPushOperators("3x^2-6x-76x^4-7");
        assertEquals("3x^2", tree2.getRoot().toString());
        assertEquals("-6x", tree2.getRoot().getLeft().toString());
        assertEquals("-76x^4", tree2.getRoot().getRight().toString());
        assertEquals("7", tree2.getRoot().getLeft().getLeft().toString());
        //assertEquals("+\n-\n-\n", int2.getOperatorsQueue().toString());

        Main.Integral int3 = new Main.Integral();
        BinTree<Term, Character> tree3 = int3.splitTermsAndPushOperators("x");
        assertEquals("x", tree3.getRoot().toString());
    }

    @Test
    public void parseBounds(){
        String rawIntegral = "4|5,1x^-2+7x^3+6";
        int[] bounds = new int[]{Integer.parseInt(rawIntegral.substring(0, rawIntegral.indexOf('|'))), Integer.parseInt(rawIntegral.substring(rawIntegral.indexOf('|')+1, rawIntegral.indexOf(',') ))};
        assertEquals(4, bounds[0]);
        assertEquals(5, bounds[1]);
        System.out.print(rawIntegral.substring(rawIntegral.indexOf("1"),rawIntegral.indexOf("+")));
    }

    @Test
    public void calculateValue(){

    }
// *      <Expression><comma><space><upperboud><pipe><lowerboun><space><=><space><value><newline>

    void buildTestCaseMap(){
        testCases.put("standardDefinite",      "1|3,6x+3x^2-7");
        testCases.put("standardIndefinite",    "|x+4x^2+3");
        testCases.put("noConstant",            "1|3,4x^2+2x^3-x");
        testCases.put("a GreaterThan b",         "5|1,3x^2+6x-76x^4-7");
        testCases.put("Max Degree",             "|4x^10-x^-4-x^-2+80^9-9x^2-8x^-3+4x+3"); //fill this out with all terms
        testCases.put("Degree Zero",            "|3");
        testCases.put("Terms With Same Degree",   "1|33x^2-6x^2+6x+7");
        testCases.put("All Negative",           "-1|-3,-3x^-2-6x^-2+6x+7");
        testCases.put("Negative Exponent",      "1|3,3x^2-6x^-2+7");
        testCases.put("Negative Coefficients",  "1|3,-6x^3+-2x^2--3x^4-7"); // ask in piazza if this is a real possibility
        testCases.put("No Coefficients",        "1|3,x^2-x^-2+7");
        testCases.put("Trig Integral",          "|3x^4+2sin 10x–6x^2+2");
        testCases.put("multipleTrigFunctions", "|cos 4x-3x^4+2sin 10x–6x^2+2");

        // calculate these integrals... (in standard form)
        expectedIntegral.put("standardDefinite",      "x^3 + 3x^2 - 14, 1|3 = 36"); // 1|3 6x+3x^2-7
        expectedIntegral.put("standardIndefinite",    "(4/3)x^3 + (1/2)x^2 + 3x + C"); //| x+4x^2+3 
        expectedIntegral.put("noConstant",            ""); //1|3 4x^2+2x^3-x
        expectedIntegral.put("aGreaterThanb",         ""); //5|1 3x^2+6x-76x^4-7
        expectedIntegral.put("maxDegree",             ""); //fill this out with all terms
        expectedIntegral.put("degreeZero",            "");
        expectedIntegral.put("termsWithSameDegree",   "");
        expectedIntegral.put("negativeExponent",      "");
        expectedIntegral.put("negativeCoefficients",  ""); // ask in piazza if this is a real possibility
        expectedIntegral.put("noCoefficients",        "");
        expectedIntegral.put("trigIntegral",          "");
        expectedIntegral.put("multipleTrigFunctions", "");
    }
}
