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
    Lieux Helium = new Lieux("Helium", 150, "NAN","La Mere de Chantal Ladessous", 20, "Sanglier", "ChampsBataille");

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
                System.out.println(lieu);
            }
        }
    }

    public void renseignementLIEU() {
        System.out.println("Pour quels lieux voulez vous des renseignement ? ");
        System.out.println("\n");
        System.out.println("1.Beurk");
        System.out.println("2.Cielus");
        System.out.println("3.Babaorum");
        System.out.println("4.Aquarium");
        System.out.println("5.Laudanum");
        System.out.println("6.Tiramisum");
        System.out.println("7.Helium");

        boolean renseignementLIEU = true;

        while(renseignementLIEU) {
            System.out.print("Entrez votre choix : ");
            String choix = scanner.nextLine().trim().toUpperCase();

            switch (choix) {
                case "1":
                    pause();
                    System.out.println("\n");
                    System.out.println(toStringBeurk());
                    renseignementLIEU = false;
                    break;
                case "2":
                    System.out.println("\n");
                    System.out.println(toStringCielus());
                    renseignementLIEU = false;
                    break;
                case "3":
                    System.out.println("\n");
                    System.out.println(toStringBabaorum());
                    renseignementLIEU = false;
                    break;
                case "4":
                    System.out.println("\n");
                    System.out.println(toStringAquarium());
                    renseignementLIEU = false;
                    break;
                case "5":
                    System.out.println("\n");
                    System.out.println(toStringLaudanum());
                    renseignementLIEU = false;
                    break;
                case "6":
                    System.out.println("\n");
                    System.out.println(toStringTiramisum());
                    renseignementLIEU = false;
                    break;
                case "7":
                    System.out.println("\n");
                    System.out.println(toStringHelium());
                    renseignementLIEU = false;
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez entrer un numéro sois 1 sois2");
                    break;
            }
        }
    }

    public String toStringBeurk() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(Beurk.getNom()).append(" ---\n");
        sb.append("Chef de Lieu: ").append(Beurk.getChefDeLieux() != null ? Beurk.getChefDeLieux().getNom() : "Aucun").append("\n");
        sb.append("Nombre de personnages: ").append(Beurk.getNbPersonnages()).append("\n");
        sb.append("Les aliments sur le lieu: ").append(Beurk.getAliments()).append("\n");
        sb.append("Superficie : ").append(Beurk.getSuperficie()).append("\n");
        sb.append("Types de lieu: ").append(Beurk.getTypeLieux()).append("\n");
        if (Beurk.getConquerieOuBienNan().equals("NAN")) {
            sb.append("Le lieux n'est pas conquérie").append("\n");
        } else {
            sb.append("Le lieux est conquérie").append("\n");
        }
        return sb.toString();
    }

    public String toStringCielus() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(Cielus.getNom()).append(" ---\n");
        sb.append("Chef de Lieu: ").append(Cielus.getChefDeLieux() != null ? Cielus.getChefDeLieux().getNom() : "Aucun").append("\n");
        sb.append("Nombre de personnages: ").append(Cielus.getNbPersonnages()).append("\n");
        sb.append("Les aliments sur le lieu: ").append(Cielus.getAliments()).append("\n");
        sb.append("Superficie : ").append(Cielus.getSuperficie()).append("\n");
        sb.append("Types de lieu: ").append(Cielus.getTypeLieux()).append("\n");
        if (Cielus.getConquerieOuBienNan().equals("NAN")) {
            sb.append("Le lieux n'est pas conquérie").append("\n");
        } else {
            sb.append("Le lieux est conquérie").append("\n");
        }
        return sb.toString();
    }

    public String toStringBabaorum() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(Babaorum.getNom()).append(" ---\n");
        sb.append("Chef de Lieu: ").append(Babaorum.getChefDeLieux() != null ? Babaorum.getChefDeLieux().getNom() : "Aucun").append("\n");
        sb.append("Nombre de personnages: ").append(Babaorum.getNbPersonnages()).append("\n");
        sb.append("Les aliments sur le lieu: ").append(Babaorum.getAliments()).append("\n");
        sb.append("Superficie : ").append(Babaorum.getSuperficie()).append("\n");
        sb.append("Types de lieu: ").append(Babaorum.getTypeLieux()).append("\n");
        if (Babaorum.getConquerieOuBienNan().equals("NAN")) {
            sb.append("Le lieux n'est pas conquérie").append("\n");
        } else {
            sb.append("Le lieux est conquérie").append("\n");
        }
        return sb.toString();
    }

    public String toStringAquarium() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(Aquarium.getNom()).append(" ---\n");
        sb.append("Chef de Lieu: ").append(Aquarium.getChefDeLieux() != null ? Aquarium.getChefDeLieux().getNom() : "Aucun").append("\n");
        sb.append("Nombre de personnages: ").append(Aquarium.getNbPersonnages()).append("\n");
        sb.append("Les aliments sur le lieu: ").append(Aquarium.getAliments()).append("\n");
        sb.append("Superficie : ").append(Aquarium.getSuperficie()).append("\n");
        sb.append("Types de lieu: ").append(Aquarium.getTypeLieux()).append("\n");
        if (Aquarium.getConquerieOuBienNan().equals("NAN")) {
            sb.append("Le lieux n'est pas conquérie").append("\n");
        } else {
            sb.append("Le lieux est conquérie").append("\n");
        }
        return sb.toString();
    }

    public String toStringLaudanum() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(Laudanum.getNom()).append(" ---\n");
        sb.append("Chef de Lieu: ").append(Laudanum.getChefDeLieux() != null ? Laudanum.getChefDeLieux().getNom() : "Aucun").append("\n");
        sb.append("Nombre de personnages: ").append(Laudanum.getNbPersonnages()).append("\n");
        sb.append("Les aliments sur le lieu: ").append(Laudanum.getAliments()).append("\n");
        sb.append("Superficie : ").append(Laudanum.getSuperficie()).append("\n");
        sb.append("Types de lieu: ").append(Laudanum.getTypeLieux()).append("\n");
        if (Laudanum.getConquerieOuBienNan().equals("NAN")) {
            sb.append("Le lieux n'est pas conquérie").append("\n");
        } else {
            sb.append("Le lieux est conquérie").append("\n");
        }
        return sb.toString();
    }

    public String toStringTiramisum() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(Tiramisum.getNom()).append(" ---\n");
        sb.append("Chef de Lieu: ").append(Tiramisum.getChefDeLieux() != null ? Tiramisum.getChefDeLieux().getNom() : "Aucun").append("\n");
        sb.append("Nombre de personnages: ").append(Tiramisum.getNbPersonnages()).append("\n");
        sb.append("Les aliments sur le lieu: ").append(Tiramisum.getAliments()).append("\n");
        sb.append("Superficie : ").append(Tiramisum.getSuperficie()).append("\n");
        sb.append("Types de lieu: ").append(Tiramisum.getTypeLieux()).append("\n");
        if (Tiramisum.getConquerieOuBienNan().equals("NAN")) {
            sb.append("Le lieux n'est pas conquérie").append("\n");
        } else {
            sb.append("Le lieux est conquérie").append("\n");
        }
        return sb.toString();
    }

    public String toStringHelium() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(Helium.getNom()).append(" ---\n");
        sb.append("Chef de Lieu: ").append(Helium.getChefDeLieux() != null ? Helium.getChefDeLieux().getNom() : "Aucun").append("\n");
        sb.append("Nombre de personnages: ").append(Helium.getNbPersonnages()).append("\n");
        sb.append("Les aliments sur le lieu: ").append(Helium.getAliments()).append("\n");
        sb.append("Superficie : ").append(Helium.getSuperficie()).append("\n");
        sb.append("Types de lieu: ").append(Helium.getTypeLieux()).append("\n");
        if (Helium.getConquerieOuBienNan().equals("NAN")) {
            sb.append("Le lieux n'est pas conquérie").append("\n");
        } else {
            sb.append("Le lieux est conquérie").append("\n");
        }
        return sb.toString();
    }
}
