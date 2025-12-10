import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import Personnage.ListeNom;
import Personnage.PNJStats;
import Personnage.CharacterType;
import Personnage.CharacterRole;
import consomable.Alliments;
import consomable.Potion;

public class ChefDeClan {
    private String nom;
    private Lieux lieu;

    private final List<PNJStats> subordonnes = new ArrayList<>();
    private final List<Alliments> inventaireAliments = new ArrayList<>();
    private Potion potionCourante = null;

    public ChefDeClan(String nom, String sexe, int age, Lieux lieu) {
        this.nom = nom;
        this.lieu = lieu;
    }

    public String getNom() {
        return nom;
    }

    public Lieux getLieu() {
        return lieu;
    }

    public List<PNJStats> getSubordonnes() {
        return new ArrayList<>(subordonnes);
    }

    public void examinerLieu() {
        System.out.println("\n/-/" + nom + " appelle son informateur /-/");
        if (lieu != null) {
            System.out.println("Nom : " + lieu.getNom());
            System.out.println("Superficie : " + lieu.getSuperficie());
            System.out.println("Nombre de personnages : " + lieu.getNbPersonnages());
        } else {
            System.out.println("'Chef, ce lieu est inconnu au bataillon...'");
        }

        System.out.println("\nPersonnages rattachés au chef :");
        if (subordonnes.isEmpty()) {
            System.out.println("'Ce lieu semble vidius chef...'");
        } else {
            for (int i = 0; i < subordonnes.size(); i++) {
                PNJStats p = subordonnes.get(i);
                System.out.println("  [" + i+1 + "] " + p.getNom() + " - Santé : " + p.getIndicateurSante() + "; Faim : " + p.getIndicateurFaim() + "; Rôle : " + p.getRole());
            }
        }

        System.out.println("\nRéserve de nourriture :");
        if (inventaireAliments.isEmpty()) {
            System.out.println("'Vous devriez aller chasser ou cueillix quelque chose...'");
        } else {
            for (int i = 0; i < inventaireAliments.size(); i++) {
                Alliments a = inventaireAliments.get(i);
                System.out.println("  [" + i+1 + "] " + a.toString());
            }
        }

        System.out.println("\nRéserve de potion magique :");
        if (potionCourante != null) {
            System.out.println("'Il nous reste " + potionCourante.getDosesRestantes() + " dose(s) de potion, chef !'");
            potionCourante.afficherIngredients();
        } else {
            System.out.println("'Les réserves de potion sont vidius !'");
        }
    }

    public PNJStats creerPersonnage(String nomPerso, CharacterType type, CharacterRole role, String sexePerso, int agePerso) {
        Random random = new Random();

        String nom;
        if (nomPerso == null || nomPerso.trim().isEmpty()) {
            nom = ListeNom.genererNom(type);
        } else {
            nom = nomPerso;
        }

        String sexe;
        if (sexePerso == null || sexePerso.trim().isEmpty()) {
            sexe = random.nextBoolean() ? "M" : "F";
        } else {
            sexe = sexePerso;
        }

        int age;
        if (agePerso <= 0) {
            age = 18 + random.nextInt(43); // 18 à 60
        } else {
            age = agePerso;
        }

        double tailleMin = 1.50;
        double tailleMax = 2.00;
        double taille = tailleMin + (tailleMax - tailleMin) * random.nextDouble();
        taille = Math.round(taille * 100.0) / 100.0;

        int force = 5 + random.nextInt(20);

        int endurance = 5 + random.nextInt(20);

        PNJStats p = new PNJStats();
        p.setNom(nom);
        p.setSexe(sexe);
        p.setAge(age);
        p.setType(type);
        p.setRole(role);
        p.setTaille(taille);
        p.setForce(force);
        p.setEndurance(endurance);
        p.setIndicateurSante(100);
        p.setIndicateurFaim(0);
        p.setIndicateurBelligerance(0);
        p.setNiveauPotionMagique(0);

        subordonnes.add(p);
        if (lieu != null) {
            lieu.setNbPersonnages(lieu.getNbPersonnages() + 1);
        }
        System.out.println("'" + nom + "' à rejoint votre équipe ! (âge: " + age + ", sexe: " + sexe +
                           ", taille: " + taille + "m, force: " + force + ", endurance: " + endurance + ")");
        return p;
    }

    public void soignerPersonnages() {
        System.out.println("\n" + nom + " utilise du baume de guérison magique pour soigniux ses alliés.");
        for (PNJStats p : subordonnes) {
            int s = p.getIndicateurSante();
            int nouveau = Math.min(100, s + 20);
            p.setIndicateurSante(nouveau);
            System.out.println("    - " + p.getNom() + " : santé " + s + " -> " + nouveau);
        }
    }

    public void nourrirPersonnages() {
        System.out.println("\n" + nom + " ordonne à ses compagniux de manger et de festoyer.");
        if (inventaireAliments.isEmpty()) {
            System.out.println("'Chef, nous ne pouvons pas manger, il n'y a rien dans les réserves...'");
            return;
        }

        Iterator<Alliments> it = inventaireAliments.iterator();
        for (PNJStats p : subordonnes) {
            if (!it.hasNext()) break;
            Alliments a = it.next();
            boolean consommable = a.estConsommablePar(auteurTypeToTypePersonnage(p.getType()));
            if (consommable) {
                int faimAvant = p.getIndicateurFaim();
                int reduction = 30; //a modifier pour changer selon le type de nourriture (ex: sanglier = 50, miel = 20)
                int faimApres = Math.max(0, faimAvant - reduction);
                p.setIndicateurFaim(faimApres);
                System.out.println("    - " + p.getNom() + " mange '" + a.getNom() + "' : " + faimAvant + " -> " + faimApres);
            } else {
                System.out.println("    - " + p.getNom() + " ne peut pas consommer " + a.getNom() + " (Logique de jeu vidéo ça).");
            }
            it.remove();
        }
    }

    private Alliments.TypePersonnage auteurTypeToTypePersonnage(CharacterType t) {
        if (t == null) return Alliments.TypePersonnage.GAULOIS;
        switch (t) {
            case GAULOIS:
                return Alliments.TypePersonnage.GAULOIS;
            case ROMAIN:
                return Alliments.TypePersonnage.ROMAIN;
            default:
                System.out.println("/-/ ERREUR /-/");
                return Alliments.TypePersonnage.GAULOIS;
        }
    }

    public Potion demanderPotionAuDruide(Druide druide) {
        System.out.println("\n" + nom + " demande à son druide de concoctius une potion magique...");
        potionCourante = druide.fabriquerPotion();
        return potionCourante;
    }

    public void faireBoirePotion(Potion potion, PNJStats cible, int doses) {
        if (potion == null) {
            System.out.println("Aucune potion disponible, demandez au druide d'en préparer une.");
            return;
        }
        if (cible == null) {
            System.out.println("'Chef, sauf erreur de ma part, vous n'avez pas spécifix qui doit boire la potion...'");
            return;
        }
        System.out.println("\n" + nom + " fait boire " + doses + " dose(s) de potion à " + cible.getNom() + "...");
        java.util.List<Potion.Effet> effets = potion.boireDose(doses);
        if (effets.isEmpty()) {
            System.out.println("La potion n'a eu aucun effecius particulius.");
            return;
        }

        int soin = 20 * doses; //a modifier si besoin
        int sAvant = cible.getIndicateurSante();
        cible.setIndicateurSante(Math.min(100, sAvant + soin));
        cible.setNiveauPotionMagique(cible.getNiveauPotionMagique() + 1);
        System.out.println(" -> " + cible.getNom() + " : santé " + sAvant + " -> " + cible.getIndicateurSante());
        System.out.println("Effets reçus :");
        for (Potion.Effet e : effets) {
            System.out.println("    - " + e.getDescription());
        }
    }

    public boolean transfererPersonnageVers(PNJStats p, Lieux destination) {
        if (!subordonnes.remove(p)) {
            System.out.println("Ce personnage n'appartient pas à ce chef.");
            return false;
        }
        if (lieu != null) {
            lieu.setNbPersonnages(Math.max(0, lieu.getNbPersonnages() - 1));
        }
        if (destination != null) {
            destination.setNbPersonnages(destination.getNbPersonnages() + 1);
        }
        System.out.println(nom + " transfère " + p.getNom() + " vers " + (destination != null ? destination.getNom() : "(inconnu)"));
        return true;
    }

    public void ajouterAliment(Alliments aliment) {
        if (aliment != null) {
            inventaireAliments.add(aliment);
            System.out.println("Aliment ajouté : " + aliment.getNom());
        }
    }

    public Potion getPotionCourante() {
        return potionCourante;
    }

}
