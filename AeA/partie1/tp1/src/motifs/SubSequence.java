package motifs;

public class SubSequence {
	
	private Base[] content;
	
	public SubSequence(Base[] content) {
		this.content = content;
	}
	
	public SubSequence getReverse() {
		final int n = content.length;
		final Base[] reverseContent = new Base[n];
		for (int i = 0 ; i < n ; i++) {
			reverseContent[i] = content[n - i - 1];
		}
		return new SubSequence(reverseContent);
	}
	
	public SubSequence getComplementary() {
		final int n = content.length;
		final Base[] complementaryContent = new Base[n];
		for (int i = 0 ; i < n ; i++) {
			complementaryContent[i] = content[i].getComplementary();
		}
		return new SubSequence(complementaryContent);
	}
	
	public SubSequence getReverseComp() {
		final SubSequence reverseSubSequence = getReverse();
		return reverseSubSequence.getComplementary();
	}

}
