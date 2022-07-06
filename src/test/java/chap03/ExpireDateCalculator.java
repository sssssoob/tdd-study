package chap03;

import java.time.LocalDate;

public class ExpireDateCalculator {

    public LocalDate calulateExpireDate(LocalDate billingDate, int payAmount){
        return billingDate.plusMonths(1);
    }

    public LocalDate calulateExpireDate(PayData payData){
        return payData.getBillingDate().plusMonths(1);
    }



}
