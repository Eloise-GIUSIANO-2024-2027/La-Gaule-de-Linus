import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Alliments> historiqueRepas = new ArrayList<>();

        System.out.println("=== Bienvenue dans La Gaule de Linus ===\n");

        // Choix du personnage
        System.out.println("Choisissez votre personnage :");
        System.out.println("1. Gaulois");
        System.out.println("2. Romain");
        System.out.print("Votre choix (1 ou 2) : ");

        int choixPersonnage = scanner.nextInt();
        Alliments.TypePersonnage personnage = (choixPersonnage == 2) ?
            Alliments.TypePersonnage.ROMAIN : Alliments.TypePersonnage.GAULOIS;

        System.out.println("\nVous êtes un " + personnage + " !\n");

        boolean continuer = true;

        while (continuer) {
            System.out.println("\n========================================");
            System.out.println("MENU - Que voulez-vous faire ?");
            System.out.println("========================================");
            System.out.println("1. Voir tous les aliments disponibles");
            System.out.println("2. Voir les aliments consommables par mon personnage");
            System.out.println("3. Manger un aliment");
            System.out.println("4. Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    Alliments.afficherTousLesAliments();
                    break;

                case 2:
                    Alliments.afficherAlimentsConsommables(personnage);
                    break;

                case 3:
                    mangerAliment(scanner, personnage, historiqueRepas);
                    break;

                case 4:
                    continuer = false;
                    System.out.println("\nMerci d'avoir joué ! Par Toutatis !");
                    break;

                default:
                    System.out.println("\nChoix invalide ! Veuillez choisir entre 1 et 4.");
            }
        }

        scanner.close();
    }

    private static void mangerAliment(Scanner scanner, Alliments.TypePersonnage personnage,
                                     List<Alliments> historique) {
        System.out.println("\n=== Choisissez un aliment à manger ===");

        Alliments.TypeAliment[] aliments = Alliments.TypeAliment.values();
        for (int i = 0; i < aliments.length; i++) {
            System.out.println((i + 1) + ". " + aliments[i].getNom());
        }
        System.out.print("\nVotre choix (1-" + aliments.length + ") : ");

        int choix = scanner.nextInt();

        if (choix < 1 || choix > aliments.length) {
            System.out.println("❌ Choix invalide !");
            return;
        }

        Alliments alimentChoisi = new Alliments(aliments[choix - 1]);

        System.out.println("\n--- Analyse de " + alimentChoisi.getNom() + " ---");

        // Vérifier si l'aliment est consommable par le personnage
        if (!alimentChoisi.estConsommablePar(personnage)) {
            System.out.println("⚠️  ATTENTION : Les " + personnage + " ne mangent normalement pas de "
                + alimentChoisi.getNom() + " !");
            System.out.println("   Mais vous pouvez quand même le manger si vous le souhaitez...");
        } else {
            System.out.println("✓ Cet aliment fait partie de votre régime alimentaire.");
        }

        // Vérifier si c'est mauvais pour la santé
        Alliments dernierAliment = historique.isEmpty() ? null : historique.get(historique.size() - 1);

        if (alimentChoisi.estMauvaisPourLaSante(dernierAliment)) {
            System.out.println("\n⚠️  DANGER POUR LA SANTÉ !");

            if (alimentChoisi.getAliment() == Alliments.TypeAliment.POISSON_PAS_FRAIS) {
                System.out.println("   Le poisson pas frais est dangereux pour la santé !");
            } else if (dernierAliment != null && alimentChoisi.getAliment().estVegetal()
                       && dernierAliment.getAliment().estVegetal()) {
                System.out.println("   Manger deux végétaux consécutifs est mauvais pour la santé !");
                System.out.println("   Dernier aliment : " + dernierAliment.getNom());
            }

            System.out.println("   Êtes-vous sûr de vouloir le manger ? (1=oui, 2=non)");
            System.out.print("   Votre choix : ");
            int confirmation = scanner.nextInt();

            if (confirmation != 1) {
                System.out.println("   Sage décision ! Vous n'avez pas mangé de " + alimentChoisi.getNom());
                return;
            }
        } else {
            System.out.println("✓ Pas de danger pour la santé.");
        }

        // Manger l'aliment
        historique.add(alimentChoisi);
        System.out.println("\n🍴 Vous avez mangé : " + alimentChoisi.getNom());
    }
}