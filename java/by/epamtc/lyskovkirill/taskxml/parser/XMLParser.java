package by.epamtc.lyskovkirill.taskxml.parser;

import jakarta.servlet.http.Part;

import java.util.List;

public interface XMLParser<T> {
    List<T> parseXML(Part xmlFilePart);
}
