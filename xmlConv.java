import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement//optional -> (name="wasIhrWollt")
class ClassToBeConverted { //Klassenname je nach gewünschtem XML tag umbenennen
    
    String tag1;
    int tag2, tag3;
    List<Integer> unter_tag = new LinkedList<>();
    //Für jede Variable muss eine getter/setter methode vorhanden sein
    
    ClassToBeConverted() {}
    
    ClassToBeConverted(String tag1, int tag2, int tag3) { //, (int, String, char)...
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        unter_tag.add(2);
        unter_tag.add(4);
        unter_tag.add(2);
    }


    @XmlElement//optional -> (name="wasIhrWollt")
    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    @XmlElement
    public int getTag2() {
        return tag2;
    }

    public void setTag2(int tag2) {
        this.tag2 = tag2;
    }

    @XmlElementWrapper(name = "wieAuchImmer")
    @XmlElement(name = "element")
    public List<Integer> getUnter_Tag() {
        return unter_tag;
    }

    public void setUnter_Tag(List<Integer> inp) {
        this.unter_tag = inp;
    }
    
    

    @XmlElement
    public int getTag3() {
        return tag3;
    }

    public void setTag3(int tag3) {
        this.tag3 = tag3;
    }

    @Override
    public String toString() {
        return "XML_Tool{" + "tag1=" + tag1 + ", tag2=" + tag2 + ", tag3=" + tag3 + ", unter_tag=" + unter_tag + '}';
    }
} //ClassToBeConverted

class XML_operator {
    public void stuffToXMLExample(String filename, ClassToBeConverted tool) throws Exception {
        File file = new File(filename);
        JAXBContext jaxbContext = JAXBContext.newInstance(ClassToBeConverted.class);

        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(tool, file);
        jaxbMarshaller.marshal(tool, System.out);
    }

    public ClassToBeConverted xmlToStuffExample(String fileName) {

            File xmlFile = new File(fileName);

            JAXBContext jaxbContext;
            try
            {
                jaxbContext = JAXBContext.newInstance(ClassToBeConverted.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                ClassToBeConverted employee = (ClassToBeConverted)jaxbUnmarshaller.unmarshal(xmlFile);

                return employee;
            }
            catch (JAXBException e) 
            {
                e.printStackTrace();
            }    
        return new ClassToBeConverted();
    }
} //Operations

public class ProStart {
    public static void main(String[] args) throws Exception {
        XML_operator op = new XML_operator();
        op.stuffToXMLExample("test.xml", new ClassToBeConverted("Macl", 1, 2));
        
        System.out.println("-----------------------");
        ClassToBeConverted xtool = op.xmlToStuffExample("test.xml");
        System.out.println(xtool.toString());
    }
}


