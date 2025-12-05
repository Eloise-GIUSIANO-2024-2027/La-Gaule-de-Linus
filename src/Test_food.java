import consomable.Alliments;
import consomable.Potion;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Test_food {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Alliments> historiqueRepas = new ArrayList<>();
        Potion potionMagique = null;

        System.out.println("/-/ Bienvenue dans La Gaule de Linus /-/\n");

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
            System.out.println("\n----------------------------------------");
            System.out.println("MENU PRINCIPAL");
            System.out.println("----------------------------------------");
            System.out.println("1. Voir tous les aliments disponibles");
            System.out.println("2. Voir les aliments consommables par mon personnage");
            System.out.println("3. Manger un aliment");
            System.out.println("4. Gérer la potion magique");
            System.out.println("5. Quitter");
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
                    potionMagique = gererPotion(scanner, potionMagique);
                    break;

                case 5:
                    continuer = false;
                    System.out.println("\nMerci d'avoir joué ! Par Toutatis !");
                    break;

                default:
                    System.out.println("\nChoix invalide ! Veuillez choisir entre 1 et 5.");
            }
        }

        scanner.close();
    }

    private static void mangerAliment(Scanner scanner, Alliments.TypePersonnage personnage,
                                     List<Alliments> historique) {
        System.out.println("\n/-/ Choisissez un aliment à manger /-/");

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

    private static Potion gererPotion(Scanner scanner, Potion potionActuelle) {
        boolean retourMenu = false;

        while (!retourMenu) {
            System.out.println("\n========================================");
            System.out.println("GESTION DE LA POTION MAGIQUE");
            System.out.println("========================================");

            if (potionActuelle == null) {
                System.out.println("Vous n'avez pas encore de potion magique.");
                System.out.println("\n1. Créer une nouvelle potion magique");
                System.out.println("2. Voir la recette de la potion");
                System.out.println("3. Retour au menu principal");
                System.out.print("Votre choix : ");

                int choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        potionActuelle = new Potion();
                        System.out.println("\n🧪 Nouvelle marmite de potion magique créée !");
                        System.out.println("   Contient " + potionActuelle.getDosesRestantes() + " doses.");
                        break;
                    case 2:
                        Potion.afficherRecette();
                        break;
                    case 3:
                        retourMenu = true;
                        break;
                    default:
                        System.out.println("Choix invalide !");
                }
            } else {
                System.out.println("Doses restantes : " + potionActuelle.getDosesRestantes());
                System.out.println("Nourrissante : " + (potionActuelle.estNourrissante() ? "Oui" : "Non"));
                System.out.println("\n1. Ajouter un ingrédient spécial");
                System.out.println("2. Voir la composition de la potion");
                System.out.println("3. Boire la potion");
                System.out.println("4. Créer une nouvelle marmite");
                System.out.println("5. Voir la recette");
                System.out.println("6. Retour au menu principal");
                System.out.print("Votre choix : ");

                int choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        ajouterIngredientSpecial(scanner, potionActuelle);
                        break;
                    case 2:
                        potionActuelle.afficherIngredients();
                        break;
                    case 3:
                        boirePotion(scanner, potionActuelle);
                        break;
                    case 4:
                        System.out.println("\n⚠️  Êtes-vous sûr de vouloir créer une nouvelle marmite ?");
                        System.out.println("   Cela supprimera la potion actuelle ! (1=oui, 2=non)");
                        System.out.print("   Votre choix : ");
                        int confirmation = scanner.nextInt();
                        if (confirmation == 1) {
                            potionActuelle = new Potion();
                            System.out.println("\n🧪 Nouvelle marmite de potion magique créée !");
                        }
                        break;
                    case 5:
                        Potion.afficherRecette();
                        break;
                    case 6:
                        retourMenu = true;
                        break;
                    default:
                        System.out.println("Choix invalide !");
                }
            }
        }

        return potionActuelle;
    }

    private static void ajouterIngredientSpecial(Scanner scanner, Potion potion) {
        System.out.println("\n=== Ajouter un ingrédient spécial ===");
        System.out.println("1. Homard (rend la potion nourrissante)");
        System.out.println("2. Fraises (rend la potion nourrissante)");
        System.out.println("3. Jus de betterave à la place de l'huile de roche (nourrissant)");
        System.out.println("4. Lait de licorne à deux têtes (pouvoir de dédoublement)");
        System.out.println("5. Poils d'Idéfix (métamorphosis - lycanthrope)");
        System.out.println("6. Annuler");
        System.out.print("Votre choix : ");

        int choix = scanner.nextInt();

        switch (choix) {
            case 1:
                potion.ajouterHomard();
                break;
            case 2:
                potion.ajouterFraises();
                break;
            case 3:
                potion.remplacerParJusDeBetterave();
                break;
            case 4:
                potion.ajouterLaitDeLicorne();
                break;
            case 5:
                potion.ajouterPoilsIdefix();
                break;
            case 6:
                System.out.println("Annulé.");
                break;
            default:
                System.out.println("Choix invalide !");
        }
    }

    private static void boirePotion(Scanner scanner, Potion potion) {
        if (potion.getDosesRestantes() == 0) {
            System.out.println("\n⚠️  La marmite est vide ! Il n'y a plus de doses à boire.");
            return;
        }

        System.out.println("\n/-/ Boire la potion magique /-/");
        System.out.println("Doses disponibles : " + potion.getDosesRestantes());
        System.out.println("\n💡 Rappel :");
        System.out.println("   - 1 dose = effets temporaires");
        System.out.println("   - 10 doses (1 marmite) = effets PERMANENTS");
        System.out.println("   - 20 doses (2 marmites) = transformation en statue de granit !");
        System.out.print("\nCombien de doses voulez-vous boire ? ");

        int nombreDoses = scanner.nextInt();

        if (nombreDoses <= 0) {
            System.out.println("Annulé.");
            return;
        }

        if (nombreDoses > potion.getDosesRestantes()) {
            System.out.println("\n⚠️  Vous n'avez que " + potion.getDosesRestantes() + " dose(s) disponible(s) !");
            System.out.print("Voulez-vous boire toutes les doses restantes ? (1=oui, 2=non) : ");
            int confirmation = scanner.nextInt();
            if (confirmation == 1) {
                nombreDoses = potion.getDosesRestantes();
            } else {
                System.out.println("Annulé.");
                return;
            }
        }

        // Boire la potion
        List<Potion.Effet> effets = potion.boireDose(nombreDoses);

        if (!effets.isEmpty()) {
            System.out.println("\nDoses restantes dans la marmite : " + potion.getDosesRestantes());
        }
    }
}