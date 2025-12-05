import consomable.Potion;

public class TestPotion {
    public static void main(String[] args) {
        System.out.println("=== TEST DE LA CLASSE POTION ===\n");
        
        // Afficher la recette
        Potion.afficherRecette();
        
        // Créer une nouvelle potion
        System.out.println("\n--- Test 1 : Création d'une potion de base ---");
        Potion potion1 = new Potion();
        potion1.afficherIngredients();
        
        // Test 2 : Ajouter des ingrédients spéciaux
        System.out.println("\n--- Test 2 : Ajout d'ingrédients spéciaux ---");
        Potion potion2 = new Potion();
        potion2.ajouterHomard();
        potion2.ajouterLaitDeLicorne();
        potion2.ajouterPoilsIdefix();
        potion2.afficherIngredients();
        
        // Test 3 : Boire une dose (effet temporaire)
        System.out.println("\n--- Test 3 : Boire 1 dose (effet temporaire) ---");
        Potion potion3 = new Potion();
        potion3.ajouterLaitDeLicorne();
        potion3.boireDose(1);
        
        // Test 4 : Boire une marmite complète (effet permanent)
        System.out.println("\n--- Test 4 : Boire 10 doses (1 marmite - effet permanent) ---");
        Potion potion4 = new Potion();
        potion4.ajouterPoilsIdefix();
        potion4.remplacerParJusDeBetterave();
        potion4.boireDose(10);
        
        // Test 5 : Boire deux marmites (statue de granit)
        System.out.println("\n--- Test 5 : Boire 20 doses (2 marmites - DANGER !) ---");
        Potion potion5 = new Potion();
        potion5.boireDose(10); // Vider la première marmite
        // Il faudrait une deuxième marmite, mais pour le test on simule
        System.out.println("\nNote : Pour boire 20 doses, il faudrait 2 marmites différentes.");
        
        System.out.println("\n=== FIN DES TESTS ===");
    }
}

