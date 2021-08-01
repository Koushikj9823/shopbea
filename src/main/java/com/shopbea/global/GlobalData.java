package com.shopbea.global;

import com.shopbea.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<>();
    }
}
