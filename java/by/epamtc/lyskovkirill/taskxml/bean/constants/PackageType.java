package by.epamtc.lyskovkirill.taskxml.bean.constants;

public enum PackageType {
    TYPE_1("Таблетки"),
    TYPE_2("Капсулы"),
    TYPE_3("Порошок"),
    TYPE_4("Капли");

    private String name;

    PackageType() {
    }

    PackageType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PackageType getTypeByName(String name) {
        PackageType packageType = null;
        for (var type : PackageType.values()) {
            if (type.getName().equals(name))
                packageType = type;
        }
        return packageType;
    }
}
