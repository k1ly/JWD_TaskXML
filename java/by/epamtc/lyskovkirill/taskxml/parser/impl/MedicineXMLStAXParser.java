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

import javax.xml.namespace.QName;
import javax.xml.stream.*;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicineXMLStAXParser implements XMLParser<Medicine> {

    @Override
    public List<Medicine> parseXML(Part xmlFilePart) {
        List<Medicine> resultList = new ArrayList<>();
        MedicineBuilder medicineBuilder = null;
        List<String> analogs = null;
        List<PackageType> versions = null;
        CertificateBuilder certificateBuilder = null;
        PackageBuilder packageBuilder = null;
        DosageBuilder dosageBuilder = null;

        try (InputStream xmlFile = xmlFilePart.getInputStream()) {
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = xmlInputFactory.createXMLEventReader(xmlFile);

            while (eventReader.hasNext()) {
                XMLEvent xmlEvent = eventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    switch (startElement.getName().getLocalPart()) {
                        case "medicine":
                            medicineBuilder = new MedicineBuilder();
                            medicineBuilder.withName(startElement.getAttributeByName(QName.valueOf("name")).getValue());
                            medicineBuilder.withPharma(startElement.getAttributeByName(QName.valueOf("pharma")).getValue());
                            medicineBuilder.withGroup(MedicineGroup.getGroupByName(startElement.getAttributeByName(QName.valueOf("group")).getValue()));
                            break;
                        case "analogs":
                            analogs = new ArrayList<>();
                            break;
                        case "analog":
                            if (analogs != null) {
                                xmlEvent = eventReader.nextEvent();
                                analogs.add(xmlEvent.asCharacters().getData());
                            }
                            break;
                        case "versions":
                            versions = new ArrayList<>();
                            break;
                        case "version":
                            if (versions != null) {
                                xmlEvent = eventReader.nextEvent();
                                versions.add(PackageType.getTypeByName(xmlEvent.asCharacters().getData()));
                            }
                            break;
                        case "certificate":
                            certificateBuilder = new CertificateBuilder();
                            certificateBuilder.withNumber(Integer.parseInt(startElement.getAttributeByName(QName.valueOf("number")).getValue()));
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                            certificateBuilder.withIssueDate(Date.from(LocalDate.parse(startElement.getAttributeByName(QName.valueOf("issueDate")).getValue(), formatter)
                                    .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                            certificateBuilder.withExpiryDate(Date.from(LocalDate.parse(startElement.getAttributeByName(QName.valueOf("expiryDate")).getValue(), formatter)
                                    .atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                            certificateBuilder.withOrganization(startElement.getAttributeByName(QName.valueOf("organization")).getValue());
                            break;
                        case "package":
                            packageBuilder = new PackageBuilder();
                            Attribute packageType = startElement.getAttributeByName(QName.valueOf("type"));
                            packageBuilder.withType(packageType != null ? PackageType.getTypeByName(packageType.getValue()) : PackageType.type);
                            packageBuilder.withQuantity(Integer.parseInt(startElement.getAttributeByName(QName.valueOf("quantity")).getValue()));
                            packageBuilder.withPrice(Double.parseDouble(startElement.getAttributeByName(QName.valueOf("price")).getValue()));
                            break;
                        case "dosage":
                            dosageBuilder = new DosageBuilder();
                            Attribute dosageAmount = startElement.getAttributeByName(QName.valueOf("amount"));
                            dosageBuilder.withAmount(dosageAmount != null ? Double.parseDouble(dosageAmount.getValue()) : 0);
                            Attribute dosagePeriod = startElement.getAttributeByName(QName.valueOf("period"));
                            dosageBuilder.withPeriod(dosagePeriod != null ? Integer.parseInt(dosagePeriod.getValue()) : 0);
                            break;
                    }
                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    switch (endElement.getName().getLocalPart()) {
                        case "medicine":
                            if (medicineBuilder != null)
                                resultList.add(medicineBuilder.build());
                        case "analogs":
                            if (medicineBuilder != null)
                                medicineBuilder.withAnalogs(analogs);
                            break;
                        case "versions":
                            if (medicineBuilder != null)
                                medicineBuilder.withVersions(versions);
                            break;
                        case "certificate":
                            if (medicineBuilder != null && certificateBuilder != null)
                                medicineBuilder.withCertificate(certificateBuilder.build());
                            break;
                        case "package":
                            if (medicineBuilder != null && packageBuilder != null)
                                medicineBuilder.withPack(packageBuilder.build());
                            break;
                        case "dosage":
                            if (medicineBuilder != null && dosageBuilder != null)
                                medicineBuilder.withDosage(dosageBuilder.build());
                            break;
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            throw new RuntimeException("Ошибка разбора XML файла StAX парсером", e);
        }
        return resultList;
    }
}