import Personnage.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Bienvenue dans La Gaule de Linus ===\n");

        PNJStats gaulois1 = new PNJStats();
        gaulois1.setNom("Astérix");
        gaulois1.setSexe("Masculin");
        gaulois1.setTaille(1.50);
        gaulois1.setAge(35);
        gaulois1.setForce(95);
        gaulois1.setEndurance(90);
        gaulois1.setIndicateurSante(100);
        gaulois1.setIndicateurFaim(50);
        gaulois1.setIndicateurBelligerance(80);
        gaulois1.setNiveauPotionMagique(100);
        CharacterRoleAssigner.assignRole(gaulois1, CharacterType.GAULOIS);

        PNJStats romain1 = new PNJStats();
        romain1.setNom("Caius Bonus");
        romain1.setSexe("Masculin");
        romain1.setTaille(1.75);
        romain1.setAge(40);
        romain1.setForce(70);
        romain1.setEndurance(75);
        romain1.setIndicateurSante(100);
        romain1.setIndicateurFaim(60);
        romain1.setIndicateurBelligerance(60);
        romain1.setNiveauPotionMagique(0);
        CharacterRoleAssigner.assignRole(romain1, CharacterType.ROMAIN, CharacterRole.GENERAL);

        PNJStats creature1 = new PNJStats();
        creature1.setNom("Fenrir");
        creature1.setSexe("Masculin");
        creature1.setTaille(2.10);
        creature1.setAge(200);
        creature1.setForce(120);
        creature1.setEndurance(100);
        creature1.setIndicateurSante(150);
        creature1.setIndicateurFaim(80);
        creature1.setIndicateurBelligerance(95);
        creature1.setNiveauPotionMagique(0);
        CharacterRoleAssigner.assignRole(creature1, CharacterType.CREATURE);

        System.out.println("Personnages créés :");
        System.out.println("1. " + gaulois1);
        System.out.println("\n2. " + romain1);
        System.out.println("\n3. " + creature1);
    }
}