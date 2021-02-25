package com.example.demospringbean;

public class ClassCFactory {
    public static final ClassC getClassFromStaticFactory(){
        return new ClassC();
    }

    public ClassC getClassFromFactory(){
        return new ClassC();
    }
}
