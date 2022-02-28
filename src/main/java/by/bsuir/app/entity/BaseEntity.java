package by.bsuir.app.entity;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable, Identifiable {

    public static final String ID = "id";
    private final static String USER_ID = "user_id";
    private final static String COURSE_ID = "course_id";
    private final static String TASK_ID = "task_id";
    public static final String DELETED = "is_deleted";

    protected Long id;
    protected boolean deleted;


    @Override
    public Long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
