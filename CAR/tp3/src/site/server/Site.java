package site.server;

import java.io.Serializable;

public class Site implements Serializable {

	private static final long serialVersionUID = 7038905654437218863L;
	private int id;
	private Site pere;
	private Site[] fils;
	private String message;

	public Site() {
	}

	public Site(int id) {
		this();
		this.id = id;
		pere = null;
		fils = null;
		message = "";
	}

	public int getId() {
		return id;
	}

	public Site getPere() {
		return pere;
	}

	public Site[] getFils() {
		return fils;
	}

	public String getMessage() {
		return message;
	}

	public void setPere(Site pere) {
		this.pere = pere;
	}

	public void setFils(Site... fils) {
		this.fils = fils;
		for (final Site unFils : fils) {
			unFils.setPere(this);
		}
	}

	public synchronized void setMessage(String message) {
		this.message = message;
	}

	public void reset() {
		message = "";
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
		final Site site = (Site) obj;
		return site.getId() == id;
	}

}
