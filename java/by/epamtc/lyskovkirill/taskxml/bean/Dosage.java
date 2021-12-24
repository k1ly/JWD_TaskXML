package by.epamtc.lyskovkirill.taskxml.bean;

import java.io.Serializable;

public class Dosage implements Serializable {
    private double amount;
    private int period;

    public Dosage() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Dosage{" +
                "amount=" + amount +
                ", period=" + period +
                '}';
    }
}
