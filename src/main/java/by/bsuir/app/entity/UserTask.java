package by.bsuir.app.entity;

import java.util.Date;

public class UserTask extends BaseEntity {

    public static final String TABLE = "user_task";
    public static final String MARK = "mark";
    public static final String CHECK_DATE = "check_date";

    private Long userId;
    private Long taskId;
    private int mark;
    private Date checkDate;

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

        public UserTask build() {
            return UserTask.this;
        }
    }
}
