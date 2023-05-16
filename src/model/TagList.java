package model;

import java.io.Serializable;
import java.util.ArrayList;

public class TagList implements Serializable {
    private ArrayList<Tag> tags;


    public TagList(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public TagList() {
        tags = new ArrayList<>();
    }


    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public Tag getTagById(Long id) {
        for (Tag tag : tags) {
            if (tag.getId().equals(id)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("No tag with that id");
    }

    public Tag get(int index) {
        return tags.get(index);
    }

    public int size() {
        return tags.size();
    }


    public boolean containsById(Tag tag) {
        for (Tag savedTag : tags) {
            if (savedTag.getId().equals(tag.getId())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "TagList{" +
                "tags=" + tags +
                '}';
    }
}
