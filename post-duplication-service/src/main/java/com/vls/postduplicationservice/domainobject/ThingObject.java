package com.vls.postduplicationservice.domainobject;
import java.util.UUID;

public class ThingObject {
    private UUID id;
    private String thing_name;
    private String origin;
    private int price;
    private int quantity;
    private String used_time;
    private CategoryObject categoryObject;

    public ThingObject() {
    }

    public ThingObject(String thing_name, String origin, int price, int quantity, String used_time, CategoryObject categoryObject) {
        this.thing_name = thing_name;
        this.origin = origin;
        this.price = price;
        this.quantity = quantity;
        this.used_time = used_time;
        this.categoryObject = categoryObject;
    }

    public ThingObject(UUID id, String thing_name, String origin, int price, int quantity, String used_time, CategoryObject categoryObject) {
        this.id = id;
        this.thing_name = thing_name;
        this.origin = origin;
        this.price = price;
        this.quantity = quantity;
        this.used_time = used_time;
        this.categoryObject = categoryObject;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getThing_name() {
        return thing_name;
    }

    public void setThing_name(String thing_name) {
        this.thing_name = thing_name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUsed_time() {
        return used_time;
    }

    public void setUsed_time(String used_time) {
        this.used_time = used_time;
    }

    public CategoryObject getCategoryObject() {
        return categoryObject;
    }

    public void setCategoryObject(CategoryObject categoryObject) {
        this.categoryObject = categoryObject;
    }

    @Override
    public String toString() {
        return "ThingObject{" +
                "id=" + id +
                ", thing_name='" + thing_name + '\'' +
                ", origin='" + origin + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", used_time='" + used_time + '\'' +
                ", categoryObject=" + categoryObject +
                '}';
    }
}
