package Personnage;

import consomable.Aliments;
import consomable.Potion;

/**
 * Classe représentant un druide gaulois qui peut aider à créer des potions magiques
 */
public class Druide {

    private final PNJStats stats;
    private final int niveauCompetence;
    private final String specialite;

    public Druide(String nom, int age, int niveauCompetence, String specialite) {
        this.stats = new PNJStats(
            nom, "Homme", 1.65, age, 5, 8, 90, 50, 10, niveauCompetence,
            CharacterType.GAULOIS, CharacterRole.DRUIDE
        );
        this.niveauCompetence = niveauCompetence;
        this.specialite = specialite;
    }

    /**
     * Le druide accueille le visiteur
     */
    public void accueillir() {
        System.out.println("\n🧙 " + stats.getNom() + " vous accueille chaleureusement !");
        System.out.println("   \"Bienvenue, noble gaulois ! Je suis " + stats.getNom() + ", druide de ce village.\"");
        System.out.println("   \"Ma spécialité : " + specialite + "\"");
        System.out.println("   \"Niveau de compétence : " + niveauCompetence + "/100\"");
        System.out.println();
    }

    /**
     * Le druide explique la recette de la potion magique
     */
    public void expliquerRecette() {
        System.out.println("🧙 " + stats.getNom() + " : \"Laissez-moi vous expliquer la recette ancestrale...\"");
        System.out.println();
        Potion.afficherRecette();
    }

    /**
     * Le druide supervise la création de la potion
     */
    public void superviserCreation() {
        System.out.println("\n🧙 " + stats.getNom() + " : \"Suivez mes instructions avec attention...\"");
        System.out.println("   *Le druide prépare le chaudron et allume le feu sacré*");
        System.out.println();
    }

    /**
     * Le druide félicite pour la réussite
     */
    public void feliciter() {
        System.out.println("\n✨ " + stats.getNom() + " : \"Magnifique ! La potion est parfaite !\"");
        System.out.println("   \"Vous êtes maintenant prêt à affronter les légions romaines !\"");

        if (niveauCompetence >= 90) {
            System.out.println("   \"Grâce à mon expertise, cette potion sera particulièrement puissante !\"");
        } else if (niveauCompetence >= 70) {
            System.out.println("   \"Une belle réussite, digne d'un vrai druide !\"");
        } else {
            System.out.println("   \"Pour une première fois, c'est très honorable !\"");
        }
        System.out.println();
    }

    /**
     * Message d'au revoir du druide
     */
    public void direAuRevoir() {
        System.out.println("🧙 " + stats.getNom() + " : \"Que Toutatis vous protège dans votre quête !\"");
        System.out.println("   \"Revenez me voir quand vous voudrez préparer une nouvelle potion.\"");
        System.out.println();
    }

    public String getNom() {
        return stats.getNom();
    }

    public int getNiveauCompetence() {
        return niveauCompetence;
    }

    public String getSpecialite() {
        return specialite;
    }

    @Override
    public String toString() {
        return "Druide " + stats.getNom() + " (Niveau " + niveauCompetence + "/100) - " + specialite;
    }
}

