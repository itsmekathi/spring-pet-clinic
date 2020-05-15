package com.aayud.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/owners")
@Controller
public class OwnersController {
    @RequestMapping({"","/"})
    public  String listOwners(){
        return "owners/index";
    }
}
