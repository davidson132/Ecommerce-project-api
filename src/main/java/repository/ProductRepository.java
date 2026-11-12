package repository;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import model.Product;

@ApplicationScoped
public class ProductRepository {
	
	@Inject
	private MongoDatabase db;
	
	public void insert(Product product) {
		MongoCollection<Product> products = db.getCollection("products", Product.class);
		products.insertOne(product);
	}
	
	public List<Product> getProducts(){
		MongoCollection<Product> products = db.getCollection("products", Product.class);
		return products.find().into(new ArrayList<>());
	}
}
