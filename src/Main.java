public class Main {

    private static void pause() {
        try {
            Thread.sleep(1500L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La pause a été interrompue : " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        System.out.println("Bienvenue dans LA GAULE DE LINUS");
        pause();
        System.out.println("Nous sommes en 50 avant Jésius-Christius.");
        pause();
        System.out.println("Toute la Gaule est occupée par les Romains...");
        pause();
        System.out.println("Toute ?");
        pause();
        System.out.println("Non !");
        pause();
        System.out.println("Un petit village d'irréductibles gaulois résiste encore et toujours à l'envahisseur.");
        pause();
        System.out.println("Un village nommé Beurk");
        pause();
        System.out.println("Votre mission si vous l'acceptez, et de reconquérir la Gaule.");

        pause();
        pause();

        Menu.lancerJeu();

    }
}
