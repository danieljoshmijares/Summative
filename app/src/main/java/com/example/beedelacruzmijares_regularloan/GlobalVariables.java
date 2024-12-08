package com.example.beedelacruzmijares_regularloan;

import static java.lang.Double.parseDouble;

public class GlobalVariables {
    public static String empid, empname, date, password;
    public static double loanamount, servicecharge, cash, months, interest, sixmonpay;
    public static double loanint;

    public double getServicecharge(){
    servicecharge = loanamount * 0.01;
    return servicecharge;
    }

    public static double getCash() {
        cash = loanamount + servicecharge;
        return cash;
    }
    public static double getEmergencyLoan() {
        sixmonpay = (loanamount + servicecharge + interest)/months;
        return sixmonpay;
    }

}
