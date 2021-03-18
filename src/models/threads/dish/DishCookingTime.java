package models.threads.dish;

import models.RandomInt;

public class DishCookingTime implements ITime {

    private RandomInt generator;
    private long time = 0;
    private long progress = 0;

    public DishCookingTime(int forId) {
        boolean isOdd = forId % 2 != 0;
        int min = isOdd ? 500 : 600;
        int max = isOdd ? 800 : 1200;

        generator = new RandomInt(min, max);
        time = (long) generator.generate();
    }

    public long advance() {
        if (progress < time) progress += 100;
        if (progress >= time) progress = time;

        return 100;
    }

    public double cookingPercentage() {
        return (100 * progress) / (time + 0.0);
    }

    public boolean isFinished() {
        return progress == time;
    }

    public long asMilliseconds() {
        return time;
    }

    public double asSeconds() {
        return (time + 0.0) / 1000;
    }
}
