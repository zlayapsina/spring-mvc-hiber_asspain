package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.validation.Valid;

@Controller
public class UsersController {
    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
        System.out.println("test");
    }

    @GetMapping(value = "/")
    public String printUser(ModelMap model) {
        model.addAttribute("users", userService.getUsers());
        return "users";
    }

    @GetMapping(value = "/new")
    public String newUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/new")
    public String newUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }
        userService.saveUser(user.getName(), user.getSurname(), user.getAge());
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String editPage(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("user", userService.showId(id));
        return "/edit";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult,
                           @RequestParam("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/edit";
        }
        userService.editUser(id, user);
        return "redirect:/";
    }

    @PostMapping ("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.removeUser(id);
        return "redirect:/";
    }

}