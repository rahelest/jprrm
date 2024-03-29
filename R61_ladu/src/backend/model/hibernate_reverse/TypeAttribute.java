package backend.model.hibernate_reverse;
// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

/**
 * TypeAttribute generated by hbm2java
 */
public class TypeAttribute implements java.io.Serializable {

	private long typeAttribute;
	private Long itemTypeFk;
	private Long itemAttributeTypeFk;
	private Long orderby;
	private String required;
	private String createdByDefault;

	public TypeAttribute() {
	}

	public TypeAttribute(long typeAttribute) {
		this.typeAttribute = typeAttribute;
	}

	public TypeAttribute(long typeAttribute, Long itemTypeFk,
			Long itemAttributeTypeFk, Long orderby, String required,
			String createdByDefault) {
		this.typeAttribute = typeAttribute;
		this.itemTypeFk = itemTypeFk;
		this.itemAttributeTypeFk = itemAttributeTypeFk;
		this.orderby = orderby;
		this.required = required;
		this.createdByDefault = createdByDefault;
	}

	public long getTypeAttribute() {
		return this.typeAttribute;
	}

	public void setTypeAttribute(long typeAttribute) {
		this.typeAttribute = typeAttribute;
	}

	public Long getItemTypeFk() {
		return this.itemTypeFk;
	}

	public void setItemTypeFk(Long itemTypeFk) {
		this.itemTypeFk = itemTypeFk;
	}

	public Long getItemAttributeTypeFk() {
		return this.itemAttributeTypeFk;
	}

	public void setItemAttributeTypeFk(Long itemAttributeTypeFk) {
		this.itemAttributeTypeFk = itemAttributeTypeFk;
	}

	public Long getOrderby() {
		return this.orderby;
	}

	public void setOrderby(Long orderby) {
		this.orderby = orderby;
	}

	public String getRequired() {
		return this.required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getCreatedByDefault() {
		return this.createdByDefault;
	}

	public void setCreatedByDefault(String createdByDefault) {
		this.createdByDefault = createdByDefault;
	}

}
