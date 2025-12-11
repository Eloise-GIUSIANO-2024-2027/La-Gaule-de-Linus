import java.util.Random;

public class ListeNom {

    private static final String[] NOMS_GAULOIS = {
        "Astérionx", "Quoicoubélix", "Paforamix"
    };

    private static final String[] NOMS_ROMAINS = {
        "Julius", "Marcus", "Caius", "Lucius", "Titus", "Brutus",
        "Claudius", "Flavius", "Augustus", "Maximus", "Quintus", "Decimus"
    };

    private static final Random random = new Random();

    public static String genererNom(CharacterType type) {
        if (type == CharacterType.ROMAIN) {
            return NOMS_ROMAINS[random.nextInt(NOMS_ROMAINS.length)] + random.nextInt(100);
        } else {
            return NOMS_GAULOIS[random.nextInt(NOMS_GAULOIS.length)] + random.nextInt(100);
        }
    }

    public static String[] getNomsGaulois() {
        return NOMS_GAULOIS.clone();
    }

    public static String[] getNomsRomains() {
        return NOMS_ROMAINS.clone();
    }
}

