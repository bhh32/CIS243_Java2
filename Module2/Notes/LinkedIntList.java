// LinkedIntList provides an int list
// using a doubly-linked list structure
// to store a list of ints
// based on UW CSE143 demo code

public class LinkedIntList implements IntList {
    private ListNode front; // first value in the list
    private ListNode back; // last value in the list
    private int size; // current number of elements

    // post: constructs an empty list.
    public LinkedIntList() {
	front = new ListNode(0);
	back = new ListNode(0);
	clear();
    }

    // post: returns the current number of elements in the list
    public int size() {
	return size;
    }

    // pre : 0 <= index < size()
    // post: returns the integer at the given index in the list
    public int get(int index) {
	checkIndex(index);
	ListNode current = nodeAt(index);
	return current.data;
    }

    // post: appends the given value to the end of the list
    public void add(int value) {
	add(size, value);
    }

    // pre: 0 <= index <= size()
    // post: inserts the given value at the given index, shifting subsequent
    // values right
    public void add(int index, int value) {
	if (index < 0 || index > size) {
	    throw new IndexOutOfBoundsException("index: " + index);
	}
	ListNode current = nodeAt(index - 1);
	ListNode newNode = new ListNode(value, current.next, current);
	current.next = newNode;
	newNode.next.prev = newNode;
	size++;
    }

    // pre : 0 <= index < size()
    // post: replaces the integer at the given index with the given value
    public void set(int index, int value) {
	checkIndex(index);
	ListNode current = nodeAt(index);
	current.data = value;
    }

    // pre : 0 <= index < size()
    // post: removes value at the given index, shifting subsequent values left
    public void remove(int index) {
	checkIndex(index);
	ListNode current = nodeAt(index - 1);
	current.next = current.next.next;
	current.next.prev = current;
	size--;
    }

    // post: list is empty
    public void clear() {
	front.next = back;
	back.prev = front;
	size = 0;

    }

    // post: returns a comma-separated, bracketed version of the list
    public String toString() {
	String result = "[";
	if (size > 0) {
	    result += front.data;
	    ListNode curr = front.next;
	    for (int i = 1; i < size; i++) {
		result += ", " + curr.data;
		curr = curr.next;
	    }
	}
	result += "]";
	return result;
    }

    // post : returns the position of the first occurrence of the given
    // value (-1 if not found)
    public int indexOf(int value) {
	if (size == 0) {
	    return -1;
	}
	ListNode curr = front;
	for (int i = 0; i < size; i++) {
	    if (curr.data == value) {
		return i;
	    }
	    curr = curr.next;
	}
	return -1;
    }

    // post: returns true if list is empty, false otherwise
    public boolean isEmpty() {
	return size == 0;
    }

    // post: returns true if the given value is contained in the list,
    // false otherwise
    public boolean contains(int value) {
	return indexOf(value) != -1;
    }

    // post: throws an exception if the given index is out of range
    private void checkIndex(int index) {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException("index: " + index);
	}
    }

    // pre : 0 <= index < size()
    // post: returns the node at a specific index. Uses the fact that the list
    // is doubly-linked to start from the front or the back, whichever
    // is closer.
    private ListNode nodeAt(int index) {
	ListNode current;
	if (index < size / 2) {
	    current = front;
	    for (int i = 0; i < index + 1; i++) {
		current = current.next;
	    }
	} else {
	    current = back;
	    for (int i = size; i >= index + 1; i--) {
		current = current.prev;
	    }
	}
	return current;
    }

    private static class ListNode {
	public int data; // data stored in this node
	public ListNode next; // link to next node in the list
	public ListNode prev; // link to previous node in the list

	// post: constructs a node with given data and null links
	public ListNode(int data) {
	    this(data, null, null);
	}

	// post: constructs a node with given data and given links
	public ListNode(int data, ListNode next, ListNode prev) {
	    this.data = data;
	    this.next = next;
	    this.prev = prev;
	}
    }
}