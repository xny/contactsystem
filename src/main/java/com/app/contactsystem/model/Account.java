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
@Table(name="account", catalog="contactsystem")
public class Account extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1460622591894070659L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
	private Long id;
	
	@Column(name = "username", length = 255, nullable = false)
	private String userName;
	
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	@Column(name = "role", length = 2, nullable = false)
	private int role;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object arg0) {
		if (this == arg0) {
			return true;
		}
		if (!(arg0 instanceof Account)) {
			return false;
		}
		Account castOther = (Account) arg0;
		return new EqualsBuilder().append(id, castOther.id).append(userName, castOther.userName)
				.append(password, castOther.password).append(role, castOther.role).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(userName).append(password).append(role).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("id", id)
				.append("userName", userName).append("password", password).append("role", role).toString();
	}

}
