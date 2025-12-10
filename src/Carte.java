import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carte {

    public static Lieux Beurk = new Lieux("Beurk", 25,"OUI","La Mere A Thomas", 8, "Sanglier", "VillageGaulois");
    public static Lieux Cielus = new Lieux("Cielus", 28,"NAN" ,"La Mere A Thomas", 3, "Sanglier", "VillageGaulois");
    public static Lieux Babaorum = new Lieux("Babaorum", 12, "NAN" ,"La Mere A Dimitri", 4, "Sanglier", "CampRomain");
    public static Lieux Aquarium = new Lieux("Aquarium", 58, "NAN","Ma Mere", 9, "Sanglier", "VilleRomain");
    public static Lieux Laudanum = new Lieux("Laudanum", 28, "NAN", "La Mere De Anni", 6, "Sanglier", "BourgadeGalloRomaine");
    public static Lieux Tiramisum = new Lieux("Tiramisum", 9, "NAN","La Mere De Emmanuel Macron", 3, "Sanglier", "Enclos");
    public static Lieux Helium = new Lieux("Helium", 150, "NAN","La Mere de Chantal Ladessous", 20, "Sanglier", "ChampsBataille");

    public static Lieux getBeurk() {
        return Beurk;
    }

    public static Lieux getCielus() {
        return Cielus;
    }

    public static Lieux getBabaorum() {
        return Babaorum;
    }

    public static Lieux getAquarium() {
        return Aquarium;
    }

    public static Lieux getLaudanum() {
        return Laudanum;
    }

    public static Lieux getTiramisum() {
        return Tiramisum;
    }

    public static Lieux getHelium() {
        return Helium;
    }

    private List<Lieux> TousLesLieux = new ArrayList<>();
    boolean renseignement = true;
    Scanner scanner = new Scanner(System.in);

    private static void pause() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La pause a été interrompue : " + e.getMessage());
        }
    }

    public Carte() {
        TousLesLieux.add(Beurk);
        TousLesLieux.add(Cielus);
        TousLesLieux.add(Babaorum);
        TousLesLieux.add(Aquarium);
        TousLesLieux.add(Laudanum);
        TousLesLieux.add(Tiramisum);
        TousLesLieux.add(Helium);

        System.out.println("Et PAF! Voila la carte!");
        pause();
        System.out.println("Si tu dois aller quelque part, elle te guidera tu peut le croire");
        pause();
        System.out.println("\n");
        System.out.println("Voila les lieux que tu as conquis : ");
        pause();
        AfficherLieuxConquis();
        pause();
        System.out.println("\n");
        System.out.println("Voila les lieux que tu n'as pas conquis : ");
        AfficherLieuxNonConquis();
        System.out.println("Voulez vous des renseignements suplémentaires sur un lieux ?");
        System.out.println("Oui : 1 et Non : 2");
        while(renseignement) { // Boucle pour que le menu se réaffiche
            System.out.print("Entrez votre choix : ");
            String choix = scanner.nextLine().trim().toUpperCase();

            switch (choix) {
                case "1":
                    pause();
                    System.out.println("Les renseignements");
                    System.out.println("\n");
                    renseignement = false;
                    break;
                case "2":
                    System.out.println("\n");
                    System.out.println("Pas de renseignements alors ...");
                    renseignement = false;
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez entrer un numéro sois 1 sois2");
                    break;
            }
        }
    }

    public void AfficherLieuxConquis() {
        for (Lieux lieu : TousLesLieux) {
            if (lieu.getConquerieOuBienNan().equals("OUI")) {
                System.out.println(lieu.toString());
            }
        }
    }

    public void AfficherLieuxNonConquis() {
        for (Lieux lieu : TousLesLieux) {
            if (lieu.getConquerieOuBienNan().equals("NAN")) {
                System.out.println(lieu);            }
        }
    }

}