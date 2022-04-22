package Helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProductBox {
    private List<Product> products = new ArrayList<>();
    private Double totalOrderCost = 0.0;
    private int totalQuantityProductsInBox = 0;
    private static Logger logger = LoggerFactory.getLogger("ProductBox.class");


    public Double getTotalOrderCostInBox(List<Product> products) {
        totalOrderCost = 0.0;
        if (products.size() > 0) {
            for (Product product : products) {
                totalOrderCost += product.getPrice()*product.getQuantity();
            }
            return totalOrderCost;
        } else return 0.0;
    }

    public int getTotalQuantityProductsInBox() {
        totalQuantityProductsInBox = 0;
        if (products.size() > 0) {
            for (Product product : products) {
                totalQuantityProductsInBox += product.getQuantity();
            }
            return totalQuantityProductsInBox;
        } else return 0;
    }


    public List<Product> getProducts() {
        return products;
    }


    public void setTotalQuantityProductsInBox(int totalQuantityProductsInBox) {
        this.totalQuantityProductsInBox = totalQuantityProductsInBox;
    }

    public Double getTotalOrderCost() {
        return getTotalOrderCostInBox(products);
    }

}
