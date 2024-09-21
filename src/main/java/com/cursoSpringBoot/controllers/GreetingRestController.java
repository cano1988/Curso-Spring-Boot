package com.cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class GreetingRestController {

    //Vamos a utilizar la anotacion @PathVariable para covertir un parametro java en un parametro web

    @GetMapping({"/saludo/{name}", "/hell/{name}"}) //name se comporta como un atributo dinámico
    public String greeting(@PathVariable String name){
        return "Hola " + name;
    }
}
