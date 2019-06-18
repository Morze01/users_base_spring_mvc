package userdb.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import userdb.model.Role;
import userdb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import userdb.service.RoleService;
import userdb.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/home")
    public ModelAndView homePage() {
        return new ModelAndView("home", "hello", "Hello!");
    }

    @GetMapping("/list")
    public ModelAndView listUsers() {
        List<User> theUsers = userService.getUsers();
        return new ModelAndView("user-list", "users", theUsers);
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User theUser,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("role") String role,
                           @RequestParam("id") String id) {

        //Set<Role> userRoleSet = Collections.singleton(roleService.findRoleByName(role));
        Set<Role> userRoleSet = roleService.getRolSetByRoleName(role);
        User user = userService.getUser(Integer.parseInt(id));
        if (user == null) {
            user = new User(username,passwordEncoder.encode(password),userRoleSet);
            userService.saveUser(user, true);
        } else {
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password));
            user.setRoleSet(userRoleSet);
            user.setEnabled(true);
            userService.saveUser(user, false);
        }

        return "redirect:/list";
    }

    @GetMapping("/showForm")
    public String showFormForAdd(Model theModel) {
        User theUser = new User();
        theModel.addAttribute("user", theUser);
        return "/user-form";
    }

    @GetMapping("user/delete")
    public String deleteUser(@RequestParam("userId") int theId) {
        userService.deleteUser(theId);
        return "redirect:/list";
    }

    @GetMapping("updateForm")
    public String showFormForUpdate(@RequestParam("userId") int theId,
                                    Model theModel) {
        User theUser = userService.getUser(theId);
        theModel.addAttribute("user", theUser);
        return "/user-form";
    }

    @GetMapping ("/login")
    public ModelAndView authorize() {

        return new ModelAndView("login", "message", "");
    }

    @GetMapping("/register")
    public String registerForm() {
        return "registration";
    }

    @PostMapping("/register/process")
    public String processRegistration(@RequestParam("username") String username,
                                      @RequestParam("password") String password) {

        userService.registerNewUser(username,password);

        return "redirect:/login";
    }

    @GetMapping("/error")
    public String errorForm(HttpServletRequest request, HttpServletResponse response) {

        return "error";
    }

}
