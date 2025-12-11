import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Bataille {

    int index = 1;
    private List<PNJStats> PersonnageEnAction = new ArrayList<>();
    private static List<PNJStats> PersonnageDeCielus = new ArrayList<>();
    private static List<PNJStats> PersonnageDeBabaorum = new ArrayList<>();
    private static List<PNJStats> PersonnageDeAquarium = new ArrayList<>();
    private static List<PNJStats> PersonnageDeLaudanum = new ArrayList<>();
    private static List<PNJStats> PersonnageDeTiramisum = new ArrayList<>();
    private static List<PNJStats> PersonnageDeHelium = new ArrayList<>();

    private static final Random RANDOM = new Random();

    private static String genererNom(CharacterType type, String sexe) {
        String[] nomsGauloisH = {"Ato", "Belo", "Camo", "Dumu", "Epo", "Gabo"};
        String[] nomsGauloisF = {"Eira", "Lita", "Bana", "Celta", "Dova"};
        String[] nomsRomainsH = {"Caius", "Marcus", "Titus", "Lucius", "Nero"};
        String[] nomsRomainsF = {"Julia", "Livia", "Octavia", "Vesta"};

        return switch (type) {
            case GAULOIS -> sexe.equals("M") ? nomsGauloisH[RANDOM.nextInt(nomsGauloisH.length)] : nomsGauloisF[RANDOM.nextInt(nomsGauloisF.length)];
            case ROMAIN -> sexe.equals("M") ? nomsRomainsH[RANDOM.nextInt(nomsRomainsH.length)] : nomsRomainsF[RANDOM.nextInt(nomsRomainsF.length)];
            case CREATURE -> "Créature-" + (RANDOM.nextInt(100) + 1);
        };
    }

    private static String genererSexe() {
        return RANDOM.nextBoolean() ? "M" : "F";
    }

    private static PNJStats creerPNJ(CharacterType type, CharacterRole role, int baseForce, int baseEndurance) {
        String sexe = genererSexe();
        String nom = genererNom(type, sexe);
        double taille = 1.65 + (0.30 * RANDOM.nextDouble());
        int age = 18 + RANDOM.nextInt(43);

        PNJStats pnj = new PNJStats(
                nom, sexe, taille, age,
                baseForce + RANDOM.nextInt(10),
                baseEndurance + RANDOM.nextInt(10),
                100, 100, 50, 0,
                type, role
        );

        CharacterRoleAssigner.assignRole(pnj, type, role);
        return pnj;
    }

    public static void initialiserPersonnages() {
        PersonnageDeCielus.clear();
        PersonnageDeTiramisum.clear();
        PersonnageDeBabaorum.clear();
        PersonnageDeLaudanum.clear();
        PersonnageDeAquarium.clear();
        PersonnageDeHelium.clear();

        // Cielus
        PersonnageDeCielus.add(creerPNJ(CharacterType.GAULOIS, CharacterRole.MARCHAND, 10, 10));
        PersonnageDeCielus.add(creerPNJ(CharacterType.GAULOIS, CharacterRole.FORGERON, 10, 10));
        PersonnageDeCielus.add(creerPNJ(CharacterType.GAULOIS, CharacterRole.AUBERGISTE, 10, 10));

        // Tiramisum
        PersonnageDeTiramisum.add(creerPNJ(CharacterType.CREATURE, CharacterRole.LYCANTHROPE, 10, 10));
        PersonnageDeTiramisum.add(creerPNJ(CharacterType.CREATURE, CharacterRole.LYCANTHROPE, 10, 10));

        // Babaorum
        PersonnageDeBabaorum.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.LEGIONNAIRE, 10, 10));
        PersonnageDeBabaorum.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.LEGIONNAIRE, 10, 10));
        PersonnageDeBabaorum.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.PREFET, 10, 10));

        // Laudanum
        PersonnageDeLaudanum.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.LEGIONNAIRE, 10, 10));
        PersonnageDeLaudanum.add(creerPNJ(CharacterType.GAULOIS, CharacterRole.FORGERON, 10, 10));
        PersonnageDeLaudanum.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.GENERAL, 10, 10));

        // Aquarium
        PersonnageDeAquarium.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.LEGIONNAIRE, 10, 10));
        PersonnageDeAquarium.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.LEGIONNAIRE, 10, 10));
        PersonnageDeAquarium.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.PREFET, 10, 10));

        // Helium
        PersonnageDeHelium.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.GENERAL, 10, 10));
        PersonnageDeHelium.add(creerPNJ(CharacterType.CREATURE, CharacterRole.LYCANTHROPE, 10, 10));
        PersonnageDeHelium.add(creerPNJ(CharacterType.ROMAIN, CharacterRole.PREFET, 10, 10));
    }

    public Bataille() {
        initialiserPersonnages();

        System.out.println("Vous avez donc décider de vous battre ! ");
        System.out.println("Quels théatre voulez vous tentez de conquérir?");
        System.out.println("1. Théâtre I (Facile) - Cielus & Tiramisum");
        System.out.println("2. Théâtre II (Moyen) - Laudanum & Babaorum");
        System.out.println("3. Théâtre III (Difficile) - Aquarium & Helium");

        Scanner scanner = new Scanner(System.in);
        boolean continuerbataille = true;

        while(continuerbataille) {
            System.out.print("Entrez votre choix : ");
            String choix = scanner.nextLine().trim().toUpperCase();
            switch (choix) {
                case "1":
                    System.out.println("\n");
                    System.out.println("Très bon choix. Et bonne chance a vous");
                    BatailleFacile(scanner);
                    continuerbataille = false;
                    break;
                case "2":
                    System.out.println("\n");
                    System.out.println("Le choix du milieu ! Bonne chance a vous");
                    BatailleMoyen(scanner);
                    continuerbataille = false;
                    break;
                case "3":
                    System.out.println("\n");
                    System.out.println("Vous choisissez donc la difficulté... Bonne chance vous en aurez besoins....");
                    BatailleDifficile(scanner);
                    continuerbataille = false;
                    break;
                default:
                    System.out.println("\n");
                    System.out.println("Choix non validius. Veuillez entrer un numérius entre 1 et 3");
                    break;
            }

            if (continuerbataille) {
                System.out.print("\nAppuyez sur ENTRÉE pour continuer...");
                scanner.nextLine();
            }
        }
    }

    public void BatailleFacile(Scanner scanner) {
        if (Carte.Cielus.getConquerieOuBienNan().equals("OUI")) {
            System.out.println("désolée mais vous avez déja conquis Cielus !");
            return;
        }

        if (Carte.Tiramisum.getConquerieOuBienNan().equals("OUI")) {
            System.out.println("désolée mais vous avez déja conquis Tiramisum !");
            return;
        }

        System.out.println("Que la Bataille commence !");
        System.out.println("Le premier lieux sur votre chemin est ....");
        System.out.println("CIELUS !");
        System.out.println("Une ville Gauloise peu peuplée");
        System.out.println("QUE LE COMBAT COMMENCE !");

        System.out.println("\nLes troupes de quel théatre voulez vous utiliser ?");
        System.out.println("0. Théâtre 0 (Départ) - " + Theatre.personnagesTheatre0.size() + " personnage(s)");
        System.out.println("1. Théâtre I - " + Theatre.personnagesTheatre1.size() + " personnage(s)");
        System.out.println("2. Théâtre II - " + Theatre.personnagesTheatre2.size() + " personnage(s)");
        System.out.println("3. Théâtre III - " + Theatre.personnagesTheatre3.size() + " personnage(s)");

        System.out.print("Votre choix : ");
        String choixTheatre = scanner.nextLine().trim();

        try {
            int choix = Integer.parseInt(choixTheatre);

            if (choix < 0 || choix > 3) {
                System.out.println("Choix invalide ! Veuillez choisir entre 0 et 3.");
                return;
            }

            List<PNJStats> personnagesTheatre = Theatre.theatreDepart.getPersonnagesTheatre(choix);

            if (personnagesTheatre.isEmpty()) {
                System.out.println("\nAucun personnage disponible dans ce théâtre !");
                System.out.println("Créez des personnages d'abord via le mode Chef de clan.");
                return;
            }

            PersonnageEnAction.addAll(personnagesTheatre);

            System.out.println("\n" + PersonnageEnAction.size() + " personnage(s) mobilisé(s) pour la bataille :");
            for (PNJStats perso : PersonnageEnAction) {
                System.out.println("  - " + perso.getNom() + " (" + perso.getType() + ", " + perso.getRole() +
                        ", Force: " + perso.getForce() + ", Endurance: " + perso.getEndurance() + ")");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide !");
            return;
        }

        initialiserPersonnages();

        boolean PasGagner = true;
        int tour = 1;

        while (PasGagner) {
            System.out.println("\nTOUR " + tour );

            if (PersonnageDeCielus.isEmpty()) {
                System.out.println("\n*** VICTOIRE ! ***");
                System.out.println("Tous les défenseurs de Cielus ont été vaincus !");
                System.out.println("Vous avez conquis Cielus !");
                PasGagner = false;
                break;
            }

            if (PersonnageEnAction.isEmpty()) {
                System.out.println("\n*** DÉFAITE ! ***");
                System.out.println("Toutes vos troupes ont été vaincues !");
                System.out.println("Cielus reste aux mains de l'ennemi...");
                PasGagner = false;
                break;
            }

            int indexCielus = RANDOM.nextInt(PersonnageDeCielus.size());
            int indexJoueur = RANDOM.nextInt(PersonnageEnAction.size());

            PNJStats combattantCielus = PersonnageDeCielus.get(indexCielus);
            PNJStats combattantJoueur = PersonnageEnAction.get(indexJoueur);

            System.out.println("\nCombat entre :");
            System.out.println("  [CIELUS] " + combattantCielus.getNom() +
                    " (Force: " + combattantCielus.getForce() +
                    ", Endurance: " + combattantCielus.getEndurance() + ")");
            System.out.println("       VS");
            System.out.println("  [VOS TROUPES] " + combattantJoueur.getNom() +
                    " (Force: " + combattantJoueur.getForce() +
                    ", Endurance: " + combattantJoueur.getEndurance() + ")");

            int puissanceCielus = combattantCielus.getForce() + combattantCielus.getEndurance();
            int puissanceJoueur = combattantJoueur.getForce() + combattantJoueur.getEndurance();

            System.out.println("\nPuissance totale : " + puissanceCielus + " vs " + puissanceJoueur);

            if (puissanceJoueur > puissanceCielus) {
                System.out.println("\n>>> " + combattantJoueur.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantCielus.getNom() + " a été vaincu et éliminé !");
                PersonnageDeCielus.remove(indexCielus);
            } else if (puissanceCielus > puissanceJoueur) {
                System.out.println("\n>>> " + combattantCielus.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantJoueur.getNom() + " a été vaincu et éliminé !");
                PersonnageEnAction.remove(indexJoueur);
            } else {
                System.out.println("\n>>> Match nul ! Les deux combattants sont épuisés !");
                System.out.println(">>> Les deux sont éliminés du combat !");
                PersonnageDeCielus.remove(indexCielus);
                PersonnageEnAction.remove(indexJoueur);
            }

            System.out.println("\n--- Statut des troupes ---");
            System.out.println("Défenseurs de Cielus restants : " + PersonnageDeCielus.size());
            System.out.println("Vos troupes restantes : " + PersonnageEnAction.size());

            tour++;

            System.out.print("\nAppuyez sur ENTRÉE pour continuer le combat...");
            scanner.nextLine();
        }

        if (PersonnageDeCielus.isEmpty()) {
            Carte.Cielus.setConquerieOuBienNan("OUI");
            System.out.println("\nCielus est maintenant sous votre contrôle !");
            System.out.println("Le théâtre I est partiellement conquis !");
        }

        System.out.println("Maintenant il vous faut conquérir le derniers lieu");

        boolean PasGagneeeeer = true;
        tour = 1;

        while (PasGagneeeeer) {
            System.out.println("\nTOUR " + tour );

            if (PersonnageDeTiramisum.isEmpty()) {
                System.out.println("\n*** VICTOIRE ! ***");
                System.out.println("Tous les défenseurs de Tiramisum ont été vaincus !");
                System.out.println("Vous avez conquis tiramisum !");
                PasGagneeeeer = false;
                break;
            }

            if (PersonnageEnAction.isEmpty()) {
                System.out.println("\n***DÉFAITE ! ***");
                System.out.println("Toutes vos troupes ont été vaincues !");
                System.out.println("Cielus reste aux mains de l'ennemi...");
                PasGagneeeeer = false;
                break;
            }

            int indexTiramisum = RANDOM.nextInt(PersonnageDeTiramisum.size());
            int indexJoueur = RANDOM.nextInt(PersonnageEnAction.size());

            PNJStats combattantCielus = PersonnageDeTiramisum.get(indexTiramisum);
            PNJStats combattantJoueur = PersonnageEnAction.get(indexJoueur);

            System.out.println("\nCombat entre :");
            System.out.println("  [TIRAMISUM] " + combattantCielus.getNom() +
                    " (Force: " + combattantCielus.getForce() +
                    ", Endurance: " + combattantCielus.getEndurance() + ")");
            System.out.println("       VS");
            System.out.println("  [VOS TROUPES] " + combattantJoueur.getNom() +
                    " (Force: " + combattantJoueur.getForce() +
                    ", Endurance: " + combattantJoueur.getEndurance() + ")");

            int puissanceTiramisum = combattantCielus.getForce() + combattantCielus.getEndurance();
            int puissanceJoueur = combattantJoueur.getForce() + combattantJoueur.getEndurance();

            System.out.println("\nPuissance totale : " + puissanceTiramisum + " vs " + puissanceJoueur);

            if (puissanceJoueur > puissanceTiramisum) {
                System.out.println("\n>>> " + combattantJoueur.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantCielus.getNom() + " a été vaincu et éliminé !");
                PersonnageDeTiramisum.remove(indexTiramisum);
            } else if (puissanceTiramisum > puissanceJoueur) {
                System.out.println("\n>>> " + combattantCielus.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantJoueur.getNom() + " a été vaincu et éliminé !");
                PersonnageEnAction.remove(indexJoueur);
            } else {
                System.out.println("\n>>> Match nul ! Les deux combattants sont épuisés !");
                System.out.println(">>> Les deux sont éliminés du combat !");
                PersonnageDeTiramisum.remove(indexTiramisum);
                PersonnageEnAction.remove(indexJoueur);
            }

            System.out.println("\n--- Statut des troupes ---");
            System.out.println("Défenseurs de Tiramisum restants : " + PersonnageDeTiramisum.size());
            System.out.println("Vos troupes restantes : " + PersonnageEnAction.size());

            tour++;

            System.out.print("\nAppuyez sur ENTRÉE pour continuer le combat...");
            scanner.nextLine();
        }

        if (PersonnageDeTiramisum.isEmpty()) {
            Carte.Tiramisum.setConquerieOuBienNan("OUI");
            System.out.println("\nTiramisum est maintenant sous votre contrôle !");
            System.out.println("Le théâtre I est conquis !");
            Theatre.theatreFacile.setPossederOuPas(true);
        }

    }

    public List<PNJStats> getPersonnageEnAction() {
        return PersonnageEnAction;
    }

    public void BatailleMoyen(Scanner scanner) {
        if (Carte.Laudanum.getConquerieOuBienNan().equals("OUI") && Carte.Babaorum.getConquerieOuBienNan().equals("OUI")) {
            System.out.println("désolée mais vous avez déjà conquis ce théâtre !");
            return;
        }

        System.out.println("Que la Bataille commence !");
        System.out.println("Le premier lieux sur votre chemin est ....");
        System.out.println("LAUDANUM !");
        System.out.println("Une bourgade Gallo-Romaine");
        System.out.println("QUE LE COMBAT COMMENCE !");

        System.out.println("\nLes troupes de quel théatre voulez vous utiliser ?");
        System.out.println("0. Théâtre 0 (Départ) - " + Theatre.personnagesTheatre0.size() + " personnage(s)");
        System.out.println("1. Théâtre I - " + Theatre.personnagesTheatre1.size() + " personnage(s)");
        System.out.println("2. Théâtre II - " + Theatre.personnagesTheatre2.size() + " personnage(s)");
        System.out.println("3. Théâtre III - " + Theatre.personnagesTheatre3.size() + " personnage(s)");

        System.out.print("Votre choix : ");
        String choixTheatre = scanner.nextLine().trim();

        try {
            int choix = Integer.parseInt(choixTheatre);

            if (choix < 0 || choix > 3) {
                System.out.println("Choix invalide ! Veuillez choisir entre 0 et 3.");
                return;
            }

            List<PNJStats> personnagesTheatre = Theatre.theatreDepart.getPersonnagesTheatre(choix);

            if (personnagesTheatre.isEmpty()) {
                System.out.println("\nAucun personnage disponible dans ce théâtre !");
                System.out.println("Créez des personnages d'abord via le mode Chef de clan.");
                return;
            }

            PersonnageEnAction.addAll(personnagesTheatre);

            System.out.println("\n" + PersonnageEnAction.size() + " personnage(s) mobilisé(s) pour la bataille :");
            for (PNJStats perso : PersonnageEnAction) {
                System.out.println("  - " + perso.getNom() + " (" + perso.getType() + ", " + perso.getRole() +
                        ", Force: " + perso.getForce() + ", Endurance: " + perso.getEndurance() + ")");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide !");
            return;
        }

        initialiserPersonnages();

        boolean PasGagner = true;
        int tour = 1;

        while (PasGagner) {
            System.out.println("\nTOUR " + tour);

            if (PersonnageDeLaudanum.isEmpty()) {
                System.out.println("\n*** VICTOIRE ! ***");
                System.out.println("Tous les défenseurs de Laudanum ont été vaincus !");
                System.out.println("Vous avez conquis Laudanum !");
                PasGagner = false;
                break;
            }

            if (PersonnageEnAction.isEmpty()) {
                System.out.println("\n*** DÉFAITE ! ***");
                System.out.println("Toutes vos troupes ont été vaincues !");
                System.out.println("Laudanum reste aux mains de l'ennemi...");
                PasGagner = false;
                break;
            }

            int indexLaudanum = RANDOM.nextInt(PersonnageDeLaudanum.size());
            int indexJoueur = RANDOM.nextInt(PersonnageEnAction.size());

            PNJStats combattantLaudanum = PersonnageDeLaudanum.get(indexLaudanum);
            PNJStats combattantJoueur = PersonnageEnAction.get(indexJoueur);

            System.out.println("\nCombat entre :");
            System.out.println("  [LAUDANUM] " + combattantLaudanum.getNom() +
                    " (Force: " + combattantLaudanum.getForce() +
                    ", Endurance: " + combattantLaudanum.getEndurance() + ")");
            System.out.println("       VS");
            System.out.println("  [VOS TROUPES] " + combattantJoueur.getNom() +
                    " (Force: " + combattantJoueur.getForce() +
                    ", Endurance: " + combattantJoueur.getEndurance() + ")");

            int puissanceLaudanum = combattantLaudanum.getForce() + combattantLaudanum.getEndurance();
            int puissanceJoueur = combattantJoueur.getForce() + combattantJoueur.getEndurance();

            System.out.println("\nPuissance totale : " + puissanceLaudanum + " vs " + puissanceJoueur);

            if (puissanceJoueur > puissanceLaudanum) {
                System.out.println("\n>>> " + combattantJoueur.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantLaudanum.getNom() + " a été vaincu et éliminé !");
                PersonnageDeLaudanum.remove(indexLaudanum);
            } else if (puissanceLaudanum > puissanceJoueur) {
                System.out.println("\n>>> " + combattantLaudanum.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantJoueur.getNom() + " a été vaincu et éliminé !");
                PersonnageEnAction.remove(indexJoueur);
            } else {
                System.out.println("\n>>> Match nul ! Les deux combattants sont épuisés !");
                System.out.println(">>> Les deux sont éliminés du combat !");
                PersonnageDeLaudanum.remove(indexLaudanum);
                PersonnageEnAction.remove(indexJoueur);
            }

            System.out.println("\n--- Statut des troupes ---");
            System.out.println("Défenseurs de Laudanum restants : " + PersonnageDeLaudanum.size());
            System.out.println("Vos troupes restantes : " + PersonnageEnAction.size());

            tour++;
            System.out.print("\nAppuyez sur ENTRÉE pour continuer le combat...");
            scanner.nextLine();
        }

        if (PersonnageDeLaudanum.isEmpty()) {
            Carte.Laudanum.setConquerieOuBienNan("OUI");
            System.out.println("\nLaudanum est maintenant sous votre contrôle !");
            System.out.println("Le théâtre II est partiellement conquis !");
        }

        if (PersonnageEnAction.isEmpty()) {
            return;
        }

        System.out.println("\nMaintenant il vous faut conquérir le dernier lieu : BABAORUM !");

        boolean PasGagner2 = true;
        tour = 1;

        while (PasGagner2) {
            System.out.println("\nTOUR " + tour);

            if (PersonnageDeBabaorum.isEmpty()) {
                System.out.println("\n*** VICTOIRE ! ***");
                System.out.println("Tous les défenseurs de Babaorum ont été vaincus !");
                System.out.println("Vous avez conquis Babaorum !");
                PasGagner2 = false;
                break;
            }

            if (PersonnageEnAction.isEmpty()) {
                System.out.println("\n*** DÉFAITE ! ***");
                System.out.println("Toutes vos troupes ont été vaincues !");
                System.out.println("Babaorum reste aux mains de l'ennemi...");
                PasGagner2 = false;
                break;
            }

            int indexBabaorum = RANDOM.nextInt(PersonnageDeBabaorum.size());
            int indexJoueur = RANDOM.nextInt(PersonnageEnAction.size());

            PNJStats combattantBabaorum = PersonnageDeBabaorum.get(indexBabaorum);
            PNJStats combattantJoueur = PersonnageEnAction.get(indexJoueur);

            System.out.println("\nCombat entre :");
            System.out.println("  [BABAORUM] " + combattantBabaorum.getNom() +
                    " (Force: " + combattantBabaorum.getForce() +
                    ", Endurance: " + combattantBabaorum.getEndurance() + ")");
            System.out.println("       VS");
            System.out.println("  [VOS TROUPES] " + combattantJoueur.getNom() +
                    " (Force: " + combattantJoueur.getForce() +
                    ", Endurance: " + combattantJoueur.getEndurance() + ")");

            int puissanceBabaorum = combattantBabaorum.getForce() + combattantBabaorum.getEndurance();
            int puissanceJoueur = combattantJoueur.getForce() + combattantJoueur.getEndurance();

            System.out.println("\nPuissance totale : " + puissanceBabaorum + " vs " + puissanceJoueur);

            if (puissanceJoueur > puissanceBabaorum) {
                System.out.println("\n>>> " + combattantJoueur.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantBabaorum.getNom() + " a été vaincu et éliminé !");
                PersonnageDeBabaorum.remove(indexBabaorum);
            } else if (puissanceBabaorum > puissanceJoueur) {
                System.out.println("\n>>> " + combattantBabaorum.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantJoueur.getNom() + " a été vaincu et éliminé !");
                PersonnageEnAction.remove(indexJoueur);
            } else {
                System.out.println("\n>>> Match nul ! Les deux combattants sont épuisés !");
                System.out.println(">>> Les deux sont éliminés du combat !");
                PersonnageDeBabaorum.remove(indexBabaorum);
                PersonnageEnAction.remove(indexJoueur);
            }

            System.out.println("\n--- Statut des troupes ---");
            System.out.println("Défenseurs de Babaorum restants : " + PersonnageDeBabaorum.size());
            System.out.println("Vos troupes restantes : " + PersonnageEnAction.size());

            tour++;
            System.out.print("\nAppuyez sur ENTRÉE pour continuer le combat...");
            scanner.nextLine();
        }

        if (PersonnageDeBabaorum.isEmpty()) {
            Carte.Babaorum.setConquerieOuBienNan("OUI");
            System.out.println("\nBabaorum est maintenant sous votre contrôle !");
            System.out.println("Le théâtre II est conquis !");
            Theatre.theatreMoyen.setPossederOuPas(true);
        }
    }

    public void BatailleDifficile(Scanner scanner) {
        if (Carte.Aquarium.getConquerieOuBienNan().equals("OUI") && Carte.Helium.getConquerieOuBienNan().equals("OUI")) {
            System.out.println("désolée mais vous avez déjà conquis ce théâtre !");
            return;
        }

        System.out.println("Que la Bataille commence !");
        System.out.println("Le premier lieux sur votre chemin est ....");
        System.out.println("AQUARIUM !");
        System.out.println("Une ville Romaine fortifiée");
        System.out.println("QUE LE COMBAT COMMENCE !");

        System.out.println("\nLes troupes de quel théatre voulez vous utiliser ?");
        System.out.println("0. Théâtre 0 (Départ) - " + Theatre.personnagesTheatre0.size() + " personnage(s)");
        System.out.println("1. Théâtre I - " + Theatre.personnagesTheatre1.size() + " personnage(s)");
        System.out.println("2. Théâtre II - " + Theatre.personnagesTheatre2.size() + " personnage(s)");
        System.out.println("3. Théâtre III - " + Theatre.personnagesTheatre3.size() + " personnage(s)");

        System.out.print("Votre choix : ");
        String choixTheatre = scanner.nextLine().trim();

        try {
            int choix = Integer.parseInt(choixTheatre);

            if (choix < 0 || choix > 3) {
                System.out.println("Choix invalide ! Veuillez choisir entre 0 et 3.");
                return;
            }

            List<PNJStats> personnagesTheatre = Theatre.theatreDepart.getPersonnagesTheatre(choix);

            if (personnagesTheatre.isEmpty()) {
                System.out.println("\nAucun personnage disponible dans ce théâtre !");
                System.out.println("Créez des personnages d'abord via le mode Chef de clan.");
                return;
            }

            PersonnageEnAction.addAll(personnagesTheatre);

            System.out.println("\n" + PersonnageEnAction.size() + " personnage(s) mobilisé(s) pour la bataille :");
            for (PNJStats perso : PersonnageEnAction) {
                System.out.println("  - " + perso.getNom() + " (" + perso.getType() + ", " + perso.getRole() +
                        ", Force: " + perso.getForce() + ", Endurance: " + perso.getEndurance() + ")");
            }

        } catch (NumberFormatException e) {
            System.out.println("Entrée invalide !");
            return;
        }

        initialiserPersonnages();

        boolean PasGagner = true;
        int tour = 1;

        while (PasGagner) {
            System.out.println("\nTOUR " + tour);

            if (PersonnageDeAquarium.isEmpty()) {
                System.out.println("\n*** VICTOIRE ! ***");
                System.out.println("Tous les défenseurs de Aquarium ont été vaincus !");
                System.out.println("Vous avez conquis Aquarium !");
                PasGagner = false;
                break;
            }

            if (PersonnageEnAction.isEmpty()) {
                System.out.println("\n*** DÉFAITE ! ***");
                System.out.println("Toutes vos troupes ont été vaincues !");
                System.out.println("Aquarium reste aux mains de l'ennemi...");
                PasGagner = false;
                break;
            }

            int indexAquarium = RANDOM.nextInt(PersonnageDeAquarium.size());
            int indexJoueur = RANDOM.nextInt(PersonnageEnAction.size());

            PNJStats combattantAquarium = PersonnageDeAquarium.get(indexAquarium);
            PNJStats combattantJoueur = PersonnageEnAction.get(indexJoueur);

            System.out.println("\nCombat entre :");
            System.out.println("  [AQUARIUM] " + combattantAquarium.getNom() +
                    " (Force: " + combattantAquarium.getForce() +
                    ", Endurance: " + combattantAquarium.getEndurance() + ")");
            System.out.println("       VS");
            System.out.println("  [VOS TROUPES] " + combattantJoueur.getNom() +
                    " (Force: " + combattantJoueur.getForce() +
                    ", Endurance: " + combattantJoueur.getEndurance() + ")");

            int puissanceAquarium = combattantAquarium.getForce() + combattantAquarium.getEndurance();
            int puissanceJoueur = combattantJoueur.getForce() + combattantJoueur.getEndurance();

            System.out.println("\nPuissance totale : " + puissanceAquarium + " vs " + puissanceJoueur);

            if (puissanceJoueur > puissanceAquarium) {
                System.out.println("\n>>> " + combattantJoueur.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantAquarium.getNom() + " a été vaincu et éliminé !");
                PersonnageDeAquarium.remove(indexAquarium);
            } else if (puissanceAquarium > puissanceJoueur) {
                System.out.println("\n>>> " + combattantAquarium.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantJoueur.getNom() + " a été vaincu et éliminé !");
                PersonnageEnAction.remove(indexJoueur);
            } else {
                System.out.println("\n>>> Match nul ! Les deux combattants sont épuisés !");
                System.out.println(">>> Les deux sont éliminés du combat !");
                PersonnageDeAquarium.remove(indexAquarium);
                PersonnageEnAction.remove(indexJoueur);
            }

            System.out.println("\n--- Statut des troupes ---");
            System.out.println("Défenseurs de Aquarium restants : " + PersonnageDeAquarium.size());
            System.out.println("Vos troupes restantes : " + PersonnageEnAction.size());

            tour++;
            System.out.print("\nAppuyez sur ENTRÉE pour continuer le combat...");
            scanner.nextLine();
        }

        if (PersonnageDeAquarium.isEmpty()) {
            Carte.Aquarium.setConquerieOuBienNan("OUI");
            System.out.println("\nAquarium est maintenant sous votre contrôle !");
            System.out.println("Le théâtre III est partiellement conquis !");
        }

        if (PersonnageEnAction.isEmpty()) {
            return;
        }

        System.out.println("\nMaintenant il vous faut conquérir le dernier lieu : HELIUM !");
        System.out.println("Le champ de bataille ultime !");

        boolean PasGagner2 = true;
        tour = 1;

        while (PasGagner2) {
            System.out.println("\nTOUR " + tour);

            if (PersonnageDeHelium.isEmpty()) {
                System.out.println("\n*** VICTOIRE ! ***");
                System.out.println("Tous les défenseurs de Helium ont été vaincus !");
                System.out.println("Vous avez conquis Helium !");
                PasGagner2 = false;
                break;
            }

            if (PersonnageEnAction.isEmpty()) {
                System.out.println("\n*** DÉFAITE ! ***");
                System.out.println("Toutes vos troupes ont été vaincues !");
                System.out.println("Helium reste aux mains de l'ennemi...");
                PasGagner2 = false;
                break;
            }

            int indexHelium = RANDOM.nextInt(PersonnageDeHelium.size());
            int indexJoueur = RANDOM.nextInt(PersonnageEnAction.size());

            PNJStats combattantHelium = PersonnageDeHelium.get(indexHelium);
            PNJStats combattantJoueur = PersonnageEnAction.get(indexJoueur);

            System.out.println("\nCombat entre :");
            System.out.println("  [HELIUM] " + combattantHelium.getNom() +
                    " (Force: " + combattantHelium.getForce() +
                    ", Endurance: " + combattantHelium.getEndurance() + ")");
            System.out.println("       VS");
            System.out.println("  [VOS TROUPES] " + combattantJoueur.getNom() +
                    " (Force: " + combattantJoueur.getForce() +
                    ", Endurance: " + combattantJoueur.getEndurance() + ")");

            int puissanceHelium = combattantHelium.getForce() + combattantHelium.getEndurance();
            int puissanceJoueur = combattantJoueur.getForce() + combattantJoueur.getEndurance();

            System.out.println("\nPuissance totale : " + puissanceHelium + " vs " + puissanceJoueur);

            if (puissanceJoueur > puissanceHelium) {
                System.out.println("\n>>> " + combattantJoueur.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantHelium.getNom() + " a été vaincu et éliminé !");
                PersonnageDeHelium.remove(indexHelium);
            } else if (puissanceHelium > puissanceJoueur) {
                System.out.println("\n>>> " + combattantHelium.getNom() + " remporte le combat !");
                System.out.println(">>> " + combattantJoueur.getNom() + " a été vaincu et éliminé !");
                PersonnageEnAction.remove(indexJoueur);
            } else {
                System.out.println("\n>>> Match nul ! Les deux combattants sont épuisés !");
                System.out.println(">>> Les deux sont éliminés du combat !");
                PersonnageDeHelium.remove(indexHelium);
                PersonnageEnAction.remove(indexJoueur);
            }

            System.out.println("\n--- Statut des troupes ---");
            System.out.println("Défenseurs de Helium restants : " + PersonnageDeHelium.size());
            System.out.println("Vos troupes restantes : " + PersonnageEnAction.size());

            tour++;
            System.out.print("\nAppuyez sur ENTRÉE pour continuer le combat...");
            scanner.nextLine();
        }

        if (PersonnageDeHelium.isEmpty()) {
            Carte.Helium.setConquerieOuBienNan("OUI");
            System.out.println("\nHelium est maintenant sous votre contrôle !");
            System.out.println("Le théâtre III est conquis !");
            System.out.println("\n*** FÉLICITATIONS ! VOUS AVEZ CONQUIS TOUTE LA GAULE ! ***");
            Theatre.theatreDifficile.setPossederOuPas(true);
        }
    }
}