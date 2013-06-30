package com.app.contactsystem.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="contact", catalog="contactsystem")
public class Contact extends BaseObject implements Serializable {

	private static final long serialVersionUID = -3544964345819551845L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
	
	@ManyToOne()
	@JoinColumn(name = "account_id", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private Account account;
	
	@Column(name = "name", length = 100, nullable = true)
	private String name;
	
	@Column(name = "phone_number", length = 50, nullable = true)
	private String phoneNumber;
	
	@Column(name = "email", length = 100, nullable = true)
	private String email;
	
	@Column(name = "telephone_number", length = 50, nullable = true)
	private String telephoneNumber;
	
	@Column(name = "address", length = 1000, nullable = true)
	private String address;
	
	@Column(name = "site", length = 400, nullable = true)
	private String site;
	
	@Column(name = "company_name", length = 1000, nullable = true)
	private String companyName;
	
	@Column(name = "position", length = 255, nullable = true)
	private String position;
	
	@ManyToOne()
	@JoinColumn(name = "company_type_id", nullable = true)
	@NotFound(action = NotFoundAction.IGNORE)
	private CompanyType companyType;
	
	@Column(name = "company_contact", length = 100, nullable = true)
	private String companyContact;
	
	@Column(name = "fax", length = 100, nullable = true)
	private String fax;
	
	@Column(name = "zip_code", length = 100, nullable = true)
	private String zipCode;
	
	@Column(name = "frequency", length = 50, nullable = true)
	private String frequency;
	
	@Column(name = "business", nullable = true)
	private String business;
	
	@ManyToOne()
	@JoinColumn(name = "sub_company_id", nullable = true)
	@NotFound(action = NotFoundAction.IGNORE)
	private SubCompany subCompany;
	
	@Column(name = "remark", nullable = true)
	private String remark;
	
	@Column(name = "create_time", nullable = true)
	private Timestamp createTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public CompanyType getCompanyType() {
		return companyType;
	}

	public void setCompanyType(CompanyType companyType) {
		this.companyType = companyType;
	}

	public String getCompanyContact() {
		return companyContact;
	}

	public void setCompanyContact(String companyContact) {
		this.companyContact = companyContact;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public SubCompany getSubCompany() {
		return subCompany;
	}

	public void setSubCompany(SubCompany subCompany) {
		this.subCompany = subCompany;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Override
	public boolean equals(Object arg0) {
		if (this == arg0) {
			return true;
		}
		if (!(arg0 instanceof Contact)) {
			return false;
		}
		Contact castOther = (Contact) arg0;
		return new EqualsBuilder().append(id, castOther.id).append(account, castOther.account)
				.append(name, castOther.name).append(phoneNumber, castOther.phoneNumber)
				.append(email, castOther.email).append(telephoneNumber, castOther.telephoneNumber)
				.append(address, castOther.address).append(site, castOther.site)
				.append(companyName, castOther.companyName).append(position, castOther.position)
				.append(companyType, castOther.companyType).append(companyContact, castOther.companyContact)
				.append(fax, castOther.fax).append(zipCode, castOther.zipCode)
				.append(frequency, castOther.frequency).append(business, castOther.business)
				.append(subCompany, castOther.subCompany).append(remark, castOther.remark)
				.append(createTime, castOther.createTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(account).append(name)
				.append(phoneNumber).append(email).append(telephoneNumber)
				.append(address).append(site).append(companyName).append(position)
				.append(companyType).append(companyContact).append(fax)
				.append(zipCode).append(frequency).append(business).append(subCompany)
				.append(remark).append(createTime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("id", id)
				.append("account", account).append("name", name).append("phoneNumber", phoneNumber)
				.append("email", email).append("telephoneNumber", telephoneNumber)
				.append("address", address).append("site", site).append("companyName", companyName)
				.append("position", position).append("companyType", companyType)
				.append("companyContact", companyContact).append("fax", fax)
				.append("zipCode", zipCode).append("frequency", frequency)
				.append("business", business).append("subCompany", subCompany)
				.append("remark", remark).append("createTime", createTime).toString();
	}

}
