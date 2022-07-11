package chap05;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DisabledTest {

    @DisplayName("값 같은지 비교")
    @Disabled
    @Test
    void assertEqualsMethod(){
        System.out.println("assert equals method");
    }

}
