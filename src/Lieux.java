import java.util.ArrayList;
import java.util.List;

public class Lieux {

    private String nom;
    private int superficie;
    private ChefDeClan chefDeLieux;
    private int NbPersonnages;
    private String Aliments;
    private String TypeLieux;
    private String ConquerieOuBienNan;
    private List<PNJStats> personnagesPresents = new ArrayList<>();


    public Lieux(String nom, int superficie, String ConquerieOuBienNan, ChefDeClan chefDeLieux,
                 int NbPersonnages, String Aliments, String TypeLieux) {
        this.nom = nom;
        this.superficie = superficie;
        this.ConquerieOuBienNan = ConquerieOuBienNan;
        this.chefDeLieux = chefDeLieux;
        this.NbPersonnages = NbPersonnages;
        this.Aliments = Aliments;
        this.TypeLieux = TypeLieux;
    }

    public Lieux(String nom, int superficie, String ConquerieOuBienNan, String chefNom,
                 int NbPersonnages, String Aliments, String TypeLieux) {
        this.nom = nom;
        this.superficie = superficie;
        this.ConquerieOuBienNan = ConquerieOuBienNan;
        this.chefDeLieux = null;
        this.NbPersonnages = NbPersonnages;
        this.Aliments = Aliments;
        this.TypeLieux = TypeLieux;
    }

    public void setConquerieOuBienNan(String ConquerieOuBienNan) {
        this.ConquerieOuBienNan = ConquerieOuBienNan;
    }

    public boolean peutContenirPersonnage(PNJStats personnage) {
        if (personnage == null) {
            return false;
        }

        CharacterType type = personnage.getType();
        CharacterRole role = personnage.getRole();

        switch (this.TypeLieux) {
            case "VillageGaulois":
                return type == CharacterType.GAULOIS || type == CharacterType.CREATURE;

            case "CampRomain":
                if (type == CharacterType.CREATURE) {
                    return true;
                }
                if (type == CharacterType.ROMAIN) {
                    return role == CharacterRole.LEGIONNAIRE ||
                            role == CharacterRole.PREFET ||
                            role == CharacterRole.GENERAL;
                }
                return false;

            case "VilleRomain":
                return type == CharacterType.ROMAIN || type == CharacterType.CREATURE;

            case "BourgadeGalloRomaine":
                return type == CharacterType.GAULOIS || type == CharacterType.ROMAIN;

            case "Enclos":
                return type == CharacterType.CREATURE;

            case "ChampsBataille":
                return true;

            default:
                System.out.println("Type de lieu inconnu : " + this.TypeLieux);
                return false;
        }
    }

    public String getMessageRefus(PNJStats personnage) {
        if (personnage == null) {
            return "Personnage invalide.";
        }

        CharacterType type = personnage.getType();
        CharacterRole role = personnage.getRole();

        switch (this.TypeLieux) {
            case "VillageGaulois":
                return "Un village gaulois ne peut accueillir que des Gaulois et des créatures fantastiques.";

            case "CampRomain":
                if (type == CharacterType.GAULOIS) {
                    return "Un Gaulois ne peut pas entrer dans un camp romain !";
                }
                if (type == CharacterType.ROMAIN &&
                        role != CharacterRole.LEGIONNAIRE &&
                        role != CharacterRole.PREFET &&
                        role != CharacterRole.GENERAL) {
                    return "Seuls les combattants romains peuvent être dans un camp retranché.";
                }
                return "Ce personnage ne peut pas être dans un camp romain.";

            case "VilleRomain":
                return "Une ville romaine ne peut accueillir que des Romains et des créatures fantastiques.";

            case "BourgadeGalloRomaine":
                return "Une bourgade gallo-romaine ne peut accueillir que des Gaulois et des Romains.";

            case "Enclos":
                return "Un enclos ne peut contenir que des créatures fantastiques.";

            case "ChampsBataille":
                return "Tous les personnages peuvent aller sur un champ de bataille.";

            default:
                return "Type de lieu inconnu.";
        }
    }

    public String getConquerieOuBienNan() {
        return ConquerieOuBienNan;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public ChefDeClan getChefDeLieux() {
        return chefDeLieux;
    }

    public void setChefDeLieux(ChefDeClan chefDeLieux) {
        this.chefDeLieux = chefDeLieux;
    }

    public int getNbPersonnages() {
        return NbPersonnages;
    }

    public void setNbPersonnages(int NbPersonnages) {
        this.NbPersonnages = NbPersonnages;
    }

    public String getAliments() {
        return Aliments;
    }

    public void setAliments(String Aliments) {
        this.Aliments = Aliments;
    }

    public String getTypeLieux() {
        return TypeLieux;
    }

    public void setTypeLieux(String TypeLieux) {
        this.TypeLieux = TypeLieux;
    }

    public void ajouterPersonnage(PNJStats personnage) {
        if (personnage == null) {
            System.out.println("Erreur : Tentative d'ajouter un personnage null.");
            return;
        }

        if (peutContenirPersonnage(personnage)) {
            this.personnagesPresents.add(personnage);
            personnage.setLieuActuel(this);
            System.out.println(personnage.getNom() + " est arrivé à " + this.getNom() + ".");
        } else {
            System.out.println("Transfert impossible. " + getMessageRefus(personnage));
        }
    }

    public void retirerPersonnage(PNJStats personnage) {
        if (this.personnagesPresents.remove(personnage)) {
            // personnage.setLieuActuel(null);
            System.out.println(personnage.getNom() + " a quitté " + this.getNom() + ".");
        } else {
            System.out.println("Erreur: " + personnage.getNom() + " n'était pas présent dans " + this.getNom() + ".");
        }
    }

    public List<PNJStats> getPersonnagesPresents() {
        return personnagesPresents;
    }

    public String toString() {
        return "Nom : " + this.nom + " | Type : " + this.TypeLieux;
    }
}
