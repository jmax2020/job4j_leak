package ru.job4j.gc.leak;

import ru.job4j.gc.leak.models.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UserGenerator implements Generate {

    public final String pathNames = "files/names.txt";
    public final String pathSurnames = "files/surnames.txt";
    public final String pathPatrons = "files/patr.txt";

    public final String separator = " ";
    public final Integer newUsers = 1000;

    public List<String> names;
    public List<String> surnames;
    public List<String> patrons;
    private final List<User> users = new ArrayList<>();
    private final Random random;

    public UserGenerator(Random random) {
        this.random = random;
        readAll();
    }

    @Override
    public void generate() {
        users.clear();
        for (int i = 0; i < newUsers; i++) {
            var name = surnames.get(random.nextInt(surnames.size())) + separator
                    + names.get(random.nextInt(names.size())) + separator
                    + patrons.get(random.nextInt(patrons.size()));
            var user = new User();
            user.setName(name);
            users.add(user);
        }
    }

    private void readAll() {
        try {
            names = read(pathNames);
            surnames = read(pathSurnames);
            patrons = read(pathPatrons);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User randomUser() {
        return users.get(random.nextInt(users.size()));
    }
}