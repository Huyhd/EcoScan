package app.creatingminds.ecoscan;

/**
 * Created by Kimsoomin on 2017-10-28.
 */

public class ListViewItemNew {
    private String titleStr ;
    private String descStr ;

    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
}