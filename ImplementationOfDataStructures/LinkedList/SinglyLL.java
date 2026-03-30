class Node {
    int val;
    Node next;

    Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }
}

class MyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }

        Node ptr = this.head;
        
        for(int i = 0 ; i < index;i++) {
            ptr = ptr.next; // null check not required because if we have verified it is valid index.
        }
        return ptr.val;
    }
    
    public void addAtHead(int val) {
        Node newNode = new Node(val, null);
        // Empty LL : Zero node in LL
        if(this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            size += 1;
            return;
        } 

        // One or more than one node
        newNode.next = this.head;
        this.head = newNode;
        size += 1;
    }
    
    public void addAtTail(int val) {
        Node newNode = new Node(val, null);
        // Empty LL : Zero node in LL
        if(this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            size += 1;
            return;
        }

        // One or more nodes
        this.tail.next = newNode;
        this.tail = newNode;
        size += 1;
    }
    
    public void addAtIndex(int index, int val) {
        if(index > size || index < 0) {
            return;
        }

        // Edge cases
        if(index == 0) {
            addAtHead(val);
            return;
        }
        if(index == size) {
            addAtTail(val);
            return;
        }

        // right now index will be in range : [1, size-1]
        Node newNode = new Node(val, null);
        Node ptr = this.head;
        for(int i = 0; i < index - 1; i++) {
            ptr = ptr.next;
        }

        newNode.next = ptr.next;
        ptr.next = newNode;
        size += 1;
    }

    public void deleteAtIndex(int index) {
        if(index >= size || index < 0){
            return;
        }

        // index : [0 , size - 1]

        if(index == 0) {
            Node temp = this.head;
            this.head = this.head.next;
            if(this.head == null) {   // list is now empty
                this.tail  = null;
            }
            temp.next = null; // isolating the node
            size -= 1;
            return;
        }

        Node ptr = this.head;
        for(int i = 0; i < index - 1; i++) {
            ptr = ptr.next;
        }
        Node temp = ptr.next;
        ptr.next = temp.next;
        //! IMP
        if(temp == this.tail) {
            this.tail = ptr;
        }
        temp.next = null; // isolating the node
        size -= 1;
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */