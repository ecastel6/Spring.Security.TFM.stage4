package com.etsisi.sps.dao;

import com.etsisi.sps.model.UserRoles;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("userProfileDao")
public class UserRoleDaoImpl extends AbstractDao<Integer, UserRoles>implements UserRoleDao {

	public UserRoles findById(int id) {
		return getByKey(id);
	}

	public UserRoles findByRole(String role) {
		Criteria filter = createEntityCriteria();
		filter.add(Restrictions.eq("role", role));
		return (UserRoles) filter.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<UserRoles> findAll(){
		Criteria filter = createEntityCriteria();
		filter.addOrder(Order.asc("role"));
		return (List<UserRoles>)filter.list();
	}
	
}
