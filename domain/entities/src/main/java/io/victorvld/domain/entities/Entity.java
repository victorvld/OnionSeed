package io.victorvld.domain.entities;

public class Entity implements Cloneable {
    private String id;

    public boolean isEquals(Entity entity) {
        var result = false;
        if (entity.id != null && this.id != null) {
            result = this.id.equals(entity.id);
        }
        return result;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static Entity.Builder<?> builder() {
        return new Entity.Builder<>();
    }
    public static class Builder<T extends Builder<T>> {

        private String id;

        public Builder() {
        }

        public T id(String value) {
            this.id = value;
            return (T) this;
        }

        public Entity build() {
            return new Entity(this);
        }
    }

    protected Entity(Builder<?> builder) {
        this.id = builder.id;
    }
}
