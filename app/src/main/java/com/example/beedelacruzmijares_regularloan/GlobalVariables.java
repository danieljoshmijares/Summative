package com.example.beedelacruzmijares_regularloan;

import static java.lang.Double.parseDouble;

public class GlobalVariables {
    public static String empid, empname, date, password;
    public static double loanamount, servicecharge, cash, months, interest, sixmonpay = 0;
    public static double loanint, loantotal, monamort = 0;
    public static double salary, regloanamount, regloanint, takehome, regservcharge, regmonamort = 0;

    //Emergency Loan
    public double getEmergencyServicecharge(){
    servicecharge = loanamount * 0.01;
    return servicecharge;
    }
    public static double getEmergencyCash() {
        cash = loanamount + servicecharge;
        return cash;
    }
    public static double getEmergencyLoanPayable() {
        interest = loanamount * 0.006;
        sixmonpay = (loanamount + servicecharge + interest)/months;
        return sixmonpay;
    }

    //Total Loan
    public static double getTotalLoanInterest() {
        loanint = loanamount * months;
        if (months >= 1 && months <= 6) {
            loanint *= 0.006;
        } else if (months >= 7 && months <= 12) {
            loanint *= 0.0062;
        } else if (months >= 13 && months <= 18) {
            loanint *= 0.0065;
        }
        return loanint;
    }
    public static double getTotalLoanAmount() {
        loantotal = loanamount + loanint;
        return loantotal;
    }
    public static double getMothlyAmort(){
        monamort = loantotal/months;
        return loantotal;
    }

    //Regular Loan
    public static double getRegularLoanAmount() {
        regloanamount = salary * 2.5;
        return regloanamount;
    }
    public static double getRegularLoanInterest() {
        regloanint = regloanamount * months;
        if (months >= 1 && months <= 5) {
            regloanint *= 0.0062;
        } else if (months >= 6 && months <= 10) {
            regloanint *= 0.0065;
        } else if (months >= 11 && months <= 15) {
            regloanint *= 0.0068;
        } else if (months >= 16 && months <= 20) {
            regloanint *= 0.0075;
        } else if (months >= 21 && months <= 24) {
            regloanint *= 0.0080;
        }
        return regservcharge;
    }
    public static double getTakeHome() {
        regservcharge = regloanamount * 0.02;
        takehome = regloanamount - (regloanint + regservcharge);
        return takehome;
    }
    public static double getRegmonamort() {
        regmonamort = takehome/months;
        return regmonamort;
    }

}
