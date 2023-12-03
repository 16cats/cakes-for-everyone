package com.example.cakestore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cakestore.domain.AppUser;
import com.example.cakestore.domain.AppUserRepository;
import com.example.cakestore.domain.Cake;
import com.example.cakestore.domain.CakeRepository;
import com.example.cakestore.domain.CategoryRepository;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class CakeController {
	@Autowired
	private CakeRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private AppUserRepository urepository;
	
	
	// Rest all books http://localhost:8080/books
    @RequestMapping(value="/cakes", method = RequestMethod.GET)
    public @ResponseBody List<Cake> bookListRest() {	
    	return (List<Cake>) repository.findAll();
    }  
    
	// Rest book by id http://localhost:8080/book/1
    @RequestMapping(value="/cake/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Cake> findBookRest(@PathVariable("id") Long cakeId) {	
    	return repository.findById(cakeId);
    }     
	
	//get mapping is better than request here(?)
	@GetMapping("/cakelist")
	public String cakeList(Model model) {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        AppUser userNow = urepository.findByUsername(username);
        System.out.println("JUKKA " + userNow.getRole());
		model.addAttribute("cakes", repository.findAll());
		return "cakelist";
	}
		
		//for adding
		@PreAuthorize("hasAuthority('ADMIN')")
	    @RequestMapping(value = "/add")
	    public String addCake(Model model){
			model.addAttribute("cake", new Cake());
			model.addAttribute("categories", crepository.findAll());
	        return "addcake";
	    }     
	    
	    //for saving
		@PreAuthorize("hasAuthority('ADMIN')")
	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Cake cake){
	        repository.save(cake);
			return "redirect:cakelist";
	    }    
	    
	    //for editing
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/edit/{id}")
		public String edit(@PathVariable("id") Long cakeId, Model model){
			model.addAttribute("cake", repository.findById(cakeId));
			model.addAttribute("categories", crepository.findAll());
			return "editcake";
		}
	    
	    //for deleting
		@PreAuthorize("hasAuthority('ADMIN')")
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteBook(@PathVariable("id") Long cakeId, Model model) {
			repository.deleteById(cakeId);
			return "redirect:../cakelist";
	    }     
	}