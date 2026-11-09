package model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Order {

	@BsonId
	private ObjectId id;
	
	@NotNull
	@BsonProperty("user_id")
	private ObjectId userId;
	
	@NotEmpty(message = "An order needs items")
	@Valid
	ArrayList<OrderItem> orderItems = new ArrayList<>();
	
	@NotNull
	@DecimalMin("0.00")
	private BigDecimal total;
	
	@NotNull
	private OrderStatus status = OrderStatus.PENDING;
	
	@NotNull
	@BsonProperty("created_at")
	private Instant created = Instant.now();

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	public ArrayList<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Instant getCreated() {
		return created;
	}

	public void setCreated(Instant created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", orderItems=" + orderItems + ", total=" + total
				+ ", status=" + status + ", created=" + created + "]";
	}
	
	
}
