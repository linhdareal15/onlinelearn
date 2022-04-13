/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Louis
 */
public class Lesson {
    private int lesson_id;
    private String title;
    private int type;
    private int belongToTopic;
    private String oder;
    private boolean status;
    private String videolink;
    private String content;

    public Lesson() {
    }

    public Lesson(int lesson_id, String title, int type, int belongToTopic, String oder, boolean status, String videolink, String content) {
        this.lesson_id = lesson_id;
        this.title = title;
        this.type = type;
        this.belongToTopic = belongToTopic;
        this.oder = oder;
        this.status = status;
        this.videolink = videolink;
        this.content = content;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getBelongToTopic() {
        return belongToTopic;
    }

    public void setBelongToTopic(int belongToTopic) {
        this.belongToTopic = belongToTopic;
    }

    public String getOder() {
        return oder;
    }

    public void setOder(String oder) {
        this.oder = oder;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    
}
