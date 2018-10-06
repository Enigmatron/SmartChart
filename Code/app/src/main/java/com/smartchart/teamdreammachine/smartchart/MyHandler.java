package com.smartchart.teamdreammachine.smartchart;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.HashMap;

public class MyHandler extends DefaultHandler {

    public static void main(String[] args) throws IOException, SAXException {
        XMLReader p = XMLReaderFactory.createXMLReader();
        p.setContentHandler(new MyHandler());
        p.parse("test.xml");
    }

    // list holding output
    private Table table;
    private TableRow row;

    public Table getTable() { return(this.table); }

    boolean x = false;
    boolean y = false;
    boolean otherVal = false;
    String otherKey = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.print("<" + qName + ">");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.print("</" + qName + ">");
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        for(int i = start; i < start + length; i++) {
            System.out.print(ch[i]);
        }
    }
}
