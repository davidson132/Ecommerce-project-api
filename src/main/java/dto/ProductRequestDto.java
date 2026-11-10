package dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductRequestDto {

	@NotBlank
	@Size(max = 200)
	private String name;
	
	@Size(max = 2000)
	private String description;
	
	@NotNull
	@DecimalMin("0.01")
	@Digits(integer = 10, fraction = 2)
	private BigDecimal price;
	
	@NotNull
	@Min(0)
	private Integer stock;
	
	@NotBlank
	private String category;

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

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductDto [name=" + name + ", description=" + description + ", price=" + price + ", stock=" + stock
				+ ", category=" + category + "]";
	}
	
	
}
