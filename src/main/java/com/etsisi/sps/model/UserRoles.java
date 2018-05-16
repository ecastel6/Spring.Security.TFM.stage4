package com.etsisi.sps.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="USER_ROLES")
public class UserRoles implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;	

	@Column(name="ROLE", length=15, unique=true, nullable=false)
	private String role = UserRoleType.USER.getUserProfileType();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role= role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserRoles that = (UserRoles) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(role, that.role);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, role);
	}

	@Override
	public String toString() {
		return "UserRoles[" + "id=" + id + ", role='" + role + ']';
	}
}
