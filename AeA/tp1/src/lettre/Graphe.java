package lettre;

import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/**
 *
 * @author diagne
 */
public class Graphe {

	private MotGraphe[] _mots;
	private int _sup;
	private int _dif;

	public Graphe(String[] lesMots, int sup, int dif) throws LongueursMotsDifferentesException {
		final int nbMots = lesMots.length;
		_mots = new MotGraphe[nbMots];
		for (int i = 0; i < nbMots; i++) {
			_mots[i] = new MotGraphe(lesMots[i]);
		}
		_sup = sup;
		_dif = dif;
		initListeSuccesseursTousMots();
	}

	public MotGraphe getMot(int i) {
		return _mots[i];
	}

	public void ajouteArete(int s, int d) {
		final MotGraphe motS = _mots[s];
		final MotGraphe motD = _mots[d];
		motS.addSuccesseur(d);
		motD.addSuccesseur(s);
	}

	public static boolean diffUneLettre(String mot1, String mot2)
			throws LongueursMotsDifferentesException {
		if (mot1.length() != mot2.length()) {
			throw new LongueursMotsDifferentesException();
		}
		int nbDiff = 0;
		for (int i = 0; i < mot1.length(); i++) {
			if (mot1.charAt(i) != mot2.charAt(i))
				nbDiff++;
		}
		return (nbDiff == 1);
	}

	public void initListeSuccesseursMot(int indiceMot)
			throws LongueursMotsDifferentesException {
		for (int i = indiceMot; i < _mots.length; i++) {
			final Entry<Integer, Integer> operations = Graphe.getNbreOperations(_mots[i].getLibelle(), _mots[indiceMot].getLibelle());
			if (operations.getKey() <= _sup && operations.getValue() <= _dif)
				ajouteArete(i, indiceMot);
		}
	}

	public void initListeSuccesseursTousMots()
			throws LongueursMotsDifferentesException {
		for (int i = 0; i < _mots.length; i++) {
			initListeSuccesseursMot(i);
		}
	}

	public void dfs(int x) {
		_mots[x].setDejaVu(true);
		System.out.print(_mots[x] + " ");
		final List<Integer> listeSuccesseursMot = _mots[x]
				.getListeSuccesseurs();
		final Iterator<Integer> iterateurListe = listeSuccesseursMot.iterator();
		while (iterateurListe.hasNext()) {
			final int y = iterateurListe.next();
			final MotGraphe motY = _mots[y];
			if (!motY.dejaVu()) {
				motY.setPere(x);
				dfs(y);
			}
		}
	}

	public void bfsIteratif(int s) {
		final Deque<Integer> pileSommets = new ArrayDeque<Integer>();
		final int[] d = new int[_mots.length];
		d[s] = 0;
		_mots[s].setDejaVu(true);
		pileSommets.addLast(s);
		while (!pileSommets.isEmpty()) {
			final int x = pileSommets.removeFirst();
			final List<Integer> listeSuccesseursY = _mots[x]
					.getListeSuccesseurs();
			final Iterator<Integer> iterateurListe = listeSuccesseursY
					.iterator();
			while (iterateurListe.hasNext()) {
				final int y = iterateurListe.next();
				final MotGraphe motY = _mots[y];
				if (!motY.dejaVu()) {
					motY.setDejaVu(true);
					motY.setPere(x);
					d[y] = d[x] + 1;
					pileSommets.addLast(y);
				}
			}
		}
	}

	public int getIndiceMot(String mot) {
		for (int i = 0; i < _mots.length; i++) {
			if (mot.equals(_mots[i].getLibelle()))
				return i;
		}
		throw new IllegalArgumentException(mot + " n'est pas dans le tableau.");
	}

	public static void printListeMots(Deque<String> listeMots) {
		final Iterator<String> iterateurList = listeMots.iterator();
		while (iterateurList.hasNext()) {
			System.out.print(iterateurList.next() + " ");
		}
		System.out.println();
	}

	private Deque<String> getListeMotsFromTo(int indiceFrom, int indiceTo) {
		final Deque<String> listeMots = new ArrayDeque<String>();
		while (indiceTo != indiceFrom) {
			listeMots.addFirst(_mots[indiceTo].getLibelle());
			indiceTo = _mots[indiceTo].getPere();
		}
		listeMots.addFirst(_mots[indiceFrom].getLibelle());
		return listeMots;
	}

	public Deque<String> chemin(String from, String to, boolean verbose)
			throws LongueursMotsDifferentesException {
		if (verbose) {
			System.out.println("Graphe.chemin()");
		}
		if (from.length() != to.length()) {
			throw new LongueursMotsDifferentesException();
		}
		reset();
		int indiceFrom = getIndiceMot(from);
		int indiceTo = getIndiceMot(to);
		bfsIteratif(indiceFrom);
		if (_mots[indiceTo].getPere() == -1) {
			if (verbose) {
				System.out.println("Il n'y a pas de chemin entre "
						+ _mots[indiceFrom] + " et " + _mots[indiceTo]);
			}
			return null;
		} else {
			final Deque<String> listeMots = getListeMotsFromTo(indiceFrom,
					indiceTo);
			if (verbose) {
				System.out.println("Plus court chemin (" + from + " - " + to + ")");
				printListeMots(listeMots);
			}
			return listeMots;
		}
	}

	public Deque<String> getExcentriciteMot(int i, boolean[] dejaTraite)
			throws LongueursMotsDifferentesException {
		final MotGraphe mot = _mots[i];
		Deque<String> cheminExcentriciteMax = new ArrayDeque<String>();
		for (int j = 0; j < _mots.length; j++) {
			if (j != i && !dejaTraite[j]) {
				final Deque<String> cheminIJ = chemin(mot.getLibelle(),
						_mots[j].getLibelle(), false);
				if (cheminIJ != null
						&& cheminIJ.size() > cheminExcentriciteMax.size()) {
					cheminExcentriciteMax = cheminIJ;
				}
			}
		}
		return cheminExcentriciteMax;
	}

	public Deque<String> getCheminMaxExcentricite()
			throws LongueursMotsDifferentesException {
		Deque<String> max = new ArrayDeque<String>();
		final boolean[] dejaTraite = new boolean[_mots.length];
		for (int i = 0; i < _mots.length; i++) {
			final Deque<String> cheminExcentriciteCourant = getExcentriciteMot(
					i, dejaTraite);
			if (cheminExcentriciteCourant != null
					&& cheminExcentriciteCourant.size() > max.size()) {
				max = cheminExcentriciteCourant;
			}
			dejaTraite[i] = true;
		}
		return max;
	}

	public void visit() {
		System.out.println("Graphe.visit()");
		reset();
		int nbComposantes = 1;
		for (int i = 0; i < _mots.length; i++) {
			if (!_mots[i].dejaVu()) {
				System.out.printf("%2d: ", nbComposantes);
				dfs(i);
				System.out.println();
				nbComposantes++;
			}
		}
	}

	public void affiche() {
		System.out.println("Graphe.affiche()");
		for (int i = 0; i < _mots.length; i++) {

			final MotGraphe motCourant = _mots[i];
			System.out.printf("%2d", i);
			System.out.print(" : " + motCourant + " -> ");
			final List<Integer> listeSuccesseursMotCourant = motCourant
					.getListeSuccesseurs();
			final Iterator<Integer> iterateurListe = listeSuccesseursMotCourant
					.iterator();
			while (iterateurListe.hasNext()) {
				final int indiceSuccesseur = iterateurListe.next();
				System.out.print(_mots[indiceSuccesseur] + " ");
			}
			System.out.println();
		}
	}

	public static int[][] construireTableLevenshtein(String u, String v) {
		final int n = u.length();
		final int m = v.length();
		final int[][] table = new int[n + 1][m + 1];
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0) {
					table[i][j] = j;
				} else {
					if (j == 0) {
						table[i][j] = i;
					} else {
						if (u.charAt(i - 1) == v.charAt(j - 1)) {
							table[i][j] = table[i - 1][j - 1];
						} else {
							table[i][j] = 1 + Math.min(Math.min(
									table[i - 1][j - 1], table[i - 1][j]),
									table[i][j - 1]);
						}
					}
				}
			}
		}
		return table;
	}

	public static Entry<Integer, Integer> getNbreOperations(String u, String v) {
		int nbSup = 0;
		int nbDif = 0;
		final int n = u.length();
		final int m = v.length();
		final int[][] table = Graphe.construireTableLevenshtein(u, v);
		int i = n;
		int j = m;
		while (table[i][j] != 0 || i != 0 || j != 0) {
			if (i == 0) {
				j--;
			} else {
				if (j == 0) {
					nbSup++;
					i--;
				} else {
					if (u.charAt(i - 1) == v.charAt(j - 1)) {
						i--;
						j--;
					} else {
						if (table[i][j] == 1 + table[i][j - 1]) {
							j--;
						} else {
							if (table[i][j] == 1 + table[i - 1][j]) {
								nbSup++;
								i--;
							} else {
								nbDif++;
								i--;
								j--;
							}
						}
					}
				}
			}
		}
		return new AbstractMap.SimpleImmutableEntry<Integer, Integer>(nbSup, nbDif);
	}

	public void reset() {
		for (int i = 0; i < _mots.length; i++) {
			final MotGraphe motI = _mots[i];
			motI.setDejaVu(false);
			motI.setPere(-1);
		}
	}

	public static void main(String[] args)
			throws LongueursMotsDifferentesException {
//		final String[] dico3Court = { "gag", "gai", "gaz", "gel", "gks", "gin",
//				"gnu", "glu", "gui", "guy", "gre", "gue", "ace", "acm", "agi",
//				"ait", "aie", "ail", "air", "and", "alu", "ami", "arc", "are",
//				"art", "apr", "avr", "sur", "mat", "mur" };
		final Graphe graphe = new Graphe(Dicos.dico4, 0, 1);
		graphe.visit();
		System.out.println();
		graphe.chemin("lion", "peur", true);
		System.out.println();
		System.out.println("Chemin d'excentricité maximale : ");
		Graphe.printListeMots(graphe.getCheminMaxExcentricite());
	}
}
