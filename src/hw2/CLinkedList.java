// *
// NAME: John Cole Van Hoogenstyn
// ID: A12572409
// *

/**
 * Linked List project for CSE12: Data Structures at the University of 
 * California, San Diego. 
 *
 * @version 1.0
 * @author John Cole Van Hoogenstyn
 * @since
 */
package hw2;
import java.util.*;

public class CLinkedList<E> extends AbstractList<E> {
    /**
     * Circular Doubly-Linked list that stores non-null objects of a type.
     */

    private int nElems;      // Current number of elements 
    private Node dummyHead;  // "dummy" Node, used for assistance in methods
    private Node dummyTail;  // "dummy" Node, used for assistance in methods
    private Node actualHead; // Actual Head node that will contain data
    private Node actualTail; // Actual Tail node that will contain data
    private static final int ZERO = 0;

    protected class Node {
        /**
         * Inner class to be utilized by CLinkedList objects to store data
         */

        E data;
        Node next;      // Reference to the object's next Node
        Node prev;      // Reference to the object's previous Node

        /**
         * Constructor to create singleton Node
         * Links to previous and next nodes are handled by helper methods.
         */
        public Node(E element) {
            this.data = element;
            this.next = null;
            this.prev = null;

        }

        /**
         * Constructor to create singleton Node
         * Params provide two Node objects for next and previous links
         *
         * @param element Element to add, can be null
         * @param prevNode predecessor Node, can be null
         * @param nextNode successor Node, can be null
         */
        public Node(E element, Node prevNode, Node nextNode) {
            // Update the member variables for the Node object
            this.data = element;
            this.prev = prevNode;
            this.next = nextNode;
            // Update the appropriate pointers for prevNode and nextNode
            prevNode.next = this;
            nextNode.prev = this;
        }

        /**
         * Remove this Node from the list. Update previous and next nodes.
         */
        public void remove() {
            this.data = null;
            // Update the LinkedList structure to maintain the links
            this.next.prev = this.prev;
            this.prev.next = this.next;
        }

        /**
         * Set the previous Node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            this.prev = p;
        }

        /**
         * Set the next Node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            this.next = n;
        }

        /**
         * Set the element for a Node object
         *
         * @param e new element
         */
        public void setElement(E e) {
            this.data = e;
        }

        /**
         * Accessor method, returns the Node's next Node
         *
         * @return Node object
         */
        public Node getNext() {
            return (Node) this.next;
        }

        /**
         * Accessor method, returns the Node's previous Node
         *
         * @return
         */
        public Node getPrev() {
            return (Node) this.prev;
        }

        /**
         * Accessor method, returns the Node's data 
         *
         * @return
         */
        public E getElement() {
            return (E) this.data;
        }

        /**
         * Helper method - prints the Node's data 
         */
        public void printNodeData() throws NullPointerException {
            System.out.println("Node data: " + this.getElement());
        }
    }

    /**
     * ListIterator implementation
     */
    protected class MyListIterator implements ListIterator<E> {
        /**
         * MyListIterator instances provide iteration of objects
         * In our case, we will use it for CLinkedList objects.
         */
        private boolean forward;    // Can the iterator move forward?
        private boolean canRemove;  // Can the iterator remove?
        private Node left;          // Cursor sits between these two nodes
        private Node right;         // Cursor sits between these two nodes
        private Node currentNode;   // Current node being processed
        private int currentNodeIndex; // Index of current node 
        private int idx = 0;     // Current position, what next() would return 
        private int size = 0;    // Size of iterator

        /**
         * Default, no-arg construtor for the MyListIterator class. Assigns
         * values to: left, right, forward, and canRemove fields.
         */
        public MyListIterator() {
            this.left = CLinkedList.this.dummyHead;
            this.right = CLinkedList.this.actualHead;
            this.currentNode = this.right;
            this.currentNodeIndex = 0;
            this.forward = true;
            this.canRemove = false;
            this.size = CLinkedList.this.nElems;
        }

        /**
         * Private helper method: Get the size of the MyListIterator object
         *
         * @return the number of elements in the Iterator (int value)
         */
        private int getSize() {
            return this.size;
        }

        /**
         * Adds an element to the list between 'left' and 'right'.
         *
         * @param e an element to be added
         * @throws NullPointerException
         */
        @Override
        public void add(E e) throws NullPointerException {
            if (e == null) {
                throw new NullPointerException();
            }

            Node addNode = new Node(e);
            addNode.setNext(this.right);
            addNode.setPrev(this.left);

            this.left = addNode;
            this.canRemove = false;
            this.idx++;
        }

        /**
         * Checks if there is another element to be retrieved by calling next().
        */
        @Override
        public boolean hasNext() {
            if (CLinkedList.this.size() > 0) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * Checks if there is a previous node
         */
        @Override
        public boolean hasPrevious() {
            if (CLinkedList.this.size() > 0) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * The next() method returns the next element in the list and also
         * advances the cursor position.
         *
         * @return The next element in the list.
         * @throws NoSuchElementException
         */
        @Override
        public E next() throws NoSuchElementException {

            this.currentNode = this.right;
            E returnData = this.right.getElement();
            this.left = this.left.getNext();
            this.right = this.right.getNext();

            this.idx++;
            this.forward = true;
            this.canRemove = true;
            return returnData;
        }

        @Override
        public int nextIndex() {
            if (this.idx == this.size) {
                this.idx = 0;
                return idx;
            }

            return this.idx;
        }

        @Override
        public E previous() throws NoSuchElementException {
            if (this.idx == 0) {
                throw new NoSuchElementException();
            }

            this.currentNode = this.left;
            E returnData = this.left.getElement();
            this.left = this.left.getPrev();
            this.right = this.left.getPrev();

            this.idx--;
            this.forward = false;
            this.canRemove = true;
            return returnData;
        }

        @Override
        public int previousIndex() {
            if (this.idx == 0) {
                return -1;
            }

            return (this.idx - 1);
        }

        @Override
        public void remove() throws IllegalStateException {
            if (this.canRemove == false) {
                throw new IllegalStateException();
            }

            if (this.forward == true) {
                CLinkedList.this.remove(idx - 1);
            } else {
                CLinkedList.this.remove(idx - 1);
            }
        }

        /**
         * Replaces last element returned by next() or previous() with a given
         * element.
         */
        @Override
        public void set(E e) throws NullPointerException, IllegalStateException {
            if (e == null) {
                throw new NullPointerException();
            }
            if (this.canRemove == false) {
                throw new IllegalStateException();
            }

            if (this.forward == true) {
                CLinkedList.this.set(idx, e);
            } else {
                CLinkedList.this.set(idx, e);
            }

        }

    }

    //  Implementation of the CLinkedList Class
    /**
     * Only 0-argument constructor is defined
     */
    public CLinkedList() {

        this.dummyHead = new Node(null);
        this.dummyTail = new Node(null);
        this.actualHead = new Node(null);
        this.actualTail = new Node(null);
        this.nElems = 0;

        dummyHead.setNext(dummyTail);
        dummyTail.setPrev(dummyHead);
    }

    /**
     * size() Method: returns the size of the current CLinkedList Object
     *
     * @return number of elements in the CLinkedList object (int value)
     */
    @Override
    public int size() {
        return this.nElems;
    }

    /**
     * Private helper method: used to increment the size of the CLinkedList
     * object. Called upon when a Node is added.
     */
    private void incrementSize() {
        this.nElems++;
    }

    /**
     * Private helper method: used to decrement the size of the CLinkedList
     * object. Called upon when a Node is removed.
     */
    private void decrementSize() {
        this.nElems--;
    }

    /**
     * Private helper method: Used to get the actual head Node (non-dummy) for
     * adjustments.
     *
     * @return Node object: the actual head node of the CLinkedList object
     */
    private Node getActualHead() {
        return this.actualHead;
    }

    /**
     * Private helper method: Used to get the actual tail Node (non-dummy)
     *
     * @return Node object, actual tail node containing data
     */
    private Node getActualTail() {
        return this.actualTail;
    }

    /**
     * Private helper method: used to establish (or re-establish) the circular
     * link between the actual head and tail nodes. Called upon when the
     * CLinkedList object head/tail nodes are changed.
     */
    private void setCircularLink() {
        this.actualHead.setPrev(this.actualTail);
        this.actualTail.setNext(this.actualHead);

        this.dummyHead.setNext(this.actualHead);
        this.dummyTail.setPrev(this.actualTail);
    }

    /**
     * Private helper method: will be utilized when the user wants to add a new
     * Node to the end of the CLinkedList object
     *
     * @param elem
     */
    /**
     * The get (int index) method will return an element E at the specified
     * location in the CLinkedList object.
     *
     * @param index: Location of the element to be retrieved
     * @return Element e at the specified index
     * @throws IndexOutOfBoundsException: index specified by the user could be
     * out of the range of the CLinkedList object.
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        Node currentNode = this.actualHead;
        int nodeCount = 0;

        while (nodeCount < this.nElems) {
            if (nodeCount == index) {
                return currentNode.getElement();
            }
            nodeCount++;
            currentNode = currentNode.getNext();
        }

        return null;
    }

    private void addFirst(E data) throws NullPointerException {
        Node tempNode = this.actualHead;
        Node addNode = new Node(data);

        addNode.setNext(tempNode);
        tempNode.setPrev(addNode);

        this.actualHead = addNode;
        this.setCircularLink();
        this.incrementSize();
    }

    private void addLast(E data) throws NullPointerException {
        Node tempNode = this.actualTail;
        Node addNode = new Node(data);

        addNode.setPrev(tempNode.getPrev());
        addNode.setNext(tempNode);
        tempNode.getPrev().setNext(addNode);
        tempNode.setPrev(addNode);

        this.actualTail = tempNode;
        this.setCircularLink();
        this.incrementSize();

    }

    /**
     * Private helper method: Called upon when the CLinkedList object is empty
     * and the add(int index, E data) method is called on.
     *
     * @param data
     * @throws NullPointerException
     */
    private void emptyAdd(E data) throws NullPointerException {
        Node addNode = new Node(data);
        this.actualHead = addNode;
        this.actualTail = addNode;
        this.setCircularLink();
        this.incrementSize();
    }

    /**
     * Private helper method: Called by the add(index, data) method when the
     * index parameter is equal to the number of elements in the CLinkedList
     * object. This will create a new tail (actual) Node.
     *
     * @param data
     */
    private void addNewTail(E data) {
        Node newTailNode = new Node(data);
        Node tailCopy = this.actualTail;

        newTailNode.setPrev(tailCopy);
        newTailNode.setNext(this.actualHead);
        this.actualTail = newTailNode;

        tailCopy.setNext(this.actualTail);

        this.incrementSize();
        this.setCircularLink();

    }

    /**
     * Add an element to the list
     *
     * @param index where in the list to add
     * @param data data to add
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    @Override
    public void add(int index, E data) throws IndexOutOfBoundsException, NullPointerException {
        if (index == 0 && this.nElems == 0) {
            this.emptyAdd(data);
        } else if (index == 0 && this.nElems != 0) {
            this.addFirst(data);
        } else if (index == this.nElems) {
            this.addNewTail(data);
        } else if (index == this.nElems - 1) {
            this.addLast(data);
        } else if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        } else if (data == null) {
            throw new NullPointerException();
        } else {
            Node currentNode = this.actualHead;
            Node addNode;
            int nodeCount = 0;

            while (nodeCount <= this.nElems) {
                if (nodeCount == index) {
                    addNode = new Node(data);
                    addNode.setPrev(currentNode.getPrev());
                    addNode.setNext(currentNode);
                    currentNode.getPrev().setNext(addNode);
                    currentNode.getNext().setPrev(addNode);

                }

                currentNode = currentNode.next;
                nodeCount++;
            }

            this.setCircularLink();
            this.incrementSize();
        }
    }

    /**
     * Set the element at an index in the list
     *
     * @param index where in the list to add
     * @param data data to add
     * @return element that was previously at this index.
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    public E set(int index, E data) throws IndexOutOfBoundsException, NullPointerException {
        Node currentNode = this.actualHead;
        int nodeCount = 0;
        E dataCopy = null;

        while (nodeCount < this.nElems) {
            if (nodeCount == index) {
                dataCopy = currentNode.getElement();
                currentNode.setElement(data);
            }

            currentNode = currentNode.getNext();
            nodeCount++;
        }

        return dataCopy;
    }

    /**
     * Private helper method: Called upon when the remove(int index) method
     * receives zero (the CLinkedList object's actual head node) as an argument.
     *
     * @param index: index of the Node that is to be removed
     * @return
     * @throws IndexOutOfBoundsException
     */
    private E removeHead(int index) throws IndexOutOfBoundsException {
        E headData = this.actualHead.getElement();

        if (this.nElems == 0) {
            throw new IndexOutOfBoundsException();
        } else if (this.nElems == 1) {
            this.actualHead.setElement(null);
            this.actualTail.setElement(null);
        } else {
            Node headNext = this.actualHead.getNext();
            this.actualHead.setElement(headNext.getElement());
            this.actualHead.setNext(headNext.getNext());
            this.actualHead.setPrev(this.actualTail);
            this.setCircularLink();
        }

        this.decrementSize();
        return headData;
    }

    /**
     * Remove the element at an index in the list
     *
     * @param index where in the list to add
     * @return element the data found
     * @throws IndexOutOfBoundsException
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            E removeData = this.removeHead(index);
            return removeData;
        }

        Node currentNode = this.actualHead;
        int nodeCount = 0;
        E removedElement;

        while (nodeCount < this.nElems) {
            if (index == nodeCount) {
                Node prevNode = currentNode.getPrev();
                Node nextNode = currentNode.getNext();

                currentNode.setNext(null);
                currentNode.setPrev(null);

                prevNode.setNext(nextNode);
                nextNode.setPrev(prevNode);

                removedElement = currentNode.getElement();

                this.decrementSize();
                return removedElement;

            }
            nodeCount++;
            currentNode = currentNode.getNext();
        }

        return null;

    }

    /**
     * Clear the linked list
     */
    public void clear() {
        this.actualHead = this.actualTail = null;
        this.nElems = 0;
    }

    /**
     * Determine if the list empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return (nElems == 0);
    }

    public Iterator<E> QQQiterator() {
        return new MyListIterator();
    }

    public ListIterator<E> QQQlistIterator() {
        return new MyListIterator();
    }

    // Helper method to get the Node at the Nth index
    private Node getNth(int index) throws IndexOutOfBoundsException {
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }

        Node currentNode = this.actualHead;
        E headData = this.actualHead.getElement();
        int nodeCount = 0;

        while (nodeCount < this.nElems) {
            if (index == nodeCount) {
                return currentNode;
            }

            currentNode = currentNode.getNext();
            nodeCount++;
        }

        return currentNode;
    }

    
    public Iterator<E> iterator()
    {
        return new MyListIterator();
    }
    
    public ListIterator<E> listIterator()
    {
        return new MyListIterator();
    }
     
    public static void main(String[] args) {
        // CLinkedList object for testing
        CLinkedList testList = new CLinkedList();

        // Node objects for testing
        CLinkedList.Node testNode1;
        CLinkedList.Node testNode2;
        CLinkedList.Node testNode3;

        // Test data for... testing
        String testData1 = "Test Data 1";
        String testData2 = "Test Data 2";
        String testData3 = "Test Data 3";
        String testData4 = "Test Data 4";
        String testData5 = "Test Data 5";
        String testData6 = "Test Data 6";
        String testChimmy = "Chimmy Gets Zeros on Customer Reviews";

        // Add elements to the CLinkedList before testing the iterator
        testList.add(ZERO, testData1);
        testList.add(1, testData2);
        testList.add(2, testData3);
        testList.add(3, testData4);
        testList.add(4, testData5);

        // MyListIterator for testing
        CLinkedList.MyListIterator testIterator = testList.new MyListIterator();

        //testIterator.add(testData6);
        System.out.println(testIterator.next());
        System.out.println(testIterator.next());
        System.out.println();
        testIterator.remove();
        
        for (int i = 0; i < testList.size(); ++i)
        {
            System.out.println(testList.get(i));
        }

    }
}

