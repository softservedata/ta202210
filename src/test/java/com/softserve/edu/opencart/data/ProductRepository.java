package com.softserve.edu.opencart.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class ProductRepository {
    private static volatile ProductRepository instance = null;
    private static final String TIME_TEMPLATE = "HH_mm_ss_S";

    private ProductRepository() {
    }

    public static ProductRepository get() {
        if (instance == null) {
            synchronized (ProductRepository.class) {
                if (instance == null) {
                    instance = new ProductRepository();
                }
            }
        }
        return instance;
    }

    public IProduct getDefault() {
        return getValidProduct();
    }

    public IProduct getValidProduct() {
        return Product.get()
                .setName("MacBook")
                .setDescription("Intel Core 2 Duo processor Powered by an Intel Core 2 Duo processor at speeds up to 2.1..")
                .setPrice("$602.00")
                .build();
    }
}
