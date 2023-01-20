import app.ui.color.Palette;
import app.ui.hub.Hub;
import utils.lang.LANGTranslate;

public class Main {
    public static void main(String[] args) {
        LANGTranslate.init();
        Palette.read();

        Hub hub = new Hub();
    }
}
