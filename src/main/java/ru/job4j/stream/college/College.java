package ru.job4j.stream.college;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class College {

    private final Map<Student, Set<Subject>> students;

    public College(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        for (Student s : students.keySet()) {
            if (account.equals(s.getAccount())) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    public Optional<Subject> findBySubjectName(String account, String name) {
        Optional<Student> s = findByAccount(account);
        if (s.isPresent()) {
            Set<Subject> subjects = students.get(s.get());
            for (Subject subj : subjects) {
                if (name.equals(subj.getName())) {
                    return Optional.of(subj);
                }
            }
        }
        return Optional.empty();
    }
}
