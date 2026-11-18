package resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;

import dto.ProductRequestDto;
import dto.ProductResponseDto;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import model.Product;
import service.ProductService;
import util.ProductUtil;

@Path("/product")
public class ProductResource {
	
	@Inject
	private Validator validate;

	@Inject
	private ProductService service;
	
	@GET
	@Path("/ping")
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
		return "Health check success!";
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(ProductRequestDto productRequest) {
		if(productRequest == null) {
			return Response.status(Status.BAD_REQUEST).entity(Map.of("error", "Product request is required")).build();
		}
		Set<ConstraintViolation<ProductRequestDto>> violations = validate.validate(productRequest);
		if(!violations.isEmpty()) {
			List<String> errors = violations.stream().map(violation -> violation.getPropertyPath() + ":" + violation.getMessage()).toList();
			return Response.status(Status.BAD_REQUEST).entity(Map.of("errors", errors)).build();
		}
		Product product = ProductUtil.dtoToProduct(productRequest);
		service.createProduct(product);
		return Response.status(Status.CREATED).entity(Map.of("message", "Product has been created")).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProducts() {
		List<ProductResponseDto> products = service.getProducts().stream().map(ProductResponseDto::new).toList();
		return Response.ok(products).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById(@PathParam("id") String id) {
		if(!ObjectId.isValid(id)) {
			return Response.status(Status.BAD_REQUEST).entity(Map.of("error", "Request must be a valid Object Id")).build();
		}
		
		return service.getProductById(new ObjectId(id)).map(product -> Response.ok(new ProductResponseDto(product)).build())
				.orElseGet(() -> Response.status(Status.NOT_FOUND).entity(Map.of("error", "Product not found")).build());
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateProduct(@PathParam("id") String id, ProductRequestDto request) {
		if(!ObjectId.isValid(id)) {
			return Response.status(Status.BAD_REQUEST).entity(Map.of("error", "Request must be a valid Object Id")).build();
		}
		
		if(request == null) {
			return Response.status(Status.BAD_REQUEST).entity(Map.of("error", "Product request is required")).build();
		}
		
		Set<ConstraintViolation<ProductRequestDto>> violations = validate.validate(request);
		if(!violations.isEmpty()) {
			List<String> errors = violations.stream().map(violation -> violation.getPropertyPath() + ":" + violation.getMessage()).toList();
			return Response.status(Status.BAD_REQUEST).entity(Map.of("errors", errors)).build();
		}
		
		return service.updateProductById(new ObjectId(id), request).map(product -> Response.ok(product).build())
				.orElseGet(() -> Response.status(Status.NOT_FOUND).entity(Map.of("error", "Product not found")).build()); 
		
	}
}
