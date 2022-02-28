package by.bsuir.app.entity;

public class CourseTask extends BaseEntity {

    private final static String COURSE_ID = "course_id";
    private final static String TASK_ID = "task_id";

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
