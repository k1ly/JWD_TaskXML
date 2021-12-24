package by.epamtc.lyskovkirill.taskxml.builder;

import by.epamtc.lyskovkirill.taskxml.bean.*;
import by.epamtc.lyskovkirill.taskxml.bean.Package;
import by.epamtc.lyskovkirill.taskxml.bean.constants.MedicineGroup;
import by.epamtc.lyskovkirill.taskxml.bean.constants.PackageType;

import java.util.List;

public class MedicineBuilder {
    private String name;
    private String pharma;
    private MedicineGroup group;
    private List<String> analogs;
    private List<PackageType> versions;
    private Certificate certificate;
    private Package pack;
    private Dosage dosage;

    public MedicineBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public MedicineBuilder withPharma(String pharma) {
        this.pharma = pharma;
        return this;
    }

    public MedicineBuilder withGroup(MedicineGroup group) {
        this.group = group;
        return this;
    }

    public MedicineBuilder withAnalogs(List<String> analogs) {
        this.analogs = analogs;
        return this;
    }

    public MedicineBuilder withVersions(List<PackageType> versions) {
        this.versions = versions;
        return this;
    }

    public MedicineBuilder withCertificate(Certificate certificate) {
        this.certificate = certificate;
        return this;
    }

    public MedicineBuilder withPack(Package pack) {
        this.pack = pack;
        return this;
    }

    public MedicineBuilder withDosage(Dosage dosage) {
        this.dosage = dosage;
        return this;
    }

    public Medicine build()
    {
        Medicine medicine = new Medicine();
        medicine.setName(name);
        medicine.setPharma(pharma);
        medicine.setGroup(group);
        medicine.setAnalogs(analogs);
        medicine.setVersions(versions);
        medicine.setCertificate(certificate);
        medicine.setPack(pack);
        medicine.setDosage(dosage);
        return medicine;
    }
}
