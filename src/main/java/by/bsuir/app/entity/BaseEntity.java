package by.bsuir.app.entity;

import java.io.Serializable;

/**
 * Abstract class to exclude similar fields from entities.
 *
 * @see Serializable
 * @see Identifiable
 */
public abstract class BaseEntity implements Serializable, Identifiable {

    public static final String ID = "id";
    public final static String USER_ID = "user_id";
    public final static String COURSE_ID = "course_id";
    public final static String TASK_ID = "task_id";
    public static final String DELETED = "is_deleted";

    protected Long id;
    protected boolean deleted;

    public BaseEntity() {
    }

    public BaseEntity(Long id) {
        this.id = id;
    }

    public BaseEntity(Long id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

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
