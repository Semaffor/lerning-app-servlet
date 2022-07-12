package by.bsuir.app.entity;

import java.util.Objects;

/**
 * Database table mapping.
 *
 * @see BaseEntity
 */
public class CourseTask extends BaseEntity {
    private static final long serialVersionUID = 42L;

    public static final String TABLE = "course_task";

    private Long courseId;
    private Long taskId;

    public CourseTask(Long courseId, Long taskId, boolean deleted) {
        this.courseId = courseId;
        this.taskId = taskId;
        this.deleted = deleted;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
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
        CourseTask that = (CourseTask) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(taskId, that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), courseId, taskId);
    }
}
