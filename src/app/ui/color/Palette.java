package app.ui.color;

import utils.cfg.CFGPropertyReader;

import java.awt.*;
import java.io.File;

public class Palette {
    public static Color primaryColor;
    public static Color secondaryColor;
    public static Color thirdColor;
    public static Color forthColor;

    private static final String PATH_THEME_FOLDER = "ParticleSimulation/config/themes/";

    private static final String[] syntax = {
            "foreground_color",
            "background_color"
    };

    public static void read() {
        String configFileName = PATH_THEME_FOLDER + "theme.cfg";

        String themeIndex = CFGPropertyReader.readString(new File(configFileName), new String[]{"theme"}, 0);

        String themeFileName = PATH_THEME_FOLDER + themeIndex + ".col";

        readColors(themeFileName);
    }

    public static void readColors(String filename) {
        String foregroundLine = CFGPropertyReader.readString(new File(filename), syntax, 0);

        int primR = Math.max(0, Math.min(255, Integer.parseInt(foregroundLine.split(" ")[0])));
        int primG = Math.max(0, Math.min(255, Integer.parseInt(foregroundLine.split(" ")[1])));
        int primB = Math.max(0, Math.min(255, Integer.parseInt(foregroundLine.split(" ")[2])));

        primaryColor = new Color(primR, primG, primB);

        String backgroundLine = CFGPropertyReader.readString(new File(filename), syntax, 1);

        int secR = Math.max(0, Math.min(255, Integer.parseInt(backgroundLine.split(" ")[0])));
        int secG = Math.max(0, Math.min(255, Integer.parseInt(backgroundLine.split(" ")[1])));
        int secB = Math.max(0, Math.min(255, Integer.parseInt(backgroundLine.split(" ")[2])));

        secondaryColor = new Color(secR, secG, secB);

        thirdColor = new Color(
                Math.max(0, Math.min(255, secR + 32)),
                Math.max(0, Math.min(255, secG + 32)),
                Math.max(0, Math.min(255, secB + 32))
        );

        forthColor = new Color(
                Math.max(0, Math.min(255, secR + 64)),
                Math.max(0, Math.min(255, secG + 64)),
                Math.max(0, Math.min(255, secB + 64))
        );
    }
}
