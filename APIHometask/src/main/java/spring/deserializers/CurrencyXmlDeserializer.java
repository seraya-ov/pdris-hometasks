package spring.deserializers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import spring.entities.Currency;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class CurrencyXmlDeserializer {
    public Currency deserialize(String xml, String date) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        Document doc = documentBuilder.parse(new InputSource(new StringReader(xml)));

        Currency currency = new Currency();
        currency.setDate(date);

        NodeList currencies = ((Element) doc.getElementsByTagName("ValCurs").item(0)).getElementsByTagName("Valute");

        for (int i = 0; i < currencies.getLength(); ++i) {
            Element element = (Element) currencies.item(i);
            switch (element.getAttribute("ID")) {
                case "R01010":
                    currency.setAud(Double.parseDouble((element.getElementsByTagName("Value").item(0)).getTextContent().replace(",", ".")));
                    break;
                case "R01035":
                    currency.setGbr(Double.parseDouble((element.getElementsByTagName("Value").item(0)).getTextContent().replace(",", ".")));
                    break;
                case "R01235":
                    currency.setUsd(Double.parseDouble((element.getElementsByTagName("Value").item(0)).getTextContent().replace(",", ".")));
                    break;
                case "R01239":
                    currency.setEur(Double.parseDouble((element.getElementsByTagName("Value").item(0)).getTextContent().replace(",", ".")));
                    break;
                case "R01350":
                    currency.setCad(Double.parseDouble((element.getElementsByTagName("Value").item(0)).getTextContent().replace(",", ".")));
                    break;
                case "R01625":
                    currency.setSgd(Double.parseDouble((element.getElementsByTagName("Value").item(0)).getTextContent().replace(",", ".")));
                    break;
                case "R01775":
                    currency.setChf(Double.parseDouble((element.getElementsByTagName("Value").item(0)).getTextContent().replace(",", ".")));
                    break;
            }

        }
        return currency;
    }
}
