package com.app.contactsystem.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;

@Entity
@Table(name="sub_company", catalog="contactsystem")
public class SubCompany extends BaseObject implements Serializable {

	private static final long serialVersionUID = 4069131790172287544L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
	
	@Column(name = "sub_company_name", length = 400, nullable = true)
	private String subCompanyName;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubCompanyName() {
		return subCompanyName;
	}

	public void setSubCompanyName(String subCompanyName) {
		this.subCompanyName = subCompanyName;
	}

	@Override
	public boolean equals(Object arg0) {
		if (this == arg0) {
			return true;
		}
		if (!(arg0 instanceof SubCompany)) {
			return false;
		}
		SubCompany castOther = (SubCompany) arg0;
		return new EqualsBuilder().append(id, castOther.id).append(subCompanyName, castOther.subCompanyName).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(subCompanyName).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("id", id)
				.append("subCompanyName", subCompanyName).toString();
	}

}
