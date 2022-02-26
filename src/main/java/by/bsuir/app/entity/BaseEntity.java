package by.bsuir.app.entity;

import java.io.Serializable;

public class BaseEntity implements Serializable, Identifiable {

    public static final String ID = "id";

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
