package com.example.controls.tda.stack;

public class Stack <E> {
    private StackOperation<E> stackOperation;
    public Stack(Integer cant) {
        this.stackOperation = new StackOperation<>(cant);
    }
    public void push(E dato) throws Exception {
        stackOperation.push(dato);
    }
    
    public Integer getSize() {
        return this.stackOperation.getSize();
    }
    
    public void clear() {
        this.stackOperation.reset();
    }
    
    public Integer getTop() {
        return this.stackOperation.getTop();
    }
    
    public void print() {
        System.out.println("PILA");
        System.out.println(stackOperation.toString());
        System.out.println("******");
    }
    //TODO.. falta push
    public E pop() throws Exception {
        return stackOperation.pop();
    }
}




