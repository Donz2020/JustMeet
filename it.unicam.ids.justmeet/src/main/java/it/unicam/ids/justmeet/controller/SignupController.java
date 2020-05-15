package it.unicam.ids.justmeet.controller;

import it.unicam.ids.justmeet.model.User;
import it.unicam.ids.justmeet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
public class SignupController {
    @Autowired
    private UserService userService;





    @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView model = new ModelAndView();
        User user = new User("", "", "");
        model.addObject("user", user);
        model.setViewName("user/signup");
        return model;
    }


    @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult bindingResult,Model model,RedirectAttributes redirectAttributes) throws InterruptedException {

      /*  User userExists = userService.findUserByEmail(user.getEmail());
        if (user.getFirstname() == null){
            bindingResult.rejectValue("nome", "error.user", "Completa tutti i campi");
        }
        if(userExists != null) {
            bindingResult.rejectValue("email", "error.user", "This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            return "user/signup";
        } else {
            userService.saveUser(user);

            model.addAttribute("user", new User());
            redirectAttributes.addFlashAttribute("signup","Account creato correttamente");
            return "redirect:/login";
        }*/

        return "redirect:/login";
    }





    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }



}

