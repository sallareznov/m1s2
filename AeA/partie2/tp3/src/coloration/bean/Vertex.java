package coloration.bean;

public class Vertex {
	
	private String name;

	public Vertex(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj.getClass().equals(getClass())) {
			final Vertex vertex = (Vertex) obj;
			return name.equals(vertex.getName());
		}
		return false;
	}

}
