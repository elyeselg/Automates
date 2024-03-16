import java.util.Scanner;

/**
 * Une classe représentant le menu principal pour choisir et exécuter différents automates cellulaires.
 */
public class CellularAutomatonMenu {

    /**
     * Fonction principale du menu.
     *
     * @param args Les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("********************************************");
        System.out.println("*     Bienvenue dans le Menu Automate      *");
        System.out.println("********************************************");
        System.out.println("Choisissez un automate cellulaire :");
        System.out.println("1. Cellular Automaton 1D");
        System.out.println("2. Forest Fire");
        System.out.println("3. Game of Life");
        System.out.println("4. Majority Cellular Automaton");
        System.out.println("********************************************");

        int choice = getUserChoice(scanner);

        switch (choice) {
            case 1:
                runAutomaton(new CellularAutomaton1D(30, choice, new int[]{1, 0, 1}), scanner);
                break;
            case 2:
                runAutomaton(new ForestFire(10, 10, 0.5, 0.3), scanner);
                break;
            case 3:
                runAutomaton(new GameOfLife(10, 10), scanner);
                break;
            case 4:
                runAutomaton(new MajorityCellularAutomaton(10, 10, 3), scanner);
                break;
            default:
                System.out.println("Choix invalide");
        }

        scanner.close();
    }

    /**
     * Demande à l'utilisateur de saisir un choix.
     *
     * @param scanner Le scanner utilisé pour la saisie.
     * @return Le choix de l'utilisateur.
     */
    private static int getUserChoice(Scanner scanner) {
        System.out.print("Entrez votre choix : ");
        while (!scanner.hasNextInt()) {
            System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            System.out.print("Entrez votre choix : ");
            scanner.next(); // consommer l'entrée incorrecte
        }
        return scanner.nextInt();
    }

    /**
     * Exécute l'automate cellulaire choisi.
     *
     * @param automaton L'automate cellulaire à exécuter.
     * @param scanner   Le scanner utilisé pour la saisie.
     */
    private static void runAutomaton(CellularAutomaton automaton, Scanner scanner) {
        System.out.println("********************************************");
        System.out.println("*        " + automaton.getClass().getSimpleName() + "             *");
        System.out.println("********************************************");

        automaton.initializeRandomState();

        System.out.print("Entrez le nombre d'étapes : ");
        int numSteps = scanner.nextInt();

        automaton.evolve(numSteps);

        System.out.println("********************************************");
    }
}
