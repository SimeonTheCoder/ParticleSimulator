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

    private static String[] syntaxTable;
    private static String[] originalTable;

    private static File langFile;

    public static void init() {
        List<String> langList = new ArrayList<>();

        langAddress = CFGPropertyReader.readString(new File(LANG_TABLE_ADDRESS + "lang.cfg"), new String[]{"current"}, 0);

        langFile = new File(LANG_TABLE_ADDRESS + langAddress + ".lang");

        File langTable = new File(LANG_TABLE_ADDRESS + "lang_table.txt");

        Scanner scanner = null;

        try {
            scanner = new Scanner(langTable);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            langList.add(line.split(" -> ")[0] + "$" + line.split(" -> ")[1]);
        }

        syntaxTable = new String[langList.size()];
        originalTable = new String[langList.size()];

        for (int i = 0; i < langList.size(); i++) {
            syntaxTable[i] = langList.get(i).split("\\$")[0];
            originalTable[i] = langList.get(i).split("\\$")[1];
        }
    }

    public static String translate(String str) {
        System.out.println(str);

        int desiredId = 0;

        for (int i = 0; i < originalTable.length; i++) {
            String original = originalTable[i];

            if (original.equals(str)) {
                desiredId = i;

                break;
            }
        }

        String converted = CFGPropertyReader.readString(langFile, syntaxTable, desiredId);

        return converted;
    }
}
