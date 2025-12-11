import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Theatre {

    private String nom;
    private int NbLieuxMAX;
    private List<Lieux> lieuxContenus;
    private Boolean PossederOuPas;
    private static Boolean possederTheatre0 = true;
    private static Boolean possederTheatre1 = false;
    private static Boolean possederTheatre2 = false;
    private static Boolean possederTheatre3 = false;
    private ChefDeClan chefDeClan;
    public static List<PNJStats> personnagesTheatre0 = new ArrayList<>();
    public static List<PNJStats> personnagesTheatre1 = new ArrayList<>();
    public static List<PNJStats> personnagesTheatre2 = new ArrayList<>();
    public static List<PNJStats> personnagesTheatre3 = new ArrayList<>();

    public Theatre(String nom, int NbLieuxMAX, Boolean PossederOuPas, ChefDeClan chef) {
        this.nom = nom;
        this.lieuxContenus = new ArrayList<>();
        this.NbLieuxMAX = NbLieuxMAX;
        this.PossederOuPas = PossederOuPas;
        this.chefDeClan = chef;
    }

    public Boolean getPossederOuPas() {
        return PossederOuPas;
    }

    public void setPossederOuPas(Boolean PossederOuPas) {
        this.PossederOuPas = PossederOuPas;
    }

    public  String getNom(){
        return nom;
    }

    public List<PNJStats> getPersonnagesTheatre(int numeroTheatre) {
        switch (numeroTheatre) {
            case 0:
                return personnagesTheatre0;
            case 1:
                return personnagesTheatre1;
            case 2:
                return personnagesTheatre2;
            case 3:
                return personnagesTheatre3;
            default:
                return new ArrayList<>();
        }
    }

    public List<Lieux> getLieuxContenus() {
        return lieuxContenus;
    }

    public ChefDeClan getChefDeClan() {
        return chefDeClan;
    }

    public static final ChefDeClan chefDepart = new ChefDeClan("Abraracourcix", "M", 50, null);
    public static final ChefDeClan chefFacile = new ChefDeClan("Assurancetourix", "M", 48, null);
    public static final ChefDeClan chefMoyen = new ChefDeClan("Jolitorax", "M", 45, null);
    public static final ChefDeClan chefDifficile = new ChefDeClan("Cetautomotix", "M", 38, null);

    public static Theatre theatreDepart = new Theatre("Théâtre 0", 1, true, chefDepart);
    public static Theatre theatreFacile = new Theatre("Théâtre I", 2, false, chefFacile);
    public static Theatre theatreMoyen = new Theatre("Théâtre II", 2, false, chefMoyen);
    public static Theatre theatreDifficile = new Theatre("Théâtre III", 2, false, chefDifficile);

    public boolean ajouterLieu(Lieux nouveauLieu) {
        if (nouveauLieu == null) {
            System.err.println("Erreur : tentative d'ajout d'un lieu null");
            return false;
        }

        if (lieuxContenus.size() < NbLieuxMAX) {
            if (nouveauLieu.getChefDeLieux() != this.chefDeClan) {
                nouveauLieu.setChefDeLieux(this.chefDeClan);
            }
            lieuxContenus.add(nouveauLieu);
            return true;
        } else {
            return false;
        }
    }

    public static void initialiserLieuxDesTheatres() {
        theatreDepart.ajouterLieu(Carte.getBeurk());

        theatreFacile.ajouterLieu(Carte.getTiramisum());
        theatreFacile.ajouterLieu(Carte.getCielus());

        theatreMoyen.ajouterLieu(Carte.getLaudanum());
        theatreMoyen.ajouterLieu(Carte.getBabaorum());

        theatreDifficile.ajouterLieu(Carte.getAquarium());
        theatreDifficile.ajouterLieu(Carte.getHelium());
    }

    private static void pause() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La pause a été interrompue : " + e.getMessage());
        }
    }

    public Theatre() {
        System.out.println("***BRUIT-DE-TROMPETTE***");
        pause();
        System.out.println("/-/ Bienvenue dans les Théâtre d'envahissement ! /-/");
        pause();
        System.out.println("\n");
        System.out.println("Voici les théatres : ");
        pause();
        System.out.println("- Théâtre 0 [Départ - Déjà conquis]");
        System.out.println("- Théâtre I [Facile]");
        System.out.println("- Théâtre II [Moyen]");
        System.out.println("- Théâtre III [Difficile]");
        pause();

        afficherMenuPrincipal();

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while(continuer) {
            System.out.print("Entrez votre choix : ");
            String choix = scanner.nextLine().trim().toUpperCase();

            switch (choix) {
                case "1":
                    pause();
                    System.out.println("\n");
                    System.out.println("Voici les renseignements");
                    LesRenseignementTheatre();
                    pause();
                    pause();
                    pause();
                    pause();
                    afficherMenuPrincipal();
                    break;
                case "2":
                    System.out.println("\n");
                    System.out.println("Allons y ! Sauvons la gaule !");
                    continuer = false;
                    break;
                case "3":
                    System.out.println("\n");
                    System.out.println("AAAAAAh mes ou sont mes troupes ");
                    continuer = false;
                    break;
                case "4":
                    System.out.println("\n");
                    System.out.println("Retours au menu  ");
                    continuer = false;
                    break;
                default:
                    System.out.println("\n");
                    afficherMenuPrincipal();
                    break;
            }
            if (continuer) {
                System.out.print("\nAppuyez sur ENTRÉE pour continuer...");
                scanner.nextLine();
            }
        }
    }

    private void afficherMenuPrincipal() {
        System.out.println("\n");
        System.out.println("Que voulez vous faire ? ");
        pause();
        System.out.println("1.Renseignements");
        System.out.println("2.Bataille");
        System.out.println("3.Mouvement de troupes");
        System.out.println("4.Retours au menu principale");
    }

    public void LesRenseignementTheatre() {
        System.out.println("o________o");
        System.out.println("|        |");
        System.out.println("|________|");
        System.out.println("|");
        System.out.println("| Voila les renseignement sur les Théâtre d'envahissement: ");
        pause();
        System.out.println("\n" + theatreDepart);
        pause();
        System.out.println("\n" + theatreFacile);
        pause();
        System.out.println("\n" + theatreMoyen);
        pause();
        System.out.println("\n" + theatreDifficile);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- ").append(this.nom).append(" ---\n");
        sb.append("Nombre de Lieux Max : ").append(this.NbLieuxMAX).append("\n");
        sb.append("Nombre de Lieux Actuels : ").append(this.lieuxContenus.size()).append("\n");
        sb.append("Lieux Contenus : \n");
        if (this.lieuxContenus.isEmpty()) {
            sb.append("Aucun lieu pour le moment.\n");
        } else {
            for (Lieux lieu : this.lieuxContenus) {
                sb.append("").append(lieu.toString()).append("\n");
            }
        }
        sb.append("Statut du Théâtre : ");
        if (this.PossederOuPas == false) {
            sb.append("Le théâtre n'as pas encore était conquis");
        } else {
            sb.append("Vous avez conquis ce théâtre !");
        }

        return sb.toString();
    }
}