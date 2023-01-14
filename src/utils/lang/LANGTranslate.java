package utils.lang;

import utils.cfg.CFGPropertyReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LANGTranslate {
    private static String langAddress = "en.lang";
    private static final String LANG_TABLE_ADDRESS = "ParticleSimulation/config/lang/";

    public static void init() {
        langAddress = CFGPropertyReader.readString(new File(LANG_TABLE_ADDRESS + "lang.cfg"), new String[]{"current"}, 0);
    }

    public static String translate(String str) {
        File langTable = new File(LANG_TABLE_ADDRESS + "lang_table.txt");

        Scanner scanner = null;

        try {
            scanner = new Scanner(langTable);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String referenceName = "";

        List<String> stringList = new ArrayList<>();

        int desiredId = 0;
        int id = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String original = line.split(" -> ")[1];

            stringList.add(line.split(" -> ")[0]);

            if (original.equals(str)) {
                referenceName = line.split(" -> ")[0];

                desiredId = id;

                break;
            }

            id++;
        }

        String[] syntaxTable = new String[stringList.size()];

        for (int i = 0; i < stringList.size(); i++) {
            syntaxTable[i] = stringList.get(i);
        }

        File file = new File(LANG_TABLE_ADDRESS + langAddress + ".lang");

        String converted = CFGPropertyReader.readString(file, syntaxTable, desiredId);

//        if (converted == null) {
        return converted;
//        } else {
//            return "NULL";
//        }
    }
}
