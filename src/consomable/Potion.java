package consomable;

import java.util.ArrayList;
import java.util.List;

public class Potion {

    // Ingrédients de base de la potion magique
    private static final Aliments.TypeAliment[] INGREDIENTS_BASE = {
        Aliments.TypeAliment.GUI,
        Aliments.TypeAliment.CAROTTES,
        Aliments.TypeAliment.SEL,
        Aliments.TypeAliment.TREFLE_QUATRE_FEUILLES_FRAIS,
        Aliments.TypeAliment.POISSON_PASSABLEMENT_FRAIS,
        Aliments.TypeAliment.HUILE_DE_ROCHE,
        Aliments.TypeAliment.MIEL,
        Aliments.TypeAliment.HYDROMEL,
        Aliments.TypeAliment.INGREDIENT_SECRET
    };

    // Énumération des effets possibles
    public enum Effet {
        FORCE_SURHUMAINE("Force surhumaine"),
        INVINCIBILITE("Invincibilité"),
        DEDOUBLEMENT("Pouvoir de dédoublement"),
        METAMORPHOSIS("Métamorphosis (lycanthrope)"),
        STATUE_GRANIT("Transformation en statue de granit");

        private final String description;

        Effet(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    // Propriétés de la potion
    private List<Aliments.TypeAliment> ingredients;
    private int dosesRestantes;
    private static final int DOSES_PAR_MARMITE = 10;
    private boolean estNourrissante;
    private boolean avecJusDeBetterave;

    /**
     * Constructeur d'une nouvelle marmite de potion magique
     */
    public Potion() {
        this.ingredients = new ArrayList<>();
        this.dosesRestantes = DOSES_PAR_MARMITE;
        this.estNourrissante = false;
        this.avecJusDeBetterave = false;

        // Ajouter les ingrédients de base
        for (Aliments.TypeAliment ingredient : INGREDIENTS_BASE) {
            ingredients.add(ingredient);
        }
    }

    /**
     * Ajoute du homard à la potion (la rend nourrissante)
     */
    public void ajouterHomard() {
        if (!ingredients.contains(Aliments.TypeAliment.HOMARD)) {
            ingredients.add(Aliments.TypeAliment.HOMARD);
            estNourrissante = true;
            System.out.println("✓ Homard ajouté ! La potion est maintenant nourrissante.");
        } else {
            System.out.println("⚠️  Le homard a déjà été ajouté !");
        }
    }

    /**
     * Ajoute des fraises à la potion (la rend nourrissante)
     */
    public void ajouterFraises() {
        if (!ingredients.contains(Aliments.TypeAliment.FRAISES)) {
            ingredients.add(Aliments.TypeAliment.FRAISES);
            estNourrissante = true;
            System.out.println("✓ Fraises ajoutées ! La potion est maintenant nourrissante.");
        } else {
            System.out.println("⚠️  Les fraises ont déjà été ajoutées !");
        }
    }

    /**
     * Remplace l'huile de roche par du jus de betterave (la rend nourrissante)
     */
    public void remplacerParJusDeBetterave() {
        if (ingredients.contains(Aliments.TypeAliment.HUILE_DE_ROCHE)) {
            ingredients.remove(Aliments.TypeAliment.HUILE_DE_ROCHE);
            ingredients.add(Aliments.TypeAliment.JUS_DE_BETTERAVE);
            estNourrissante = true;
            avecJusDeBetterave = true;
            System.out.println("✓ Huile de roche remplacée par du jus de betterave ! La potion est maintenant nourrissante.");
        } else {
            System.out.println("⚠️  L'huile de roche a déjà été remplacée ou n'est plus présente !");
        }
    }

    /**
     * Ajoute du lait de licorne à deux têtes (octroie le pouvoir de dédoublement)
     */
    public void ajouterLaitDeLicorne() {
        if (!ingredients.contains(Aliments.TypeAliment.LAIT_DE_LICORNE)) {
            ingredients.add(Aliments.TypeAliment.LAIT_DE_LICORNE);
            System.out.println("✓ Lait de licorne à deux têtes ajouté ! Octroie le pouvoir de dédoublement.");
        } else {
            System.out.println("⚠️  Le lait de licorne a déjà été ajouté !");
        }
    }

    /**
     * Ajoute des poils d'Idéfix (octroie le pouvoir de métamorphosis)
     */
    public void ajouterPoilsIdefix() {
        if (!ingredients.contains(Aliments.TypeAliment.POILS_IDEFIX)) {
            ingredients.add(Aliments.TypeAliment.POILS_IDEFIX);
            System.out.println("✓ Poils d'Idéfix ajoutés ! Octroie le pouvoir de métamorphosis (lycanthrope).");
        } else {
            System.out.println("⚠️  Les poils d'Idéfix ont déjà été ajoutés !");
        }
    }

    /**
     * Vérifie si la potion est valide (contient tous les ingrédients de base ou leurs remplacements)
     */
    public boolean estValide() {
        // Vérifier les ingrédients essentiels (sauf huile de roche qui peut être remplacée)
        for (Aliments.TypeAliment ingredient : INGREDIENTS_BASE) {
            if (ingredient != Aliments.TypeAliment.HUILE_DE_ROCHE && !ingredients.contains(ingredient)) {
                return false;
            }
        }

        // Vérifier que huile de roche OU jus de betterave est présent
        if (!ingredients.contains(Aliments.TypeAliment.HUILE_DE_ROCHE) &&
            !ingredients.contains(Aliments.TypeAliment.JUS_DE_BETTERAVE)) {
            return false;
        }

        return true;
    }

    /**
     * Boit une dose de potion magique
     * @param nombreDoses le nombre de doses à boire
     * @return les effets obtenus
     */
    public List<Effet> boireDose(int nombreDoses) {
        List<Effet> effets = new ArrayList<>();

        if (!estValide()) {
            System.out.println("⚠️  ATTENTION : Cette potion n'est pas valide !");
            return effets;
        }

        if (nombreDoses > dosesRestantes) {
            System.out.println("⚠️  Pas assez de doses ! Il reste " + dosesRestantes + " dose(s).");
            return effets;
        }

        dosesRestantes -= nombreDoses;

        // Calculer les effets selon le nombre de doses
        if (nombreDoses >= 2 * DOSES_PAR_MARMITE) {
            // Deux marmites ou plus = statue de granit
            effets.add(Effet.STATUE_GRANIT);
            System.out.println("\n💀 ATTENTION ! Vous avez bu " + nombreDoses + " doses (deux marmites ou plus) !");
            System.out.println("🗿 Vous êtes transformé en statue de granit !");
        } else if (nombreDoses >= DOSES_PAR_MARMITE) {
            // Une marmite complète = effets permanents
            System.out.println("\n🌟 Vous avez bu une marmite complète !");
            System.out.println("⚡ Les effets deviennent PERMANENTS !");
            ajouterEffetsStandard(effets, true);
        } else {
            // Moins d'une marmite = effets temporaires
            System.out.println("\n✨ Vous avez bu " + nombreDoses + " dose(s) !");
            System.out.println("⏳ Les effets sont temporaires.");
            ajouterEffetsStandard(effets, false);
        }

        return effets;
    }

    /**
     * Ajoute les effets standard de la potion
     */
    private void ajouterEffetsStandard(List<Effet> effets, boolean permanent) {
        effets.add(Effet.FORCE_SURHUMAINE);
        effets.add(Effet.INVINCIBILITE);

        System.out.println("💪 Force surhumaine " + (permanent ? "(permanent)" : "(temporaire)"));
        System.out.println("🛡️  Invincibilité " + (permanent ? "(permanente)" : "(temporaire)"));

        // Effets additionnels selon les ingrédients spéciaux
        if (ingredients.contains(Aliments.TypeAliment.LAIT_DE_LICORNE)) {
            effets.add(Effet.DEDOUBLEMENT);
            System.out.println("👥 Pouvoir de dédoublement " + (permanent ? "(permanent)" : "(temporaire)"));
        }

        if (ingredients.contains(Aliments.TypeAliment.POILS_IDEFIX)) {
            effets.add(Effet.METAMORPHOSIS);
            System.out.println("🐺 Métamorphosis - lycanthrope " + (permanent ? "(permanent)" : "(temporaire)"));
        }

        if (estNourrissante) {
            System.out.println("🍖 La potion est nourrissante !");
        }
    }

    public int getDosesRestantes() {
        return dosesRestantes;
    }

    public boolean estNourrissante() {
        return estNourrissante;
    }

    public List<Aliments.TypeAliment> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    /**
     * Affiche les ingrédients de la potion
     */
    public void afficherIngredients() {
        System.out.println("\n=== Composition de la potion ===");
        System.out.println("Doses restantes : " + dosesRestantes + "/" + DOSES_PAR_MARMITE);
        System.out.println("Nourrissante : " + (estNourrissante ? "Oui" : "Non"));
        System.out.println("\nIngrédients :");
        for (Aliments.TypeAliment ingredient : ingredients) {
            System.out.println("  - " + ingredient.getNom());
        }
        System.out.println();
    }

    /**
     * Affiche la recette de base de la potion magique
     */
    public static void afficherRecette() {
        System.out.println("\n=== Recette de la potion magique ===");
        System.out.println("Ingrédients de base :");
        for (Aliments.TypeAliment ingredient : INGREDIENTS_BASE) {
            System.out.println("  - " + ingredient.getNom());
        }
        System.out.println("\nOptions nourrissantes :");
        System.out.println("  - Ajouter du homard");
        System.out.println("  - Ajouter des fraises");
        System.out.println("  - Remplacer l'huile de roche par du jus de betterave");
        System.out.println("\nPouvoirs additionnels :");
        System.out.println("  - Lait de licorne à deux têtes : pouvoir de dédoublement");
        System.out.println("  - Poils d'Idéfix : métamorphosis (lycanthrope)");
        System.out.println("\nEffets :");
        System.out.println("  - 1 dose : Force surhumaine et invincibilité (temporaire)");
        System.out.println("  - 1 marmite (10 doses) : Effets permanents");
        System.out.println("  - 2 marmites (20 doses) : Transformation en statue de granit !");
        System.out.println();
    }
}
