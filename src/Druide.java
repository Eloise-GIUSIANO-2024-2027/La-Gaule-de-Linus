import consomable.Potion;

public class Druide {
    private String nom;

    public Druide(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public Potion fabriquerPotion() {
        System.out.println("Le druide " + nom + " concoctius une mixture magique...");
        return new Potion();
    }
}

