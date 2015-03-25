package site.server.bean.tree;

import java.rmi.RemoteException;

import site.server.bean.Node;

public interface TreeNode extends Node {
	
	TreeNode getFather() throws RemoteException;
	
	TreeNode[] getSons() throws RemoteException;
	
	void setFather(TreeNode father) throws RemoteException;
	
	void setSons(TreeNode... sons) throws RemoteException;
	
}
