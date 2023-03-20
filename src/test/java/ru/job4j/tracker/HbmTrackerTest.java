package ru.job4j.tracker;

import org.hamcrest.core.IsNull;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class HbmTrackerTest {
    private final SessionFactory sf = new MetadataSources(
            new StandardServiceRegistryBuilder().configure().build()
    ).buildMetadata().buildSessionFactory();

    @Before
    public void wipeTable() {
        var session = sf.openSession();
        session.beginTransaction();
        session.createQuery("delete from Item").executeUpdate();
        session.getTransaction();
        session.close();
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName(), is(item.getName()));
        }
    }

    @Test
    public void whenReplace() {
        try (var tracker = new HbmTracker()) {
            Item item = tracker.add(new Item("item"));
            int id = item.getId();
            Item item1 = tracker.add(new Item("item1"));
            tracker.replace(id, item1);
            assertThat(tracker.findById(id).getName(), is("item1"));
        }
    }

    @Test
    public void whenDelete() {
        try (var tracker = new HbmTracker()) {
            Item item = tracker.add(new Item("item"));
            int id = item.getId();
            tracker.delete(id);
            assertThat(tracker.findById(id), is(IsNull.nullValue()));
        }
    }

    @Test
    public void whenFindAll() {
        try (var tracker = new HbmTracker()) {
            Item item = tracker.add(new Item("item"));
            Item item1 = tracker.add(new Item("item1"));
            assertThat(tracker.findAll(), is(List.of(item, item1)));
        }
    }

    @Test
    public void whenFindByName() {
        try (var tracker = new HbmTracker()) {
            Item item = tracker.add(new Item("item"));
            assertThat(tracker.findByName(item.getName()), is(List.of(item)));
        }
    }
}