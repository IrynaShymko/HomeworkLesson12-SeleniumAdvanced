package Helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Product {
    private String productName;
    private Double price;
    private int quantity=0;
    private static Logger logger = LoggerFactory.getLogger("Product.class");

    public Product(String productName, Double price, int quantity) {
        this.productName = productName;
        this.price = Double.parseDouble(String.format("%.2f",price));
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Boolean isTheSameProduct(Product product){
        if (this.productName.equals(product.productName)){
            return true;
        }
        else return false;
    }


    public Boolean areEqual(ProductBox productBox){
        if (this.productName.equals(productBox.getProducts().get(productBox.getProducts().size()-1).productName)
                && String.valueOf(this.price).equals(String.valueOf(productBox.getProducts().get(productBox.getProducts().size()-1).price))
                &&this.quantity==productBox.getProducts().get(productBox.getProducts().size()-1).getQuantity()){
            return true;
        }
        else return false;
    }
}
