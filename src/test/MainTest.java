package test;

import com.solomonherron.Main;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
    }

    @Test
    void getFileData() throws FileNotFoundException {
        String expected ="1|3 6x+3x^2-7\n" +
                        "| x+4x^2+3\n" +
                        "1|3 4x^2+2x^3-x\n" +
                        "5|1 3x^2+6x-76x^4-7\n" +
                        "| 4x^10+x+3\n" +
                        "| 3\n" +
                        "1|3 3x^2-6x^2+6x+7\n" +
                        "1|3 3x^2-6x^-2+7\n" +
                        "1|3 -6x^3+-2x^2--3x^4-7\n" +
                        "1|3 x^2-x^-2+7\n" +
                        "| 3x^4+2sin 10x–6x^2+2\n" +
                        "| -cos 4x-3x^4+2sin 10x–6x^2+2";
        Main.LinkedList<String> result = Main.getFileData();
        assertEquals(expected, result.toString());

    }

    @Test
    void cleanInput() {
    }

    @Test
    void getIntegrals() {
    }

    @Test
    void displayResults() {
    }
}