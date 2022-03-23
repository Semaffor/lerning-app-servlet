package by.bsuir.app.entity;

import java.util.Date;

/**
 * Database table mapping.
 *
 * @see BaseEntity
 */
public class UserCourse extends BaseEntity {

    public static final String TABLE = "user_course";
    public final static String STATUS = "status";
    public final static String START_DATE = "start_date";

    private Long userId;
    private Long courseId;
    private Date startDate;

    public UserCourse() {
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public static Builder getBuilder() {
        return new UserCourse().new Builder();
    }

    public class Builder {
        private Builder() {
        }

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
