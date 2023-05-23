package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class IdObjectList<T extends IdObject> implements Serializable {
    private ArrayList<T> list;
    public IdObjectList() {
        list = new ArrayList<T>();
    }

    public void add(T object) {
        list.add(object);
    }

    public T getById(Long id) {
        for (T object : list) {
            if (Objects.equals(object.getId(), id)) {
                return object;
            }
        }
        throw new RuntimeException("Object with such id has not been found");
    }

    public int size() {
        return list.size();
    }

    public T get(int index) {
        return list.get(index);
    }

    public void remove(T object) {
        list.remove(object);
    }

    public void removeById(Long id) {
        list.remove(getById(id));
    }

}
