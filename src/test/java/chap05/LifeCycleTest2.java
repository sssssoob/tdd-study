package chap05;

import org.junit.jupiter.api.*;

public class LifeCycleTest2 {

    public LifeCycleTest2() {
        System.out.println("new life cycle test");
    }

    @BeforeAll
    static void setUp(){
        System.out.println("Set up");
    }

    @Test
    void a() {
        System.out.println("A");
    }

    @Test
    void b(){
        System.out.println("B");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("tearDown");
    }

}
