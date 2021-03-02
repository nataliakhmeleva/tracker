package ru.job4j.search;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PhoneDictionary {

    private ArrayList<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     *
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public ArrayList<Person> find(String key) {
        Predicate<Person> containsGetName = person -> person.getName().contains(key);
        Predicate<Person> containsGetSurname = person -> person.getSurname().contains(key);
        Predicate<Person> containsGetPhone = person -> person.getPhone().contains(key);
        Predicate<Person> containsGetAddress = person -> person.getAddress().contains(key);
        Predicate<Person> combine = containsGetName.or(containsGetSurname).or(containsGetPhone)
            .or(containsGetAddress);
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }
}
