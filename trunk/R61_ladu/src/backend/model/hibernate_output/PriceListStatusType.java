package backend.model.hibernate_output;
// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

/**
 * PriceListStatusType generated by hbm2java
 */
public class PriceListStatusType implements java.io.Serializable {

	private long priceListStatusType;
	private String typeName;

	public PriceListStatusType() {
	}

	public PriceListStatusType(long priceListStatusType) {
		this.priceListStatusType = priceListStatusType;
	}

	public PriceListStatusType(long priceListStatusType, String typeName) {
		this.priceListStatusType = priceListStatusType;
		this.typeName = typeName;
	}

	public long getPriceListStatusType() {
		return this.priceListStatusType;
	}

	public void setPriceListStatusType(long priceListStatusType) {
		this.priceListStatusType = priceListStatusType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}
