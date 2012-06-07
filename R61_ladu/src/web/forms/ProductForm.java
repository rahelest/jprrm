package web.forms;

import java.util.Map;

import backend.model.Product;

public class ProductForm {

	String name;
	String description;
	String sale_price;
	Map<String, String> attributes;
	String type;

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

	public String getSale_price() {
		return sale_price;
	}

	public void setSale_price(String sale_price) {
		this.sale_price = sale_price;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void getDataFromModel(Product product) {
		setName(product.getName());
		setDescription(product.getDescription());
		setSale_price(product.getSale_price() + "");
		setType(product.getType());
		setAttributes(product.getAttributes());
	}
}
