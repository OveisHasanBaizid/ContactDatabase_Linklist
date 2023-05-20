public class DataNode {
    private String value;
    private DataNode next;
    public DataNode(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataNode getNext() {
        return next;
    }

    public void setNext(DataNode next) {
        this.next = next;
    }
}
