package by.bsuir.app.entity;

import java.util.Date;
import java.util.Objects;

/**
 * Class which mapping custom sql query result set.
 *
 * @see BaseEntity
 */
public class UserTaskDto extends BaseEntity {
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

    private UserTaskDto() {
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

    public static UserTaskDto.Builder getBuilder() {
        return new UserTaskDto().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public UserTaskDto.Builder setTitle(String title) {
            UserTaskDto.this.title = title;
            return this;
        }

        public UserTaskDto.Builder setDescription(String description) {
            UserTaskDto.this.description = description;
            return this;
        }

        public UserTaskDto.Builder setStudentUsername(String studentUsername) {
            UserTaskDto.this.studentUsername = studentUsername;
            return this;
        }

        public UserTaskDto.Builder setAnswer(String answer) {
            UserTaskDto.this.solution = answer;
            return this;
        }

        public UserTaskDto.Builder setMark(int mark) {
            UserTaskDto.this.mark = mark;
            return this;
        }

        public UserTaskDto.Builder setDeadline(Date deadline) {
            UserTaskDto.this.deadline = deadline;
            return this;
        }

        public UserTaskDto.Builder setSubmittedDate(Date submittedDate) {
            UserTaskDto.this.submittedDate = submittedDate;
            return this;
        }

        public UserTaskDto.Builder setFeedback(String feedback) {
            UserTaskDto.this.feedback = feedback;
            return this;
        }

        public UserTaskDto.Builder setId(Long id) {
            UserTaskDto.super.id = id;
            return this;
        }

        public UserTaskDto.Builder setCheckDate(Date checkDate) {
            UserTaskDto.this.checkDate = checkDate;
            return this;
        }

        public UserTaskDto build() {
            return UserTaskDto.this;
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
        UserTaskDto that = (UserTaskDto) o;
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
