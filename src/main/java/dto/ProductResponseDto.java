package dto;

import java.math.BigDecimal;
import java.time.Instant;

import model.Product;

public class ProductResponseDto {
	
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
	private int stock;
	private String category;
	private Instant created;
	private String status;
	
	public ProductResponseDto(Product product) {
		this.id = product.getId().toString();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
		this.stock = product.getStock();
		this.category = product.getCategory();
		this.created = product.getCreated();
		this.status = product.getStatus();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Instant getCreated() {
		return created;
	}
	public void setCreated(Instant created) {
		this.created = created;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ProductResponseDto [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", stock=" + stock + ", category=" + category + ", created=" + created + ", status=" + status + "]";
	}
	
	
}
