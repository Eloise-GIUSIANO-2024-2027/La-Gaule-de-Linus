import java.util.Scanner;

import Personnage.PNJStats;
import Personnage.CharacterType;

public class Menu {

    public static String getMenu(){
        return "Voici le Menu :" +
                "\n" + "1. Carte" +
                "\n" + "2. Renseignement" +
                "\n" + "3. Inventaire" +
                "\n" + "4. Mode chef de clan" +
                "\n" + "5. Activation du mode loup-garou (non actif)" +
                "\n" + "6. Quitter";
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

        while(continuer) { // Boucle pour que le menu se réaffiche

            System.out.println("\n" + getMenu());
            System.out.print("Entrez votre choix : ");

            String choix = scanner.nextLine().trim().toUpperCase();

            switch (choix) {
                case "1":
                    pause();
                    System.out.println("\n");
                    Carte Carte = new Carte();
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
                    System.out.println("Le mode Loups Garou n'est pas encore disponible.");
                    continuer = false;
                    break;
                case "6":
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
                scanner.nextLine(); // Attend que l'utilisateur appuie sur Entrée
            }
        }
    }

    // Sous-menu simple pour diriger un Chef de clan
    private static void entrerModeChefDeClan(Scanner scanner) {
        // Crée un lieu de démonstration et un chef
        Lieux lieuDemo = new Lieux("Village d'Astérix", 100, "Non", "Abraracourcix", 0, "Sanglier", "VillageGaulois");
        ChefDeClan chef = new ChefDeClan("Abraracourcix", "M", 50, lieuDemo);
        Druide druide = new Druide("Panoramix");

        boolean quitter = false;
        while (!quitter) {
            System.out.println("\n--- Mode Chef de Clan : " + chef.getNom() + " ---");
            System.out.println("1. Examiner le lieu");
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
                    chef.examinerLieu();
                    break;
                case "2":
                    System.out.print("Nom du personnage : ");
                    String nomPerso = scanner.nextLine().trim();
                    // valeurs par défaut pour type/role
                    chef.creerPersonnage(nomPerso, CharacterType.GAULOIS, null, "M", 30);
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
                    System.out.println("Choisissez l'index du personnage :");
                    for (int i = 0; i < chef.getSubordonnes().size(); i++) {
                        System.out.println(i + ") " + chef.getSubordonnes().get(i).getNom());
                    }
                    System.out.print("Index : ");
                    try {
                        int idx = Integer.parseInt(scanner.nextLine().trim());
                        if (idx >= 0 && idx < chef.getSubordonnes().size()) {
                            PNJStats cible = chef.getSubordonnes().get(idx);
                            System.out.print("Nombre de doses à faire boire : ");
                            int doses = Integer.parseInt(scanner.nextLine().trim());
                            chef.faireBoirePotion(chef.getPotionCourante(), cible, doses);
                        } else {
                            System.out.println("Index invalide.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide.");
                    }
                    break;
                case "7":
                    System.out.println("Transfert vers un lieu de démonstration (Champ de bataille)...");
                    Lieux champ = new Lieux("Champ d'Idéfix", 200, "Non", "", 0, "", "ChampsBataille");
                    System.out.println("Choisissez l'index du personnage à transférer :");
                    for (int i = 0; i < chef.getSubordonnes().size(); i++) {
                        System.out.println(i + ") " + chef.getSubordonnes().get(i).getNom());
                    }
                    System.out.print("Index : ");
                    try {
                        int idx = Integer.parseInt(scanner.nextLine().trim());
                        if (idx >= 0 && idx < chef.getSubordonnes().size()) {
                            PNJStats p = chef.getSubordonnes().get(idx);
                            chef.transfererPersonnageVers(p, champ);
                        } else {
                            System.out.println("Index invalide.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Entrée invalide.");
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

}
