package by.bsuir.app.entity;

import java.util.Date;

public class UserTask extends BaseEntity {

    public static final String TABLE = "user_task";
    public static final String MARK = "mark";
    public static final String ANSWER = "answer";
    public static final String FEEDBACK = "feedback";
    public static final String SUBMITTED_DATE = "submitted_date";
    public static final String CHECK_DATE = "check_date";

    private Long userId;
    private Long taskId;
    private int mark;
    private Date submittedDate;
    private Date checkDate;
    private String answer;
    private String feedback;

    public Long getUserId() {
        return userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public int getMark() {
        return mark;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public String getAnswer() {
        return answer;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public static Builder getBuilder() {
        return new UserTask().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setId(Long id) {
            UserTask.this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            UserTask.this.userId = userId;
            return this;
        }

        public Builder setTaskId(Long taskId) {
            UserTask.this.taskId = taskId;
            return this;
        }

        public Builder setMark(int mark) {
            UserTask.this.mark = mark;
            return this;
        }

        public Builder setCheckDate(Date checkDate) {
            UserTask.this.checkDate = checkDate;
            return this;
        }

        public Builder setDeleted(boolean deleted) {
            UserTask.this.deleted = deleted;
            return this;
        }

        public Builder setAnswer(String answer) {
            UserTask.this.answer = answer;
            return this;
        }

        public Builder setSubmittedDate(Date submittedDate) {
            UserTask.this.submittedDate = submittedDate;
            return this;
        }

        public Builder setFeedback(String feedback) {
            UserTask.this.feedback = feedback;
            return this;
        }

        public UserTask build() {
            return UserTask.this;
        }
    }
}
