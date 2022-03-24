package by.bsuir.app.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Class which mapping custom sql query result set.
 *
 * @see BaseEntity
 */
public class UserTaskDTO extends BaseEntity {
    private static final long serialVersionUID = 42L;

    private String title;
    private String description;
    private String studentUsername;
    private String solution;
    private String feedback;
    private int mark;
    private Date deadline;
    private Date checkDate;
    private Date submittedDate;

    private UserTaskDTO() {
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

    public String getDescription() {
        return description;
    }

    public String getSolution() {
        return solution;
    }

    public String getFeedback() {
        return feedback;
    }

    public Date getDeadline() {
        return deadline;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public static UserTaskDTO.Builder getBuilder() {
        return new UserTaskDTO().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public UserTaskDTO.Builder setTitle(String title) {
            UserTaskDTO.this.title = title;
            return this;
        }

        public UserTaskDTO.Builder setDescription(String description) {
            UserTaskDTO.this.description = description;
            return this;
        }

        public UserTaskDTO.Builder setStudentUsername(String studentUsername) {
            UserTaskDTO.this.studentUsername = studentUsername;
            return this;
        }

        public UserTaskDTO.Builder setAnswer(String answer) {
            UserTaskDTO.this.solution = answer;
            return this;
        }

        public UserTaskDTO.Builder setMark(int mark) {
            UserTaskDTO.this.mark = mark;
            return this;
        }

        public UserTaskDTO.Builder setDeadline(Date deadline) {
            UserTaskDTO.this.deadline = deadline;
            return this;
        }

        public UserTaskDTO.Builder setSubmittedDate(Date submittedDate) {
            UserTaskDTO.this.submittedDate = submittedDate;
            return this;
        }

        public UserTaskDTO.Builder setFeedback(String feedback) {
            UserTaskDTO.this.feedback = feedback;
            return this;
        }

        public UserTaskDTO.Builder setId(Long id) {
            UserTaskDTO.super.id = id;
            return this;
        }

        public UserTaskDTO.Builder setCheckDate(Date checkDate) {
            UserTaskDTO.this.checkDate = checkDate;
            return this;
        }

        public UserTaskDTO build() {
            return UserTaskDTO.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        UserTaskDTO that = (UserTaskDTO) o;
        return mark == that.mark && Objects.equals(title, that.title) && Objects
                .equals(description, that.description) && Objects
                .equals(studentUsername, that.studentUsername) && Objects
                .equals(solution, that.solution) && Objects.equals(feedback, that.feedback) && Objects
                .equals(deadline, that.deadline) && Objects.equals(checkDate, that.checkDate) && Objects
                .equals(submittedDate, that.submittedDate);
    }

    @Override
    public int hashCode() {
        return Objects
                .hash(super.hashCode(), title, description, studentUsername, solution, feedback, mark, deadline,
                        checkDate,
                        submittedDate);
    }
}
