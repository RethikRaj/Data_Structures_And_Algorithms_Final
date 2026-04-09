
public class TwoStacksInArray {
	int[] arr;
	int top1;
	int top2;
	int capacity;

	// Initialize TwoStack.
	public TwoStack(int s) {
		arr = new int[s];
		capacity = s;
		top1 = -1;
		top2 = s;
	}

	// Push in stack 1.
	public void push1(int num) {
		if(top1 + 1 == top2) {
			return;
		}

		arr[++top1] = num;
	}

	// Push in stack 2.
	public void push2(int num) {
		if(top2 - 1 == top1) {
			return;
		}

		arr[--top2] = num;
	}

	// Pop from stack 1 and return popped element.
	public int pop1() {
		if(top1 == -1) {
			return -1;
		}

		int poppedEle = arr[top1];
		top1--;
		return poppedEle;
	}

	// Pop from stack 2 and return popped element.
	public int pop2() {
		if(top2 == capacity) {
			return -1;
		}

		int poppedEle = arr[top2];
		top2++;
		return poppedEle;

	}

}
