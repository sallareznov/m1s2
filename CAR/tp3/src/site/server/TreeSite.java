package site.server;


public class TreeSite extends AbstractSite {

	private static final long serialVersionUID = 7038905654437218863L;
	private TreeSite father;
	private TreeSite[] sons;

	public TreeSite() {
		super();
	}

	public TreeSite(int id) {
		super(id);
		father = null;
		sons = null;
	}

	public TreeSite getPere() {
		return father;
	}

	public TreeSite[] getFils() {
		return sons;
	}

	public void setPere(TreeSite pere) {
		this.father = pere;
	}

	public void setFils(TreeSite... fils) {
		this.sons = fils;
		for (final TreeSite unFils : fils) {
			unFils.setPere(this);
		}
	}

}
