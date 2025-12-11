public class Main {

    private static void pause() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("La pause a été interrompue : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Theatre.initialiserLieuxDesTheatres();
        System.out.println("Bienvenue dans");
        pause();
        System.out.println("    __             ______            __            __        __    _____   ____  _______\n" +
                "   / /   ____ _   / ____/___ ___  __/ /__     ____/ /__     / /   /  _/ | / / / / / ___/\n" +
                "  / /   / __ `/  / / __/ __ `/ / / / / _ \\   / __  / _ \\   / /    / //  |/ / / / /\\__ \\ \n" +
                " / /___/ /_/ /  / /_/ / /_/ / /_/ / /  __/  / /_/ /  __/  / /____/ // /|  / /_/ /___/ / \n" +
                "/_____/\\__,_/   \\____/\\__,_/\\__,_/_/\\___/   \\__,_/\\___/  /_____/___/_/ |_/\\____//____/  ");
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