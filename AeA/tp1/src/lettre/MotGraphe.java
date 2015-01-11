package lettre;

import java.util.LinkedList;
import java.util.List;

public class MotGraphe {

	private String _libelleMot;
	private List<Integer> _listeSuccesseurs;

	public MotGraphe(String libelleMot) {
		_libelleMot = libelleMot;
		_listeSuccesseurs = new LinkedList<Integer>();
	}

	public String getLibelle() {
		return _libelleMot;
	}

	public int getLongueur() {
		return _libelleMot.length();
	}

	public List<Integer> getListeSuccesseurs() {
		return _listeSuccesseurs;
	}

	public void addSuccesseur(int i) {
		_listeSuccesseurs.add(i);
	}

	@Override
	public String toString() {
		return _libelleMot;
	}

}
