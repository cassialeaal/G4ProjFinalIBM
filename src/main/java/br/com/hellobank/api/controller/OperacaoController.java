package br.com.hellobank.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hellobank.api.models.request.SaqueRequest;
import br.com.hellobank.api.models.entidades.Transferencia;
import br.com.hellobank.api.models.request.DepositoRequest;
import br.com.hellobank.api.models.request.TransferenciaRequest;
import br.com.hellobank.api.models.response.SaqueResponse;
import br.com.hellobank.api.models.response.DepositoResponse;
import br.com.hellobank.api.models.response.TransferenciaResponse;
import br.com.hellobank.api.service.interfacesServices.OperacaoService;

@RestController
@RequestMapping(path = "/operacao")
public class OperacaoController {

    @Autowired
    private OperacaoService operacaoService;

    @GetMapping(value = "/saldo/{contaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> balance(@PathVariable("contaId") Integer contaId) {
        String saldo = operacaoService.getSaldo(Long.valueOf(contaId));
        return new ResponseEntity<>(saldo, HttpStatus.OK);
    }

    @PostMapping(value = "/deposito", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deposito(@RequestBody DepositoRequest depositoRequest) {
        DepositoResponse depositoResponse = operacaoService.deposito(depositoRequest);
        return new ResponseEntity<>(depositoResponse, HttpStatus.OK);
    }

    @PostMapping( value = "/saque", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity Saque (@RequestBody SaqueRequest saqueRequest) {
        SaqueResponse saqueResponse = operacaoService.saque(saqueRequest);
        return new ResponseEntity<>(saqueResponse, HttpStatus.OK);
    }

    @PostMapping( value = "/transferencia", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity transferencia(@RequestBody TransferenciaRequest transferenciaRequest) {
        TransferenciaResponse transferenciaResponse = operacaoService.transferencia(transferenciaRequest);
        return new ResponseEntity<>(transferenciaResponse, HttpStatus.OK);
    }

    @GetMapping( value = "/contaTransacoes/{contaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity contaTransacoes(@PathVariable("contaId") Integer contaId) {
        List<Transferencia> transferencias = operacaoService.getTransferencias(Long.valueOf(contaId));
        return new ResponseEntity<>(transferencias, HttpStatus.OK);
    }
}