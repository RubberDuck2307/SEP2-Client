package model;

import java.io.Serializable;

public class Tag implements Serializable, IdObject
{

    private String name;
    private Long id;
    private String color;

    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Tag(String name, Long id, String color) {
        this.name = name;
        this.id = id;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
