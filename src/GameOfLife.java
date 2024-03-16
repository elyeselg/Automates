/**
 * Classe représentant un automate cellulaire simulant le "Jeu de la Vie".
 */
public class GameOfLife extends CellularAutomaton {

    /**
     * Constructeur de la classe GameOfLife.
     *
     * @param rows Le nombre de lignes dans le plateau de jeu.
     * @param cols Le nombre de colonnes dans le plateau de jeu.
     */
    public GameOfLife(int rows, int cols) {
        super(rows, cols);
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
     * Évolue le "Jeu de la Vie" sur un certain nombre d'étapes.
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
     * Applique les règles d'évolution spécifiques au "Jeu de la Vie".
     */
    @Override
    protected void applyRules() {
        int[][] newBoard = new int[state.length][state[0].length];

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                int neighbors = countLiveNeighbors(i, j);
                if (state[i][j] == 1) {
                    // Cellule vivante
                    newBoard[i][j] = (neighbors == 2 || neighbors == 3) ? 1 : 0;
                } else {
                    // Cellule morte
                    newBoard[i][j] = (neighbors == 3) ? 1 : 0;
                }
            }
        }

        state = newBoard;
    }

    /**
     * Compte le nombre de voisins vivants autour d'une cellule donnée.
     *
     * @param row La ligne de la cellule.
     * @param col La colonne de la cellule.
     * @return Le nombre de voisins vivants.
     */
    private int countLiveNeighbors(int row, int col) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < state.length && j >= 0 && j < state[0].length && !(i == row && j == col)) {
                    count += state[i][j];
                }
            }
        }

        return count;
    }

    /**
     * Affiche l'état actuel du "Jeu de la Vie".
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
