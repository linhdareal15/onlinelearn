/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.admin.settingslist;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import Model.Setting;
import Model.SettingType;

/**
 *
 * @author ducky
 */
@XmlRootElement(name = "types")
@XmlAccessorType(XmlAccessType.FIELD)
class Types {
    @XmlElement(name = "type")
    private ArrayList<SettingType> types;

    public Types() {
        types = new ArrayList<SettingType>();
    }

    public ArrayList<SettingType> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<SettingType> types) {
        this.types = types;
    }

}

public class TypeConfigController {
    private static final String FILENAME = "/Config/type_config.xml";

    public String getRealPath(String fileName) {
        String path = this.getClass().getResource(fileName).getPath();
        return path;
    }

    public TypeConfigController() {
    }

    public ArrayList<SettingType> getTypesList() {
        ArrayList<SettingType> typesList = new ArrayList<>();
        try {
            
            File file = new File(getRealPath(FILENAME));
            System.out.println(file.exists());;
            JAXBContext jaxbContext = JAXBContext.newInstance(Types.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Types types = (Types) unmarshaller.unmarshal(file);
            
            return types.getTypes();
            
        } catch (JAXBException e) {
            return null;
        }
    }

    public void updateTypesList(ArrayList<SettingType> typesList) {

        try {

            File file = new File(getRealPath(FILENAME));
            JAXBContext jaxbContext = JAXBContext.newInstance(Types.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            Types types = new Types();
            types.setTypes(typesList);
            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(types, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        TypeConfigController tcc = new TypeConfigController();
        System.out.println(tcc.getRealPath(FILENAME));
        for (SettingType t : tcc.getTypesList()) {
            System.out.println(t.getSetting_type_name());
        }
//        Types types = new Types();
//        
//        types.getTypes().add(new Type(1, "Role"));
//        types.getTypes().add(new Type(2, "Subject"));

//        tcc.updateTypesList(types.getTypes());
    }
}
