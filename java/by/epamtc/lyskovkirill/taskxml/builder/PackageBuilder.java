package by.epamtc.lyskovkirill.taskxml.builder;

import by.epamtc.lyskovkirill.taskxml.bean.Package;
import by.epamtc.lyskovkirill.taskxml.bean.constants.PackageType;

public class PackageBuilder {
    private PackageType type;
    private int quantity;
    private double price;

    public PackageBuilder withType(PackageType type) {
        this.type = type;
        return this;
    }

    public PackageBuilder withQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public PackageBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    public Package build()
    {
        Package pack = new Package();
        pack.setType(type);
        pack.setQuantity(quantity);
        pack.setPrice(price);
        return pack;
    }
}
