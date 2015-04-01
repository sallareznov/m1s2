package mst.sort;

public class MinHeap {

	private int[] values;
	private int lastElementIndex;

	public MinHeap(int size) {
		values = new int[size];
		for (int i = 0; i < size; i++) {
			values[i] = -1;
		}
		lastElementIndex = -1;
	}

	public int remove() {
		int topElement = values[0];
		percolateDown();
		return topElement;
	}

	public void addToTree(int value) {
		lastElementIndex++;
		values[lastElementIndex] = value;
	}

	public int[] getValues() {
		return values;
	}

	public void percolateUp() {
		int currentIndex = lastElementIndex;
		int fatherIndex = getFatherIndex(currentIndex);
		while (currentIndex != 0 && values[currentIndex] < values[fatherIndex]) {
			swapValues(currentIndex, fatherIndex);
			currentIndex = fatherIndex;
			fatherIndex = getFatherIndex(currentIndex);
		}
		System.out.println("heap = ");
		for (int value : values) {
			System.out.println(value);
		}
	}

	public boolean add(int value) {
		if (lastElementIndex == values.length - 1) {
			return false;
		}
		addToTree(value);
		percolateUp();
		return true;
	}

	public int getFatherIndex(int index) {
		return (index - 1) / 2;
	}

	public int getLeftChildIndex(int index) {
		return (2 * index) + 1;
	}

	public int getRightChildIndex(int index) {
		return (2 * index) + 2;
	}

	public void swapValues(int index1, int index2) {
		int temp = values[index1];
		values[index1] = values[index2];
		values[index2] = temp;
	}

	public void percolateDown() {
		values[0] = values[lastElementIndex];
		values[lastElementIndex] = -1;
		lastElementIndex--;
		int currentIndex = 0;
		int leftChildIndex = getLeftChildIndex(currentIndex);
		int rightChildIndex = getRightChildIndex(currentIndex);
		while ((leftChildIndex <= lastElementIndex)) {
			if ((values[currentIndex] <= values[leftChildIndex] && values[currentIndex] <= values[rightChildIndex])
					|| (values[leftChildIndex] < 0 && values[rightChildIndex] < 0)) {
				return;
			}
			if (values[rightChildIndex] < 0) {
				if ((values[currentIndex] > values[leftChildIndex])) {
					swapValues(currentIndex, leftChildIndex);
					currentIndex = leftChildIndex;
				} else {
					return;
				}
			} else {
				if ((values[currentIndex] > values[leftChildIndex] && values[leftChildIndex] < values[rightChildIndex])) {
					swapValues(currentIndex, leftChildIndex);
					currentIndex = leftChildIndex;
				} else if (values[leftChildIndex] > values[rightChildIndex]
						&& values[currentIndex] > values[rightChildIndex]) {
					swapValues(currentIndex, rightChildIndex);
					currentIndex = rightChildIndex;
				}
			}
			leftChildIndex = getLeftChildIndex(currentIndex);
			rightChildIndex = getRightChildIndex(currentIndex);
		}
	}

	public static void main(String[] args) {
		final MinHeap heap = new MinHeap(11);
		heap.add(53);
		heap.add(30);
		heap.add(46);
		heap.add(28);
		heap.add(6);
		heap.add(36);
		heap.add(31);
		heap.add(16);
		heap.add(21);
		heap.add(41);
		heap.add(20);
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
		System.out.println("removed = " + heap.remove());
	}

}
