package test;

import com.solomonherron.Main;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class IntegralTest {

    // <a|b> <space> <3x^2> <-> <9x> <+> <2>
    public static HashMap<String, String> testCases = new HashMap<>();
    public static HashMap<String, String> expectedIntegral = new HashMap<>();

    @Test
    void Integral(){
        buildTestCaseMap();
        String expected, actual;

        for(Map.Entry<String, String> testcase : testCases.entrySet()){
            expected = expectedIntegral.get(testcase.getKey());
            actual = Main.Integral.constructorTestDriver(testcase.getValue());
            assertEquals(expected, actual);
        }
    }

    @Test
    void splitTermsAndPushOperators(){

    }

    @Test
    void parseBound(){

    }

    @Test
    void calculateValue(){

    }
// *      <Expression><comma><space><upperboud><pipe><lowerboun><space><=><space><value><newline>

    void buildTestCaseMap(){
        testCases.put("standardDefinite",      "1|3 6x+3x^2-7");
        testCases.put("standardIndefinite",    "| x+4x^2+3");
        testCases.put("noConstant",            "1|3 4x^2+2x^3-x");
        testCases.put("aGreaterThanb",         "5|1 3x^2+6x-76x^4-7");
        testCases.put("maxDegree",             "| 4x^10+x+3"); //fill this out with all terms
        testCases.put("degreeZero",            "| 3");
        testCases.put("termsWithSameDegree",   "1|3 3x^2-6x^2+6x+7");
        testCases.put("negativeExponent",      "1|3 3x^2-6x^-2+7");
        testCases.put("negativeCoefficients",  "1|3 -6x^3+-2x^2--3x^4-7"); // ask in piazza if this is a real possibility
        testCases.put("noCoefficients",        "1|3 x^2-x^-2+7");
        testCases.put("trigIntegral",          "| 3x^4+2sin 10x–6x^2+2");
        testCases.put("multipleTrigFunctions", "| -cos 4x-3x^4+2sin 10x–6x^2+2");

        // calculate these integrals... (in standard form)
        //(3/2)x^2 + 4x -x^-1, 1|4 = 35.250
        //x^3 + x^2 + x + C
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
