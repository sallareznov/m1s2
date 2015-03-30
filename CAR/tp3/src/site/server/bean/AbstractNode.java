package site.server.bean;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Logger;

import site.shared.logger.LoggerFactory;

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
		if ("".equals(message)) {
			LOGGER.info("My message has been resetted ! I don't have any message now");
			return;
		}
		LOGGER.info("I've just received a message ! My message is now : \"" + this.message + "\"");
	}
	
	@Override
	public void setMessageWithSender(String message, Node sender) throws RemoteException {
		this.message = message;
		if (!"".equals(message)) {
			LOGGER.info("I've just received a message from " + sender.printId() + " ! Now my message is : \"" + this.message + "\"");
			return;
		}
		LOGGER.info("My message has been resetted ! I don't have any message now");
	}

	@Override
	public void resetMessage() throws RemoteException {
		setMessage("");
	}
	
	@Override
	public String printId() throws RemoteException {
		return "Node " + id;
	}
	
	@Override
	public String getAllInfos() throws RemoteException {
		final StringBuilder builder = new StringBuilder();
		builder.append("Node ");
		builder.append(id);
		builder.append(" : ");
		builder.append("\n");
		builder.append("message = ");
		builder.append("".equals(message) ? "No message" : message);
		return builder.toString();
	}

}
