package com.controllers;

import com.dao.entities.Contact;
import com.dao.entities.Role;
import com.dao.implementations.xml.helpers.XmlManager;
import com.dao.interfaces.ContactIDAO;
import com.dao.interfaces.RoleIDAO;
import com.dao.interfaces.UserIDAO;
import com.services.ContactValidator;
import com.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@SessionAttributes("contact")
public class FrontController {
    /*    @RequestMapping("/login")
        public String loginPage() {
            return "login";
        }*/
    @Autowired
    private UserIDAO userDAO;

    @Autowired
    private ContactIDAO contactDAO;

    @Autowired
    private RoleIDAO roleIDAO;

    @Autowired
    XmlManager xmlManager;

    /*@Autowired*/
    private final Validator contactValidator = new ContactValidator();
    private final Validator userValidator = new UserValidator();

    @RequestMapping("/test")
    public ModelAndView test(){
        // ModelAndView modelAndView = new ModelAndView("test",);
        //modelAndView.
/*        Role adminRole = new Role("ADMIN", "admin description");
        adminRole.setRoleId(Role.ADMIN_ROLE_ID);
        roleIDAO.saveRole(adminRole);*/

        Role userRole = new Role("USER", "user description");
        userRole.setRoleId(Role.USER_ROLE_ID);
        roleIDAO.saveRole(userRole);

        return new ModelAndView("test", new HashMap<String,Object>(){{
            put("test1", xmlManager.getPhoneBook().getUsers());
            put("test2", userRole);
        }});
    }

    /**
     * Check whether user is valid;
     * Shows contacts page
     *
     * @param model
     * @return view name, contacts page
     */
    @RequestMapping("/")
    public ModelAndView showContactsPage(Model model) {

/*        Role adminRole = new Role("ADMIN", "admin description");
        adminRole.setRoleId(Role.ADMIN_ROLE_ID);
        roleIDAO.saveRole(adminRole);

        Role userRole = new Role("USER", "user description");
        userRole.setRoleId(Role.USER_ROLE_ID);
        roleIDAO.saveRole(userRole);*/

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        model.addAttribute("roles", user.getAuthorities());

        return new ModelAndView("contacts", "contacts", contactDAO.findContactsByUser(userDAO.findUserByLogin(user.getUsername())));
    }

    /**
     * Shows login page
     *
     * @return view name
     */
    @RequestMapping("/login")
    public String showLoginPage() {
        /*if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null) return "redirect:/";*/
        /*if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) return "redirect:/";*/
        return "login";
    }

    /**
     * Performs login
     * Shows login page
     *
     * @return view name
     */
    @RequestMapping("/doLogin")
    public String doLogin() {
        /* Login processing done by Spring Security */
        return "contacts";
    }

    /**
     * Shows registration page
     *
     * @return view name
     */
    @RequestMapping("/register")
    public String showRegistrationPage() {
        // TODO: 04.11.2016 check if already loggedin?
        return "register";
    }

    /**
     * Performs registration
     * Shows login page
     *
     * @return view name
     */
    @RequestMapping("/doRegister")
    // @Transactional
    public ModelAndView doRegister(@RequestParam(value = "login") String login,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "firstName", required = false) String firstName,
                                   @RequestParam(value = "lastName", required = false) String lastName,
                                   @RequestParam(value = "patronymic", required = false) String patronymic,
                                   HttpServletRequest request, HttpServletResponse response) {
        try {
            com.dao.entities.User user = new com.dao.entities.User(login, password, firstName, lastName, patronymic);

            MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), Contact.class.getName());
            userValidator.validate(user, errors);

            if (userDAO.findUserByLogin(login) != null) {
                errors.rejectValue("login", "login", "User with such login already exists");
            }

            if (errors.hasErrors()) {
                ModelAndView modelAndView = new ModelAndView("register", "user", user);
                modelAndView.addObject("errors", errors);
                return modelAndView;
            }

            userDAO.saveUser(user);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new ModelAndView("redirect:/login");
    }

    // Contacts

    /**
     * Shows add contact page
     *
     * @return view name
     */
    @RequestMapping("/addContact")
    public ModelAndView showAddContactPage() {
        return new ModelAndView("contact", "contact", new Contact());
    }

    /**
     * Performs addition of new contact
     *
     * @return view name, contacts page
     */
    @RequestMapping("/doAddContact")
    // @Transactional
    public ModelAndView doAddContact(@RequestParam(value = "firstName") String firstName,
                                     @RequestParam(value = "lastName") String lastName,
                                     @RequestParam(value = "patronymic") String patronymic,
                                     @RequestParam(value = "cellPhone") String cellPhone,
                                     @RequestParam(value = "homePhone", required = false) String homePhone,
                                     @RequestParam(value = "address", required = false) String address,
                                     @RequestParam(value = "email", required = false) String email,
                               /*@ModelAttribute(value = "", re) Contact contact,*/
                                     HttpSession session,
                                     HttpServletRequest request, HttpServletResponse response) {
        try {
            /*Object obj = session.getAttribute("contact");
            Contact contact = null;
            if (obj != null) {
                contact = (Contact) obj;
            }*/

            Contact contact = (Contact) session.getAttribute("contact");

            if (contact == null || contact.getContactId() == null) {
                contact = new Contact();
                User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                contact.setUser(userDAO.findUserByLogin(user.getUsername()));
            }

            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setPatronymic(patronymic);
            contact.setCellPhone(cellPhone);
            contact.setHomePhone(homePhone);
            contact.setAddress(address);
            contact.setEmail(email);

            MapBindingResult errors = new MapBindingResult(new HashMap<String, String>(), Contact.class.getName());

            contactValidator.validate(contact, errors);
            if (errors.hasErrors()) {
                ModelAndView modelAndView = new ModelAndView("contact", "contact", contact);
                modelAndView.addObject("errors", errors);
                return modelAndView;
            }

            contactDAO.saveContact(contact);
            /*contact = null;
            session.removeAttribute("contact");*/

        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping("/editContact")
    // @Transactional
    public ModelAndView showEditContactPage(@RequestParam(value = "contactId") Long contactId,
                                            /*HttpSession session,*/
                                            HttpServletRequest request, HttpServletResponse response) {
        Contact contact = null;
        try {
            contact = contactDAO.findOne(contactId);
            /*session.setAttribute("contact", contact);*/
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return new ModelAndView("contact", "contact", contact);
    }

    @RequestMapping("/deleteContact")
    // @Transactional
    public String deleteContact(@RequestParam(value = "contactId") Long contactId,
                                HttpServletRequest request, HttpServletResponse response) {
        try {
            contactDAO.delete(contactId);
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return "redirect:/";
    }

    // SEARCH BEGIN

    /**
     * Searches for a contact with a given pattern in First Name
     *
     * @return model with contacts satisfying pattern and view name, contacts page
     */
    @RequestMapping("/searchByFirstName")
    public ModelAndView searchByFirstName(@RequestParam(value = "firstName") String firstName) {
        if (firstName == null || firstName.equals(""))
            return new ModelAndView("redirect:/");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.dao.entities.User userEntity = userDAO.findUserByLogin(user.getUsername());

        return new ModelAndView("contacts", "contacts",
                contactDAO.findByUserUserIdAndFirstNameContainingIgnoreCaseOrderByFirstNameAsc(
                        userEntity.getUserId(), firstName));
    }

    /**
     * Searches for a contact with a given pattern in Last Name
     *
     * @return model with contacts satisfying pattern and view name, contacts page
     */
    @RequestMapping("/searchByLastName")
    public ModelAndView searchByLastName(@RequestParam(value = "lastName") String lastName) {
        if (lastName == null || lastName.equals(""))
            return new ModelAndView("redirect:/");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.dao.entities.User userEntity = userDAO.findUserByLogin(user.getUsername());

        return new ModelAndView("contacts", "contacts",
                contactDAO.findByUserUserIdAndLastNameContainingIgnoreCaseOrderByFirstNameAsc(
                        userEntity.getUserId(), lastName));
    }

    /**
     * Searches for a contact with a given pattern in Patronymic
     *
     * @return model with contacts satisfying pattern and view name, contacts page
     */
    @RequestMapping("/searchByPatronymic")
    public ModelAndView searchByPatronymic(@RequestParam(value = "patronymic") String patronymic) {
        if (patronymic == null || patronymic.equals(""))
            return new ModelAndView("redirect:/");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.dao.entities.User userEntity = userDAO.findUserByLogin(user.getUsername());

        return new ModelAndView("contacts", "contacts",
                contactDAO.findByUserUserIdAndPatronymicContainingIgnoreCaseOrderByFirstNameAsc(
                        userEntity.getUserId(), patronymic));
    }

    /**
     * Searches for a contact with a given pattern in Cell Phone
     *
     * @return model with contacts satisfying pattern and view name, contacts page
     */
    @RequestMapping("/searchByCellPhone")
    public ModelAndView searchByCellPhone(@RequestParam(value = "cellPhone") String cellPhone) {
        if (cellPhone == null || cellPhone.equals(""))
            return new ModelAndView("redirect:/");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.dao.entities.User userEntity = userDAO.findUserByLogin(user.getUsername());

        return new ModelAndView("contacts", "contacts",
                contactDAO.findByUserUserIdAndCellPhoneContainingIgnoreCaseOrderByFirstNameAsc(
                        userEntity.getUserId(), cellPhone));
    }

    /**
     * Searches for a contact with a given pattern in Home Phone
     *
     * @return model with contacts satisfying pattern and view name, contacts page
     */
    @RequestMapping("/searchByHomePhone")
    public ModelAndView searchByHomePhone(@RequestParam(value = "homePhone") String homePhone) {
        if (homePhone == null || homePhone.equals(""))
            return new ModelAndView("redirect:/");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.dao.entities.User userEntity = userDAO.findUserByLogin(user.getUsername());

        return new ModelAndView("contacts", "contacts",
                contactDAO.findByUserUserIdAndHomePhoneContainingIgnoreCaseOrderByFirstNameAsc(
                        userEntity.getUserId(), homePhone));
    }

    /**
     * Searches for a contact with a given pattern in Address
     *
     * @return model with contacts satisfying pattern and view name, contacts page
     */
    @RequestMapping("/searchByAddress")
    public ModelAndView searchByAddress(@RequestParam(value = "address") String address) {
        if (address == null || address.equals(""))
            return new ModelAndView("redirect:/");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.dao.entities.User userEntity = userDAO.findUserByLogin(user.getUsername());

        return new ModelAndView("contacts", "contacts",
                contactDAO.findByUserUserIdAndAddressContainingIgnoreCaseOrderByFirstNameAsc(
                        userEntity.getUserId(), address));
    }

    /**
     * Searches for a contact with a given pattern in Email
     *
     * @return model with contacts satisfying pattern and view name, contacts page
     */
    @RequestMapping("/searchByEmail")
    public ModelAndView searchByEmail(@RequestParam(value = "email") String email) {
        if (email == null || email.equals(""))
            return new ModelAndView("redirect:/");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        com.dao.entities.User userEntity = userDAO.findUserByLogin(user.getUsername());

        return new ModelAndView("contacts", "contacts",
                contactDAO.findByUserUserIdAndEmailContainingIgnoreCaseOrderByFirstNameAsc(
                        userEntity.getUserId(), email));
    }
    // SEARCH END

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

/*    @RequestMapping("/error")
    public String error() {
        return "error";
    }*/

    @RequestMapping("/unauthorized")
    public String unauthorized(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("login", user.getUsername());
        return "unauthorized";
    }
}
