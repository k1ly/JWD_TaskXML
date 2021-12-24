package by.epamtc.lyskovkirill.taskxml.bean;

import by.epamtc.lyskovkirill.taskxml.bean.constants.PackageType;

import java.io.Serializable;

public class Package implements Serializable {
    private PackageType type;
    private int quantity;
    private double price;

    public Package() {
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Package{" +
                "type=" + type +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
