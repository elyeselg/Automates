/**
 * Classe représentant un automate cellulaire simulant un feu de forêt.
 */
public class ForestFire extends CellularAutomaton {
    private static final int EMPTY = 0;
    private static final int TREE = 1;
    private static final int BURNING = 2;

    private double treeDensity;
    private double ignitionProbability;

    /**
     * Constructeur de la classe ForestFire.
     *
     * @param rows               Le nombre de lignes dans la forêt.
     * @param cols               Le nombre de colonnes dans la forêt.
     * @param treeDensity        La densité d'arbres dans la forêt.
     * @param ignitionProbability La probabilité d'ignition d'un arbre.
     */
    public ForestFire(int rows, int cols, double treeDensity, double ignitionProbability) {
        super(rows, cols);
        this.treeDensity = treeDensity;
        this.ignitionProbability = ignitionProbability;
    }

    /**
     * Initialise l'état initial de la forêt de manière aléatoire en fonction de la densité d'arbres.
     */
    @Override
    public void initializeRandomState() {
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                state[i][j] = (Math.random() < treeDensity) ? TREE : EMPTY;
            }
        }
    }

    /**
     * Évolue la forêt de feu de forêt sur un certain nombre d'étapes.
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
     * Applique les règles d'évolution spécifiques au feu de forêt.
     */
    @Override
    protected void applyRules() {
        int[][] newForest = new int[state.length][state[0].length];

        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                if (state[i][j] == BURNING) {
                    newForest[i][j] = EMPTY; // La cellule brûlée devient vide
                } else if (state[i][j] == TREE) {
                    if (hasBurningNeighbor(i, j) || Math.random() < ignitionProbability) {
                        newForest[i][j] = BURNING; // Un arbre prend feu
                    } else {
                        newForest[i][j] = TREE; // L'arbre reste inchangé
                    }
                } else {
                    newForest[i][j] = EMPTY; // La cellule vide reste inchangée
                }
            }
        }

        state = newForest;
    }

    /**
     * Vérifie si un voisin en feu est présent autour d'une cellule donnée.
     *
     * @param row La ligne de la cellule.
     * @param col La colonne de la cellule.
     * @return True si un voisin en feu est présent, sinon False.
     */
    private boolean hasBurningNeighbor(int row, int col) {
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < state.length && j >= 0 && j < state[0].length && !(i == row && j == col) && state[i][j] == BURNING) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Affiche l'état actuel de la forêt de feu de forêt.
     */
    @Override
    protected void printState() {
        for (int[] row : state) {
            for (int cell : row) {
                if (cell == EMPTY) {
                    System.out.print("= "); // Cellule vide
                } else if (cell == TREE) {
                    System.out.print("+ "); // Arbre
                } else {
                    System.out.print("! "); // Feu
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
