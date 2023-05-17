public class PropertyNode {
    private String title ;
    private PropertyNode next;
    private PropertyNode down;
    private DataNode data;

    public PropertyNode(String title, PropertyNode next, PropertyNode down, DataNode data) {
        this.title = title;
        this.next = next;
        this.down = down;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PropertyNode getNext() {
        return next;
    }

    public void setNext(PropertyNode next) {
        this.next = next;
    }

    public PropertyNode getDown() {
        return down;
    }

    public void setDown(PropertyNode down) {
        this.down = down;
    }

    public DataNode getData() {
        return data;
    }

    public void setData(DataNode data) {
        this.data = data;
    }
}
