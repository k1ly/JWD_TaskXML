package by.epamtc.lyskovkirill.taskxml.parser;

import by.epamtc.lyskovkirill.taskxml.bean.Medicine;
import by.epamtc.lyskovkirill.taskxml.bean.constants.MedicineGroup;
import by.epamtc.lyskovkirill.taskxml.bean.constants.PackageType;
import by.epamtc.lyskovkirill.taskxml.builder.CertificateBuilder;
import by.epamtc.lyskovkirill.taskxml.builder.DosageBuilder;
import by.epamtc.lyskovkirill.taskxml.builder.MedicineBuilder;
import by.epamtc.lyskovkirill.taskxml.builder.PackageBuilder;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineSAXHandler extends DefaultHandler {
    private String elementName = "";
    private List<Medicine> medicineList;

    private MedicineBuilder medicineBuilder;
    private List<String> analogs;
    private List<PackageType> versions;
    private CertificateBuilder certificateBuilder;
    private PackageBuilder packageBuilder;
    private DosageBuilder dosageBuilder;

    public MedicineSAXHandler() {
        medicineList = new ArrayList<>();
    }

    public List<Medicine> getMedicineList() {
        return medicineList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "medicine":
                medicineBuilder = new MedicineBuilder();
                medicineBuilder.withName(attributes.getValue("name"));
                medicineBuilder.withPharma(attributes.getValue("pharma"));
                medicineBuilder.withGroup(MedicineGroup.getGroupByName(attributes.getValue("group")));
                break;
            case "analogs":
                analogs = new ArrayList<>();
                break;
            case "versions":
                versions = new ArrayList<>();
                break;
            case "certificate":
                certificateBuilder = new CertificateBuilder();
                certificateBuilder.withNumber(Integer.parseInt(attributes.getValue("number")));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                certificateBuilder.withIssueDate(Date.from(LocalDate.parse(attributes.getValue("issueDate"), formatter)
                        .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                certificateBuilder.withExpiryDate(Date.from(LocalDate.parse(attributes.getValue("expiryDate"), formatter)
                        .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                certificateBuilder.withOrganization(attributes.getValue("organization"));
                break;
            case "package":
                packageBuilder = new PackageBuilder();
                String packageType = attributes.getValue("type");
                packageBuilder.withType(packageType != null ? PackageType.getTypeByName(packageType) : PackageType.TYPE_1);
                packageBuilder.withQuantity(Integer.parseInt(attributes.getValue("quantity")));
                packageBuilder.withPrice(Double.parseDouble(attributes.getValue("price")));
                break;
            case "dosage":
                dosageBuilder = new DosageBuilder();
                String dosageAmount = attributes.getValue("amount");
                dosageBuilder.withAmount(dosageAmount != null ? Double.parseDouble(dosageAmount) : 0);
                String dosagePeriod = attributes.getValue("period");
                dosageBuilder.withPeriod(dosagePeriod != null ? Integer.parseInt(dosagePeriod) : 0);
                break;
        }
        elementName = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "medicine":
                medicineList.add(medicineBuilder.build());
            case "analogs":
                medicineBuilder.withAnalogs(analogs);
                break;
            case "versions":
                medicineBuilder.withVersions(versions);
                break;
            case "certificate":
                medicineBuilder.withCertificate(certificateBuilder.build());
                break;
            case "package":
                medicineBuilder.withPack(packageBuilder.build());
                break;
            case "dosage":
                medicineBuilder.withDosage(dosageBuilder.build());
                break;
        }
        elementName = "";
    }

    @Override
    public void characters(char[] chars, int start, int length) {
        switch (elementName) {
            case "analog":
                if (analogs != null)
                    analogs.add(new String(chars, start, length));
                break;
            case "version":
                if (versions != null)
                    versions.add(PackageType.getTypeByName(new String(chars, start, length)));
                break;
        }
    }
}
