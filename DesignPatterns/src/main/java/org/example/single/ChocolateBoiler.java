package org.example.single;

public class ChocolateBoiler {
    private volatile static ChocolateBoiler instance;
    private boolean empty;
    private boolean boild;

    private ChocolateBoiler(){
        this.empty = true;
        this.boild = false;
    }

    public static ChocolateBoiler getInstance(){
        if(instance == null){
            synchronized (ChocolateBoiler.class){
                if(instance == null){
                    instance = new ChocolateBoiler();
                }
            }
        }
        return instance;
    }

    public void drain(){
        if(!isEmpty() && isBoild()){
            this.empty = true;
        }
    }

    public void fill(){
        if(isEmpty()){
            this.empty = false;
            this.boild = false;
        }
    }

    public boolean isEmpty(){
        return this.empty;
    }

    public void boil(){
        if(!isEmpty() && !isBoild()){
            this.boild = true;
        }
    }

    public boolean isBoild(){
        return this.boild;
    }
}
