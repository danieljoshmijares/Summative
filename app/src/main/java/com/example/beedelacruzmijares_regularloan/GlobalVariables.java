package com.example.beedelacruzmijares_regularloan;

import android.content.Context;
import android.widget.Toast;

public class GlobalVariables {
    public static String empid, empname, date, password;
    public static String adminid = "admin";
    public static double emloanamount, servicecharge, cash, emmonths, interest, sixmonpay = 0; //EmergencyLoan
    public static double sploanamount, spmonths, loanint, loantotal, monamort = 0; //SpecialLoan
    public static double salary, regloanamount, regmonths, regloanint, takehome, regservcharge, regmonamort, userLoanAmount = 0; //Regularloan

    private static Context context;

    // Constructor to set context
    public GlobalVariables(Context context) {
        GlobalVariables.context = context;
    }

    // Emergency Loan
    public static void setEmergencyLoanAmount(double newloan){
        if (newloan >= 5000 && newloan <= 25000) {
            emloanamount = newloan;
        }
    }
    public static void setEmergencyMonths(double newmon){
            emmonths = newmon;
    }
    public double getEmergencyServicecharge(){
        servicecharge = emloanamount * 0.01;
        return servicecharge;
    }
    public static double getEmergencyCash() {
        servicecharge = emloanamount * 0.01;
        cash = emloanamount + servicecharge;
        return cash;
    }
    public static double getEmergencyLoanPayable() {
        servicecharge = emloanamount * 0.01;
        interest = emloanamount * 0.006;
        sixmonpay = (emloanamount + servicecharge + interest) / emmonths;
        return sixmonpay;
    }

    // Special Loan
    public static void setSpecialLoanAmount(double newloan){
        sploanamount = newloan;
    }
    public static void setSpecialMonths(double newmon){
        spmonths = newmon;
    }
    public static double getTotalLoanInterest() {
        loanint = sploanamount * spmonths;
        if (spmonths >= 1 && spmonths <= 6) {
            loanint *= 0.006;
        } else if (spmonths >= 7 && spmonths <= 12) {
            loanint *= 0.0062;
        } else if (spmonths >= 13 && spmonths <= 18) {
            loanint *= 0.0065;
        }
        return loanint;
    }
    public static double getTotalLoanAmount() {
        loantotal = sploanamount + loanint;
        return loantotal;
    }
    public static double getMothlyAmort(){
        monamort = loantotal / spmonths;
        return loantotal;
    }

    // Regular Loan
    public static void setSalary(double newsal){
        salary = newsal;
    }

    public static void setRegularMonths(double newmon){
        regmonths = newmon;
    }

    public static double getMaxLoanAmount() {
        return salary * 2.5; // This is the maximum loanable amount
    }

    public static void setUserLoanAmount(double newLoan) {
        userLoanAmount = newLoan;
    }

    public static double getRegularLoanInterest() {
        regloanint = userLoanAmount * regmonths;
        if (regmonths >= 1 && regmonths <= 5) {
            regloanint *= 0.0062;
        } else if (regmonths >= 6 && regmonths <= 10) {
            regloanint *= 0.0065;
        } else if (regmonths >= 11 && regmonths <= 15) {
            regloanint *= 0.0068;
        } else if (regmonths >= 16 && regmonths <= 20) {
            regloanint *= 0.0075;
        } else if (regmonths >= 21 && regmonths <= 24) {
            regloanint *= 0.0080;
        }
        return regloanint;
    }

    public static double getServiceCharge() {
        regservcharge = userLoanAmount * 0.02;
        return regservcharge;
    }

    public static double getRegmonamort() {
        regservcharge = getServiceCharge();
        takehome = userLoanAmount - regservcharge;
        regmonamort = (userLoanAmount + regloanint) / regmonths;
        return regmonamort;
    }
}
