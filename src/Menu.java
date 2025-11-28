import java.util.Scanner;

public class Menu {

    public static String getMenu(){
        return "Voici le Menu :" +
                "\n" + "1. Carte" +
                "\n" + "2. Renseignement" +
                "\n" + "3. Inventaire" +
                "\n" + "4. Activation du mode loup-garou (non actif)" +
                "\n" + "5. Quitter";
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
                    System.out.println("Affichage de la Carte...");
                    continuer = false;
                    break;
                case "2":
                    System.out.println("Accès aux Renseignements...");
                    continuer = false;
                    break;
                case "3":
                    System.out.println("Consultation de l'Inventaire...");
                    continuer = false;
                    break;
                case "4":
                    System.out.println("Le mode Loups Garou n'est pas encore disponible.");
                    continuer = false;
                    break;
                case "5":
                    System.out.println("La Gaule attendras avec patience votre retours");
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix non valide. Veuillez entrer un numéro entre 1 et 5");
                    break;
            }

            if (continuer) {
                System.out.print("\nAppuyez sur ENTRÉE pour continuer...");
                scanner.nextLine(); // Attend que l'utilisateur appuie sur Entrée
            }
        }
    }

}
