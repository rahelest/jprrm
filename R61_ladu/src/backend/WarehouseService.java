package backend;

import java.util.HashMap;
import java.util.List;

import interfaces.AuthInfo;
import interfaces.Customer;
import interfaces.CustomerSearchCriteria;
import interfaces.FacadeInterface;
import interfaces.PriceList;
import interfaces.PriceListInfo;
import interfaces.Product;
import interfaces.ProductSearchCriteria;
import interfaces.Subject;
import interfaces.Transaction;

public class WarehouseService implements FacadeInterface {

	@Override
	public int getActionResult() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap getErrors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductById(int product_id) {
		// TODO Auto-generated method stub
		return null;
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
