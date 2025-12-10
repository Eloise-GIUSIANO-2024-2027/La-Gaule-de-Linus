import consomable.Aliments;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private Random random = new Random();
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

        // Donner des récompenses de départ au joueur
        donnerRecompensesDepart();

        System.out.println("\n");
        System.out.println("Voila les lieux que tu as conquis : ");
        pause();
        AfficherLieuxConquis();
        pause();
        System.out.println("\n");
        System.out.println("Voila les lieux que tu n'as pas conquis : ");
        AfficherLieuxNonConquis();

        // Proposer de conquérir un lieu
        proposerConquete();

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

    /**
     * Donne des récompenses de départ au joueur
     */
    private void donnerRecompensesDepart() {
        System.out.println("\n🎁 === RÉCOMPENSES DE DÉPART === 🎁");
        System.out.println("Bienvenue brave guerrier ! Voici quelques provisions pour commencer votre aventure :");
        pause();

        // Aliments de départ
        inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.SANGLIER));
        inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.VIN));

        // Ingrédients de départ pour créer une potion
        System.out.println("\nVous recevez également des ingrédients pour fabriquer une potion magique :");
        inventaire.ajouterIngredient(Aliments.TypeAliment.GUI, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.CAROTTES, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.SEL, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.TREFLE_QUATRE_FEUILLES_FRAIS, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.POISSON_PASSABLEMENT_FRAIS, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.MIEL, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.HYDROMEL, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.INGREDIENT_SECRET, 2);

        System.out.println("\n✨ Vous avez assez d'ingrédients pour créer 2 potions magiques !");
        System.out.println("💡 Astuce : Allez dans l'inventaire (option 3 du menu) pour créer vos potions.\n");
        pause();
    }

    /**
     * Propose au joueur de conquérir un lieu
     */
    private void proposerConquete() {
        System.out.println("\n⚔️  === CONQUÊTE === ⚔️");
        System.out.println("Voulez-vous tenter de conquérir un nouveau lieu ?");
        System.out.print("Oui : 1 / Non : 2 : ");

        String choix = scanner.nextLine().trim();

        if (choix.equals("1")) {
            List<Lieux> lieuxNonConquis = new ArrayList<>();
            for (Lieux lieu : TousLesLieux) {
                if (lieu.getConquerieOuBienNan().equals("NAN")) {
                    lieuxNonConquis.add(lieu);
                }
            }

            if (lieuxNonConquis.isEmpty()) {
                System.out.println("\n🎉 Félicitations ! Vous avez déjà conquis tous les lieux !");
                return;
            }

            System.out.println("\nChoisissez un lieu à conquérir :");
            for (int i = 0; i < lieuxNonConquis.size(); i++) {
                System.out.println((i + 1) + ". " + lieuxNonConquis.get(i).getNom() +
                                   " (" + lieuxNonConquis.get(i).getTypeLieux() + ")");
            }

            System.out.print("\nVotre choix (1-" + lieuxNonConquis.size() + ") : ");
            try {
                int choixLieu = Integer.parseInt(scanner.nextLine().trim());
                if (choixLieu >= 1 && choixLieu <= lieuxNonConquis.size()) {
                    conquerirLieu(lieuxNonConquis.get(choixLieu - 1));
                } else {
                    System.out.println("❌ Choix invalide !");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Veuillez entrer un nombre !");
            }
        } else {
            System.out.println("Peut-être une prochaine fois...");
        }
    }

    /**
     * Conquiert un lieu et donne des récompenses
     */
    private void conquerirLieu(Lieux lieu) {
        System.out.println("\n⚔️  BATAILLE EN COURS... ⚔️");
        pause();
        pause();

        // Simulation de bataille (aléatoire pour l'instant)
        boolean victoire = random.nextInt(100) < 70; // 70% de chance de victoire

        if (victoire) {
            System.out.println("🎉 VICTOIRE ! Vous avez conquis " + lieu.getNom() + " !");
            lieu.setConquerieOuBienNan("OUI");

            // Donner des récompenses
            donnerRecompensesConquete(lieu);
        } else {
            System.out.println("💥 DÉFAITE ! Vous n'avez pas réussi à conquérir " + lieu.getNom() + "...");
            System.out.println("Mais vous trouvez quelques provisions abandonnées :");

            // Petite récompense même en cas de défaite
            int nbRecompenses = 1 + random.nextInt(2); // 1 ou 2 objets
            for (int i = 0; i < nbRecompenses; i++) {
                donnerAlimentAleatoire();
            }
        }
        pause();
    }

    /**
     * Donne des récompenses après avoir conquis un lieu
     */
    private void donnerRecompensesConquete(Lieux lieu) {
        System.out.println("\n🎁 === RÉCOMPENSES === 🎁");
        pause();

        // Ajouter l'aliment du lieu
        String alimentLieu = lieu.getAliments();
        if (alimentLieu != null && !alimentLieu.isEmpty()) {
            try {
                Aliments.TypeAliment type = Aliments.TypeAliment.valueOf(alimentLieu.toUpperCase().replace(" ", "_"));
                inventaire.ajouterAliment(new Aliments(type));
            } catch (IllegalArgumentException e) {
                // Si le nom ne correspond pas exactement, donner un sanglier par défaut
                inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.SANGLIER));
            }
        }

        // Récompenses selon le type de lieu
        String typeLieu = lieu.getTypeLieux();
        switch (typeLieu) {
            case "VillageGaulois":
                System.out.println("Les villageois vous offrent des provisions :");
                inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.POISSON_PASSABLEMENT_FRAIS));
                inventaire.ajouterIngredient(Aliments.TypeAliment.GUI, 2);
                inventaire.ajouterIngredient(Aliments.TypeAliment.MIEL, 1);
                break;

            case "CampRomain":
                System.out.println("Vous pillez le camp romain et trouvez :");
                inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.VIN));
                inventaire.ajouterIngredient(Aliments.TypeAliment.SEL, 2);
                inventaire.ajouterIngredient(Aliments.TypeAliment.HYDROMEL, 1);
                break;

            case "VilleRomain":
                System.out.println("La ville regorge de trésors :");
                inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.HOMARD));
                inventaire.ajouterIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 2);
                inventaire.ajouterIngredient(Aliments.TypeAliment.FRAISES, 1);
                break;

            case "BourgadeGalloRomaine":
                System.out.println("Les habitants partagent leurs récoltes :");
                inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.MIEL));
                inventaire.ajouterIngredient(Aliments.TypeAliment.CAROTTES, 2);
                inventaire.ajouterIngredient(Aliments.TypeAliment.TREFLE_QUATRE_FEUILLES_FRAIS, 1);
                break;

            case "Enclos":
                System.out.println("L'enclos contient des animaux et des plantes :");
                inventaire.ajouterIngredient(Aliments.TypeAliment.GUI, 1);
                inventaire.ajouterIngredient(Aliments.TypeAliment.CAROTTES, 1);
                inventaire.ajouterIngredient(Aliments.TypeAliment.TREFLE_QUATRE_FEUILLES_FRAIS, 2);
                break;

            case "ChampsBataille":
                System.out.println("🌟 Sur ce champ de bataille légendaire, vous trouvez des objets rares :");
                inventaire.ajouterIngredient(Aliments.TypeAliment.INGREDIENT_SECRET, 1);
                inventaire.ajouterIngredient(Aliments.TypeAliment.LAIT_DE_LICORNE, 1);
                inventaire.ajouterIngredient(Aliments.TypeAliment.POILS_IDEFIX, 1);
                System.out.println("✨ Des ingrédients magiques pour créer des potions extraordinaires !");
                break;

            default:
                donnerAlimentAleatoire();
        }

        System.out.println("\n💡 Consultez votre inventaire (option 3) pour voir vos nouvelles provisions !");
    }

    /**
     * Donne un aliment ou ingrédient aléatoire
     */
    private void donnerAlimentAleatoire() {
        Aliments.TypeAliment[] types = Aliments.TypeAliment.values();
        Aliments.TypeAliment typeAleatoire = types[random.nextInt(types.length)];

        // 50% de chance d'être un aliment ou un ingrédient
        if (random.nextBoolean()) {
            inventaire.ajouterAliment(new Aliments(typeAleatoire));
        } else {
            inventaire.ajouterIngredient(typeAleatoire, 1);
        }
    }

}