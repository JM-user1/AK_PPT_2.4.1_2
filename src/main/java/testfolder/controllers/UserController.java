package testfolder.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import testfolder.model.User;
import testfolder.service.UserService;


@Controller
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }


  @GetMapping("/")
  public String start(ModelMap modelMap){
    modelMap.addAttribute("users", userService.allUser());
    modelMap.addAttribute("user", new User());
    return "users";
  }

  @PostMapping()
  public String addUser(@ModelAttribute("user") User user){
    userService.add(user);
    return "redirect:/";
  }

  @GetMapping("/edit/{id}")
  public String editUser(ModelMap modelMap, @PathVariable("id") int id){
    modelMap.addAttribute("user",userService.getById(id));
    return "/editUser";
  }

  @PatchMapping("/edit/{id}")
  public String editUser(@ModelAttribute("user") User user, @PathVariable("id")int id){
    userService.edit(id, user);
    return "redirect:/";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") int id){
    userService.delete(id);
    return "redirect:/";
  }


}
