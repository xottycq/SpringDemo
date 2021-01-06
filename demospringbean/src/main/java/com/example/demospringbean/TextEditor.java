package com.example.demospringbean;

public class TextEditor {
    private SpellChecker spellChecker;
    public TextEditor(){};
    //构造器注入
    public TextEditor(SpellChecker spellChecker) {
        System.out.println("Inside TextEditor constructor." );
        this.spellChecker = spellChecker;
    }

    //Setter注入
    public void setSpellChecker(SpellChecker spellChecker) {
        System.out.println("Inside setSpellChecker." );
        this.spellChecker = spellChecker;
    }

    public SpellChecker getSpellChecker() {
        return spellChecker;
    }

    public void spellCheck() {
        spellChecker.checkSpelling();
    }
}
