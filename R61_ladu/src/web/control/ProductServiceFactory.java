package web.control;

import java.util.ResourceBundle;

import backend.model.service.ProductCatalogTree;
import backend.model.service.ProductService;

public class ProductServiceFactory {
	
	ProductCatalogTreeEmulator treeEmu;
	ProductServiceEmulator servEmu;
	
	ProductCatalogTree tree;
	ProductService serv;

	/*
	 * ‹hendus kas emu vıi backiga. M‰‰rab faili j‰rgi.
	 */
           
    public ProductServiceFactory() {
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
   
    
    public Object getTree() {
    	if (tree != null) {
    		return tree;
    	} else {
    		return treeEmu;
    	}
    }
    
    public Object getService() {
    	if (serv != null) {
    		return serv;
    	} else {
    		return servEmu;
    	}
    }
        
}
