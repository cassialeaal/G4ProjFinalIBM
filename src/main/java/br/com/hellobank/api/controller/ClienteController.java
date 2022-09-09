package br.com.hellobank.api.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.hellobank.api.model.Cliente;
import br.com.hellobank.api.service.ClienteService;

@RestController
public class ClienteController {
     
     @Autowired
     private ClienteService service;

     @GetMapping("/clientes")
     private ArrayList<Cliente> listarTodos(){
          return service.listarTodos();
     }

     @GetMapping("/clientes/{id}")
     public ResponseEntity<Cliente> listarPeloI(@PathVariable Integer id){
          Cliente cliente = service.listarPeloId(id);
          if(cliente != null){
               return ResponseEntity.ok(cliente);
          }
          return ResponseEntity.notFound().build();
     }

     @PostMapping("/clientes")
     public ResponseEntity<Cliente> cadastrarNovo(@RequestBody Cliente novo){
          Cliente cliente = service.cadastrarNovo(novo);
          if(cliente != null){
               return ResponseEntity.ok(cliente);
          }
          return ResponseEntity.badRequest().build();
     }
     
     @PutMapping("/clientes")
     public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente atualiza){
          Cliente cliente = service.atualizarCliente(atualiza);
          if(cliente != null){
               return ResponseEntity.ok(cliente);
          }
          return ResponseEntity.badRequest().build();
     }
     
     @DeleteMapping("/clientes/{id}")
     public ResponseEntity<Cliente> deletarCliente(@RequestBody Integer id){
          service.deletarCliente(id);
          return ResponseEntity.ok(null);
     }
}