import java.util.Scanner;

public class Menu {

    public static String getMenu(){
        return "Voici le Menu :" +
                "\n" + "1. Carte" +
                "\n" + "2. Renseignement" +
                "\n" + "3. Inventaire" +
                "\n" + "4. Visiter un druide (créer des potions)" +
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
        Inventaire inventaire = new Inventaire(); // Créer une instance d'inventaire pour le jeu
        Carte carte = null;

        while(continuer) { // Boucle pour que le menu se réaffiche

            System.out.println("\n" + getMenu());
            System.out.print("Entrez votre choix : ");

            String choix = scanner.nextLine().trim().toUpperCase();

            switch (choix) {
                case "1":
                    pause();
                    System.out.println("\n");
                    carte = new Carte(inventaire);
//                    continuer = false;
                    break;
                case "2":
                    System.out.println("\n");
                    System.out.println("Accès aux Renseignements...");
                    continuer = false;
                    break;
                case "3":
                    System.out.println("\n");
                    inventaire.gererInventaire();
//                    continuer = false;
                    break;
                case "4":
                    System.out.println("\n");
                    if (carte == null) {
                        System.out.println("❌ Vous devez d'abord accéder à la carte (option 1) !");
                    } else {
                        visiterDruide(scanner, carte, inventaire);
                    }
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
                    System.out.println("Choix non valide. Veuillez entrer un numéro entre 1 et 6");
                    break;
            }

            if (continuer) {
                System.out.print("\nAppuyez sur ENTRÉE pour continuer...");
                scanner.nextLine(); // Attend que l'utilisateur appuie sur Entrée
            }
        }
    }

    private static void visiterDruide(Scanner scanner, Carte carte, Inventaire inventaire) {
        carte.afficherLieuxAvecDruides();
        System.out.println();
        System.out.print("Choisissez un lieu à visiter (numéro du lieu dans la liste totale) : ");

        try {
            int choix = Integer.parseInt(scanner.nextLine().trim());
            if (choix >= 1 && choix <= carte.getTousLesLieux().size()) {
                carte.visiterLieu(choix - 1);
            } else {
                System.out.println("❌ Numéro de lieu invalide !");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Veuillez entrer un numéro valide !");
        }
    }

}
