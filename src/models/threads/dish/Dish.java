package models.threads.dish;

import java.util.concurrent.Semaphore;

public class Dish extends Thread {

    private int id;
    private Semaphore semaphore;

    public Dish(int id, Semaphore semaphore) {
        this.id = id;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            cook();
        } catch (Exception exception) {
            exception.printStackTrace();
            interrupt();
        }
    }

    private void cook() throws InterruptedException {
        var timeToFinish = new DishCookingTime(id);

        while (!timeToFinish.isFinished()) {
            sleep(timeToFinish.advance());
            System.out.println(
                this +
                " está " +
                timeToFinish.cookingPercentage() +
                " preparada!\n"
            );
        }

        finish();
    }

    private void finish() {
        try {
            semaphore.acquire();
            sleep(500);
            System.out.println(this + " terminou de cozinhar!\n");
            semaphore.release();
        } catch (Exception exception) {
            exception.printStackTrace();
            interrupt();
        } finally {
            semaphore.release();
        }
    }

    @Override
    public String toString() {
        String name = (id % 2 == 0) ? "Sopa de cebola" : "Lasanha a Bolonhesa";
        return name + " " + id;
    }
}
