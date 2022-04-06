package com.assignment03;

public class Queue<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Queue() {
        setHead(null);
        setTail(null);
        setSize(0);
    }

    public void Enqueue(T element){
        if(IsEmpty()){
            setTail(new Node<T>(element, null));
            setHead(getTail());
        } else {
            Node<T> oldNode = getTail();
            setTail(new Node<T>(element, null));
            oldNode.setNextNode(getTail());
        }
        setSize(size + 1);
    }

    public T Front(){
        if(IsEmpty()){
            throw new IllegalArgumentException();
        }
        return getHead().getElement();
    }

    public T Dequeue(){
        if(IsEmpty()){
            throw new IllegalArgumentException();
        }
        Node<T> oldHead = getHead();
        if(getSize() == 1){
            setHead(null);
            setTail(null);
        } else {
            setHead(getHead().getNext());
        }

        setSize(size - 1);
        return oldHead.getElement();
    }

    public void Clear(){
        while (!IsEmpty()){
            Dequeue();
        }
    }


    public boolean IsEmpty(){
        return getSize() == 0;
    }
    // Self generated methods
    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
