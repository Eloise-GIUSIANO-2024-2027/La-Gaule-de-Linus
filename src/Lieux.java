public class Lieux {

    private String nom;
    private int superficie;
    private String ChefDeLieux;
    private int NbPersonnages;
    private String Aliments;
    private String TypeLieux;
    private String ConquerieOuBienNan;


    public Lieux(String nom, int superficie, String ConquerieOuBienNan, String ChefDeLieux, int NbPersonnages, String Aliments,String TypeLieux) {
        this.nom = nom;
        this.superficie = superficie;
        this.ConquerieOuBienNan = ConquerieOuBienNan;
        this.ChefDeLieux = ChefDeLieux;
        this.NbPersonnages = NbPersonnages;
        this.Aliments = Aliments;
        this.TypeLieux = TypeLieux;
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

    public String getChefDeLieux() {
        return ChefDeLieux;
    }

    public void setChefDeLieux(String ChefDeLieux) {
        this.ChefDeLieux = ChefDeLieux;
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

//    public void SpecificationLieux() {
//        if (this.TypeLieux.equals("VillageGaulois")) {
//
//        }
//        if (this.TypeLieux.equals("CampRomain")) {
//
//        }
//        if (this.TypeLieux.equals("VilleRomain")) {
//
//        }
//        if (this.TypeLieux.equals("BourgadeGalloRomaine")) {
//
//        }
//        if (this.TypeLieux.equals("Enclos")) {
//
//        }
//        if (this.TypeLieux.equals("ChampsBataille")) {
//
//        }
//    }

    public  String toString() {
        return "Nom : " + this.nom + " | Type : " + this.TypeLieux;
    }
}
