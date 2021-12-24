package by.epamtc.lyskovkirill.taskxml.bean.constants;

public enum PackageType {
    type("Таблетки"),
    type1("Капсулы");

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
