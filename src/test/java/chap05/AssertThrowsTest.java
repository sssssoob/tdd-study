package chap05;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertThrowsTest {

    void func() {
        throw new RuntimeException("some exception message...");
    }

    @Test
    void junit5에서_exception_테스트_1() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            func();
        });
    }

    @Test
    @Disabled
    void AssertAllTest(){
        assertAll(
                () -> assertEquals(3,5/ 2),
                () -> assertEquals(4,2*2),
                () -> assertEquals(6, 11/2)

        );
    }


}
