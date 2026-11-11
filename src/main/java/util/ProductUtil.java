package util;

import dto.ProductRequestDto;
import jakarta.enterprise.context.ApplicationScoped;
import model.Product;

@ApplicationScoped
public class ProductUtil {
	
	public static Product dtoToProduct(ProductRequestDto dto) {
		Product product = new Product();
		product.setName(dto.getName());
		product.setDescription(dto.getDescription());
		product.setCategory(dto.getCategory());
		product.setPrice(dto.getPrice());
		product.setStock(dto.getStock());
		return product;
	}
}
