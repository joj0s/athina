package athina.formatters;

import java.time.LocalDate;

/**
 *
 * @author jojos
 */
public class FormattedRegInfo {
    private String type;
    private String username;
    private String course;
    private LocalDate date, examDate;
    private float grade;

    public FormattedRegInfo(String type, String username, String course, LocalDate date, LocalDate examDate, float grade) {
        this.type = type;
        this.username = username;
        this.course = course;
        this.date = date;
        this.examDate = examDate;
        this.grade = grade;
    }

    public FormattedRegInfo(String type, String username, String course, LocalDate date) {
        this.type = type;
        this.username = username;
        this.course = course;
        this.date = date;
    }
    
    

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getCourse() {
        return course;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public float getGrade() {
        return grade;
    }
    
    
}
