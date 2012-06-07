package web.forms;

public class MoveBetweenWarehousesForm {
	
	String product;
	String from_warehouse;
	String to_warehouse;
	String amount;
	String commentary;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getFrom_warehouse() {
		return from_warehouse;
	}

	public void setFrom_warehouse(String from_warehouse) {
		this.from_warehouse = from_warehouse;
	}

	public String getTo_warehouse() {
		return to_warehouse;
	}

	public void setTo_warehouse(String to_warehouse) {
		this.to_warehouse = to_warehouse;
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

}
