package viewmodel.TagsView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.Model;
import model.Tag;
import model.TagList;
import viewmodel.ViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteTagsViewModel implements ViewModel, PropertyChangeListener {

    private TagList allTags;
    private Model model;
    private BooleanProperty notification;


    public DeleteTagsViewModel(Model model) {
        this.model = model;
        notification = new SimpleBooleanProperty(false);

        load();
    }

    public void reset() {
        load();
    }

    public void load() {
        allTags = model.getAllTags();
    }

    public void deleteTag(Tag tag) {
        model.deleteTag(tag.getId());
    }

    public TagList getAllTags() {
        return allTags;
    }

    public Model getModel() {
        return model;
    }


    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("notification")) {
            notification.setValue(true);
        }
    }


}
