package views;

import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;
import models.threads.dish.Dish;

public class Chef {

    public static void main(String[] args) {
        var semaphore = new Semaphore(1);
        IntStream.range(1, 6).forEach(id -> (new Dish(id, semaphore)).start());
    }
}
