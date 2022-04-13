/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Thanh Phong
 */
public class Quiz {
    private int quizId;
    private String quizQuestion;
    private String quizAnswer;
    private String correctAnswer;
    private int subLessonId;
    private int lessonId;
    private int courseId;

    public Quiz() {
    }

    public Quiz(int quizId, String quizLesson, String quizAnswer, String correctAnswer, int subLessonId, int lessonId, int courseId) {
        this.quizId = quizId;
        this.quizQuestion = quizLesson;
        this.quizAnswer = quizAnswer;
        this.correctAnswer = correctAnswer;
        this.subLessonId = subLessonId;
        this.lessonId = lessonId;
        this.courseId = courseId;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(String quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    public String getQuizAnswer() {
        return quizAnswer;
    }

    public void setQuizAnswer(String quizAnswer) {
        this.quizAnswer = quizAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getSubLessonId() {
        return subLessonId;
    }

    public void setSubLessonId(int subLessonId) {
        this.subLessonId = subLessonId;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Quiz{" + "quizId=" + quizId + ", quizLesson=" + quizQuestion + ", quizAnswer=" + quizAnswer + ", correctAnswer=" + correctAnswer + ", subLessonId=" + subLessonId + ", lessonId=" + lessonId + ", courseId=" + courseId + '}';
    }
    
}
