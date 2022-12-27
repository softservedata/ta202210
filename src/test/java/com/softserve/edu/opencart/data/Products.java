package com.softserve.edu.opencart.data;

public enum Products {
    MACBOOK ("MacBook", "Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1..", "$602.00");

    private String name;
    private String description;
    private String price;

    private Products(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Products{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
