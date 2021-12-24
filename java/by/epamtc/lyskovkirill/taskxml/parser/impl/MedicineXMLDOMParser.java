package by.epamtc.lyskovkirill.taskxml.parser.impl;

import by.epamtc.lyskovkirill.taskxml.bean.Medicine;
import by.epamtc.lyskovkirill.taskxml.bean.constants.MedicineGroup;
import by.epamtc.lyskovkirill.taskxml.bean.constants.PackageType;
import by.epamtc.lyskovkirill.taskxml.builder.CertificateBuilder;
import by.epamtc.lyskovkirill.taskxml.builder.DosageBuilder;
import by.epamtc.lyskovkirill.taskxml.builder.MedicineBuilder;
import by.epamtc.lyskovkirill.taskxml.builder.PackageBuilder;
import by.epamtc.lyskovkirill.taskxml.parser.XMLParser;
import jakarta.servlet.http.Part;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineXMLDOMParser implements XMLParser<Medicine> {

    @Override
    public List<Medicine> parseXML(Part xmlFilePart) {
        List<Medicine> resultList = new ArrayList<>();
        MedicineBuilder medicineBuilder;
        CertificateBuilder certificateBuilder;
        PackageBuilder packageBuilder;
        DosageBuilder dosageBuilder;

        Document xmlDocument;
        try (InputStream xmlFile = xmlFilePart.getInputStream()) {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            xmlDocument = documentBuilder.parse(xmlFile);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException("Ошибка разбора XML файла DOM парсером", e);
        }
        NodeList medicines = xmlDocument.getDocumentElement().getElementsByTagName("medicine");
        for (int i = 0; i < medicines.getLength(); i++) {
            Element medicineElement = (Element) medicines.item(i);

            medicineBuilder = new MedicineBuilder();
            medicineBuilder.withName(medicineElement.getAttribute("name"));
            medicineBuilder.withPharma(medicineElement.getAttribute("pharma"));
            medicineBuilder.withGroup(MedicineGroup.getGroupByName(medicineElement.getAttribute("group")));

            List<String> analogList = new ArrayList<>();
            NodeList analogs = medicineElement.getElementsByTagName("analog");
            for (int j = 0; j < analogs.getLength(); j++) {
                Element analogElement = (Element) analogs.item(j);
                analogList.add(analogElement.getTextContent());
            }

            medicineBuilder.withAnalogs(analogList);

            List<PackageType> versionList = new ArrayList<>();
            NodeList versions = medicineElement.getElementsByTagName("version");
            for (int j = 0; j < versions.getLength(); j++) {
                Element versionElement = (Element) versions.item(j);
                versionList.add(PackageType.getTypeByName(versionElement.getTextContent()));
            }
            medicineBuilder.withVersions(versionList);

            certificateBuilder = new CertificateBuilder();
            Element certificateElement = (Element) medicineElement.getElementsByTagName("certificate").item(0);

            certificateBuilder.withNumber(Integer.parseInt(certificateElement.getAttribute("number")));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            certificateBuilder.withIssueDate(Date.from(LocalDate.parse(certificateElement.getAttribute("issueDate"), formatter)
                    .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            certificateBuilder.withExpiryDate(Date.from(LocalDate.parse(certificateElement.getAttribute("expiryDate"), formatter)
                    .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
            certificateBuilder.withOrganization(certificateElement.getAttribute("organization"));

            medicineBuilder.withCertificate(certificateBuilder.build());

            packageBuilder = new PackageBuilder();
            Element packageElement = (Element) medicineElement.getElementsByTagName("package").item(0);

            String packageType = packageElement.getAttribute("type");
            packageBuilder.withType(!packageType.isEmpty() ? PackageType.getTypeByName(packageType) : PackageType.TYPE_1);
            packageBuilder.withQuantity(Integer.parseInt(packageElement.getAttribute("quantity")));
            packageBuilder.withPrice(Double.parseDouble(packageElement.getAttribute("price")));

            medicineBuilder.withPack(packageBuilder.build());

            dosageBuilder = new DosageBuilder();
            Element dosageElement = (Element) medicineElement.getElementsByTagName("dosage").item(0);

            String dosageAmount = dosageElement.getAttribute("amount");
            dosageBuilder.withAmount(!dosageAmount.isEmpty() ? Double.parseDouble(dosageAmount) : 0);
            String dosagePeriod = dosageElement.getAttribute("period");
            dosageBuilder.withPeriod(!dosagePeriod.isEmpty() ? Integer.parseInt(dosagePeriod) : 0);

            medicineBuilder.withDosage(dosageBuilder.build());

            resultList.add(medicineBuilder.build());
        }
        return resultList;
    }
}
