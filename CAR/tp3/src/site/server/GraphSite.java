package site.server;

public class GraphSite extends AbstractSite {

	private static final long serialVersionUID = -1100015806680768624L;
	private GraphSite[] neighbors;
	
	public GraphSite() {
		super();
	}
	
	public GraphSite(int id) {
		super(id);
		neighbors = null;
	}
	
	public GraphSite[] getNeighbors() {
		return neighbors;
	}
	
	public void setNeighbors(GraphSite[] neighbors) {
		this.neighbors = neighbors;
	}

}
