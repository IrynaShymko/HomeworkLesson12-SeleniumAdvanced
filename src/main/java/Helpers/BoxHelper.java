package Helpers;

import Helpers.Product;
import Helpers.ProductBox;
import Pages.ProductDetailsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoxHelper {
    private static Logger logger = LoggerFactory.getLogger("BoxHelper.class");

//    public Product buildProduct(ProductDetailsPage productDetailsPage){
//        logger.info("<<<<<<<<<< Building product");
//        Product product=new Product(productDetailsPage.getProductNameFromProductDetailsPage(),
//                productDetailsPage.getProductPriceFromProductDetailsPage(),
//                productDetailsPage.getQuantityFromProductDetailsPage());
//        return product;
//    }

//    public void changeProductBoxContent(ProductBox productBox, ProductDetailsPage productDetailsPage){
//        logger.info("<<<<<<<<<< Changing product box content");
//        Product productCreated = buildProduct(productDetailsPage);
//        for(Product product: productBox.getProducts()){
//            if(product.isTheSameProduct(productCreated)){
//                product.setQuantity((product.getQuantity()) + productCreated.getQuantity());
//            }
//            else {
//                productBox.getProducts().add(productCreated);
//            }
//        }
//
//    }
}
