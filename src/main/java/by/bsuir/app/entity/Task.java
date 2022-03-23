package by.bsuir.app.entity;

import java.util.Date;

/**
 * Database table mapping.
 *
 * @see BaseEntity
 */
public class Task extends BaseEntity {

    public static final String TABLE = "task";
    public final static String TITLE = "title";
    public final static String DESCRIPTION = "description";
    public final static String DEADLINE = "deadline";

    private String title;
    private String description;
    private Long courseId;
    private Date deadline;

    private Task() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Date getDeadline() {
        return deadline;
    }

    public static Builder getBuilder() {
        return new Task().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(Long id) {
            Task.this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            Task.this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            Task.this.description = description;
            return this;
        }

        public Builder setCourse_id(Long course_id) {
            Task.this.courseId = course_id;
            return this;
        }

        public Builder setDeadline(Date deadline) {
            Task.this.deadline = deadline;
            return this;
        }

        public Builder setDeleted(boolean deleted) {
            Task.this.deleted = deleted;
            return this;
        }

        public Task build() {
            return Task.this;
        }
    }
}
