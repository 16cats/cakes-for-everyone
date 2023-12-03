package com.example.cakestore.web;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.example.cakestore.domain.Cake;
import com.example.cakestore.domain.CakeRepository;

@Controller
public class ShoppingCartController {

    @Autowired
    private HttpSession session;

    @Autowired
    private CakeRepository repository;

    // Show shopping cart
    @RequestMapping(value = "/shoppingcart")
    public String showCart(Model model) {
        model.addAttribute("cart", session.getAttribute("cart"));
        return "shoppingcart";
    }

    // Add to shopping cart
    @RequestMapping(value = "/addtocart/{id}", method = RequestMethod.GET)
    public String addtoCart(@PathVariable(value = "id") Long cakeId) {
        List<Cake> cart = (List<Cake>) session.getAttribute("cart");

        if (cart == null) { 
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        Cake item = repository.findById(cakeId).get();
        cart.add(item);

        return "redirect:/shoppingcart";
    }

    // Delete from cart
    @RequestMapping(value = "/deletefromcart/{id}", method = RequestMethod.GET)
    public String deleteFromCart(@PathVariable(value = "id") Long cakeId) {
        System.out.println("Deleting cake with ID: " + cakeId);

        List<Cake> cart = (List<Cake>) session.getAttribute("cart");

        Cake item = repository.findById(cakeId).orElse(null);
        if (item != null) {
            cart.remove(item);
            System.out.println("Cake removed from cart: " + item);
        } else {
            System.out.println("Cake not found in the repository with ID: " + cakeId);
        }

        return "redirect:/shoppingcart";
    }

}