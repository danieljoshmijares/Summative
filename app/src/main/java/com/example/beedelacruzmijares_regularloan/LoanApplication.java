package com.example.beedelacruzmijares_regularloan;

public class LoanApplication {
    private String employeeId;
    private String loanType;
    private double loanAmount;
    private double monthsToPay;
    private double serviceCharge;
    private double interest;
    private double monthlyAmortization;
    private double totalAmountPayable;
    private String loanStatus;

    public LoanApplication() {
        // Default constructor required for calls to DataSnapshot.getValue(LoanApplication.class)
    }

    public LoanApplication(String employeeId, String loanType, double loanAmount, double monthsToPay,
                           double serviceCharge, double interest, double monthlyAmortization,
                           double totalAmountPayable, String loanStatus) {
        this.employeeId = employeeId;
        this.loanType = loanType;
        this.loanAmount = loanAmount;
        this.monthsToPay = monthsToPay;
        this.serviceCharge = serviceCharge;
        this.interest = interest;
        this.monthlyAmortization = monthlyAmortization;
        this.totalAmountPayable = totalAmountPayable;
        this.loanStatus = loanStatus;
    }

    // Getters and setters...

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }

    public double getLoanAmount() { return loanAmount; }
    public void setLoanAmount(double loanAmount) { this.loanAmount = loanAmount; }

    public double getMonthsToPay() { return monthsToPay; }
    public void setMonthsToPay(double monthsToPay) { this.monthsToPay = monthsToPay; }

    public double getServiceCharge() { return serviceCharge; }
    public void setServiceCharge(double serviceCharge) { this.serviceCharge = serviceCharge; }

    public double getInterest() { return interest; }
    public void setInterest(double interest) { this.interest = interest; }

    public double getMonthlyAmortization() { return monthlyAmortization; }
    public void setMonthlyAmortization(double monthlyAmortization) { this.monthlyAmortization = monthlyAmortization; }

    public double getTotalAmountPayable() { return totalAmountPayable; }
    public void setTotalAmountPayable(double totalAmountPayable) { this.totalAmountPayable = totalAmountPayable; }

    public String getLoanStatus() { return loanStatus; }
    public void setLoanStatus(String loanStatus) { this.loanStatus = loanStatus; }
}
