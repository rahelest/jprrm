// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Enterprise generated by hbm2java
 */
public class Enterprise implements java.io.Serializable {

	private long enterprise;
	private String name;
	private String fullName;
	private Long createdBy;
	private Long updatedBy;
	private Date created;
	private Date updated;

	public Enterprise() {
	}

	public Enterprise(long enterprise) {
		this.enterprise = enterprise;
	}

	public Enterprise(long enterprise, String name, String fullName,
			Long createdBy, Long updatedBy, Date created, Date updated) {
		this.enterprise = enterprise;
		this.name = name;
		this.fullName = fullName;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.created = created;
		this.updated = updated;
	}

	public long getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(long enterprise) {
		this.enterprise = enterprise;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
