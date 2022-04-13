/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javax.xml.bind.annotation.*;
/**
 *
 * @author Quang
 */
@XmlRootElement
public class SettingType {

    private int setting_type_id;
    private String setting_type_name;

    public SettingType() {
    }

    public SettingType(int setting_type_id, String setting_type_name) {
        this.setting_type_id = setting_type_id;
        this.setting_type_name = setting_type_name;
    }

    public int getSetting_type_id() {
        return setting_type_id;
    }

    public void setSetting_type_id(int setting_type_id) {
        this.setting_type_id = setting_type_id;
    }

    public String getSetting_type_name() {
        return setting_type_name;
    }

    public void setSetting_type_name(String setting_type_name) {
        this.setting_type_name = setting_type_name;
    }

}
