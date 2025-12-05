//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import consomable.Potion;

public class TestPotion {
    public static void main(String[] var0) {
        System.out.println("=== TEST DE LA CLASSE POTION ===\n");
        Potion.afficherRecette();
        System.out.println("\n--- Test 1 : Création d'une potion de base ---");
        Potion var1 = new Potion();
        var1.afficherIngredients();
        System.out.println("\n--- Test 2 : Ajout d'ingrédients spéciaux ---");
        Potion var2 = new Potion();
        var2.ajouterHomard();
        var2.ajouterLaitDeLicorne();
        var2.ajouterPoilsIdefix();
        var2.afficherIngredients();
        System.out.println("\n--- Test 3 : Boire 1 dose (effet temporaire) ---");
        Potion var3 = new Potion();
        var3.ajouterLaitDeLicorne();
        var3.boireDose(1);
        System.out.println("\n--- Test 4 : Boire 10 doses (1 marmite - effet permanent) ---");
        Potion var4 = new Potion();
        var4.ajouterPoilsIdefix();
        var4.remplacerParJusDeBetterave();
        var4.boireDose(10);
        System.out.println("\n--- Test 5 : Boire 20 doses (2 marmites - DANGER !) ---");
        Potion var5 = new Potion();
        var5.boireDose(10);
        System.out.println("\nNote : Pour boire 20 doses, il faudrait 2 marmites différentes.");
        System.out.println("\n=== FIN DES TESTS ===");
    }
}
