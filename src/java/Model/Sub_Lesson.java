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
public class Sub_Lesson {

    private int sub_lesson_id;
    private String quiz_id;
    private String sub_lesson_name;
    private String description;
    private String video_url;

    public Sub_Lesson() {
    }

    public Sub_Lesson(int sub_lesson_id, String quiz_id, String sub_lesson_name, String description, String video_url) {
        this.sub_lesson_id = sub_lesson_id;
        this.quiz_id = quiz_id;
        this.sub_lesson_name = sub_lesson_name;
        this.description = description;
        this.video_url = video_url;
    }

    public int getSub_lesson_id() {
        return sub_lesson_id;
    }

    public void setSub_lesson_id(int sub_lesson_id) {
        this.sub_lesson_id = sub_lesson_id;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(String quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getSub_lesson_name() {
        return sub_lesson_name;
    }

    public void setSub_lesson_name(String sub_lesson_name) {
        this.sub_lesson_name = sub_lesson_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    

}
