package cz.martin.beans;

import cz.martin.entities.ProductEntity;
import cz.martin.services.ProductsService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class UpdateBean {

    @Inject
    private ProductsService productsService;

    public ProductEntity getProduct() {
        String sId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        return productsService.getProductById(Integer.parseInt(sId));
    }
}
