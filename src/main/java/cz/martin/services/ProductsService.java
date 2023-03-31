package cz.martin.services;

import cz.martin.entities.ProductEntity;
import cz.martin.repositories.ProductsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;

@ApplicationScoped
@Named
public class ProductsService {

    @Inject
    private ProductsRepository productsRepository;

    public void addProduct(ProductEntity productEntity) {
        productsRepository.addProduct(productEntity);
    }

    public void deleteProduct(int id) {
        productsRepository.deleteProduct(id);
    }

    public void update(ProductEntity productEntity) {
        productsRepository.update(productEntity);
    }

    public List<ProductEntity> getProducts() {
        return productsRepository.getProducts();
    }

    public ProductEntity getProductById(int id) {
        return productsRepository.getProductById(id);
    }
}
