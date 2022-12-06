package main;
import java.util.TooManyListenersException;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TooManyListenersException {

        new GUI().loadGUI();

    }

}
