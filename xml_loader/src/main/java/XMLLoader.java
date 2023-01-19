import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLLoader {
    private static final XMLLoader xmlLoaderInstance=new XMLLoader();
    public Port port;
    private final List<Gin> gins=new ArrayList<>();
    public XMLLoader(){
        port=new Port();
    }

    public static XMLLoader getInstance(){
        return xmlLoaderInstance;
    }

    public List<Gin> innerLoad(String filePath){

        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            Element element = document.getDocumentElement();
            NodeList list = element.getChildNodes();

            for(int i=0;i<list.getLength();i++){
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el=(Element) list.item(i);
                    if(el.getNodeName().contains("row")){
                        Gin gin=new Gin();
                        String manufacturerName=el.getElementsByTagName("manufacturer").item(0).getTextContent();
                        String bottle=el.getElementsByTagName("bottle").item(0).getTextContent();
                        String size=el.getElementsByTagName("size").item(0).getTextContent();
                        String price=el.getElementsByTagName("price").item(0).getTextContent();
                        gin.setManufacturer(manufacturerName);
                        gin.setBottle(bottle);
                        gin.setPrice(price);
                        gin.setSize(size);
                        gins.add(gin);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return gins;
    }

    public class Port implements IXMLLoader{
        @Override
        public List<Gin>  load(String filePath) {
            return innerLoad(filePath);
        }
    }
}
