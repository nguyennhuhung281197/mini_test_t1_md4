package com.example.posts.controller;

import com.example.posts.model.Posts;
import com.example.posts.service.IPosts;
import com.example.posts.service.PostsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostsController {
    private IPosts postsService = new PostsService();

    @GetMapping("")
    public ModelAndView index() {
        List<Posts> postsList = postsService.findAll();
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("posts", postsList);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("posts", new Posts());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView save(Posts posts) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postsService.save(posts);
        return modelAndView;
    }

    @GetMapping("{id}/edit")
    public ModelAndView showEdit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("posts", postsService.findById(id));
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(Posts posts, RedirectAttributes redirect) {
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postsService.update(posts.getId(), posts);
        redirect.addFlashAttribute("resultEdit", "Edit successful.....");
        return modelAndView;
    }

    @GetMapping("{id}/delete")
    public ModelAndView showDelete(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("posts", postsService.findById(id));
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(Posts posts , RedirectAttributes redirect){
        ModelAndView modelAndView = new ModelAndView("redirect:/posts");
        postsService.delete(posts.getId());
        redirect.addFlashAttribute("resultDelete" , "Delete successful..... ");
        return modelAndView;
    }

    @GetMapping("{id}/view")
    public ModelAndView showView(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView("/view");
        modelAndView.addObject("posts" ,postsService.findById(id));
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("search") String name){
        ModelAndView modelAndView = new ModelAndView("/search");
        modelAndView.addObject("posts" , postsService.search(name));
        return modelAndView;
    }
}