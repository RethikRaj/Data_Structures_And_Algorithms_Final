
class Node {
    int val;
    Node next;
    
    Node(int val) {
        this.val = val;
        // this.next = null // done by compiler (zero value for instance variables)
    }
}

class MyLinkedList {
    private Node headSentinel; // dummyHead
    private Node tailSentinel; // dummyTail
    private int size;

    public MyLinkedList() {
        headSentinel = new Node(-1);
        tailSentinel = new Node(-1);
        headSentinel.next = tailSentinel;
        // size = 0; // instance variables initialize by default
    }
    
    public int get(int index) {
        if(index < 0 || index >= size) return -1;

        Node ptr = headSentinel.next; // start at first real node
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.val;
    }
    
    public void addAtHead(int val) {
        addAtIndex(0, val);
    }
    
    public void addAtTail(int val) {
        addAtIndex(size, val);
    }
    
    public void addAtIndex(int index, int val) {
        if(index < 0 || index > size) {
            return;
        }

        // Walk to the node just BEFORE the insertion point => Move ptr "index" times
        Node prev = headSentinel;
        for(int i=0; i < index;i++) {
            prev = prev.next;
        }

        // Insert between prev and prev.next
        Node newNode = new Node(val);
        newNode.next = prev.next;
        prev.next = newNode;
        size += 1;
    }
    
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) {
            return;
        }

        // Walk to the node just BEFORE the deletion point => Move ptr "index" times
        Node prev = headSentinel;
        for(int i=0; i < index;i++) {
            prev = prev.next;
        }

        Node toDelete = prev.next;
        prev.next = toDelete.next;
        toDelete.next = null; // isolate the node
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