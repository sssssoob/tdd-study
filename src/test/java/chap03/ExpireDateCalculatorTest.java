package chap03;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpireDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){

        assertExpireDate(
                LocalDate.of(2019,03,01),
                1000,
                LocalDate.of(2019, 04, 01)
        );

        assertExpireDate(
                LocalDate.of(2019,05,05),
                1000,
                LocalDate.of(2019, 06, 05)
        );
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpireDate(
             LocalDate.of(2019,01,31),
             10_000,
             LocalDate.of(2019, 02, 28)
        );

        assertExpireDate(
                LocalDate.of(2019,05,31),
                10_000,
                LocalDate.of(2019, 06, 30)
        );

        assertExpireDate(
                LocalDate.of(2020,01,31),
                10_000,
                LocalDate.of(2020, 02, 29)
        );
    }

    private void assertExpireDate(
            LocalDate billingDate, int payAmount, LocalDate expectedExpireDate) {

        ExpireDateCalculator cal = new ExpireDateCalculator();

        LocalDate realExpireDate = cal.calulateExpireDate(billingDate, payAmount);
        assertEquals(expectedExpireDate, realExpireDate);
    }




}
