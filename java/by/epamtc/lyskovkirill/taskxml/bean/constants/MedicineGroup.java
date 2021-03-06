package by.epamtc.lyskovkirill.taskxml.bean.constants;

public enum MedicineGroup {
    GROUP_1("Обезболевоющее"),
    GROUP_2("Жаропонижающее"),
    GROUP_3("Антибиотики"),
    GROUP_4("Витамины");

    private String name;

    MedicineGroup() {
    }

    MedicineGroup(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MedicineGroup getGroupByName(String name) {
        MedicineGroup medicineGroup = null;
        for (var group : MedicineGroup.values()) {
            if (group.getName().equals(name))
                medicineGroup = group;
        }
        return medicineGroup;
    }
}
