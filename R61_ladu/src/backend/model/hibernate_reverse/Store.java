package backend.model.hibernate_reverse;
// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

/**
 * Store generated by hbm2java
 */
public class Store implements java.io.Serializable {

	private long store;
	private String name;

	public Store() {
	}

	public Store(long store) {
		this.store = store;
	}

	public Store(long store, String name) {
		this.store = store;
		this.name = name;
	}

	public long getStore() {
		return this.store;
	}

	public void setStore(long store) {
		this.store = store;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
