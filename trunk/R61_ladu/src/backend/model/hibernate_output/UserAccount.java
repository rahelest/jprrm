package backend.model.hibernate_output;
// default package
// Generated Jun 8, 2012 6:23:43 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * UserAccount generated by hbm2java
 */
public class UserAccount implements java.io.Serializable {

	private long userAccount;
	private Long subjectTypeFk;
	private Long subjectFk;
	private String username;
	private String passw;
	private Long status;
	private Date validFrom;
	private Date validTo;
	private Long createdBy;
	private Date created;
	private String passwordNeverExpires;

	public UserAccount() {
	}

	public UserAccount(long userAccount) {
		this.userAccount = userAccount;
	}

	public UserAccount(long userAccount, Long subjectTypeFk, Long subjectFk,
			String username, String passw, Long status, Date validFrom,
			Date validTo, Long createdBy, Date created,
			String passwordNeverExpires) {
		this.userAccount = userAccount;
		this.subjectTypeFk = subjectTypeFk;
		this.subjectFk = subjectFk;
		this.username = username;
		this.passw = passw;
		this.status = status;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.createdBy = createdBy;
		this.created = created;
		this.passwordNeverExpires = passwordNeverExpires;
	}

	public long getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(long userAccount) {
		this.userAccount = userAccount;
	}

	public Long getSubjectTypeFk() {
		return this.subjectTypeFk;
	}

	public void setSubjectTypeFk(Long subjectTypeFk) {
		this.subjectTypeFk = subjectTypeFk;
	}

	public Long getSubjectFk() {
		return this.subjectFk;
	}

	public void setSubjectFk(Long subjectFk) {
		this.subjectFk = subjectFk;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassw() {
		return this.passw;
	}

	public void setPassw(String passw) {
		this.passw = passw;
	}

	public Long getStatus() {
		return this.status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Date getValidFrom() {
		return this.validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getPasswordNeverExpires() {
		return this.passwordNeverExpires;
	}

	public void setPasswordNeverExpires(String passwordNeverExpires) {
		this.passwordNeverExpires = passwordNeverExpires;
	}

}
