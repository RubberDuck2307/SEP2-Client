package viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CommentsTable
{
    private StringProperty worker;
    private StringProperty comment;
    
    public CommentsTable(String worker, String comment)
    {
        this.worker = new SimpleStringProperty(worker);
        this.comment = new SimpleStringProperty(comment);
    }
    
    public String getWorker()
    {
        return worker.get();
    }
    
    public StringProperty workerProperty()
    {
        return worker;
    }
    
    public String getComment()
    {
        return comment.get();
    }
    
    public StringProperty commentProperty()
    {
        return comment;
    }
}
