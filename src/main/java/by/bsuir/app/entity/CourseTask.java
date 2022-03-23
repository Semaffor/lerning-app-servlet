package by.bsuir.app.entity;

/**
 * Database table mapping.
 *
 * @see BaseEntity
 */
public class CourseTask extends BaseEntity {

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
}
