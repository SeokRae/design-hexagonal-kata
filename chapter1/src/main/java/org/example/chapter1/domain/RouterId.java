package org.example.chapter1.domain;

public class RouterId {
    private final String id;

    // private constructor - use factory method 'of' instead
    private RouterId(String id){
        this.id = id;
    }

    // Factory Method - use this instead of constructor
    public static RouterId of(String id){
        return new RouterId(id);
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id='" + id + '\'' +
                '}';
    }
}
