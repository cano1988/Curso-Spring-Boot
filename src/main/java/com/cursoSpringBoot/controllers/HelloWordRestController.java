package com.cursoSpringBoot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Anotaciones
@RestController

public class HelloWordRestController { //En este momento spring sabe que esta clase es controladora del tipo rest

  /*  Se debe realizar un mapeo para que cuando se realice una solicitud
    desde el cliente se pueda ejecujar el metodo y retornar la respuesta*/

    @GetMapping({"/hello", "/world", "/hw"})
    //esta anotacion requiere un atributo la cual va ser la URL para acceder al enpoint
    // Tambien puedo tener varias rutas para una misma solicitud
    public  String helloWorld(){
        return  "Hello world hola";
    }
}
