package ru.job4j.bank;

import java.util.*;

/**
 * Класс описывает главный банковский сервис, который выполняет операции со счетами пользователей.
 *
 * @author Natalia Khmeleva
 * @version 1.0
 */
public class BankService {

    /**
     * Хранение всех пользователей системы с привязанными к ним счетами в коллекции типа HashMap
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод позволяет добавить пользователя в систему. Для добавления используется метод
     * Map.putIfAbsent, который принимает два параметра: пользователя и список счетов и осуществляет
     * проверку, что такого пользователя еще нет в системе. Если он есть, то нового добавлять не
     * надо.
     *
     * @param user новый пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<Account>());
    }

    /**
     * Метод позволяет добавить новый счет к пользователю. Для добавления используется метод
     * findByPassport, который производит поиск пользователя по паспортным данным.После этого можно
     * получить список всех счетов пользователя и добавить новый счет к ним, предварительно,
     * осуществив проверку, что такого счета у пользователя еще нет.
     *
     * @param passport паспортный данные, по которым необходимо найти пользователя
     * @param account  новый банковский счет пользователя
     */
    public void addAccount(String passport, Account account) {
        Optional<User> findUser = findByPassport(passport);
        if (findUser.isPresent()) {
            List<Account> accounts = users.get(findByPassport(passport).get());
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод позволяет найти пользователя по паспортным данным путем перебора всех элементов и с
     * помощью метода keySet.
     *
     * @param passport паспортный данные, по которым необходимо найти пользователя
     * @return возвращает пользователя, паспортные данные которого совпали с искомыми, либо null,
     * если совпадений нет
     */
    public Optional<User> findByPassport(String passport) {
        return users.keySet().stream()
            .filter(n -> n.getPassport().equals(passport))
            .findFirst();
    }

    /**
     * Метод позволяет найти пользователя по реквизитам банковского счета. Для поиска используется
     * метод findByPassport, который производит поиск пользователя по паспортным данным.После этого
     * можно получить список всех счетов пользователя и найти в нем нужный счет, предварительно,
     * осуществив проверку по реквизитам, что такого счета у пользователя еще нет.
     *
     * @param passport  паспортный данные, по которым необходимо найти пользователя
     * @param requisite реквизиты счета, по которым необходимо найти счет пользователя
     * @return возвращает список счетов пользователя, либо null,если совпадений нет
     */
    public Optional<Account> findByRequisite(String passport, String requisite) {
        Optional<User> findUser = findByPassport(passport);
        if (findUser.isPresent()) {
            return users.get(findUser.get()).stream()
                .filter(n -> n.getRequisite().equals(requisite))
                .findFirst();
        }
        return Optional.empty();
    }

    /**
     * Метод позволяет перечислить деньги с одного счета на другой счет. Для поиска счетов, с
     * которого нужно перечислить деньги (srcAccount) и на который(destAccount), необходимо
     * использовать метод findByRequisite.Если счёт не найден или не хватает денег на счёте
     * srcAccount (с которого переводят), то метод должен вернуть false.
     *
     * @param srcPassport   паспортный данные пользователя-отправителя
     * @param srcRequisite  реквизиты счета, с которого необходимо перечислить деньги
     * @param destPassport  паспортный данные пользователя-получателя
     * @param destRequisite реквизиты счета, на который необходимо перечислить деньги
     * @param amount        денежная сумма, которую необходимо перечислить со счета на счет
     * @return возвращает true, если деньги были переведены со счета на счет, или false, если счет,
     * с которого переводят, не найден или не хватает денег на нем
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
        String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Optional<Account> srcAccount = findByRequisite(srcPassport, srcRequisite);
        Optional<Account> destAccount = findByRequisite(destPassport, destRequisite);
        if (srcAccount.isPresent() && destAccount.isPresent()
            && srcAccount.get().getBalance() >= amount) {
            srcAccount.get().setBalance(srcAccount.get().getBalance() - amount);
            destAccount.get().setBalance(destAccount.get().getBalance() + amount);
            rsl = true;
        }
        return rsl;
    }
}
