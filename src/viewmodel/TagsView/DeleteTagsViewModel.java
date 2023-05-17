package viewmodel.TagsView;

import model.Model;
import model.Tag;
import model.TagList;
import viewmodel.ViewModel;

public class DeleteTagsViewModel implements ViewModel {

    private TagList allTags;
    private Model model;


    public DeleteTagsViewModel(Model model){
        this.model = model;

        load();
    }
    public void reset(){
        load();
    }

    public void load(){
        allTags = model.getAllTags();
    }
    public void deleteTag(Tag tag){
        model.deleteTag(tag.getId());
    }
    public TagList getAllTags() {
        return allTags;
    }

    public Model getModel() {
        return model;
    }

    public void setAllTags(TagList allTags) {
        this.allTags = allTags;
    }

    public void setModel(Model model) {
        this.model = model;
    }


}
