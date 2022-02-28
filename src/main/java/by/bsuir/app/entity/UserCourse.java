package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.Status;

import java.util.Date;

public class UserCourse extends BaseEntity{

    public final static String STATUS = "status";
    public final static String START_DATE = "start_date";

    private Long userId;
    private Long courseId;
    private Status status;
    private Date startDate;

    private UserCourse() {}

    public Long getUserId() {
        return userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Status getStatus() {
        return status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public static Builder getBuilder() {
        return new UserCourse().new Builder();
    }

    public class Builder {
        private Builder() {}

        public Builder setId(Long id) {
            UserCourse.this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            UserCourse.this.userId = userId;
            return this;
        }

        public Builder setCourseId(Long courseId) {
            UserCourse.this.courseId = courseId;
            return this;
        }

        public Builder setStatus(Status status) {
            UserCourse.this.status = status;
            return this;
        }

        public Builder setStartDate(Date startDate) {
            UserCourse.this.startDate = startDate;
            return this;
        }

        public Builder setDeleted(boolean deleted) {
            UserCourse.this.deleted = deleted;
            return this;
        }

        public UserCourse build() {
            return UserCourse.this;
        }
    }
}
