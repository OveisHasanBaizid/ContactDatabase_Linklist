public class Contact {
    private String name;
    private Contact next;
    private PropertyNode down;

    public Contact(String name, Contact next, PropertyNode down) {
        this.name = name;
        this.next = next;
        this.down = down;
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

    public PropertyNode getDown() {
        return down;
    }

    public void setDown(PropertyNode down) {
        this.down = down;
    }
}
