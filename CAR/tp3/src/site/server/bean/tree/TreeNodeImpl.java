package site.server.bean.tree;

import java.rmi.RemoteException;

import site.server.bean.AbstractNode;

public class TreeNodeImpl extends AbstractNode implements TreeNode {

	private static final long serialVersionUID = 2785546018274519218L;
	private transient TreeNode father;
	private transient TreeNode[] sons;
	
	public TreeNodeImpl(int id) throws RemoteException {
		super(id);
		this.father = null;
		this.sons = null;
	}
	
	@Override
	public TreeNode getFather() throws RemoteException {
		return father;
	}
	
	@Override
	public TreeNode[] getSons() throws RemoteException {
		return sons;
	}
	
	@Override
	public void setFather(TreeNode father) throws RemoteException {
		this.father = father;
	}
	
	@Override
	public void setSons(TreeNode... sons) throws RemoteException {
		this.sons = sons;
		for (final TreeNode aSon : sons) {
			aSon.setFather(this);
		}
	}

}
