package chap03;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;

public class ExpireDateCalculator {


    public LocalDate calulateExpireDate(PayData payData){

        int payAmount = payData.getPayAmount();

        int addedMonth = payAmount >= 100_000?
                (payAmount/100_000) * 12  + (payAmount -((payAmount/100_000) * 100_000)) / 10_000
                : payData.getPayAmount()/10_000;

        if(payData.getFirstBillingDate() != null){
            return expireDateUsingFirstBillingDate(payData, addedMonth);
        }else {
            return payData.getBillingDate().plusMonths(addedMonth);
        }



    }

    private LocalDate expireDateUsingFirstBillingDate(
            PayData payData, int addedMonths) {

        LocalDate candidateExp
                = payData.getBillingDate().plusMonths(addedMonths);
        final int dayOfFirstBilling
                = payData.getFirstBillingDate().getDayOfMonth();

        if(dayOfFirstBilling != candidateExp.getDayOfMonth()){

            final int dayLenOfCandiMon = YearMonth.from(candidateExp).lengthOfMonth();

            if(dayLenOfCandiMon < payData.getFirstBillingDate().getDayOfMonth()){
                return candidateExp.withDayOfMonth(dayLenOfCandiMon);
            }
            return candidateExp.withDayOfMonth(
                    dayOfFirstBilling
            );

        }else{
            return candidateExp;
        }


    }



}
