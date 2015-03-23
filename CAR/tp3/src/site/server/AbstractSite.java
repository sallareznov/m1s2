package site.server;

public abstract class AbstractSite implements Site {
	
	private static final long serialVersionUID = -8611133904341805110L;
	private int id;
	private String message;
	
	public AbstractSite() {
	}
	
	public AbstractSite(int id) {
		this();
		this.id = id;
		message = "";
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public void reset() {
		setMessage("");
	}

	@Override
	public synchronized void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Site " + id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != getClass()) {
			return false;
		}
		final TreeSite site = (TreeSite) obj;
		return site.getId() == id;
	}

}
