package lettre;

import java.io.IOException;
import java.util.List;

public class Main {
	
	/**
	 * affiche sur la sortie d'erreur comment utiliser le programme
	 */
	private static void usage() {
		System.err.println("java lettre.Graphe <mot1> <mot2>");
		System.err
				.println("\tCe programme affiche les composantes connexes du graphe Dicos.dico4,");
		System.err
				.println("\taffiche un plus court chemin entre <mot1> et <mot2> s'il en existe");
		System.err
				.println("\tet affiche le chemin d'excentricité maximale du graphe.");
	}

	/**
	 * méthode principale
	 * 
	 * @param args
	 *            le tableau d'arguments
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			if (args.length < 5) {
				usage();
				return;
			}
			final String filename = args[0];
			final String mot1 = args[1];
			final String mot2 = args[2];
			final Integer sup = Integer.parseInt(args[3]);
			final Integer dif = Integer.parseInt(args[4]);
			final DicoReader dicoReader = new DicoReader();
			final List<String> motsGraphe = dicoReader.read(filename);
			final String[] motsGrapheArray = motsGraphe
					.toArray(new String[motsGraphe.size()]);
			final Graphe graphe = new Graphe(motsGrapheArray, sup, dif);
			System.out.println("***************************************");
			graphe.visit();
			System.out.println("***************************************");
			graphe.chemin(mot1, mot2, true);
			System.out.println("***************************************");
			System.out.println("Chemin d'excentricité maximale : ");
			Graphe.printListeMots(graphe.getCheminMaxExcentricite());
			System.out.println("***************************************");
		} catch (NumberFormatException e) {
			usage();
			return;
		}
	}

}
