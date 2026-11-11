package org.example.controller;

import jakarta.validation.Valid;
import org.example.Service.ServiceArtigos;
import org.example.entity.Artigos;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping
public class ControllerArtigos {
    private final ServiceArtigos service;
    private final String emailClaim;

    public ControllerArtigos(ServiceArtigos service, @Value("@{auth.audience") String audience){
        this.service = service;
        this.emailClaim = audience + "/email";
    }

    @PostMapping
    public Artigos criar(@Valid @RequestBody Artigos artigo, @AuthenticationPrincipal Jwt jwt){
        String criador = jwt.getClaimAsString(emailClaim);
        if (criador == null || criador.isBlank()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Token sem e-mail do avaliador");
        }
        return service.cria(artigo, criador);
    }

    @GetMapping
    public List<Artigos> listar (){ return service.ListarAtigos();}

    @DeleteMapping
    public void deletar(Long id){ service.DeleteArtigos(id);}
}
