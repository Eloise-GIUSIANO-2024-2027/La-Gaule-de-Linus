public class Alliments {
    
    // Énumération des types d'aliments
    public enum TypeAliment {
        SANGLIER("Sanglier", TypeCategorie.VIANDE),
        POISSON_PASSABLEMENT_FRAIS("Poisson passablement frais", TypeCategorie.POISSON),
        POISSON_PAS_FRAIS("Poisson pas frais", TypeCategorie.POISSON),
        GUI("Gui", TypeCategorie.VEGETAL),
        HOMARD("Homard", TypeCategorie.VIANDE),
        FRAISES("Fraises", TypeCategorie.VEGETAL),
        CAROTTES("Carottes", TypeCategorie.VEGETAL),
        SEL("Sel", TypeCategorie.CONDIMENT),
        TREFLE_QUATRE_FEUILLES_FRAIS("Trèfle à quatre feuilles frais", TypeCategorie.VEGETAL),
        TREFLE_QUATRE_FEUILLES_PAS_FRAIS("Trèfle à quatre feuilles pas frais", TypeCategorie.VEGETAL),
        HUILE_DE_ROCHE("Huile de roche", TypeCategorie.CONDIMENT),
        JUS_DE_BETTERAVE("Jus de betterave", TypeCategorie.BOISSON),
        MIEL("Miel", TypeCategorie.CONDIMENT),
        VIN("Vin", TypeCategorie.BOISSON),
        HYDROMEL("Hydromel", TypeCategorie.BOISSON),
        LAIT_DE_LICORNE("Lait de licorne à deux têtes", TypeCategorie.BOISSON),
        POILS_IDEFIX("Poils d'Idéfix", TypeCategorie.INGREDIENT_SPECIAL),
        INGREDIENT_SECRET("Ingrédient secret", TypeCategorie.INGREDIENT_SPECIAL);
        
        private final String nom;
        private final TypeCategorie categorie;
        
        TypeAliment(String nom, TypeCategorie categorie) {
            this.nom = nom;
            this.categorie = categorie;
        }
        
        public String getNom() {
            return nom;
        }
        
        public TypeCategorie getCategorie() {
            return categorie;
        }
        
        public boolean estVegetal() {
            return categorie == TypeCategorie.VEGETAL;
        }
        
        public boolean estDangereux() {
            return this == POISSON_PAS_FRAIS;
        }
    }
    
    // Énumération des catégories d'aliments
    public enum TypeCategorie {
        VIANDE,
        POISSON,
        VEGETAL,
        CONDIMENT,
        BOISSON,
        INGREDIENT_SPECIAL
    }
    
    // Énumération des types de personnages
    public enum TypePersonnage {
        GAULOIS,
        ROMAIN
    }
    
    private TypeAliment aliment;
    
    public Alliments(TypeAliment aliment) {
        this.aliment = aliment;
    }
    
    public TypeAliment getAliment() {
        return aliment;
    }
    
    public String getNom() {
        return aliment.getNom();
    }
    
    /**
     * Vérifie si un aliment est consommable par un type de personnage
     */
    public boolean estConsommablePar(TypePersonnage personnage) {
        switch (personnage) {
            case GAULOIS:
                return aliment == TypeAliment.SANGLIER 
                    || aliment == TypeAliment.POISSON_PASSABLEMENT_FRAIS 
                    || aliment == TypeAliment.VIN;
            case ROMAIN:
                return aliment == TypeAliment.SANGLIER 
                    || aliment == TypeAliment.MIEL 
                    || aliment == TypeAliment.VIN 
                    || aliment == TypeAliment.HYDROMEL;
            default:
                return false;
        }
    }
    
    /**
     * Vérifie si manger cet aliment après le précédent est mauvais pour la santé
     */
    public boolean estMauvaisPourLaSante(Alliments alimentPrecedent) {
        // Le poisson pas frais est toujours mauvais pour la santé
        if (aliment.estDangereux()) {
            return true;
        }
        
        // Manger deux fois consécutivement des légumes ou végétaux est mauvais
        if (alimentPrecedent != null && 
            aliment.estVegetal() && 
            alimentPrecedent.getAliment().estVegetal()) {
            return true;
        }
        
        return false;
    }
    
    @Override
    public String toString() {
        return aliment.getNom() + " (" + aliment.getCategorie() + ")";
    }
    
    /**
     * Affiche tous les aliments disponibles
     */
    public static void afficherTousLesAliments() {
        System.out.println("=== Liste des aliments disponibles ===");
        for (TypeAliment type : TypeAliment.values()) {
            System.out.println("- " + type.getNom());
        }
        System.out.println();
    }
    
    /**
     * Affiche les aliments consommables par un type de personnage
     */
    public static void afficherAlimentsConsommables(TypePersonnage personnage) {
        System.out.println("=== Aliments consommables par les " + personnage + " ===");
        for (TypeAliment type : TypeAliment.values()) {
            Alliments nourriture = new Alliments(type);
            if (nourriture.estConsommablePar(personnage)) {
                System.out.println("- " + type.getNom());
            }
        }
        System.out.println();
    }
}

