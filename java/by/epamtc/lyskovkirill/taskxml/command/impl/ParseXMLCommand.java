package by.epamtc.lyskovkirill.taskxml.command.impl;

import by.epamtc.lyskovkirill.taskxml.bean.Medicine;
import by.epamtc.lyskovkirill.taskxml.command.Command;
import by.epamtc.lyskovkirill.taskxml.command.CommandResult;
import by.epamtc.lyskovkirill.taskxml.controller.page.Page;
import by.epamtc.lyskovkirill.taskxml.parser.XMLParser;
import by.epamtc.lyskovkirill.taskxml.parser.impl.MedicineXMLDOMParser;
import by.epamtc.lyskovkirill.taskxml.parser.impl.MedicineXMLSAXParser;
import by.epamtc.lyskovkirill.taskxml.parser.impl.MedicineXMLStAXParser;
import by.epamtc.lyskovkirill.taskxml.validation.XMLValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

public class ParseXMLCommand implements Command {
    private final static String MULTIPART_CONTENT_TYPE = "multipart/form-data";
    private final static String XML_FILE = "xml_file";
    private final static String XSD_FILE = "xsd_file";

    private final static String PARSER = "parser";
    private final static String DOM = "DOM";
    private final static String SAX = "SAX";
    private final static String STAX = "StAX";

    private final static String RESULT_LIST = "result_list";
    private final static String NOT_CHECKED_XML = "not_checked_xml";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        List<Medicine> resultList = null;
        boolean notCheckedXML = true;

        String contentType = request.getContentType();
        if (contentType != null && contentType.contains(MULTIPART_CONTENT_TYPE)) {
            Logger logger = LogManager.getLogger();
            try {
                Part xmlFilePart = request.getPart(XML_FILE);
                Part xsdFilePart = request.getPart(XSD_FILE);

                if (!xsdFilePart.getSubmittedFileName().isEmpty()) {
                    XMLValidator validator = new XMLValidator();
                    validator.validateXML(xmlFilePart, xsdFilePart);
                    notCheckedXML = false;
                    logger.info("XML файл успешно прошел валидацию");
                }

                XMLParser<Medicine> parser = null;
                switch (request.getParameter(PARSER)) {
                    case DOM:
                        parser = new MedicineXMLDOMParser();
                        break;
                    case SAX:
                        parser = new MedicineXMLSAXParser();
                        break;
                    case STAX:
                        parser = new MedicineXMLStAXParser();
                        break;
                }
                if (parser != null)
                    resultList = parser.parseXML(xmlFilePart);
                logger.info("XML файл успешно прошел парсинг");
            } catch (ServletException | IOException e) {
                logger.error("Ошибка чтения загруженного файла!");
                throw new RuntimeException("Ошибка чтения загруженного файла", e);
            }
        }
        request.setAttribute(RESULT_LIST, resultList);
        request.setAttribute(PARSER, request.getParameter(PARSER));
        request.setAttribute(NOT_CHECKED_XML, notCheckedXML);
        return new CommandResult(Page.RESULT_PAGE.getValue());
    }
}