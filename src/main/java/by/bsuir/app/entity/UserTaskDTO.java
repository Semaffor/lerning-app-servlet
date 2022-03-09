package by.bsuir.app.entity;

import java.util.Date;

public class UserTaskDTO extends BaseEntity{
    private final String title;
    private final String studentUsername;
    private final int mark;
    private final Date submittedDate;

    public UserTaskDTO(Long id, String title, String studentUsername, int mark,
                       Date checkDate, boolean deleted) {
        super(id, deleted);
        this.title = title;
        this.studentUsername = studentUsername;
        this.mark = mark;
        this.submittedDate = checkDate;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public int getMark() {
        return mark;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public String getTitle() {
        return title;
    }
}
