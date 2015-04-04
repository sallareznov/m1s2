package mst.sort;


public class MinHeap<T extends Comparable<T>> {

	private T[] values;
	private int lastElementIndex;

	@SuppressWarnings("unchecked")
	public MinHeap(int size) {
		values = (T[]) new Comparable[size];
		for (int i = 0; i < size; i++) {
			values[i] = null;
		}
		lastElementIndex = -1;
	}
	
	public T[] getValues() {
		return values;
	}
	
	public int size() {
		return lastElementIndex + 1;
	}
	
	private int getFatherIndex(int index) {
		return (index - 1) / 2;
	}

	private int getLeftChildIndex(int index) {
		return (2 * index) + 1;
	}

	private int getRightChildIndex(int index) {
		return (2 * index) + 2;
	}

	private void swapValues(int index1, int index2) {
		final T temp = values[index1];
		values[index1] = values[index2];
		values[index2] = temp;
	}
	
	public boolean add(T value) {
		if (lastElementIndex == values.length - 1) {
			return false;
		}
		addToTree(value);
		percolateUp();
		return true;
	}
	
	private void addToTree(T value) {
		lastElementIndex++;
		values[lastElementIndex] = value;
	}
	
	public T getValue(int index) {
		return values[index];
	}
	
	public T top() {
		if (lastElementIndex < 0) {
			return null;
		}
		return values[0];
	}

	public T remove() {
		if (lastElementIndex < 0) {
			return null;
		}
		final T topElement = values[0];
		percolateDown();
		return topElement;
	}

	private void percolateUp() {
		int currentIndex = lastElementIndex;
		int fatherIndex = getFatherIndex(currentIndex);
		while (currentIndex != 0
				&& values[currentIndex].compareTo(values[fatherIndex]) < 0) {
			swapValues(currentIndex, fatherIndex);
			currentIndex = fatherIndex;
			fatherIndex = getFatherIndex(currentIndex);
		}
	}

	private void percolateDown() {
		values[0] = values[lastElementIndex];
		values[lastElementIndex] = null;
		lastElementIndex--;
		int currentIndex = 0;
		int leftChildIndex = getLeftChildIndex(currentIndex);
		int rightChildIndex = getRightChildIndex(currentIndex);
		while ((leftChildIndex <= lastElementIndex)) {
//			System.out.println("currentIndex = " + values[currentIndex]);
//			System.out.println("leftChild = " + values[leftChildIndex]);
//			System.out.println("rightChild = " + values[rightChildIndex]);
			if (((values[leftChildIndex] == null && values[rightChildIndex] == null) || (values[leftChildIndex] != null
					&& values[rightChildIndex] != null
					&& values[currentIndex].compareTo(values[leftChildIndex]) <= 0 && values[currentIndex]
					.compareTo(values[rightChildIndex]) <= 0))) {
				return;
			}
			if (values[rightChildIndex] == null) {
				if ((values[currentIndex].compareTo(values[leftChildIndex]) > 0)) {
					swapValues(currentIndex, leftChildIndex);
					currentIndex = leftChildIndex;
				} else {
					return;
				}
			} else {
				if ((values[currentIndex].compareTo(values[leftChildIndex]) > 0 && values[leftChildIndex]
						.compareTo(values[rightChildIndex]) <= 0)) {
					swapValues(currentIndex, leftChildIndex);
					currentIndex = leftChildIndex;
				} else if (values[leftChildIndex]
						.compareTo(values[rightChildIndex]) >= 0
						&& values[currentIndex]
								.compareTo(values[rightChildIndex]) > 0) {
					swapValues(currentIndex, rightChildIndex);
					currentIndex = rightChildIndex;
				}
			}
			leftChildIndex = getLeftChildIndex(currentIndex);
			rightChildIndex = getRightChildIndex(currentIndex);
		}
	}
	
	public boolean contains(T value) {
		for (int i = 0 ; i < values.length ; i++) {
			if (values[i] == null)
				return false;
			if (values[i].equals(value))
				return true;
		}
		return false;
	}
	
	public boolean remove(T value) {
		for (int i = 0 ; i < values.length ; i++) {
			if (values[i] == null)
				return false;
			if (values[i].equals(value)) {
				values[i] = null;
				percolateDown();
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		for (int i = 0 ; i < size() ; i++) {
			builder.append(getValue(i) + "\n");
		}
		return builder.toString();
	}

}
