package ru.job4j.stream;

public class Car {

    private String model;

    private String color;

    private String carBody;

    private int power;

    private int fuel;

    private int maxSpeed;

    static class Builder {

        private String model;
        private String color;
        private String carBody;
        private int power;
        private int fuel;
        private int maxSpeed;

        Builder buildModel(String model) {
            this.model = model;
            return this;
        }

        Builder buildColor(String color) {
            this.color = color;
            return this;
        }

        Builder buildCarBody(String carBody) {
            this.carBody = carBody;
            return this;
        }

        Builder buildPower(int power) {
            this.power = power;
            return this;
        }

        Builder buildFuel(int fuel) {
            this.fuel = fuel;
            return this;
        }

        Builder buildMaxSpeed(int maxSpeed) {
            this.maxSpeed = maxSpeed;
            return this;
        }

        Car build() {
            Car car = new Car();
            car.model = model;
            car.color = color;
            car.carBody = carBody;
            car.power = power;
            car.fuel = fuel;
            car.maxSpeed = maxSpeed;
            return car;
        }
    }

    public static void main(String[] args) {
        Car car = new Builder().buildModel("Kia Rio")
            .buildColor("blue")
            .buildCarBody("hatchback")
            .buildPower(100)
            .buildFuel(95)
            .buildMaxSpeed(175)
            .build();
        System.out.println(car);
    }
}
