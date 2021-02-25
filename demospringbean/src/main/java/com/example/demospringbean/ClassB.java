package com.example.demospringbean;

public class ClassB {

    private ClassC classC;
    public ClassB(){};

    //构造器注入
    public ClassB(ClassC classC) {
        System.out.println("ClassB constructor：构造器注入ClassC到ClassB中" );
        this.classC = classC;
    }

    public ClassB(Object o) {
        System.out.println("ClassB constructor：构造器注入ClassC到ClassB中" );
        this.classC = (ClassC)o;
    }

    //Setter注入
    public void setClassC(ClassC classC) {
        System.out.println("setClassC：Setter注入ClassC到ClassB中" );
        this.classC = classC;
    }

    public ClassC getClassC() {
        return classC;
    }
    public void spellCheck() {
        classC.checkSpelling();
    }
}
