package org.agentelf.util;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DiffText {

    public static void assertTextEquals(String pathToExpected, String actualString)  {
      new DiffText().assertTextEqualsInternal(pathToExpected,actualString);
    }

    private void assertTextEqualsInternal(String pathToExpected, String actualString) {
        InputStream stream = this.getClass().getResourceAsStream(pathToExpected);
        List<String> expected = IOUtils.readLines(stream);

        List<String> actual = new LinkedList<>();
        Scanner scanner = new Scanner(actualString);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(!line.isBlank()) {
                actual.add(line);
            }
        }

        boolean same = true;
        int index = 0;
        for(String expectedString : expected) {
            if(! expectedString.isBlank()) {
                if(index < actual.size()) {
                    String actualLine = actual.get(index);
                    if (!actualLine.trim().equals(expectedString.trim())) {
                        System.err.println("different  " + expectedString.trim());
                        System.err.println("to  " + actualLine.trim());
                        same = false;
                    }
                }
                    else {
                        System.err.println("missing  " + expectedString.trim());
                        same = false;
                    }
                index++;
                }

        }

            if (! same) {
            System.err.println("actual:");
            System.err.println("-----------------------------------------------");
            System.err.println(toString(actual));
            System.err.println("-----------------------------------------------");
            System.err.println("expected:");
            System.err.println("-----------------------------------------------");
            System.err.println(toString(expected));
            System.err.println("-----------------------------------------------");
            throw new RuntimeException("not the same");
        }
    }

    private String toString(List<String> list) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter result = new PrintWriter(stringWriter);
        for(String line : list) {
            result.println(line);
        }

        return stringWriter.toString();
    }

}
