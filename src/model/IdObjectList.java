package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A class for storing objects that implement IdObject interface into array list.
 * It can be used as an alternative to other list classes for example TagList.
 * @param <T> type of objects that are stored in the list
 */
public class IdObjectList<T extends IdObject> implements Serializable {
    /**
     * The list of objects.
     */
    private ArrayList<T> list;

    /**
     * The zero-argument constructor initializes the list to a new empty ArrayList.
     */
    public IdObjectList() {
        list = new ArrayList<T>();
    }

    /**
     * Add the given object to the list.
     * @param object
     */

    public void add(T object) {
        list.add(object);
    }

    /**
     *
     * @param id
     * @return the object with the given id.
     */
    public T getById(Long id) {
        for (T object : list) {
            if (Objects.equals(object.getId(), id)) {
                return object;
            }
        }
        throw new RuntimeException("Object with such id has not been found");
    }

    /**
     *
     * @return the size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     *
     * @param index
     * @return the object with the given index.
     */
    public T get(int index) {
        return list.get(index);
    }

    /**
     * remove the given object from the list.
     * @param object
     */

    public void remove(T object) {
        list.remove(object);
    }

    /**
     * remove the object with the given id from the list.
     * @param id
     */
    public void removeById(Long id) {
        list.remove(getById(id));
    }

}
