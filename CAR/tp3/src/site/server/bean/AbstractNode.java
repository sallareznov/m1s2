package site.server.bean;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import site.shared.LoggerFactory;

public abstract class AbstractNode extends UnicastRemoteObject implements Node {
	
	private static final Logger LOGGER = LoggerFactory.create(AbstractNode.class);
	private static final long serialVersionUID = 1028010952148000335L;
	private int id;
	private String message;
	
	public AbstractNode(int id) throws RemoteException {
		this.id = id;
		this.message = "";
	}

	@Override
	public int getId() throws RemoteException {
		return id;
	}

	@Override
	public String getMessage() throws RemoteException {
		return message;
	}

	@Override
	public void setMessage(String message) throws RemoteException {
		this.message = message;
		LOGGER.info(printMe());
		LOGGER.info(" <--- message = " + this.message);
	}

	@Override
	public void resetMessage() throws RemoteException {
		setMessage("");
	}
	
	@Override
	public String printMe() throws RemoteException {
		return "Node " + id;
	}
	

}
