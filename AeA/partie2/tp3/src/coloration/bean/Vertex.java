package coloration.bean;

public class Vertex {
	
	private String name;
	private int color;

	public Vertex(String name) {
		this.name = name;
		this.color = -1;
	}

	public String getName() {
		return name;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public boolean isColored() {
		return color != -1;
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
