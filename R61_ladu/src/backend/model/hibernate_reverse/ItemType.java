package backend.model.hibernate_reverse;
// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

/**
 * ItemType generated by hbm2java
 */
public class ItemType implements java.io.Serializable {

	private long itemType;
	private String typeName;
	private Long level;
	private Long superTypeFk;

	public ItemType() {
	}

	public ItemType(long itemType) {
		this.itemType = itemType;
	}

	public ItemType(long itemType, String typeName, Long level, Long superTypeFk) {
		this.itemType = itemType;
		this.typeName = typeName;
		this.level = level;
		this.superTypeFk = superTypeFk;
	}

	public long getItemType() {
		return this.itemType;
	}

	public void setItemType(long itemType) {
		this.itemType = itemType;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getLevel() {
		return this.level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public Long getSuperTypeFk() {
		return this.superTypeFk;
	}

	public void setSuperTypeFk(Long superTypeFk) {
		this.superTypeFk = superTypeFk;
	}

}
