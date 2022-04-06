package com.assignment03;

public class Node<T> {
    private T element;
    private Node<T> nextNode;
    private Node<T> previousNode;

    //Constructors
    public Node(){
        setElement(null);
        setNextNode(null);
        setPreviousNode(null);
    }

    public Node(T element){
        setElement(element);
    }

    public Node(T element, Node<T> nextNode){
        setElement(element);
        setNext(nextNode);
    }

    //Method
    public T getElement(){
        return this.element;
    }

    public void setElement(T element){
        this.element = element;
    }


    // Self generated code

    public Node<T> getNext(){
        return this.getNextNode();
    }

    public void setNext(Node<T> next){
        this.setNextNode(next);
    }

    public Node<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node<T> nextNode) {
        this.nextNode = nextNode;
    }

    public Node<T> getPrevious(){
        return this.getPreviousNode();
    }

    public void setPrevious(Node<T> prev){
        this.setPreviousNode(prev);
    }

    public Node<T> getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node<T> previousNode) {
        this.previousNode = previousNode;
    }

}

