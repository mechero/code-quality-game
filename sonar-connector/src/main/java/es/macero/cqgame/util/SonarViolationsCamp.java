package es.macero.cqgame.util;

public class SonarViolationsCamp {

    public void testSomeBadPractices() {
        String s = "This is a message";
        if(s == "This is a"){
            System.out.println("Miracle");
            throw new NullPointerException();
        }
    }
}
