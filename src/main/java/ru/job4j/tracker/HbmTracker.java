package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery(
                            "UPDATE Item SET name = :fName, created = :fCreated WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fCreated", item.getCreated())
                    .setParameter("fId", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            result = session.createQuery("DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            items = session.createQuery("from Item", Item.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            items = session.createQuery("from Item as i where i.name = :fName", Item.class)
                    .setParameter("fName", key)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            item = session.createQuery("from Item as i where i.id = :fId", Item.class)
                    .setParameter("fId", id)
                    .uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return item;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
