/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Quang
 */
public class Setting {

    private int settingslist_id;
    private String settingsName;
    private String description;
    private SettingType type;
    private String value;
    private SettingStatus status;
    private int type1;
    private int status1;

    public Setting() {
    }

    public Setting(int settingslist_id, String settingsName, String description, SettingType type, String value, SettingStatus status) {
        this.settingslist_id = settingslist_id;
        this.settingsName = settingsName;
        this.description = description;
        this.type = type;
        this.value = value;
        this.status = status;
    }

    public Setting(int settingslist_id, String settingsName, String description,  int type1, String value, int status1 ) {
        this.settingslist_id = settingslist_id;
        this.settingsName = settingsName;
        this.description = description;
        this.value = value;
        this.type1 = type1;
        this.status1 = status1;
    }

    public int getSettingslist_id() {
        return settingslist_id;
    }

    public void setSettingslist_id(int settingslist_id) {
        this.settingslist_id = settingslist_id;
    }

    public String getSettingsName() {
        return settingsName;
    }

    public void setSettingsName(String settingsName) {
        this.settingsName = settingsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SettingType getType() {
        return type;
    }

    public void setType(SettingType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SettingStatus getStatus() {
        return status;
    }

    public void setStatus(SettingStatus status) {
        this.status = status;
    }

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public int getStatus1() {
        return status1;
    }

    public void setStatus1(int status1) {
        this.status1 = status1;
    }
    
    
    @Override
    public String toString() {
        return "Setting{" + "settingslist_id=" + settingslist_id + ", settingsName=" + settingsName + ", description=" + description + ", type=" + type + ", value=" + value + ", status=" + status + '}';
    }

   
    

}
