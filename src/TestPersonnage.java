import Personnage.*;

public class TestPersonnage {
    public static void main(String[] args) {
        System.out.println("/-/ Test d'attribution des rôles /-/\n");

        System.out.println("--- GAULOIS ---");

        PNJStats marchand = new PNJStats();
        marchand.setNom("Ordralfabétix");
        marchand.setSexe("M");
        marchand.setTaille(1.65);
        marchand.setAge(45);
        marchand.setForce(50);
        marchand.setEndurance(60);
        marchand.setIndicateurSante(100);
        marchand.setIndicateurFaim(40);
        marchand.setIndicateurBelligerance(0);
        marchand.setNiveauPotionMagique(10);
        CharacterRoleAssigner.assignRole(marchand, CharacterType.GAULOIS, CharacterRole.MARCHAND);
        System.out.println(marchand);

        PNJStats forgeron = new PNJStats();
        forgeron.setNom("Cétautomatix");
        forgeron.setSexe("F");
        forgeron.setTaille(1.70);
        forgeron.setAge(40);
        forgeron.setForce(85);
        forgeron.setEndurance(80);
        forgeron.setIndicateurSante(100);
        forgeron.setIndicateurFaim(50);
        forgeron.setIndicateurBelligerance(0);
        forgeron.setNiveauPotionMagique(5);
        CharacterRoleAssigner.assignRole(forgeron, CharacterType.GAULOIS, CharacterRole.FORGERON);
        System.out.println(forgeron);

        PNJStats aubergiste = new PNJStats();
        aubergiste.setNom("Termomix");
        aubergiste.setSexe("M");
        aubergiste.setTaille(1.90);
        aubergiste.setAge(46);
        aubergiste.setForce(90);
        aubergiste.setEndurance(50);
        aubergiste.setIndicateurSante(100);
        aubergiste.setIndicateurFaim(60);
        aubergiste.setIndicateurBelligerance(5);
        aubergiste.setNiveauPotionMagique(40);
        CharacterRoleAssigner.assignRole(aubergiste, CharacterType.GAULOIS, CharacterRole.AUBERGISTE);
        System.out.println(aubergiste);

        PNJStats druide = new PNJStats();
        druide.setNom("Paforamix");
        druide.setSexe("M");
        druide.setTaille(1.60);
        druide.setAge(70);
        druide.setForce(40);
        druide.setEndurance(70);
        druide.setIndicateurSante(100);
        druide.setIndicateurFaim(30);
        druide.setIndicateurBelligerance(0);
        druide.setNiveauPotionMagique(100);
        CharacterRoleAssigner.assignRole(druide, CharacterType.GAULOIS, CharacterRole.DRUIDE);
        System.out.println(druide);

        System.out.println("\n--- ROMAINS ---");

        PNJStats legionnaire = new PNJStats();
        legionnaire.setNom("Marcus Diplodocus");
        legionnaire.setSexe("M");
        legionnaire.setTaille(1.75);
        legionnaire.setAge(25);
        legionnaire.setForce(75);
        legionnaire.setEndurance(80);
        legionnaire.setIndicateurSante(100);
        legionnaire.setIndicateurFaim(50);
        legionnaire.setIndicateurBelligerance(70);
        legionnaire.setNiveauPotionMagique(0);
        CharacterRoleAssigner.assignRole(legionnaire, CharacterType.ROMAIN, CharacterRole.LEGIONNAIRE);
        System.out.println(legionnaire);

        PNJStats prefet = new PNJStats();
        prefet.setNom("Galius Proutidus");
        prefet.setSexe("Masculin");
        prefet.setTaille(1.70);
        prefet.setAge(35);
        prefet.setForce(60);
        prefet.setEndurance(65);
        prefet.setIndicateurSante(100);
        prefet.setIndicateurFaim(40);
        prefet.setIndicateurBelligerance(50);
        prefet.setNiveauPotionMagique(0);
        CharacterRoleAssigner.assignRole(prefet, CharacterType.ROMAIN, CharacterRole.PREFET);
        System.out.println(prefet);

        PNJStats general = new PNJStats();
        general.setNom("Saladus Caesarus");
        general.setSexe("Masculin");
        general.setTaille(1.80);
        general.setAge(50);
        general.setForce(85);
        general.setEndurance(90);
        general.setIndicateurSante(100);
        general.setIndicateurFaim(30);
        general.setIndicateurBelligerance(80);
        general.setNiveauPotionMagique(0);
        CharacterRoleAssigner.assignRole(general, CharacterType.ROMAIN, CharacterRole.GENERAL);
        System.out.println(general);

        System.out.println("\n--- CRÉATURES ---");

        PNJStats lycanthrope = new PNJStats();
        lycanthrope.setNom("Fabrice le furry");
        lycanthrope.setSexe("M");
        lycanthrope.setTaille(2.20);
        lycanthrope.setAge(354);
        lycanthrope.setForce(125);
        lycanthrope.setEndurance(120);
        lycanthrope.setIndicateurSante(150);
        lycanthrope.setIndicateurFaim(90);
        lycanthrope.setIndicateurBelligerance(85);
        lycanthrope.setNiveauPotionMagique(0);
        CharacterRoleAssigner.assignRole(lycanthrope, CharacterType.CREATURE);
        System.out.println(lycanthrope);

        System.out.println("\n/-/ Test d'attribution aléatoirius /-/");

        for (int i = 1; i <= 3; i++) {
            PNJStats gauloisAleatoire = getPnjStats(i);
            CharacterRoleAssigner.assignRole(gauloisAleatoire, CharacterType.GAULOIS);
            System.out.println("Gaulois " + i + " : " + gauloisAleatoire.getRole());
        }

        System.out.println("\n/-/ Test de validation de rôle /-/");
        try {
            PNJStats testInvalide = new PNJStats();
            testInvalide.setNom("INVALIDIUS");
            CharacterRoleAssigner.assignRole(testInvalide, CharacterType.GAULOIS, CharacterRole.LEGIONNAIRE);
            System.out.println("ERREURIUS : Cette lignius ne devrait jamais s'affichius!");
        } catch (IllegalArgumentException e) {
            System.out.println("ERREURIUS : " + e.getMessage());
        }

        System.out.println("\n/-/ Fin de la démonstratius /-/");
    }

    private static PNJStats getPnjStats(int i) {
        PNJStats gauloisAleatoire = new PNJStats();
        gauloisAleatoire.setNom("Gaulois #" + i);
        gauloisAleatoire.setSexe("M");
        gauloisAleatoire.setTaille(1.70);
        gauloisAleatoire.setAge(30);
        gauloisAleatoire.setForce(70);
        gauloisAleatoire.setEndurance(70);
        gauloisAleatoire.setIndicateurSante(100);
        gauloisAleatoire.setIndicateurFaim(50);
        gauloisAleatoire.setIndicateurBelligerance(50);
        gauloisAleatoire.setNiveauPotionMagique(20);
        return gauloisAleatoire;
    }
}

