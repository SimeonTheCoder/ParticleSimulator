package utils.cfg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CFGPropertyReader {
    public static int readInt(File file, String[] syntax, int desired) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith(syntax[desired])) {
                int propertyValue = Integer.parseInt(line.split(" = ")[1]);

                return propertyValue;
            }
        }

        return -1;
    }

    public static double readDouble(File file, String[] syntax, int desired) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith(syntax[desired])) {
                double propertyValue = Double.parseDouble(line.split(" = ")[1]);

                return propertyValue;
            }
        }

        return -1;
    }

    public static String readString(File file, String[] syntax, int desired) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith(syntax[desired])) {
                String propertyValue = line.split(" = ")[1];

                return propertyValue;
            }
        }

        return null;
    }

    public static boolean readBool(File file, String[] syntax, int desired) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith(syntax[desired])) {
                boolean propertyValue = Boolean.parseBoolean(line.split(" = ")[1]);

                return propertyValue;
            }
        }

        return false;
    }
}
