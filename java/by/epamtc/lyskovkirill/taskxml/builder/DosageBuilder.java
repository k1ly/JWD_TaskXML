package by.epamtc.lyskovkirill.taskxml.builder;

import by.epamtc.lyskovkirill.taskxml.bean.Dosage;

public class DosageBuilder {
    private double amount;
    private int period;

    public DosageBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public DosageBuilder withPeriod(int period) {
        this.period = period;
        return this;
    }

    public Dosage build()
    {
        Dosage dosage = new Dosage();
        dosage.setAmount(amount);
        dosage.setPeriod(period);
        return dosage;
    }
}
