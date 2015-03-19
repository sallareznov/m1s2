package site.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SiteFactoryImpl extends UnicastRemoteObject implements SiteFactory {

	private static final long serialVersionUID = -724629253681607589L;

	public SiteFactoryImpl() throws RemoteException {
	}

	@Override
	public Site create(int id) throws RemoteException {
		return new Site(id);
	}

}
