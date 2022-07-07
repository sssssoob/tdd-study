package chap03;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpireDateCalculatorTest {

    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨(){

        assertExpireDate(PayData.builder()
                .billingDate(LocalDate.of(2019, 03, 01))
                .payAmount(10_000)
                .build(),
                LocalDate.of(2019, 04, 01)
        );

        assertExpireDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 05, 05))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 06, 05)
        );
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpireDate(PayData.builder()
                        .billingDate(LocalDate.of(2019, 01, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 02, 28)
        );

        assertExpireDate(PayData.builder()
                        .billingDate(LocalDate.of(2020, 05, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 06, 30)
        );

        assertExpireDate(PayData.builder()
                        .billingDate(LocalDate.of(2020, 01, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 02, 29)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,01,31))
                .billingDate(LocalDate.of(2019,02,28))
                .payAmount(10_000)
                .build();

        assertExpireDate(payData, LocalDate.of(2019, 03, 31));

        PayData payData2 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,01,30))
                .billingDate(LocalDate.of(2019,02,28))
                .payAmount(10_000)
                .build();

        assertExpireDate(payData2, LocalDate.of(2019, 03, 30));

        PayData payData3 = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,05,31))
                .billingDate(LocalDate.of(2019,06,30))
                .payAmount(10_000)
                .build();

        assertExpireDate(payData3, LocalDate.of(2019, 07, 31));

    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산(){
        PayData payData = PayData.builder()
                .billingDate(LocalDate.of(2019,03,01))
                .payAmount(20_000)
                .build();

        assertExpireDate(payData, LocalDate.of(2019, 05, 01));


        PayData payData2 = PayData.builder()
                .billingDate(LocalDate.of(2019,03,01))
                .payAmount(30_000)
                .build();

        assertExpireDate(payData2, LocalDate.of(2019, 06, 01));
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019,01,31))
                .billingDate(LocalDate.of(2019,02,28))
                .payAmount(20_000)
                .build();

        assertExpireDate(payData, LocalDate.of(2019, 04, 30));
    }

    @Test
    void 십만원을_납부하면_1년_제공(){
        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 01, 28))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2020, 01, 28)
        );
    }

    @Test
    void 십만원_이상_납부하면_1년_n개월_제공(){
        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 01, 28))
                        .payAmount(130_000)
                        .build(),
                LocalDate.of(2020, 04, 28)
        );
    }

    @Test
    void 윤달_마지막날에_10만원_납부(){
        assertExpireDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 02, 29))
                        .payAmount(100_000)
                        .build(),
                LocalDate.of(2021, 02, 28)
        );
    }


    private void assertExpireDate(
            PayData payData, LocalDate expectedExpireDate) {

        ExpireDateCalculator cal = new ExpireDateCalculator();

        LocalDate realExpireDate = cal.calulateExpireDate(payData);
        assertEquals(expectedExpireDate, realExpireDate);
    }





}
