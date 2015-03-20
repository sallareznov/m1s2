package mst;

public abstract class MSTFinder {
	
//	public Ridge getWeakerOutgoingRidge(MST mst, NeighborsManager neighborsManager) {
//		for (Ridge ridge : mst.get)
//		return null;
//	}
	
	public abstract WeightedGraph findMST(WeightedGraph graphe) throws CloneNotSupportedException;
	
}
