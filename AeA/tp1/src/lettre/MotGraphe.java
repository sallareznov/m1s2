package lettre;

import java.util.LinkedList;
import java.util.List;

public class MotGraphe {

	private String _libelleMot;
	private List<Integer> _listeSuccesseurs;
	private boolean _dejaVu;
	private int _pere;

	public MotGraphe(String libelleMot) {
		_libelleMot = libelleMot;
		_listeSuccesseurs = new LinkedList<Integer>();
		_dejaVu = false;
		_pere = -1;
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
	
	public boolean dejaVu() {
		return _dejaVu;
	}
	
	public int getPere() {
		return _pere;
	}
	
	public void setDejaVu(boolean dejaVu) {
		_dejaVu = dejaVu;
	}
	
	public void setPere(int pere) {
		_pere = pere;
	}

	public void addSuccesseur(int i) {
		_listeSuccesseurs.add(i);
	}

	@Override
	public String toString() {
		return _libelleMot;
	}

}
