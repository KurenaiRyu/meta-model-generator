package io.github.natsusai.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void test() {
        System.out.println("new Student() = " + new Student().getId());
        System.out.println(Student_.STU_AGE);
    }
}