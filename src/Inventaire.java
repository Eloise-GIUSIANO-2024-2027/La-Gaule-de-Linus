
import consomable.Aliments;
import consomable.Potion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Inventaire {

    private List<Potion> potions;
    private List<Aliments> aliments;
    private Map<Aliments.TypeAliment, Integer> ingredients;
    
    public Inventaire() {
        this.potions = new ArrayList<>();
        this.aliments = new ArrayList<>();
        this.ingredients = new HashMap<>();
    }

    public void ajouterPotion(Potion potion) {
        potions.add(potion);
        System.out.println("✓ Potion ajoutée à l'inventaire !");
    }

    public void ajouterAliment(Aliments aliment) {
        aliments.add(aliment);
        System.out.println("✓ " + aliment.getNom() + " ajouté à l'inventaire !");
    }

    public void ajouterIngredient(Aliments.TypeAliment ingredient, int quantite) {
        int quantiteActuelle = ingredients.getOrDefault(ingredient, 0);
        ingredients.put(ingredient, quantiteActuelle + quantite);
        System.out.println("✓ " + quantite + "x " + ingredient.getNom() + " ajouté(s) à l'inventaire !");
    }

    public boolean retirerIngredient(Aliments.TypeAliment ingredient, int quantite) {
        int quantiteActuelle = ingredients.getOrDefault(ingredient, 0);
        if (quantiteActuelle >= quantite) {
            if (quantiteActuelle == quantite) {
                ingredients.remove(ingredient);
            } else {
                ingredients.put(ingredient, quantiteActuelle - quantite);
            }
            return true;
        }
        return false;
    }

    public boolean possedeIngredient(Aliments.TypeAliment ingredient, int quantite) {
        return ingredients.getOrDefault(ingredient, 0) >= quantite;
    }

    public boolean retirerAliment(int index) {
        if (index >= 0 && index < aliments.size()) {
            Aliments aliment = aliments.remove(index);
            System.out.println("✓ " + aliment.getNom() + " retiré de l'inventaire !");
            return true;
        }
        return false;
    }

    public boolean retirerPotion(int index) {
        if (index >= 0 && index < potions.size()) {
            potions.remove(index);
            System.out.println("✓ Potion retirée de l'inventaire !");
            return true;
        }
        return false;
    }

    public void afficherInventaire() {
        System.out.println("\n/-/ INVENTAIRE /-/");

        System.out.println("\n/-/ POTIONS (" + potions.size() + ") /-/:");
        if (potions.isEmpty()) {
            System.out.println("  (vide)");
        } else {
            for (int i = 0; i < potions.size(); i++) {
                Potion p = potions.get(i);
                System.out.println("  " + (i + 1) + ". Potion magique - " + p.getDosesRestantes() + " doses restantes" +
                                   (p.estNourrissante() ? " (nourrissante)" : ""));
            }
        }

        System.out.println("\n/-/ ALIMENTS (" + aliments.size() + ") /-/:");
        if (aliments.isEmpty()) {
            System.out.println("  (vide)");
        } else {
            for (int i = 0; i < aliments.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + aliments.get(i).getNom());
            }
        }
        System.out.println("\n/-/ INGRÉDIENTS POUR POTIONS /-/:");
        if (ingredients.isEmpty()) {
            System.out.println("  (vide)");
        } else {
            for (Map.Entry<Aliments.TypeAliment, Integer> entry : ingredients.entrySet()) {
                System.out.println("  - " + entry.getKey().getNom() + " x" + entry.getValue());
            }
        }
        System.out.println();
    }

    public void gererInventaire() {
        gererInventaire(null);
    }

    public void gererInventaire(Lieux lieuActuel) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;
        
        while (continuer) {
            afficherInventaire();
            System.out.println("/-/ GESTION DE L'INVENTAIRE /-/");
            System.out.println("1. Créer une potion magique ");
            System.out.println("2. Utiliser une potion ");
            System.out.println("3. Consommer un aliment ");
            System.out.println("4. Retour au menu principal ");
            System.out.print("Votre choix : ");
            
            String choix = scanner.nextLine().trim();
            
            switch (choix) {
                case "1":
                    creerPotionInteractif(scanner, lieuActuel);
                    break;
                case "2":
                    utiliserPotionInteractif(scanner);
                    break;
                case "3":
                    consommerAlimentInteractif(scanner);
                    break;
                case "4":
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide !");
                    pause(1000);
            }
            
            if (continuer) {
                System.out.print("\nAppuyez sur ENTRÉE pour continuer...");
                scanner.nextLine();
            }
        }
    }

    public void gererInventaireAvecDruide(Lieux lieu, Personnage.Druide druide) {
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            afficherInventaire();
            System.out.println("/-/ GESTION DE L'INVENTAIRE /-/");
            System.out.println("1. Créer une potion magique avec " + druide.getNom());
            System.out.println("2. Utiliser une potion ");
            System.out.println("3. Consommer un aliment ");
            System.out.println("4. Partir ");
            System.out.print("Votre choix : ");

            String choix = scanner.nextLine().trim();

            switch (choix) {
                case "1":
                    creerPotionAvecDruide(scanner, druide);
                    break;
                case "2":
                    utiliserPotionInteractif(scanner);
                    break;
                case "3":
                    consommerAlimentInteractif(scanner);
                    break;
                case "4":
                    druide.direAuRevoir();
                    continuer = false;
                    break;
                default:
                    System.out.println("❌ Choix invalide !");
                    pause(1000);
            }

            if (continuer) {
                System.out.print("\nAppuyez sur ENTRÉE pour continuer...");
                scanner.nextLine();
            }
        }
    }

    private void creerPotionAvecDruide(Scanner scanner, Personnage.Druide druide) {
        System.out.println("\n/-/ CRÉER UNE POTION MAGIQUE /-/");

        druide.expliquerRecette();

        // Vérifier les ingrédients
        Aliments.TypeAliment[] ingredientsBase = {
            Aliments.TypeAliment.GUI,
            Aliments.TypeAliment.CAROTTES,
            Aliments.TypeAliment.SEL,
            Aliments.TypeAliment.TREFLE_QUATRE_FEUILLES_FRAIS,
            Aliments.TypeAliment.POISSON_PASSABLEMENT_FRAIS,
            Aliments.TypeAliment.MIEL,
            Aliments.TypeAliment.HYDROMEL,
            Aliments.TypeAliment.INGREDIENT_SECRET
        };

        boolean aHuileOuJus = possedeIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 1) ||
                              possedeIngredient(Aliments.TypeAliment.JUS_DE_BETTERAVE, 1);

        boolean tousIngredientsPresents = aHuileOuJus;
        for (Aliments.TypeAliment ing : ingredientsBase) {
            if (!possedeIngredient(ing, 1)) {
                tousIngredientsPresents = false;
                System.out.println("❌ Ingrédient manquant : " + ing.getNom());
            }
        }

        if (!aHuileOuJus) {
            System.out.println("❌ Ingrédient manquant : Huile de roche OU Jus de betterave");
        }

        if (!tousIngredientsPresents) {
            System.out.println("\n❌ Vous n'avez pas tous les ingrédients nécessaires !");
            return;
        }

        System.out.print("\nVoulez-vous créer une potion magique ? (O/N) : ");
        String reponse = scanner.nextLine().trim().toUpperCase();

        if (!reponse.equals("O")) {
            System.out.println("Création annulée.");
            return;
        }

        druide.superviserCreation();

        // Retirer les ingrédients de base
        for (Aliments.TypeAliment ing : ingredientsBase) {
            retirerIngredient(ing, 1);
        }

        Potion nouvellePotion = new Potion();

        // Gestion de l'huile de roche ou du jus de betterave
        if (possedeIngredient(Aliments.TypeAliment.JUS_DE_BETTERAVE, 1)) {
            System.out.print("Utiliser du jus de betterave à la place de l'huile de roche ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.remplacerParJusDeBetterave();
                retirerIngredient(Aliments.TypeAliment.JUS_DE_BETTERAVE, 1);
            } else {
                retirerIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 1);
            }
        } else {
            retirerIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 1);
        }

        // Proposer les ingrédients optionnels
        if (possedeIngredient(Aliments.TypeAliment.HOMARD, 1)) {
            System.out.print("Ajouter du homard (nourrissant) ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.ajouterHomard();
                retirerIngredient(Aliments.TypeAliment.HOMARD, 1);
            }
        }

        if (possedeIngredient(Aliments.TypeAliment.FRAISES, 1)) {
            System.out.print("Ajouter des fraises (nourrissant) ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.ajouterFraises();
                retirerIngredient(Aliments.TypeAliment.FRAISES, 1);
            }
        }

        if (possedeIngredient(Aliments.TypeAliment.LAIT_DE_LICORNE, 1)) {
            System.out.print("Ajouter du lait de licorne (pouvoir de dédoublement) ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.ajouterLaitDeLicorne();
                retirerIngredient(Aliments.TypeAliment.LAIT_DE_LICORNE, 1);
            }
        }

        if (possedeIngredient(Aliments.TypeAliment.POILS_IDEFIX, 1)) {
            System.out.print("Ajouter des poils d'Idéfix (métamorphosis) ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.ajouterPoilsIdefix();
                retirerIngredient(Aliments.TypeAliment.POILS_IDEFIX, 1);
            }
        }

        ajouterPotion(nouvellePotion);
        druide.feliciter();
        nouvellePotion.afficherIngredients();
    }

    private void creerPotionInteractif(Scanner scanner, Lieux lieuActuel) {
        System.out.println("\n/-/ CRÉER UNE POTION MAGIQUE /-/");

        // Vérifier si on est dans un lieu avec un druide
        if (lieuActuel == null || !lieuActuel.aDruide()) {
            System.out.println("❌ Vous devez être dans un lieu avec un DRUIDE pour créer une potion magique !");
            System.out.println("💡 Rendez-vous dans un village gaulois pour trouver un druide.");
            return;
        }

        System.out.println("✓ Le druide " + lieuActuel.getDruide().getNom() + " peut vous aider à créer une potion magique !");
        System.out.println();
        Potion.afficherRecette();

        Aliments.TypeAliment[] ingredientsBase = {
            Aliments.TypeAliment.GUI,
            Aliments.TypeAliment.CAROTTES,
            Aliments.TypeAliment.SEL,
            Aliments.TypeAliment.TREFLE_QUATRE_FEUILLES_FRAIS,
            Aliments.TypeAliment.POISSON_PASSABLEMENT_FRAIS,
            Aliments.TypeAliment.MIEL,
            Aliments.TypeAliment.HYDROMEL,
            Aliments.TypeAliment.INGREDIENT_SECRET
        };

        boolean aHuileOuJus = possedeIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 1) ||
                              possedeIngredient(Aliments.TypeAliment.JUS_DE_BETTERAVE, 1);
        
        boolean tousIngredientsPresents = aHuileOuJus;
        for (Aliments.TypeAliment ing : ingredientsBase) {
            if (!possedeIngredient(ing, 1)) {
                tousIngredientsPresents = false;
                System.out.println("❌ Ingrédient manquant : " + ing.getNom());
            }
        }
        
        if (!aHuileOuJus) {
            System.out.println("❌ Ingrédient manquant : Huile de roche OU Jus de betterave");
        }
        
        if (!tousIngredientsPresents) {
            System.out.println("\n❌ Vous n'avez pas tous les ingrédients nécessaires !");
            return;
        }
        
        System.out.print("\nVoulez-vous créer une potion magique ? (O/N) : ");
        String reponse = scanner.nextLine().trim().toUpperCase();
        
        if (!reponse.equals("O")) {
            System.out.println("Création annulée.");
            return;
        }

        // Retirer les ingrédients de base (sauf huile/jus qui sera géré après)
        for (Aliments.TypeAliment ing : ingredientsBase) {
            retirerIngredient(ing, 1);
        }

        Potion nouvellePotion = new Potion();

        // Gestion de l'huile de roche ou du jus de betterave
        if (possedeIngredient(Aliments.TypeAliment.JUS_DE_BETTERAVE, 1)) {
            System.out.print("Utiliser du jus de betterave à la place de l'huile de roche ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.remplacerParJusDeBetterave();
                retirerIngredient(Aliments.TypeAliment.JUS_DE_BETTERAVE, 1);
            } else {
                retirerIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 1);
            }
        } else {
            retirerIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 1);
        }

        if (possedeIngredient(Aliments.TypeAliment.HOMARD, 1)) {
            System.out.print("Ajouter du homard (nourrissant) ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.ajouterHomard();
                retirerIngredient(Aliments.TypeAliment.HOMARD, 1);
            }
        }
        
        if (possedeIngredient(Aliments.TypeAliment.FRAISES, 1)) {
            System.out.print("Ajouter des fraises (nourrissant) ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.ajouterFraises();
                retirerIngredient(Aliments.TypeAliment.FRAISES, 1);
            }
        }
        
        if (possedeIngredient(Aliments.TypeAliment.LAIT_DE_LICORNE, 1)) {
            System.out.print("Ajouter du lait de licorne (pouvoir de dédoublement) ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.ajouterLaitDeLicorne();
                retirerIngredient(Aliments.TypeAliment.LAIT_DE_LICORNE, 1);
            }
        }
        
        if (possedeIngredient(Aliments.TypeAliment.POILS_IDEFIX, 1)) {
            System.out.print("Ajouter des poils d'Idéfix (métamorphosis) ? (O/N) : ");
            reponse = scanner.nextLine().trim().toUpperCase();
            if (reponse.equals("O")) {
                nouvellePotion.ajouterPoilsIdefix();
                retirerIngredient(Aliments.TypeAliment.POILS_IDEFIX, 1);
            }
        }
        
        ajouterPotion(nouvellePotion);
        System.out.println("\n🎉 Potion magique créée avec succès !");
        nouvellePotion.afficherIngredients();
    }

    private void utiliserPotionInteractif(Scanner scanner) {
        if (potions.isEmpty()) {
            System.out.println("\n❌ Vous n'avez aucune potion dans votre inventaire !");
            return;
        }
        
        System.out.println("\n/-/ UTILISER UNE POTION /-/");
        for (int i = 0; i < potions.size(); i++) {
            Potion p = potions.get(i);
            System.out.println((i + 1) + ". Potion magique - " + p.getDosesRestantes() + " doses restantes");
        }
        
        System.out.print("\nChoisissez une potion (1-" + potions.size() + ") : ");
        try {
            int choix = Integer.parseInt(scanner.nextLine().trim());
            if (choix >= 1 && choix <= potions.size()) {
                Potion potion = potions.get(choix - 1);
                potion.afficherIngredients();
                
                System.out.print("Combien de doses voulez-vous boire ? (1-" + potion.getDosesRestantes() + ") : ");
                int doses = Integer.parseInt(scanner.nextLine().trim());
                
                if (doses > 0 && doses <= potion.getDosesRestantes()) {
                    potion.boireDose(doses);
                    
                    // Retirer la potion si elle est vide
                    if (potion.getDosesRestantes() == 0) {
                        potions.remove(choix - 1);
                        System.out.println("\n🗑️  La potion est vide et a été retirée de l'inventaire.");
                    }
                } else {
                    System.out.println("❌ Nombre de doses invalide !");
                }
            } else {
                System.out.println("❌ Choix invalide !");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Veuillez entrer un nombre !");
        }
    }

    private void consommerAlimentInteractif(Scanner scanner) {
        if (aliments.isEmpty()) {
            System.out.println("\n❌ Vous n'avez aucun aliment dans votre inventaire !");
            return;
        }
        
        System.out.println("\n/-/ CONSOMMER UN ALIMENT /-/");
        for (int i = 0; i < aliments.size(); i++) {
            System.out.println((i + 1) + ". " + aliments.get(i).getNom());
        }
        
        System.out.print("\nChoisissez un aliment (1-" + aliments.size() + ") : ");
        try {
            int choix = Integer.parseInt(scanner.nextLine().trim());
            if (choix >= 1 && choix <= aliments.size()) {
                Aliments aliment = aliments.get(choix - 1);
                System.out.println("\n🍴 Vous consommez : " + aliment.getNom());
                retirerAliment(choix - 1);
            } else {
                System.out.println("❌ Choix invalide !");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Veuillez entrer un nombre !");
        }
    }

    private void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    // Getters
    public List<Potion> getPotions() {
        return new ArrayList<>(potions);
    }
    
    public List<Aliments> getAliments() {
        return new ArrayList<>(aliments);
    }
    
    public Map<Aliments.TypeAliment, Integer> getIngredients() {
        return new HashMap<>(ingredients);
    }
}

