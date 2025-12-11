import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carte {

    Lieux Beurk = new Lieux("Beurk", 25,"OUI","La Mere A Thomas", 8, "Sanglier", "VillageGaulois");
    Lieux Cielus = new Lieux("Cielus", 28,"NAN" ,"La Mere A Thomas", 3, "Sanglier", "VillageGaulois");
    Lieux Babaorum = new Lieux("Babaorum", 12, "NAN" ,"La Mere A Dimitri", 4, "Sanglier", "CampRomain");
    Lieux Aquarium = new Lieux("Aquarium", 58, "NAN","Ma Mere", 9, "Sanglier", "VilleRomain");
    Lieux Laudanum = new Lieux("Laudanum", 28, "NAN", "La Mere De Anni", 6, "Sanglier", "BourgadeGalloRomaine");
    Lieux Tiramisum = new Lieux("Tiramisum", 9, "NAN","La Mere De Emmanuel Macron", 3, "Sanglier", "Enclos");
    Lieux Hélium = new Lieux("Hélium", 150, "NAN","La Mere de Chantal Ladessous", 20, "Sanglier", "ChampsBataille");

    private List<Lieux> TousLesLieux = new ArrayList<>();
    private Inventaire inventaire;
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

    public Carte(Inventaire inventaire) {
        this.inventaire = inventaire;

        // Ajouter des druides dans les villages gaulois
        Personnage.Druide panoramix = new Personnage.Druide("Panoramix", 65, 100, "Potions de force surhumaine");
        Beurk.setDruide(panoramix);

        Personnage.Druide diagnostix = new Personnage.Druide("Diagnostix", 58, 95, "Potions de guérison");
        Cielus.setDruide(diagnostix);

        TousLesLieux.add(Beurk);
        TousLesLieux.add(Cielus);
        TousLesLieux.add(Babaorum);
        TousLesLieux.add(Aquarium);
        TousLesLieux.add(Laudanum);
        TousLesLieux.add(Tiramisum);
        TousLesLieux.add(Hélium);

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


        System.out.println("\nVoulez vous des renseignements suplémentaires sur un lieux ?");
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
                System.out.println(lieu);
            }
        }
    }

    public void AfficherLieuxNonConquis() {
        for (Lieux lieu : TousLesLieux) {
            if (lieu.getConquerieOuBienNan().equals("NAN")) {
                System.out.println(lieu);            }
        }
    }

    public void afficherLieuxAvecDruides() {
        System.out.println("\n🧙 LIEUX AVEC DES DRUIDES :");
        boolean aDruide = false;
        for (int i = 0; i < TousLesLieux.size(); i++) {
            Lieux lieu = TousLesLieux.get(i);
            if (lieu.aDruide()) {
                System.out.println((i + 1) + ". " + lieu.getNom() + " - Druide : " + lieu.getDruide().getNom());
                aDruide = true;
            }
        }
        if (!aDruide) {
            System.out.println("Aucun druide disponible pour le moment.");
        }
    }

    public void visiterLieu(int index) {
        if (index >= 0 && index < TousLesLieux.size()) {
            Lieux lieu = TousLesLieux.get(index);
            System.out.println("\n🏛️  Vous visitez : " + lieu.getNom());
            System.out.println("Type : " + lieu.getTypeLieux());
            System.out.println("Chef : " + lieu.getChefDeLieux());

            if (lieu.aDruide()) {
                Personnage.Druide druide = lieu.getDruide();
                druide.accueillir();
                inventaire.gererInventaireAvecDruide(lieu, druide);
            } else {
                System.out.println("\n❌ Aucun druide dans ce lieu.");
                System.out.println("💡 Retournez dans un village gaulois pour trouver un druide.");
            }
        }
    }

    public List<Lieux> getTousLesLieux() {
        return TousLesLieux;
    }

}