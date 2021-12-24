package by.epamtc.lyskovkirill.taskxml.bean;

import by.epamtc.lyskovkirill.taskxml.bean.constants.MedicineGroup;
import by.epamtc.lyskovkirill.taskxml.bean.constants.PackageType;

import java.io.Serializable;
import java.util.List;

public class Medicine implements Serializable {
    private String name;
    private String pharma;
    private MedicineGroup group;
    private List<String> analogs;
    private List<PackageType> versions;
    private Certificate certificate;
    private Package pack;
    private Dosage dosage;

    public Medicine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharma() {
        return pharma;
    }

    public void setPharma(String pharma) {
        this.pharma = pharma;
    }

    public MedicineGroup getGroup() {
        return group;
    }

    public void setGroup(MedicineGroup group) {
        this.group = group;
    }

    public List<String> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public List<PackageType> getVersions() {
        return versions;
    }

    public void setVersions(List<PackageType> versions) {
        this.versions = versions;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public Package getPack() {
        return pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", pharma='" + pharma + '\'' +
                ", group=" + group +
                ", analogs=" + analogs +
                ", versions=" + versions +
                ", certificate=" + certificate +
                ", pack=" + pack +
                ", dosage=" + dosage +
                '}';
    }
}
