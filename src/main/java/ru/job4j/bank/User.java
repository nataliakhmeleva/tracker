package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель пользователя банковского счета.
 *
 * @author Natalia Khmeleva
 * @version 1.0.
 */
public class User {

    /**
     * Паспортные данные пользователя хранятся в переменной типа String
     */
    private String passport;
    /**
     * ФИО пользователя хранятся в переменной типа String
     */
    private String username;

    /**
     * Конструктор принимает в качестве входных паспортные данные и ФИО пользователя банковского
     * счета.
     *
     * @param passport текущие паспортные данные
     * @param username текущие ФИО пользователя
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Метод позволяет получить паспортные данные пользователя счета.
     *
     * @return возвращает текущие паспортные данные
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Метод принимает на вход новые паспортные данные и производит их замену.
     *
     * @param passport новые паспортные данные
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Метод позволяет получить ФИО пользователя счета.
     *
     * @return возвращает текущее ФИО
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод принимает на вход новое ФИО паользователя и производит его замену.
     *
     * @param username новое ФИО пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Метод проверяет объекты на идентичность. Сравнение объектов происходит по паспортным данным
     * пользователя.
     *
     * @param o объект принимается на вход для сравнения с текущем
     * @return возвращает true - в случае, если объекты равны, false - если различны
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    /**
     * Метод позволяет получить хеш-код объекта.
     * @return возвращает хеш-код паспортных данных пользователя
     */
    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
