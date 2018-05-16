package com.etsisi.sps.converter;

import com.etsisi.sps.model.UserRoles;
import com.etsisi.sps.service.UserRolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserRolesConverter implements Converter<Object, UserRoles> {

	static final Logger logger = LoggerFactory.getLogger(RoleToUserRolesConverter.class);
	
	@Autowired
	UserRolesService userRolesService;

	/**
	 * Gets UserProfile by Id
	 * @see org.springframework.core.convert.converter.Converter#convert(Object)
	 */
	public UserRoles convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		UserRoles role= userRolesService.findById(id);
		logger.info("Role : {}",role);
		return role;
	}
	
}