package com.challenge.challenge.cron;

import com.challenge.challenge.entity.ProductEntity;
import com.challenge.challenge.repository.ProductRepository;
import com.challenge.challenge.util.SoapClient;
import com.challenge.challenge.wsdl.GetProductRequest;
import com.challenge.challenge.wsdl.GetProductResponse;
import com.challenge.challenge.wsdl.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WSDLScheduled {

    private final SoapClient soapClient;

    private final ProductRepository productRepository;

    @Autowired
    public WSDLScheduled(SoapClient soapClient, ProductRepository productRepository) {
        this.soapClient = soapClient;
        this.productRepository = productRepository;
    }

    @Scheduled(cron = "0 * * * * ?") // every minute
    public void updateProductsWithWSDL() {
        try {
            // Get all products that are not deleted
            List<ProductEntity> products = productRepository.findByDeletedFalse();

            // Create a request object to connect to the WSDL
            GetProductRequest request = new GetProductRequest();
            request.setWsVersion("2.0.0");
            request.setId("111111");
            request.setPassword("AS9N23R89QHWANHSCPAIH82399RGUAPS");
            request.setLocalizationCountry("US");
            request.setLocalizationLanguage("en");

            // Create a list to store updated products
            List<ProductEntity> updatedProducts = new ArrayList<>();
            for (ProductEntity product : products) {
                request.setProductId(String.valueOf(product.getExternalProductId()));
                GetProductResponse data = (GetProductResponse) soapClient
                        .callWebService("https://dsstandardtest.hitpromo.net/productDataV2RC1?ws=1", request);
                Product getProduct = data.getProduct();
                if (getProduct != null) {
                    product.setExternalName("external");
                    product.setInternalName("external");
                    product.setInternalProductId("external");
                    product.setDeleted(true);
                    updatedProducts.add(product);
                }
            }

            // Save all updated products
            productRepository.saveAll(updatedProducts);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
