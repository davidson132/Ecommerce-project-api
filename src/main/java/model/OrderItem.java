package model;

import java.math.BigDecimal;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OrderItem {
	
	@NotNull
	private ObjectId productId;
	
	@NotBlank
	private String productName;
	
	@NotNull
	@DecimalMin(value = "0.01")
	private BigDecimal price;
	
	@Min(1)
	private int quantity;

	public ObjectId getProductId() {
		return productId;
	}

	public void setProductId(ObjectId productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [productId=" + productId + ", productName=" + productName + ", price=" + price + ", quantity="
				+ quantity + "]";
	}

	
}
