package patterns;

import bases.Base;

public class EmptyStrand implements Strand {
	
	@Override
	public Base[] getContent() {
		return new Base[0];
	}
	
	public Base getBaseAt(int index)
	{
		return this.getContent()[index];
	}

	@Override
	public int getSize() {
		return 0;
	}

	@Override
	public Strand getReverse() {
		return new EmptyStrand();
	}

	@Override
	public Strand getComplementary() {
		return new EmptyStrand();
	}

	@Override
	public Strand getReverseComplementary() {
		return new EmptyStrand();
	}

	@Override
	public Strand getPrefix(int size) {
		return new EmptyStrand();
	}

	@Override
	public Strand getSuffix(int size) {
		return new EmptyStrand();
	}
	
	@Override
	public Strand getLongestEdge() {
		return new EmptyStrand();
	}

	@Override
	public boolean isPrefix(Strand prefix) {
		return false;
	}

	@Override
	public boolean isSuffix(Strand suffix) {
		return false;
	}

	@Override
	public boolean isEdge(Strand preAndSuffix) {
		return false;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof EmptyStrand);
	}
	
	@Override
	public Strand clone() {
	    return new EmptyStrand();
	}

}
