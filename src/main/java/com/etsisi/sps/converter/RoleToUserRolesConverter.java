package com.etsisi.sps.converter;

import com.etsisi.sps.entities.UserRoles;
import com.etsisi.sps.services.UserRolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * class implements Converter interface maps id -> userRoles objects.
 * org.springframework.core.convert.converter.Converter#convert(Object)
 */
@Component
public class RoleToUserRolesConverter implements Converter<Object, UserRoles>
{

    static final Logger logger = LoggerFactory.getLogger(RoleToUserRolesConverter.class);

    @Autowired
    UserRolesService userRolesService;

    public UserRoles convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        UserRoles role = userRolesService.findById(id);
        logger.info("Role : {}", role);
        return role;
    }

}