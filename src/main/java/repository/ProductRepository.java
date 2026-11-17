package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Product;

@ApplicationScoped
public class ProductRepository {
	
	@Inject
	private MongoDatabase db;
	
	private MongoCollection<Product> products;
	
	@PostConstruct
	void initialize() {
		products = db.getCollection("products", Product.class);
	}
	
	public void insert(Product product) {
		products.insertOne(product);
	}
	
	public List<Product> getProducts(){
		return products.find().into(new ArrayList<>());
	}
	
	public Optional<Product> getProductById(ObjectId id){
		Product product =  products.find(Filters.eq("_id", id)).first();
		return Optional.ofNullable(product);
	}
}
