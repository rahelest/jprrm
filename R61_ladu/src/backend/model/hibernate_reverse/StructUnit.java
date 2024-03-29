package backend.model.hibernate_reverse;
// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

/**
 * StructUnit generated by hbm2java
 */
public class StructUnit implements java.io.Serializable {

	private long structUnit;
	private Long enterpriseFk;
	private Long upperUnitFk;
	private Long level;
	private String name;

	public StructUnit() {
	}

	public StructUnit(long structUnit) {
		this.structUnit = structUnit;
	}

	public StructUnit(long structUnit, Long enterpriseFk, Long upperUnitFk,
			Long level, String name) {
		this.structUnit = structUnit;
		this.enterpriseFk = enterpriseFk;
		this.upperUnitFk = upperUnitFk;
		this.level = level;
		this.name = name;
	}

	public long getStructUnit() {
		return this.structUnit;
	}

	public void setStructUnit(long structUnit) {
		this.structUnit = structUnit;
	}

	public Long getEnterpriseFk() {
		return this.enterpriseFk;
	}

	public void setEnterpriseFk(Long enterpriseFk) {
		this.enterpriseFk = enterpriseFk;
	}

	public Long getUpperUnitFk() {
		return this.upperUnitFk;
	}

	public void setUpperUnitFk(Long upperUnitFk) {
		this.upperUnitFk = upperUnitFk;
	}

	public Long getLevel() {
		return this.level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
