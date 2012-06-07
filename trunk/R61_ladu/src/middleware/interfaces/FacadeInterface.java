/**
 * 
 */
package middleware.interfaces;

import java.util.List;
import java.util.Map;

import web.forms.SearchForm;

import backend.model.AuthInfo;
import backend.model.Customer;
import backend.model.CustomerSearchCriteria;
import backend.model.PriceList;
import backend.model.PriceListInfo;
import backend.model.Product;
import backend.model.ProductSearchCriteria;
import backend.model.Subject;
import backend.model.Transaction;

/**
 * @author Jaanus Fassaad tõmbab käima backendi erinevaid klasse.
 */
public interface FacadeInterface {
	public int getActionResult(); 
	public Map getErrors(); 
	public Product getProductById(int product_id);
	public List<Product> searchProducts(SearchForm form);
	public Map<String, String> getAttributesOfType(int type_id);
	public List<Product> getProductsByCatalog(int catalog_id);
	public int createProduct(Product newProduct);   
	public int updateProduct(Product updatedProduct);
	public int incrementStock(Transaction change);
	public int moveStock(Transaction movement);
	public int makePriceList(PriceListInfo details);
	public int addProductToPriceList(Product product);
	public int removeProductFromPriceList(Product product);
	public int addClientToPriceList(Subject subject);
	public int removeClientFromPriceList(Subject subject);
	public List<Customer> searchCustomerByName(CustomerSearchCriteria customerSearchCriteria);
	public int setNewPriceListDiscount(PriceList priceList, int discount);
	public int logIn(AuthInfo details);
	public int logOut(AuthInfo details);
	
}
