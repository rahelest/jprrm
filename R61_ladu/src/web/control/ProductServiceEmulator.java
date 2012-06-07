package web.control;

import java.util.HashMap;
import java.util.List;

import backend.model.AuthInfo;
import backend.model.Customer;
import backend.model.CustomerSearchCriteria;
import backend.model.PriceList;
import backend.model.PriceListInfo;
import backend.model.Product;
import backend.model.ProductSearchCriteria;
import backend.model.Subject;
import backend.model.Transaction;
import middleware.interfaces.FacadeInterface;

public class ProductServiceEmulator implements FacadeInterface {

	@Override
	public int getActionResult() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<String, String> getErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(int product_id) {
		Product product = new Product();
		
		product.setName("Hello Kitty pingike");
		product.setDescription("Uus toode mehistele plikatirtsudele!");
		product.setSale_price(34);
		product.setType("Freespingid");
		
		HashMap<String, String> map = new HashMap<>();
		map.put("Võimsus", "55kW");
		
		product.setAttributes(map);
		
		return product;
	}

	@Override
	public List<Product> searchProducts(
			ProductSearchCriteria productSearchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByCatalog(int catalog_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createProduct(Product newProduct) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateProduct(Product updatedProduct) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int incrementStock(Transaction change) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int moveStock(Transaction movement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int makePriceList(PriceListInfo details) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addProductToPriceList(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeProductFromPriceList(Product product) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addClientToPriceList(Subject subject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeClientFromPriceList(Subject subject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Customer> searchCustomerByName(
			CustomerSearchCriteria customerSearchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int setNewPriceListDiscount(PriceList priceList, int discount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logIn(AuthInfo details) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int logOut(AuthInfo details) {
		// TODO Auto-generated method stub
		return 0;
	}

}
