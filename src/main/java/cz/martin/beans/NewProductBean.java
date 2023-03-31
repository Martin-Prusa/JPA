package cz.martin.beans;

import cz.martin.entities.ProductEntity;
import cz.martin.services.ProductsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;

@Named
@RequestScoped
public class NewProductBean {
    @Inject
    private ProductsService productsService;

    private ProductEntity entity;

    public NewProductBean() {
        entity = new ProductEntity();
        entity.setTitle("");
    }

    public void addNewEntity() throws IOException {
        productsService.addProduct(entity);
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }

    public ProductEntity getEntity() {
        return entity;
    }
}
