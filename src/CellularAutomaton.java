/**
 * La classe abstraite représentant un automate cellulaire.
 * Les automates cellulaires sont des modèles discrets définis sur une grille de cellules,
 * où l'état de chaque cellule évolue dans le temps en fonction de certaines règles.
 */
public abstract class CellularAutomaton {

    /**
     * Le tableau 2D représentant l'état de l'automate cellulaire.
     * Les éléments du tableau représentent l'état des cellules individuelles.
     * Les dimensions du tableau sont lignes x colonnes.
     */
    protected int[][] state;

    /**
     * Initialise une nouvelle instance de la classe {@code CellularAutomaton}
     * avec le nombre spécifié de lignes et de colonnes.
     *
     * @param rows Le nombre de lignes dans l'automate cellulaire.
     * @param cols Le nombre de colonnes dans l'automate cellulaire.
     */
    public CellularAutomaton(int rows, int cols) {
        this.state = new int[rows][cols];
    }

    /**
     * Méthode abstraite pour initialiser l'état de l'automate cellulaire de manière aléatoire.
     * Cette méthode doit être implémentée par les sous-classes concrètes pour fournir
     * une logique d'initialisation spécifique.
     */
    public abstract void initializeRandomState();

    /**
     * Méthode abstraite pour faire évoluer l'automate cellulaire sur un nombre spécifié d'étapes.
     * Cette méthode doit être implémentée par les sous-classes concrètes pour définir les règles
     * pour l'évolution de l'automate.
     *
     * @param numSteps Le nombre d'étapes pour faire évoluer l'automate cellulaire.
     */
    public abstract void evolve(int numSteps);

    /**
     * Méthode abstraite protégée pour appliquer les règles de l'automate cellulaire.
     * Les sous-classes concrètes doivent implémenter cette méthode pour définir les règles
     * spécifiques régissant l'évolution de l'automate.
     */
    protected abstract void applyRules();

    /**
     * Méthode abstraite protégée pour afficher l'état actuel de l'automate cellulaire.
     * Les sous-classes concrètes doivent implémenter cette méthode pour définir la manière dont l'état
     * est affiché à la console ou à une autre sortie.
     */
    protected abstract void printState();
}
