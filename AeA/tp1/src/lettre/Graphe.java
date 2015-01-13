/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lettre;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author diagne
 */
public class Graphe {

	private MotGraphe[] _mots;

	public Graphe(String[] lesMots) {
		final int nbMots = lesMots.length;
		_mots = new MotGraphe[nbMots];
		for (int i = 0; i < nbMots; i++) {
			_mots[i] = new MotGraphe(lesMots[i]);
		}
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

	public boolean diffUneLettre(String mot1, String mot2)
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
			if (diffUneLettre(_mots[i].getLibelle(),
					_mots[indiceMot].getLibelle()))
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
			if (!_mots[y].dejaVu()) {
				_mots[y].setPere(x);
				dfs(y);
			}
		}
	}

	public void bfsIteratif(int x) {
		final Queue<Integer> pileSommets = new LinkedList<Integer>();
		_mots[x].setDejaVu(true);
		System.out.print(_mots[x] + " ");
		pileSommets.add(x);
		while (!pileSommets.isEmpty()) {
			final int y = pileSommets.remove();
			final List<Integer> listeSuccesseursY = _mots[y]
					.getListeSuccesseurs();
			final Iterator<Integer> iterateurListe = listeSuccesseursY
					.iterator();
			while (iterateurListe.hasNext()) {
				final int successeur = iterateurListe.next();
				if (!_mots[successeur].dejaVu()) {
					System.out.print(_mots[successeur] + " ");
					_mots[successeur].setDejaVu(true);
					pileSommets.add(successeur);
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

	public void chemin(String from, String to) {

	}

	public void visit() {
		System.out.println("Graphe.visit()");
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

	public void resetDejaVu() {
		for (int i = 0; i < _mots.length; i++) {
			_mots[i].setDejaVu(false);
		}
	}

	public void test() {
		for (int i = 0; i < _mots.length; i++) {
			System.out.print(_mots[i] + " -> ");
			if (_mots[i].getPere() != -1)
				System.out.println(_mots[_mots[i].getPere()]);
			else
				System.out.println();
		}
	}

	public static void main(String[] args)
			throws LongueursMotsDifferentesException {
		final String[] dico3Court = { "gag", "gai", "gaz", "gel", "gks", "gin",
				"gnu", "glu", "gui", "guy", "gre", "gue", "ace", "acm", "agi",
				"ait", "aie", "ail", "air", "and", "alu", "ami", "arc", "are",
				"art", "apr", "avr", "sur", "mat", "mur" };
		final Graphe graphe = new Graphe(dico3Court);
		graphe.initListeSuccesseursTousMots();
		graphe.affiche();
		System.out.println();
		graphe.visit();
		System.out.println();
		graphe.test();
		// graphe.bfsIteratif(graphe.getIndiceMot("lion"));
	}
}
