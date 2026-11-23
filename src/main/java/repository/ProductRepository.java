package repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;

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
	
	public Optional<Product> updateById(ObjectId id, Product product){
		Product updatedProduct = products.findOneAndUpdate(Filters.eq("_id", id), 
				Updates.combine(
					Updates.set("name", product.getName()),
					Updates.set("description", product.getDescription()),
					Updates.set("price", product.getPrice()),
					Updates.set("stock", product.getStock()),
					Updates.set("category", product.getCategory()),
					Updates.set("created", Instant.now())
				), 
				new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER));
		return Optional.ofNullable(updatedProduct);
	}
	
	public Optional<Product> deleteById(ObjectId id){
		Product deletedProduct = products.findOneAndUpdate(
				Filters.eq("_id", id),
				Updates.set("status", "inactive"),
				new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER)
				);
		
		return Optional.ofNullable(deletedProduct);
	}
}
