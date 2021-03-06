package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    private List<Profile> profiles = new ArrayList<>();

    @Before
    public void setUp() {
        profiles.add(new Profile(new Address("Ufa", "Borodinskaya", 5, 20)));
        profiles.add(new Profile(new Address("Samara", "Pushkina", 12, 103)));
        profiles.add(new Profile(new Address("Irkutsk", "Lomonosova", 42, 47)));
        profiles.add(new Profile(new Address("Moscow", "Tverskaya", 85, 73)));
        profiles.add(new Profile(new Address("Saint Petersburg", "Griboedova", 100, 6)));
    }

    @Test
    public void whenCollectAddress() {
        Profiles files = new Profiles();
        List<Address> expected = new ArrayList<>();
        List<Address> rsl = files.collect(profiles);
        expected.add(new Address("Ufa", "Borodinskaya", 5, 20));
        expected.add(new Address("Samara", "Pushkina", 12, 103));
        expected.add(new Address("Irkutsk", "Lomonosova", 42, 47));
        expected.add(new Address("Moscow", "Tverskaya", 85, 73));
        expected.add(new Address("Saint Petersburg", "Griboedova", 100, 6));
        assertThat(rsl, is(expected));
    }
}