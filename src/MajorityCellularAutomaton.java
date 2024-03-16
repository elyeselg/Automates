import java.util.Arrays;

/**
 * Classe représentant un automate cellulaire basé sur la règle de majorité.
 */
public class MajorityCellularAutomaton extends CellularAutomaton {
    private int neighborhoodSize;

    /**
     * Constructeur de la classe MajorityCellularAutomaton.
     *
     * @param rows             Le nombre de lignes dans le plateau de jeu.
     * @param cols             Le nombre de colonnes dans le plateau de jeu.
     * @param neighborhoodSize La taille du voisinage utilisé pour la règle de majorité.
     */
    public MajorityCellularAutomaton(int rows, int cols, int neighborhoodSize) {
        super(rows, cols);
        this.neighborhoodSize = neighborhoodSize;
    }

    /**
     * Initialise l'état initial du plateau de jeu de manière aléatoire.
     */
    @Override
    public void initializeRandomState() {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                state[i][j] = (Math.random() < 0.5) ? 0 : 1;
            }
        }
    }

    /**
     * Évolue l'automate cellulaire basé sur la règle de majorité sur un certain nombre d'étapes.
     *
     * @param numSteps Le nombre d'étapes d'évolution.
     */
    @Override
    public void evolve(int numSteps) {
        for (int step = 0; step < numSteps; step++) {
            System.out.println("Étape " + (step + 1));
            printState();
            applyRules();
        }
    }

    /**
     * Applique les règles d'évolution spécifiques à l'automate cellulaire basé sur la règle de majorité.
     */
    @Override
    protected void applyRules() {
        int[][] newState = new int[state.length][state[0].length];

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                int[] neighborhood = getNeighborhood(i, j);
                newState[i][j] = applyMajorityRuleToCell(neighborhood);
            }
        }

        state = newState;
    }

    /**
     * Obtient le voisinage d'une cellule donnée.
     *
     * @param row La ligne de la cellule.
     * @param col La colonne de la cellule.
     * @return Un tableau représentant le voisinage.
     */
    private int[] getNeighborhood(int row, int col) {
        int[] neighborhood = new int[neighborhoodSize * neighborhoodSize];
        int index = 0;

        for (int i = row - neighborhoodSize / 2; i <= row + neighborhoodSize / 2; i++) {
            for (int j = col - neighborhoodSize / 2; j <= col + neighborhoodSize / 2; j++) {
                if (i >= 0 && i < state.length && j >= 0 && j < state[0].length) {
                    neighborhood[index++] = state[i][j];
                } else {
                    neighborhood[index++] = 0; // Assume un état vide pour les voisins hors limites
                }
            }
        }

        return neighborhood;
    }

    /**
     * Applique la règle de majorité à une cellule en fonction de son voisinage.
     *
     * @param neighborhood Le voisinage de la cellule.
     * @return La valeur de la cellule après l'application de la règle de majorité.
     */
    private int applyMajorityRuleToCell(int[] neighborhood) {
        int sum = Arrays.stream(neighborhood).sum();
        // La cellule prend la valeur majoritaire dans le voisinage
        return (sum > neighborhood.length / 2) ? 1 : 0;
    }

    /**
     * Affiche l'état actuel de l'automate cellulaire basé sur la règle de majorité.
     */
    @Override
    protected void printState() {
        for (int[] row : state) {
            for (int cell : row) {
                System.out.print((cell == 1) ? "+ " : "- ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
