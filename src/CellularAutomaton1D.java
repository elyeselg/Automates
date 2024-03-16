import java.util.Arrays;

/**
 * Une classe représentant un automate cellulaire unidimensionnel.
 * Cet automate évolue dans le temps en fonction de règles spécifiques appliquées à chaque cellule
 * en se basant sur l'état de ses voisins immédiats.
 */
public class CellularAutomaton1D extends CellularAutomaton {

    /**
     * La règle spécifique utilisée pour déterminer l'évolution des cellules.
     * La règle est un entier représentant la combinaison binaire des états voisins.
     */
    private int rule;

    /**
     * Constructeur de la classe {@code CellularAutomaton1D}.
     *
     * @param cols          Le nombre de colonnes de l'automate cellulaire unidimensionnel.
     * @param rule          La règle utilisée pour déterminer l'évolution des cellules.
     * @param initialState  L'état initial de l'automate cellulaire unidimensionnel.
     */
    public CellularAutomaton1D(int cols, int rule, int[] initialState) {
        super(1, cols);
        this.rule = rule;
        this.state[0] = Arrays.copyOf(initialState, initialState.length);
    }

    /**
     * Initialise l'état de l'automate cellulaire unidimensionnel de manière aléatoire.
     * Les cellules sont initialisées avec des valeurs aléatoires de 0 ou 1.
     */
    @Override
    public void initializeRandomState() {
        for (int i = 0; i < state[0].length; i++) {
            state[0][i] = (Math.random() < 0.5) ? 0 : 1;
        }
    }

    /**
     * Évolue l'automate cellulaire unidimensionnel sur un nombre spécifié d'étapes.
     *
     * @param numSteps Le nombre d'étapes pour faire évoluer l'automate cellulaire.
     */
    @Override
    public void evolve(int numSteps) {
        for (int step = 0; step < numSteps; step++) {
            System.out.println(Arrays.toString(state[0]));
            applyRules();
        }
    }

    /**
     * Applique les règles spécifiques de l'automate cellulaire unidimensionnel pour évoluer l'état des cellules.
     */
    @Override
    protected void applyRules() {
        int[] newState = Arrays.copyOf(state[0], state[0].length);

        for (int i = 0; i < state[0].length; i++) {
            int[] neighborhood = getNeighborhood(i);
            newState[i] = applyRuleToCell(neighborhood);
        }

        // Décaler les états
        System.arraycopy(state, 0, state, 1, state.length - 1);
        state[0] = newState;
    }

    /**
     * Affiche l'état actuel de l'automate cellulaire unidimensionnel.
     */
    @Override
    protected void printState() {
        System.out.println(Arrays.toString(state[0]));
    }

    /**
     * Applique la règle spécifique à une cellule en fonction de son voisinage.
     *
     * @param neighborhood Le voisinage de la cellule.
     * @return Le nouvel état de la cellule après l'application de la règle.
     */
    private int applyRuleToCell(int[] neighborhood) {
        // Appliquer la règle spécifique à l'automate 1D avec la règle fournie
        // Exemple de règle : la somme des voisins détermine le nouvel état
        return (rule >> (4 * neighborhood[0] + 2 * neighborhood[1] + neighborhood[2])) & 1;
    }

    /**
     * Obtient le voisinage d'une cellule à une position donnée.
     *
     * @param index La position de la cellule.
     * @return Le voisinage de la cellule, représenté par un tableau d'entiers.
     */
    private int[] getNeighborhood(int index) {
        int[] neighborhood = new int[3];
        for (int i = 0; i < 3; i++) {
            int neighborIndex = index - 1 + i;
            if (neighborIndex >= 0 && neighborIndex < state[0].length) {
                neighborhood[i] = state[0][neighborIndex];
            } else {
                neighborhood[i] = 0; // Assume un état vide pour les voisins hors limites
            }
        }
        return neighborhood;
    }
}
