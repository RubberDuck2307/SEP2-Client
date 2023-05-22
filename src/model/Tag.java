package model;

import java.io.Serializable;

/**
 * A class representing a tag
 * @author Anna Andrlova, Alex Bolfa, Cosmin Demian, Jan Metela, Arturs Ricards Rijnieks
 * @version 1.0 - May 2023
 */
public class Tag implements Serializable, IdObject
{
    /**
     * The name of the tag.
     */
    private String name;

    /**
     * The id of the tag.
     */
    private Long id;
    /**
     * The color of the tag.
     */
    private String color;

    /**
     * 2-argument constructor for the class Tag. Id is set to null.
     * @param name
     * @param color
     */
    public Tag(String name, String color) {
        this.name = name;
        this.color = color;
        this.id = null;
    }

    /**
     * 3-argument constructor for the class Tag.
     * @param name
     * @param id
     * @param color
     */
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
