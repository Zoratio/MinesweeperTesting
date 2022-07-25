package com.TSI.minesweeper.testing;
import com.TSI.minesweeper.ScannerInputs;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScannerInputsTest {
    @Test
    public void test_checkRange(){
        ScannerInputs myScanner = new ScannerInputs();

        String input = "5";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertEquals(5, myScanner.CheckRange(), "size that is input does not equal the size");
    }
}
