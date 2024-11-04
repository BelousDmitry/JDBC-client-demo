package jdbc.demo.DTO;

public class Item {

    private String title;
    private float price;
    private User user;

    public Item() {
    }

    public Item(String title, float price, User user) {
        this.title = title;
        this.price = price;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
