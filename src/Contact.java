public class Contact {
    private String name;
    private Contact next;
    private PropertyNode property;

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getNext() {
        return next;
    }

    public void setNext(Contact next) {
        this.next = next;
    }

    public PropertyNode getProperty() {
        return property;
    }

    public void setProperty(PropertyNode property) {
        this.property = property;
    }
}
