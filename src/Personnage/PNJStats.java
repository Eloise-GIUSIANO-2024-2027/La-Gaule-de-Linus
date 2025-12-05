package Personnage;

public class PNJStats {
    private String nom;
    private String sexe;
    private double taille;
    private int age;
    private int force;
    private int endurance;
    private int indicateurSante;
    private int indicateurFaim;
    private int indicateurBelligerance;
    private int niveauPotionMagique;
    private CharacterType type;
    private CharacterRole role;

    public PNJStats() {
    }

    public PNJStats(String nom, String sexe, double taille, int age, int force, int endurance,
                    int indicateurSante, int indicateurFaim, int indicateurBelligerance, int niveauPotionMagique,
                    CharacterType type, CharacterRole role) {
        this.nom = nom;
        this.sexe = sexe;
        this.taille = taille;
        this.age = age;
        this.force = force;
        this.endurance = endurance;
        this.indicateurSante = indicateurSante;
        this.indicateurFaim = indicateurFaim;
        this.indicateurBelligerance = indicateurBelligerance;
        this.niveauPotionMagique = niveauPotionMagique;
        this.type = type;
        this.role = role;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getIndicateurSante() {
        return indicateurSante;
    }

    public void setIndicateurSante(int indicateurSante) {
        this.indicateurSante = indicateurSante;
    }

    public int getIndicateurFaim() {
        return indicateurFaim;
    }

    public void setIndicateurFaim(int indicateurFaim) {
        this.indicateurFaim = indicateurFaim;
    }

    public int getIndicateurBelligerance() {
        return indicateurBelligerance;
    }

    public void setIndicateurBelligerance(int indicateurBelligerance) {
        this.indicateurBelligerance = indicateurBelligerance;
    }

    public int getNiveauPotionMagique() {
        return niveauPotionMagique;
    }

    public void setNiveauPotionMagique(int niveauPotionMagique) {
        this.niveauPotionMagique = niveauPotionMagique;
    }

    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
    }

    public CharacterRole getRole() {
        return role;
    }

    public void setRole(CharacterRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Stats {" +
                "nom='" + nom + '\'' +
                ", sexe='" + sexe + '\'' +
                ", taille=" + taille +
                ", age=" + age +
                ", force=" + force +
                ", endurance=" + endurance +
                ", indicateurSante=" + indicateurSante +
                ", indicateurFaim=" + indicateurFaim +
                ", indicateurBelligerance=" + indicateurBelligerance +
                ", niveauPotionMagique=" + niveauPotionMagique +
                ", type=" + type +
                ", role=" + role +
                '}';
    }
}
