package com.etsisi.sps.web;

import com.etsisi.sps.model.User;
import com.etsisi.sps.model.UserRoles;
import com.etsisi.sps.service.UserRolesService;
import com.etsisi.sps.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class UsersController {
	static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	UserRolesService userRolesService;
	
	@Autowired
    MessageSource messageSource;

	@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
	
	@Autowired
    AuthenticationTrustResolver authenticationTrustResolver;



    /**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/users" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());
		return "users/list";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/users/add" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "users/registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/users/add" }, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
                           ModelMap model) {

		if (result.hasErrors()) {
			return "users/registration";
		}
        if (!userService.isUserEmailValid(user.getEmail())) {
            FieldError emailError=new FieldError("user","email",
                    messageSource.getMessage("Pattern.userForm.email",
                            new String[]{user.getEmail()},Locale.getDefault()));
            result.addError(emailError);
            return "users/registration";
        }

        /*
		 * Preferred way to achieve uniqueness of field [username] should be implementing custom @Unique annotation
		 * and applying it on field [username] of Model class [User].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 */

		if(!userService.isUserUsernameUnique(user.getId(), user.getUsername())){
			FieldError userNameError =new FieldError("user","username",
					messageSource.getMessage("non.unique.username",
					new String[]{user.getUsername()}, Locale.getDefault()));
		    result.addError(userNameError);
			return "users/registration";
		}

        userService.saveUser(user);
		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName()
				+ " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "users/registrationsuccess";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/users/edit-user-{username}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String username, ModelMap model) {
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "users/registration";
	}
	
	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/users/edit-user-{username}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result,
                             ModelMap model, @PathVariable String username) {

		if (result.hasErrors()) {
			return "users/registration";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/

        logger.info(String.format("calling userService.updateUser(%s)", username));
		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "users/registrationsuccess";
	}

	
	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/users/delete-user-{username}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String username) {
		logger.debug("deleteUser {}", username);
		userService.deleteUserByUsername(username);
		return "redirect:/users";
	}
	

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserRoles> initializeRoles() {
		return userRolesService.findAll();
	}
	
	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
	    } else {
	    	return "redirect:/users";
	    }
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String username = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			username = ((UserDetails)principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}
	
	/**
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
	    final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    return authenticationTrustResolver.isAnonymous(authentication);
	}
}