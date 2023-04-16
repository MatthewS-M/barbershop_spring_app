package com.example.client;
import com.example.blog.Blog;
import com.example.blog.BlogService;
import com.example.config.UserInfo;
import com.example.config.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class AppController {

    private final ClientService clientService;
    private final BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/blog")
    public String showBlog(Model model, @Param("keyword") String keyword) {
        List<Blog> listPosts = blogService.listAll(keyword);
        model.addAttribute("listPosts", listPosts);
        model.addAttribute("keyword", keyword);
        return "blog";
    }
    @GetMapping("/new_post")
    public String showNewPostForm(Model model) {
        Blog blog = new Blog();
        model.addAttribute("blog", blog);
        return "new_post";
    }
    @PostMapping("/save_post") // POST submits data to be processed to the identified resource.
    // requests a representation of the specified resource
    public String savePost(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }
    @PostMapping("/auth/register")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        System.out.println("UserInfo object is: " + userInfo); // Debugging code
        userService.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }
    @GetMapping("/auth/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        // logout the current user
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "register";
    }
    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Client> listClients = clientService.listAll(keyword);
        model.addAttribute("listClients", listClients);
        model.addAttribute("keyword", keyword);
        return "index";
    }
    @GetMapping("/new") // создаем контроллер по добавлению студента
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showNewClientForm(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "new_client";
    }
    @PostMapping("/save") // POST submits data to be processed to the identified resource.
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.save(client);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView showEditClientForm(@PathVariable(name="id") Long id) { // Annotation which indicates that a method parameter should be bound (связано) to a URI template variable
        ModelAndView mav = new ModelAndView("edit_client");
        Client client = clientService.get(id);
        mav.addObject("Client", client);
        return mav;
    }
    @GetMapping("/edit_post/{post_id}")
    public ModelAndView showEditPostForm(@PathVariable(name="post_id") Long post_id) {
        ModelAndView mav = new ModelAndView("edit_post");
        Blog blog = blogService.get(post_id);
        mav.addObject("Blog", blog);
        return mav;
    }
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") Long id) {
        clientService.delete(id);
        return "redirect:/";
    }
    @GetMapping("/delete_post/{post_id}")
    public String deletePost(@PathVariable(name = "post_id") Long post_id) {
        blogService.delete(post_id);
        return "redirect:/blog";
    }
    @GetMapping("/login_page") // is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
    public String showLogin() {// add any additional objects you want to pass to the HTML page
        return "login_page";
    }
    @GetMapping("/about_us") // is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
    public String showAbout() {// add any additional objects you want to pass to the HTML page
        return "about_us";
    }
    @PostMapping("/login_page")
    public String SuccessLogin(@RequestParam String username, HttpSession session) {
        System.out.println("Username is: " + username); // Debugging code
        // Authenticate the user and set the "username" attribute in the model
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
//        HttpSession session = request.getSession(true);
        session.setAttribute("username", currentUser);
//        session.setAttribute("password", password);
        return "redirect:/";
    }
}


