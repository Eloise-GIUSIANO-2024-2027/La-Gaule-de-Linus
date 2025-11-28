import java.util.Scanner;

public class Main {

    private static void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La pause a été interrompue : " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        final long DELAI_MS = 1500;

        System.out.println("Bienvenue dans LA GAULE DE LINUS");
        pause(DELAI_MS);
        System.out.println("Nous sommes en 50 avant Jésus-Christ.");
        pause(DELAI_MS);
        System.out.println("Toute la Gaule est occupée par les Romains... ");
        pause(DELAI_MS);
        System.out.println("Toute ? ");
        pause(DELAI_MS);
        System.out.println("Non !");
        pause(DELAI_MS);
        System.out.println("Un petit village d'irréductibles gaulois résiste encore et toujours à l'envahisseur. ");
        pause(DELAI_MS);
        System.out.println("Un village nommé Beurk");
        pause(DELAI_MS);
        System.out.println("Votre mission si vous l'acceptez, et de reconquérir la Gaule");

        pause(DELAI_MS);
        pause(DELAI_MS);

        Menu.lancerJeu();

    }
}