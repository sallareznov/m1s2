/**
 * Algorithme de Knuth, Morris et Pratt
 */
package algorithms;

import bases.Base;
import patterns.Genome;
import patterns.Strand;
import algorithms.util.StrandOccurences;

/**
 * @author leo
 *
 */
public class KMPAlgorithm extends Algorithm
{
	/**
	 * Chaque case contient la longueur du plus long mot <i>u</i> possible tel que
	 * <i>u</i> est un bord de M(0..i-1) | M étant le motif
	 * et uM(i) n'est pas un préfixe de M
	 * next[i] = -1 si un tel <i>u</i> n'existe pas 
	 */
	protected int[] next;
	
	/**
	 * @param genome
	 */
	public KMPAlgorithm(Genome genome)
	{
		super(genome);
	}

	@Override
	public StrandOccurences findRepetitiveStrand(Strand strand)
	{
		final StrandOccurences strandOccurences = new StrandOccurences();
		final Base[] genomeBases = getGenome().getBases();
		final Base[] strandBases = strand.getContent();
		this.preTreatment(strand);
		for (int i = 0; i < genomeBases.length - strand.getSize() + 1; i = i - this.next[i]) {
			int j = 0;
			while (j < strand.getSize()
					&& genomeBases[j + i].equals(strandBases[j])) {
				j++;
			}
			if (j == strand.getSize())
				strandOccurences.addIndex(i);
		}
		return strandOccurences;
	}

	protected void preTreatment(Strand strand)
	{
		this.next = new int[strand.getSize() + 1];
		Base[] bases = strand.getContent();
		
		for (int i = 0; i < this.next.length - 1; i++)
		{
			Base[] subStrand;
			if (i > 0)
				subStrand = new Base[i-1];
			else
				subStrand = new Base[0];
			
			Base motif;
			if (i > strand.getSize())
				motif = null;
			else
				motif = bases[i];
			
			System.arraycopy(bases, 0, subStrand, 0, subStrand.length);
			Strand edge = this.longestEdge(new Strand(subStrand));
			
			
			this.next[i] = this.edgeMotifIsNotPrefixeOfStrand(edge.getContent(), motif, strand);
		}
	}
	
	protected int edgeMotifIsNotPrefixeOfStrand(Base[] edge, Base motif, Strand strand)
	{
		Base[] motifBase;
		if (motif == null)
		{
			motifBase = new Base[edge.length];
			System.arraycopy(edge, 0, motifBase, 0, edge.length);
		}
		else
		{
			motifBase = new Base[edge.length + 1];
			System.arraycopy(edge, 0, motifBase, 0, edge.length);
			motifBase[edge.length] = motif;
		}
		
		Strand prefix = new Strand(motifBase);
		if (!strand.isPrefix(prefix))
			return edge.length;
		return -1;
	}
	
	protected Strand longestEdge(Strand strand)
	{
		Strand edge = new Strand(new Base[0]);
		for (int i = strand.getSize() - 2; i > 0 ; i--)
		{
			Strand prefixe = strand.getPrefix(i);
			Strand suffixe = strand.getSuffix(i);
			if (prefixe.equals(suffixe) && strand.isEdge(prefixe))
			{
				edge = prefixe;
				break;
			}
		}
		return edge;
	}
}
