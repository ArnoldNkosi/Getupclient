package za.co.metropolitan.getup.client.modelRepository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.metropolitan.getup.client.model.product.ProductLifeHackLookup;

import java.util.List;


public interface ProductLifeHackLookupRepository  extends JpaRepository<ProductLifeHackLookup, Integer> {

    public ProductLifeHackLookup findByProductAndLifehack(String product, String lifehack);

    public List<ProductLifeHackLookup> findByProduct(String product);
}
