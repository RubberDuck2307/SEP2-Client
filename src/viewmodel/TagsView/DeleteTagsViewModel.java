package viewmodel.TagsView;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.Model;
import model.Tag;
import model.TagList;
import viewmodel.ViewModel;
import viewmodel.ViewModelWithNavigationMenu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeleteTagsViewModel extends ViewModelWithNavigationMenu {

    private TagList allTags;

    public DeleteTagsViewModel(Model model) {
        super(model);
        notification = new SimpleBooleanProperty(false);

    }

    public void reset() {
        super.reset();
        load();
    }

    public void load() {
        super.load();
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        super.propertyChange(evt);
    }


}
