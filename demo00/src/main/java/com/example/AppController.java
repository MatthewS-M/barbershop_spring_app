/**
 The AppController class is a Spring MVC controller that handles user requests and responses for the application.
 This controller is responsible for handling user interactions with the blog, clients and authentication functionality.
 It receives requests from the user, passes the requests to the corresponding service classes for processing, and
 then returns a view to the user to display the results.The controller is annotated with @Controller to indicate that it is a Spring
 controller and @RequestMapping("/") to map all requests to the root path. It is also annotated with @RequiredArgsConstructor
 to create a constructor with a required argument for each final field.

 @author Matthew Suprunov
 @version 1.0
 @since 20/04/2023
 */

package com.example;
import com.example.blog.Blog;
import com.example.blog.BlogService;
import com.example.client.Client;
import com.example.client.ClientService;
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

    /**
     Handles GET requests to the "/blog" path. Searches for blog posts based on the provided criteria.
     @param model the model object to add attributes to for rendering the view
     @param keywordId the ID keyword to search for
     @param keywordPostName the post name keyword to search for
     @param keywordDate the date keyword to search for
     @param keywordText the text keyword to search for
     @param keywordClientName the client name keyword to search for
     @param keyword the keyword to search for
     @return the name of the view to render
     */
    @GetMapping("/blog")
    public String searchBlog(Model model, @Param("keywordId") String keywordId,
                            @Param("keywordPostName") String keywordPostName,
                            @Param("keywordDate") String keywordDate,
                            @Param("keywordText") String keywordText,
                            @Param("keywordClientName") String keywordClientName,
                            @Param("keyword") String keyword) {
        List<Blog> listPostsByCriteria = blogService.listByPostCriteria(keywordId, keywordPostName,
                keywordDate, keywordText, keywordClientName, keyword);
        model.addAttribute("listPosts", listPostsByCriteria);
        String postKeywordName = blogService.getPostKeywordName();
        if (postKeywordName != null) {
            model.addAttribute(postKeywordName, blogService.getPostKeyword());
        }
        return "blog";
    }

    /**
     Handles GET requests to the "/new_post" path. Displays the form for creating a new blog post.
     @param model the model object to add attributes to for rendering the view
     @return the name of the view to render
     */
    @GetMapping("/new_post")
    public String showNewPostForm(Model model) {
        Blog blog = new Blog();
        model.addAttribute("blog", blog);
        return "new_post";
    }
    /**
     Handles POST requests to the "/save_post" path. Saves a new blog post.
     @param blog the blog post to save
     @return a redirect to the "/blog" path
     */
    @PostMapping("/save_post") // POST submits data to be processed to the identified resource.
    // requests a representation of the specified resource
    public String savePost(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        return "redirect:/blog";
    }
    /**
     Handles POST requests to the "/auth/register" path. Adds a new user to the system.
     @param userInfo the user information to add
     @param name the username of the user
     @param roles the roles of the user
     @param session the HttpSession object to add attributes to
     @return a redirect to the root path
     */
    @PostMapping("/auth/register")
    public String addNewUser(@ModelAttribute UserInfo userInfo, @RequestParam String name, @RequestParam String roles, HttpSession session) {
        System.out.println("UserInfo object is: " + userInfo); // Debugging code
        userService.addUser(userInfo);
        session.setAttribute("username", name);
        session.setAttribute("roles", roles);
        return "redirect:/";
    }
    /**
     The {@code register} method logs out the current user and redirects to the register page.
     @param request The {@link HttpServletRequest} object that contains the request the client has made of the servlet.
     @param response The {@link HttpServletResponse} object that contains the response the servlet sends to the client.
     @return The name of the view to be rendered, in this case, the register page.
     */
    @GetMapping("/auth/register")
    public String register(HttpServletRequest request, HttpServletResponse response) {
        // logout the current user
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "register";
    }
    /**
     The {@code viewHomePage} method retrieves a list of clients from the {@link ClientService} and displays them on the home page.
     @param model The {@link Model} object to be populated with the client list and keyword search term.
     @param keyword The search term entered by the user to filter the client list.
     @return The name of the view to be rendered, in this case, the home page.
     */
    @GetMapping("/")
    public String viewHomePage(Model model, @Param("keyword") String keyword) { // , Long post_id
        List<Client> listClients = clientService.listAll(keyword);
//        Blog blog = blogService.get(post_id);
        model.addAttribute("listClients", listClients);
        model.addAttribute("keyword", keyword);
//        model.addAttribute("Blog", blog);
        return "index";
    }
//    public ModelAndView showEditPostForm(@PathVariable(name="post_id") Long post_id) {
//        ModelAndView mav = new ModelAndView("edit_post");
//        Blog blog = blogService.get(post_id);
//        mav.addObject("Blog", blog);
//        return mav;
//    }
    /**
     The {@code showNewClientForm} method displays a form for creating a new client.
     Annotation "PreAuthorize" means that only authenticated users with ROLE_ADMIN have access to this page.
     If ROLE_USER tries to get to this page, 403 mistake (FORBIDDEN) comes out that is handled later as a custom page.
     @param model The {@link Model} object to be populated with a new {@link Client} object.
     @return The name of the view to be rendered, in this case, the new client form.
     */
    @GetMapping("/new") // создаем контроллер по добавлению студента
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String showNewClientForm(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "new_client";
    }
    /**
     The {@code saveClient} method saves a new or updated {@link Client} object to the {@link ClientService} and redirects to the home page.
     @param client The {@link Client} object to be saved.
     @return The URL of the home page to which the user will be redirected after the client is saved.
     */
    @PostMapping("/save") // POST submits data to be processed to the identified resource.
    public String saveClient(@ModelAttribute("client") Client client) {
        clientService.save(client);
        return "redirect:/";
    }
    /**
     Displays the form to edit an existing client, but only if the user is authorized.
     @param id the ID of the client to be edited
     @return the name of the HTML page to display
     */
    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ModelAndView showEditClientForm(@PathVariable(name="id") Long id) { // Annotation which indicates that a method parameter should be bound (связано) to a URI template variable
        ModelAndView mav = new ModelAndView("edit_client");
        Client client = clientService.get(id);
        mav.addObject("client", client);
        return mav;
    }
    /**
     Displays the form to edit an existing blog post.
     @param post_id the ID of the post to be edited
     @return the name of the HTML page to display
     */
//    @GetMapping("/edit_post/{post_id}")
//    public ModelAndView showEditPostForm(@PathVariable(name="post_id") Long post_id) {
//        ModelAndView mav = new ModelAndView("edit_post");
//        Blog blog = blogService.get(post_id);
//        mav.addObject("Blog", blog);
//        return mav;
//    }
    /**
     Deletes a client from the list.
     @param id the ID of the client to be deleted
     @return the name of the HTML page to display after the client has been deleted
     */
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable(name = "id") Long id) {
        clientService.delete(id);
        return "redirect:/";
    }
    /**
     Deletes a post from the list.
     @param post_id the ID of the post to be deleted
     @return the name of the HTML page to display after the post has been deleted
     */
    @GetMapping("/delete_post/{post_id}")
    public String deletePost(@PathVariable(name = "post_id") Long post_id) {
        blogService.delete(post_id);
        return "redirect:/blog";
    }

    /**
     Controller method for showing the "about us" page.
     @return The name of the HTML template to be rendered for the "about us" page.
     */
    @GetMapping("/about_us") // is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
    public String showAbout() {// add any additional objects you want to pass to the HTML page
        return "about_us";
    }
    /**
    Controller method for showing the login page.
    @return The name of the HTML template to be rendered for the "login" page.
    */
    @GetMapping("/login_page") // is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
    public String showLogin() {// add any additional objects you want to pass to the HTML page
        return "login_page";
    }
    /**
     Controller method for handling a successful login attempt.
     @param username The username entered by the user.
     @param session The HttpSession object to store the user's information.
     @return The URL to redirect to (the main page) after a successful login.
     */
    @PostMapping("/login_page")
    public String SuccessLogin(@RequestParam String username, HttpSession session) {
        System.out.println("Username is: " + username); // Debugging code
        // Authenticate the user and set the "username" attribute in the model
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        session.setAttribute("username", currentUser);
        return "redirect:/";
    }
    /**
     Controller method for showing the "access denied" page.
     @return The name of the HTML template to be rendered for the "access denied" page.
     */
    @GetMapping("/403")
    public String AccessDeniedError() {
        return "403";
    }
}


