package web.control;

import java.util.ResourceBundle;

import middleware.interfaces.CatalogTree;
import middleware.interfaces.FacadeInterface;

import backend.model.service.ProductCatalogTree;
import backend.model.service.ProductService;

public class ProductServiceFactory {
	
	static ProductCatalogTreeEmulator treeEmu;
	static ProductServiceEmulator servEmu;
	
	static ProductCatalogTree tree;
	static ProductService serv;

	/*
	 * ‹hendus kas emu vıi backiga. M‰‰rab faili j‰rgi.
	 */
           
    public static void initialize() {
    	ResourceBundle bundle = ResourceBundle.getBundle("ApplicationSetup");
	     String type = bundle.getString("service_type");
	    if (type == "real_backend_service") {
	    	treeEmu = new ProductCatalogTreeEmulator();
	    	servEmu = new ProductServiceEmulator();
	    } else {
	    	tree = new ProductCatalogTree();
	    	serv = new ProductService();
	    }
	}
   
    
    public static CatalogTree getTree() {
    	if (tree != null) {
    		return tree;
    	} else {
    		return treeEmu;
    	}
    }
    
    public static FacadeInterface getService() {
    	if (serv != null) {
    		return serv;
    	} else {
    		return servEmu;
    	}
    }
        
}
