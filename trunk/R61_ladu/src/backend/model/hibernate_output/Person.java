// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Person generated by hbm2java
 */
public class Person implements java.io.Serializable {

	private long person;
	private String firstName;
	private String lastName;
	private String identityCode;
	private Date birthDate;
	private Long createdBy;
	private Long updatedBy;
	private Date created;
	private Date updated;

	public Person() {
	}

	public Person(long person) {
		this.person = person;
	}

	public Person(long person, String firstName, String lastName,
			String identityCode, Date birthDate, Long createdBy,
			Long updatedBy, Date created, Date updated) {
		this.person = person;
		this.firstName = firstName;
		this.lastName = lastName;
		this.identityCode = identityCode;
		this.birthDate = birthDate;
		this.createdBy = createdBy;
		this.updatedBy = updatedBy;
		this.created = created;
		this.updated = updated;
	}

	public long getPerson() {
		return this.person;
	}

	public void setPerson(long person) {
		this.person = person;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentityCode() {
		return this.identityCode;
	}

	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
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
