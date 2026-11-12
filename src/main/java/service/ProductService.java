package service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import model.Product;
import repository.ProductRepository;

@ApplicationScoped
public class ProductService {

	@Inject
	private Validator validate;
	
	@Inject
	private ProductRepository productRepo;
	
	public Product createProduct(Product product) {
		Objects.requireNonNull(product, "Product is required");
		Set<ConstraintViolation<Product>> violations = validate.validate(product);
		
		if(!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
		productRepo.insert(product);
		return product;
	}
	
	public List<Product> getProducts(){
		return productRepo.getProducts();
	}
}
