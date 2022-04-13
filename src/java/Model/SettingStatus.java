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
public class SettingStatus {
    private int setting_status_id;
    private String setting_status_name;

    public SettingStatus() {
    }

    public SettingStatus(int setting_status_id, String setting_status_name) {
        this.setting_status_id = setting_status_id;
        this.setting_status_name = setting_status_name;
    }

    public int getSetting_status_id() {
        return setting_status_id;
    }

    public void setSetting_status_id(int setting_status_id) {
        this.setting_status_id = setting_status_id;
    }

    public String getSetting_status_name() {
        return setting_status_name;
    }

    public void setSetting_status_name(String setting_status_name) {
        this.setting_status_name = setting_status_name;
    }
    
}
