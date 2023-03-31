package cz.martin.repositories;

import cz.martin.entities.ProductEntity;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;

import java.util.List;

@ApplicationScoped
public class ProductsRepository {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ShopApp");

    public void addProduct(ProductEntity productEntity) {
        EntityManager em = emf.createEntityManager();

        EntityTransaction et = em.getTransaction();
        et.begin();

        em.persist(productEntity);

        et.commit();

        em.close();
    }

    public List<ProductEntity> getProducts() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProductEntity> query = em.createQuery("SELECT product FROM ProductEntity AS product", ProductEntity.class);
        List<ProductEntity> result = query.getResultList();
        em.close();
        return result;
    }

    public void deleteProduct(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProductEntity> query = em.createQuery("SELECT product FROM ProductEntity AS product WHERE product.id = :id", ProductEntity.class);
        query.setParameter("id", id);

        ProductEntity result = query.getSingleResult();

        EntityTransaction et = em.getTransaction();

        et.begin();

        em.remove(result);

        et.commit();

        em.close();
    }

    public void update(ProductEntity productEntity) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProductEntity> query = em.createQuery("SELECT product FROM ProductEntity AS product WHERE product.id = :id", ProductEntity.class);
        query.setParameter("id", productEntity.getId());

        ProductEntity result = query.getSingleResult();

        EntityTransaction et = em.getTransaction();

        et.begin();

        result.setTitle(productEntity.getTitle());
        result.setPrice(productEntity.getPrice());

        em.persist(result);
        et.commit();
        em.close();
    }

    public ProductEntity getProductById(int id) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProductEntity> query = em.createQuery("SELECT product FROM ProductEntity AS product WHERE product.id = :id", ProductEntity.class);
        query.setParameter("id", id);

        ProductEntity result = query.getSingleResult();

        em.close();

        return result;
    }

    @PreDestroy
    public void onDestroy() {
        emf.close();
    }
}
