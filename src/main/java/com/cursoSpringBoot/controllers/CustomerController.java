package com.cursoSpringBoot.controllers;

import com.cursoSpringBoot.domain.Customer;
import jakarta.servlet.Servlet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class CustomerController {

   private List<Customer> customers = new ArrayList<>(Arrays.asList(
      new Customer(123,"Andres","andriu","12"),
      new Customer(124,"Juan","Junis","contrasena123"),
      new Customer(125,"Alfredo","alfre32","contrasena34"),
      new Customer(234,"Pedro","pedrito","contrasenapedro")
   ));

   @GetMapping
   public ResponseEntity<List<Customer>> getCustomer(){
       return ResponseEntity.ok(customers); //ok representa el codigo de respuesta 200
   }


   @GetMapping("/{username}")
   public ResponseEntity<?>  getCliente(@PathVariable String username){  //Cuando pongo un parametro tipo ? indica que puedo mandar una lista de tipo customer u otro en general
      for (Customer c : customers){
         if(c.getUsername().equalsIgnoreCase(username)){
            return ResponseEntity.ok(c);//ok representa el codigo de respuesta 200

         }
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con username: " + username);
   }
   @PostMapping
   public ResponseEntity<?> postCliente(@RequestBody Customer customer){
      customers.add(customer);

      //return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado exitosamente " + customer.getUsername()); Mensaje personalizado
      URI location = ServletUriComponentsBuilder
              .fromCurrentRequest()
              .path("/{username}")
              .buildAndExpand(customer.getUsername())
              .toUri();

      //return ResponseEntity.created(location).build(); muestra la uri, sin el recurso con los valores
      return ResponseEntity.created(location).body(customer); //muestra la uri con el recurso creado
   }

   @PutMapping
   public ResponseEntity<?> putCliente(@RequestBody Customer customer){
      for (Customer c : customers){
         if(c.getID()  == customer.getID()){
            c.setName(customer.getName());
            c.setUsername(customer.getUsername());
            c.setPassword(customer.getPassword());


            //return ResponseEntity.ok("Cliente actualizado correctamente: " + customer.getID()); Mensaje personalizado
            return ResponseEntity.noContent().build();
         }
      }

      //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado " + customer.getID()); Mensaje personalizado
      return ResponseEntity.notFound().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<?> deleteCliente(@PathVariable int id){
      for(Customer c : customers){
         if(c.getID() == id){
            customers.remove(c);

            //return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente eliminado Satisfactoriamente " + id) ;
            return ResponseEntity.noContent().build();
         }
      }
      //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado " + id);
     return ResponseEntity.notFound().build();
   }

   @PatchMapping
   public ResponseEntity<?> patchCliente(@RequestBody Customer customer){
      for(Customer c: customers){
         if(c.getID() == customer.getID()){

            if(customer.getName() != null){
               c.setName(customer.getName());
            }
            if(customer.getUsername() != null){
               c.setUsername(customer.getUsername());
            }
            if(customer.getPassword() != null){
               c.setPassword(customer.getPassword());
            }
            return ResponseEntity.ok("Cliente modificado satistactoroiamente " +customer.getID());
         }
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Clente no encontrado: " + customer.getID());
   }
}
