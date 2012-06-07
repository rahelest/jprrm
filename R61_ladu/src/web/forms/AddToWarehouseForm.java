package web.forms;

public class AddToWarehouseForm {

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}

	public String getWarehouse_price() {
		return warehouse_price;
	}

	public void setWarehouse_price(String warehouse_price) {
		this.warehouse_price = warehouse_price;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	String product;
	String warehouse;
	String warehouse_price;
	String amount;
	String commentary;

}
