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

import java.util.HashSet;
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
//                           @RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("role") String role) {
        Role userRole = roleService.findRoleByName(role);
        theUser.setPassword(passwordEncoder.encode(password));
        theUser.addRoleToUser(userRole);
        userService.saveUser(theUser);
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
        Role userRole = roleService.findRoleByName("user");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(userRole);
        User newUser = new User(username,passwordEncoder.encode(password),roleSet);
        userService.saveUser(newUser);

        return "redirect:/login";
    }

}
