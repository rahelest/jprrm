package backend.service_layer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import web.forms.ProductForm;
import web.forms.SearchForm;

import middleware.interfaces.FacadeInterface;

import backend.model.AuthInfo;
import backend.model.Customer;
import backend.model.CustomerSearchCriteria;
import backend.model.PriceList;
import backend.model.PriceListInfo;
import backend.model.Product;
import backend.model.ProductSearchCriteria;
import backend.model.Subject;
import backend.model.Transaction;


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
	public List<Product> getProductsByCatalog(int catalog_id) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public List<Product> searchProducts(SearchForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getAttributesOfType(int type_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createProduct(ProductForm form) {
		// TODO Auto-generated method stub
		return 0;
	}

}
