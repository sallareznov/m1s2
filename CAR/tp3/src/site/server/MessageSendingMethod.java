package site.server;

public interface MessageSendingMethod {
	
	void sendMessage(Site expediteur, VisitedSitesManager visitedSitesManager, MessageSendingManagerImpl messageSendingManager) throws InterruptedException;

}
