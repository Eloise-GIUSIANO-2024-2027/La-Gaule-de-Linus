import consomable.Aliments;
import consomable.Potion;

public class TestInventaire {
    
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║    TEST DE LA CLASSE INVENTAIRE                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");
        
        // Créer un inventaire
        Inventaire inventaire = new Inventaire();
        
        // 1. Ajouter des aliments
        System.out.println("=== 1. AJOUT D'ALIMENTS ===");
        inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.SANGLIER));
        inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.POISSON_PASSABLEMENT_FRAIS));
        inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.VIN));
        inventaire.ajouterAliment(new Aliments(Aliments.TypeAliment.MIEL));
        
        // 2. Ajouter des ingrédients pour fabriquer une potion
        System.out.println("\n=== 2. AJOUT D'INGRÉDIENTS POUR POTION ===");
        inventaire.ajouterIngredient(Aliments.TypeAliment.GUI, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.CAROTTES, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.SEL, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.TREFLE_QUATRE_FEUILLES_FRAIS, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.POISSON_PASSABLEMENT_FRAIS, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.HUILE_DE_ROCHE, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.MIEL, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.HYDROMEL, 2);
        inventaire.ajouterIngredient(Aliments.TypeAliment.INGREDIENT_SECRET, 2);
        
        // Ajouter des ingrédients optionnels
        inventaire.ajouterIngredient(Aliments.TypeAliment.HOMARD, 1);
        inventaire.ajouterIngredient(Aliments.TypeAliment.FRAISES, 1);
        inventaire.ajouterIngredient(Aliments.TypeAliment.LAIT_DE_LICORNE, 1);
        inventaire.ajouterIngredient(Aliments.TypeAliment.POILS_IDEFIX, 1);
        inventaire.ajouterIngredient(Aliments.TypeAliment.JUS_DE_BETTERAVE, 1);
        
        // 3. Créer une potion manuellement
        System.out.println("\n=== 3. CRÉATION D'UNE POTION MAGIQUE ===");
        Potion potionTest = new Potion();
        potionTest.ajouterHomard();
        potionTest.ajouterLaitDeLicorne();
        inventaire.ajouterPotion(potionTest);
        
        // 4. Afficher l'inventaire
        System.out.println("\n=== 4. AFFICHAGE DE L'INVENTAIRE ===");
        inventaire.afficherInventaire();
        
        // 5. Test d'utilisation interactive
        System.out.println("\n=== 5. LANCEMENT DU MODE INTERACTIF ===");
        System.out.println("Vous pouvez maintenant gérer votre inventaire de manière interactive.");
        System.out.println("Vous disposez de :");
        System.out.println("  - 4 aliments à consommer");
        System.out.println("  - 1 potion magique prête à utiliser");
        System.out.println("  - Suffisamment d'ingrédients pour créer 2 potions magiques");
        System.out.println("\nOptions disponibles :");
        System.out.println("  - Créer une nouvelle potion avec les ingrédients");
        System.out.println("  - Boire la potion existante");
        System.out.println("  - Consommer les aliments");
        System.out.println("  - Ajouter plus d'aliments ou d'ingrédients");
        System.out.println("\nAppuyez sur ENTRÉE pour continuer...");
        
        try {
            System.in.read();
        } catch (Exception e) {
            // Ignorer
        }
        
        inventaire.gererInventaire();
    }
}

