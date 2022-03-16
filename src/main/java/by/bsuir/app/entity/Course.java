package by.bsuir.app.entity;

import by.bsuir.app.entity.enums.CourseFormat;
import by.bsuir.app.entity.enums.TechnologyType;

public class Course extends BaseEntity {

    public static final String TABLE = "course";
    public static final String COUCH_ID = "couch_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String DURATION = "duration";
    public static final String COURSE_FORMAT = "course_format";
    public static final String TECHNOLOGY = "technology";
    public static final String CURRENT_PUPILS_QUANTITY = "current_pupils_quantity";
    public static final String MAX_PUPILS_QUANTITY = "max_pupils_quantity";
    public static final String PHOTO = "photo_uri";
    public static final String ACTIVE = "is_active";
    public static final String DELETED = "is_deleted";

    private String title;
    private String description;
    private int duration;
    private TechnologyType technology;
    private CourseFormat courseFormat;
    private int currentPupilsQuantity;
    private int maxPupilsQuantity;
    private boolean active;
    private boolean deleted;
    private Long couchId;
    private String photoUri;

    private Course() {
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TechnologyType getTechnology() {
        return technology;
    }

    public int getDuration() {
        return duration;
    }

    public CourseFormat getCourseFormat() {
        return courseFormat;
    }

    public int getCurrentPupilsQuantity() {
        return currentPupilsQuantity;
    }

    public int getMaxPupilsQuantity() {
        return maxPupilsQuantity;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Long getCouchId() {
        return couchId;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCurrentPupilsQuantity(int currentPupilsQuantity) {
        this.currentPupilsQuantity = currentPupilsQuantity;
    }

    @Override
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public static Builder getBuilder() {
        return new Course().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setId(Long id) {
            Course.this.id = id;
            return this;
        }

        public Builder setTitle(String title) {
            Course.this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            Course.this.description = description;
            return this;
        }

        public Builder setTechnology(TechnologyType technologyType) {
            Course.this.technology = technologyType;
            return this;
        }

        public Builder setDuration(int duration) {
            Course.this.duration = duration;
            return this;
        }

        public Builder setCourseFormat(CourseFormat courseFormat) {
            Course.this.courseFormat = courseFormat;
            return this;
        }

        public Builder setCurrentPupilsQuantity(int currentPupilsQuantity) {
            Course.this.currentPupilsQuantity = currentPupilsQuantity;
            return this;
        }

        public Builder setMaxPupilsQuantity(int maxPupilsQuantity) {
            Course.this.maxPupilsQuantity = maxPupilsQuantity;
            return this;
        }

        public Builder setActive(boolean active) {
            Course.this.active = active;
            return this;
        }

        public Builder setDeleted(boolean deleted) {
            Course.this.deleted = deleted;
            return this;
        }

        public Builder setCouchId(Long couchId) {
            Course.this.couchId = couchId;
            return this;
        }

        public Builder setPhotoURI(String photoURL) {
            Course.this.photoUri = photoURL;
            return this;
        }
        public Course build() {
            return Course.this;
        }
    }
}
