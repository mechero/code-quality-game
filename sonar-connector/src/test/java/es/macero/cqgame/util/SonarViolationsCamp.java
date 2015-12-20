package es.macero.cqgame.util;

/**
 * Created by Moisés on 20/12/2015.
 */
public class SonarViolationsCamp {

    public void testSomeBadPractices() {
        String s = "This is a message";
        if(s == "This is a"){
            System.out.println("Miracle");
        }
    }
}
