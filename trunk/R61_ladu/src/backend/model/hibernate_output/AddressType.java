// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

/**
 * AddressType generated by hbm2java
 */
public class AddressType implements java.io.Serializable {

	private long addressType;
	private String typeName;

	public AddressType() {
	}

	public AddressType(long addressType) {
		this.addressType = addressType;
	}

	public AddressType(long addressType, String typeName) {
		this.addressType = addressType;
		this.typeName = typeName;
	}

	public long getAddressType() {
		return this.addressType;
	}

	public void setAddressType(long addressType) {
		this.addressType = addressType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
