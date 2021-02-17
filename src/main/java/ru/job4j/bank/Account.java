package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель банковского счета.
 *
 * @author Natalia Khmeleva
 * @version 1.0.
 */
public class Account {

    /**
     * Реквизиты банковского счета хранятся в переменной типа String
     */
    private String requisite;
    /**
     * Баланс банковского счета хранится в переменной типа double
     */
    private double balance;

    /**
     * Конструктор принимает в качестве входных данных реквизиты и баланс банковского счета.
     *
     * @param requisite текущие реквизиты
     * @param balance   текущий баланс
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Метод позволяет получить реквизиты банковского счета.
     *
     * @return возвращяет текущие реквизиты
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод принимает на вход новые реквизиты и производит замену данных.
     *
     * @param requisite реквизиты, которые были заменены
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод позволяет получить баланс.
     *
     * @return возвращает текущий баланс
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Метод принимает на вход новый баланс и производит замену данных.
     *
     * @param balance баланс, который был заменен
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Метод проверяет объекты на идентичность. Сравнение объектов происходит по реквизитам.
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
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    /**
     * Метод позволяет получить хеш-код объекта.
     *
     * @return возвращает хеш-код реквизитов
     */
    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
