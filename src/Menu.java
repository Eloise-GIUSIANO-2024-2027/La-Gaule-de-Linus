import java.util.List;
import java.util.Scanner;

public class Menu {

    public static String getMenu(){
        return "Voici le Menu :" +
                "\n" + "1. Carte" +
                "\n" + "2. Renseignement" +
                "\n" + "3. Inventaire" +
                "\n" + "4. Mode chef de clan" +
                "\n" + "5. Theatre d'envahissement" +
                "\n" + "6. Activation du mode loup-garou (non actif)" +
                "\n" + "7. Quitter";
    }

    private static void pause() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La pause a été interrompue : " + e.getMessage());
        }
    }


    public static void lancerJeu() {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while(continuer) {

            System.out.println("\n" + getMenu());
            System.out.print("Entrez votre choix : ");

            String choix = scanner.nextLine().trim().toUpperCase();

            switch (choix) {
                case "1":
                    pause();
                    System.out.println("\n");
                    Carte maCarte = new Carte();
//                    continuer = false;
                    break;
                case "2":
                    System.out.println("\n");
                    System.out.println("Accès aux Renseignements...");
                    continuer = false;
                    break;
                case "3":
                    System.out.println("\n");
                    System.out.println("Consultation de l'Inventaire...");
                    continuer = false;
                    break;
                case "4":
                    System.out.println("\n");
                    System.out.println("Vous sentez un pouvoir grandir en vous... le pouvoir de diriger.");
                    entrerModeChefDeClan(scanner);
                    break;
                case "5":
                    System.out.println("\n");
                    Theatre theaaaatre = new Theatre();
//                    continuer = false;
                    break;
                case "6":
                    System.out.println("\n");
                    System.out.println("Le mode Loups Garou n'est pas encore disponible.");
                    continuer = false;
                    break;
                case "7":
                    System.out.println("\n");
                    System.out.println("La Gaule attendras avec patience votre retours");
                    continuer = false;
                    break;
                default:
                    System.out.println("\n");
                    System.out.println("Choix non validius. Veuillez entrer un numérius entre 1 et 5");
                    break;
            }

            if (continuer) {
                System.out.print("\nAppuyez sur ENTRÉE pour continuer...");
                scanner.nextLine();
            }
        }
    }

    private static void entrerModeChefDeClan(Scanner scanner) {

        System.out.println("\n--- Choix du Chef de Clan ---");
        System.out.println("1. " + Theatre.chefFacile.getNom() + " (Théâtre I)");
        System.out.println("2. " + Theatre.chefMoyen.getNom() + " (Théâtre II)");
        System.out.println("3. " + Theatre.chefDifficile.getNom() + " (Théâtre III)");
        System.out.print("Entrez le numéro du chef à incarner : ");

        String choixChef = scanner.nextLine().trim();
        ChefDeClan chef;
        Theatre theatreActuel;

        switch (choixChef) {
            case "1":
                chef = Theatre.chefFacile;
                theatreActuel = Theatre.theatreFacile;
                break;
            case "2":
                chef = Theatre.chefMoyen;
                theatreActuel = Theatre.theatreMoyen;
                break;
            case "3":
                chef = Theatre.chefDifficile;
                theatreActuel = Theatre.theatreDifficile;
                break;
            default:
                System.out.println("Choix invalide. Retour au menu principal.");
                return;
        }

        if (!theatreActuel.getLieuxContenus().isEmpty()) {
            chef.setLieu(theatreActuel.getLieuxContenus().get(0));
        }

        Druide druide = new Druide("Panoramix");

        boolean quitter = false;
        while (!quitter) {
            System.out.println("\n--- Mode Chef de Clan : " + chef.getNom() + " (Théâtre " + theatreActuel.getNom() + ") ---");            System.out.println("1. Examiner le lieu");
            System.out.println("2. Créer un personnage dans le lieu");
            System.out.println("3. Soigner les personnages du lieu");
            System.out.println("4. Nourrir les personnages du lieu");
            System.out.println("5. Demander au druide de préparer une potion");
            System.out.println("6. Faire boire une potion à un personnage");
            System.out.println("7. Transférer un personnage vers un autre lieu (ex : champ de bataille)");
            System.out.println("8. Ajouter un aliment à l'inventaire du chef");
            System.out.println("9. Quitter le mode chef de clan");
            System.out.print("Choix : ");
            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    gererLieuxDuChef(scanner, chef, theatreActuel);
                    break;
                case "2":
                    System.out.println("\n--- Création d'un nouveau personnage ---");

                    System.out.println("\nDans quel lieu voulez-vous créer ce personnage ?");
                    List<Lieux> lieuxDuTheatre = theatreActuel.getLieuxContenus();

                    if (lieuxDuTheatre.isEmpty()) {
                        System.out.println("Ce théâtre ne possède aucun lieu.");
                        break;
                    }

                    for (int i = 0; i < lieuxDuTheatre.size(); i++) {
                        System.out.println((i + 1) + ". " + lieuxDuTheatre.get(i).getNom() + " (" + lieuxDuTheatre.get(i).getTypeLieux() + ")");
                    }

                    System.out.print("Choix du lieu : ");
                    String choixLieuCreation = scanner.nextLine().trim();

                    Lieux lieuDeCreation = null;
                    try {
                        int idxLieu = Integer.parseInt(choixLieuCreation) - 1;
                        if (idxLieu >= 0 && idxLieu < lieuxDuTheatre.size()) {
                            lieuDeCreation = lieuxDuTheatre.get(idxLieu);
                        } else {
                            System.out.println("Choix invalide.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide.");
                        break;
                    }

                    Lieux lieuOriginal = chef.getLieu();
                    chef.setLieu(lieuDeCreation);

                    System.out.print("Nom du personnage : ");
                    String nomPerso = scanner.nextLine().trim();

                    System.out.println("\nType de personnage :");
                    System.out.println("1. Gaulois");
                    System.out.println("2. Romain");
                    System.out.println("3. Créature (Lycanthrope)");
                    System.out.print("Choix : ");
                    String choixType = scanner.nextLine().trim();

                    CharacterType type = CharacterType.GAULOIS;
                    switch (choixType) {
                        case "1":
                            type = CharacterType.GAULOIS;
                            break;
                        case "2":
                            type = CharacterType.ROMAIN;
                            break;
                        case "3":
                            type = CharacterType.CREATURE;
                            break;
                        default:
                            System.out.println("Choix invalide, Gaulois par défaut.");
                    }

                    CharacterRole role = null;
                    if (type == CharacterType.GAULOIS) {
                        System.out.println("\nRôle du Gaulois :");
                        System.out.println("1. Marchand");
                        System.out.println("2. Aubergiste");
                        System.out.println("3. Forgeron");
                        System.out.println("4. Druide");
                        System.out.println("5. Aléatoire");
                        System.out.print("Choix : ");
                        String choixRole = scanner.nextLine().trim();
                        switch (choixRole) {
                            case "1":
                                role = CharacterRole.MARCHAND;
                                break;
                            case "2":
                                role = CharacterRole.AUBERGISTE;
                                break;
                            case "3":
                                role = CharacterRole.FORGERON;
                                break;
                            case "4":
                                role = CharacterRole.DRUIDE;
                                break;
                            case "5":
                            default:
                                role = null;
                        }
                    } else if (type == CharacterType.ROMAIN) {
                        System.out.println("\nRôle du Romain :");
                        System.out.println("1. Légionnaire");
                        System.out.println("2. Préfet");
                        System.out.println("3. Général");
                        System.out.println("4. Aléatoire");
                        System.out.print("Choix : ");
                        String choixRole = scanner.nextLine().trim();
                        switch (choixRole) {
                            case "1":
                                role = CharacterRole.LEGIONNAIRE;
                                break;
                            case "2":
                                role = CharacterRole.PREFET;
                                break;
                            case "3":
                                role = CharacterRole.GENERAL;
                                break;
                            case "4":
                            default:
                                role = null;
                        }
                    } else if (type == CharacterType.CREATURE) {
                        role = CharacterRole.LYCANTHROPE;
                    }

                    System.out.print("\nSexe (M/F, appuyez sur Entrée pour aléatoire) : ");
                    String sexe = scanner.nextLine().trim().toUpperCase();
                    if (sexe.isEmpty()) {
                        sexe = null;
                    } else if (!sexe.equals("M") && !sexe.equals("F")) {
                        System.out.println("Sexe invalide, sera assigné aléatoirement.");
                        sexe = null;
                    }

                    System.out.print("Âge (appuyez sur Entrée pour aléatoire) : ");
                    String ageStr = scanner.nextLine().trim();
                    int age = 0;
                    if (!ageStr.isEmpty()) {
                        try {
                            age = Integer.parseInt(ageStr);
                            if (age < 18 || age > 60) {
                                System.out.println("Âge invalide (18-60), sera assigné aléatoirement.");
                                age = 0;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Âge invalide, sera assigné aléatoirement.");
                            age = 0;
                        }
                    }

                    PNJStats persoTest = new PNJStats();
                    persoTest.setType(type);
                    persoTest.setRole(role != null ? role : (type == CharacterType.CREATURE ? CharacterRole.LYCANTHROPE : null));

                    if (!lieuDeCreation.peutContenirPersonnage(persoTest)) {
                        System.out.println("\n La création est annulé");
                        System.out.println( lieuDeCreation.getMessageRefus(persoTest));
                        System.out.println("Veuillez choisir un autre type de personnage ou un autre lieu.");

                        chef.setLieu(lieuOriginal);
                        break;
                    }

                    PNJStats nouveauPerso = chef.creerPersonnage(nomPerso, type, role, sexe, age);
                    System.out.println("Personnage créé avec succès dans " + lieuDeCreation.getNom() + " !");

                    chef.setLieu(lieuOriginal);
                    break;
                case "3":
                    chef.soignerPersonnages();
                    break;
                case "4":
                    chef.nourrirPersonnages();
                    break;
                case "5":
                    chef.demanderPotionAuDruide(druide);
                    break;
                case "6":
                    if (chef.getSubordonnes().isEmpty()) {
                        System.out.println("Aucun personnage disponible.");
                        break;
                    }

                    System.out.println("\n--- Faire boire une potion ---");
                    System.out.println("Personnages disponibles :");
                    for (int i = 0; i < chef.getSubordonnes().size(); i++) {
                        PNJStats p = chef.getSubordonnes().get(i);
                        System.out.println((i + 1) + ") " + p.getNom() + " (Santé: " + p.getIndicateurSante() + ")");
                    }

                    System.out.print("\nEntrez le nom du personnage : ");
                    String nomRecherche = scanner.nextLine().trim();

                    PNJStats cible = null;
                    for (PNJStats p : chef.getSubordonnes()) {
                        if (p.getNom().equalsIgnoreCase(nomRecherche)) {
                            cible = p;
                            break;
                        }
                    }

                    if (cible != null) {
                        System.out.print("Nombre de doses à faire boire : ");
                        try {
                            int doses = Integer.parseInt(scanner.nextLine().trim());
                            chef.faireBoirePotion(chef.getPotionCourante(), cible, doses);
                        } catch (NumberFormatException e) {
                            System.out.println("Nombre de doses invalide.");
                        }
                    } else {
                        System.out.println("Aucun personnage trouvé avec le nom '" + nomRecherche + "'.");
                    }
                    break;
                case "7":
                    System.out.println("\n--- Transfert de personnage ---");

                    if (chef.getSubordonnes().isEmpty()) {
                        System.out.println("Aucun personnage à transférer.");
                        break;
                    }

                    System.out.println("\nChoisissez le personnage à transférer :");
                    for (int i = 0; i < chef.getSubordonnes().size(); i++) {
                        PNJStats perso = chef.getSubordonnes().get(i);
                        System.out.println(i + ") " + perso.getNom() + " - " + perso.getType() + " (" + perso.getRole() + ")");
                    }
                    System.out.print("Lequel voulez-vous transférer : ");

                    int idxPerso = -1;
                    try {
                        idxPerso = Integer.parseInt(scanner.nextLine().trim());
                        if (idxPerso < 0 || idxPerso >= chef.getSubordonnes().size()) {
                            System.out.println("Index invalide.");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide.");
                        break;
                    }

                    PNJStats personnageATransferer = chef.getSubordonnes().get(idxPerso);

                    System.out.println("\nChoisissez le lieu de destination :");
                    System.out.println("1. Beurk");
                    System.out.println("2. Cielus");
                    System.out.println("3. Babaorum");
                    System.out.println("4. Aquarium");
                    System.out.println("5. Laudanum");
                    System.out.println("6. Tiramisum");
                    System.out.println("7. Helium");
                    System.out.print("Choix : ");

                    String choixLieu = scanner.nextLine().trim();
                    Lieux destination = null;

                    switch (choixLieu) {
                        case "1":
                            destination = Carte.Beurk;
                            break;
                        case "2":
                            destination = Carte.Cielus;
                            break;
                        case "3":
                            destination = Carte.Babaorum;
                            break;
                        case "4":
                            destination = Carte.Aquarium;
                            break;
                        case "5":
                            destination = Carte.Laudanum;
                            break;
                        case "6":
                            destination = Carte.Tiramisum;
                            break;
                        case "7":
                            destination = Carte.Helium;
                            break;
                        default:
                            System.out.println("Choix invalide.");
                            break;
                    }

                    if (destination != null) {
                        chef.transfererPersonnageVers(personnageATransferer, destination);
                        System.out.println(personnageATransferer.getNom() + " a été transféré");
                    }
                    break;
                case "8":
                    System.out.println("Ajouter un aliment (choisissez le numéro) :");
                    consomable.Alliments.afficherTousLesAliments();
                    System.out.print("Nom (ex: SANGLIER) : ");
                    String alimStr = scanner.nextLine().trim().toUpperCase();
                    try {
                        consomable.Alliments.TypeAliment t = consomable.Alliments.TypeAliment.valueOf(alimStr);
                        chef.ajouterAliment(new consomable.Alliments(t));
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Type d'aliment inconnu.");
                    }
                    break;
                case "9":
                    quitter = true;
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void gererLieuxDuChef(Scanner scanner, ChefDeClan chef, Theatre theatre) {
        List<Lieux> lieux = theatre.getLieuxContenus();

        if (lieux.isEmpty()) {
            System.out.println("\nCe théâtre ne possède aucun lieu à examiner.");
            return;
        }

        System.out.println("\n--- Choix du Lieu à Examiner ---");
        for (int i = 0; i < lieux.size(); i++) {
            System.out.println((i + 1) + ". " + lieux.get(i).getNom() + " (" + lieux.get(i).getTypeLieux() + ")");
        }

        System.out.print("Entrez le numéro du lieu (ou Entrée pour annuler) : ");
        String choixLieuStr = scanner.nextLine().trim();

        if (choixLieuStr.isEmpty()) {
            return;
        }

        try {
            int choixLieuIdx = Integer.parseInt(choixLieuStr) - 1;

            if (choixLieuIdx >= 0 && choixLieuIdx < lieux.size()) {
                Lieux lieuCible = lieux.get(choixLieuIdx);
                chef.setLieu(lieuCible);
                chef.examinerLieu();

            } else {
                System.out.println("Numéro de lieu invalide.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide.");
        }
    }

}
