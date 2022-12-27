package com.softserve.edu.opencart.data;

interface IName {
    IDescription setName(String name);
}

interface IDescription {
    IPrice setDescription(String description);
}

interface IPrice {
    IBuildProduct setPrice(String price);
}

interface IBuildProduct {
    IProduct build();
}

public class Product implements IName, IDescription, IPrice, IBuildProduct, IProduct {
    private String name;

    private String description;

    private String price;

    public static IName get() {
        return new Product();
    }
    public IDescription setName(String name) {
        this.name = name;
        return this;
    }

    public IPrice setDescription(String description) {
        this.description = description;
        return this;
    }
    public IBuildProduct setPrice (String price) {
        this.price = price;
        return this;
    }

    public IProduct build() {
        return this;
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
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}