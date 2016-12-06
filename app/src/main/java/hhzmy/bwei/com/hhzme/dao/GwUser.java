package hhzmy.bwei.com.hhzme.dao;

/**
 * Created by asus on 2016/11/17.
 */
public class GwUser {
    private int id;
    private String name;
    private String image;
    private String price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public GwUser(int id, String name, String image, String price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public GwUser() {
        super();
    }

    @Override
    public String toString() {
        return "GwUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
