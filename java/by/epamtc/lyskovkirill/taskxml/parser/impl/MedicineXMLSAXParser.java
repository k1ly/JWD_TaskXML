package by.epamtc.lyskovkirill.taskxml.parser.impl;

import by.epamtc.lyskovkirill.taskxml.bean.Medicine;
import by.epamtc.lyskovkirill.taskxml.parser.MedicineSAXHandler;
import by.epamtc.lyskovkirill.taskxml.parser.XMLParser;
import jakarta.servlet.http.Part;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MedicineXMLSAXParser implements XMLParser<Medicine> {

    @Override
    public List<Medicine> parseXML(Part xmlFilePart) {
        List<Medicine> resultList;
        try(InputStream xmlFile = xmlFilePart.getInputStream()) {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();

            MedicineSAXHandler handler = new MedicineSAXHandler();
            saxParser.parse(xmlFile, handler);
            resultList = handler.getMedicineList();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException("Ошибка разбора XML файла SAX парсером", e);
        }
        return resultList;
    }
}

