package br.com.votacao.controller;

import br.com.votacao.model.VotoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/votacao")
public class VotacaoController {

    @PostMapping()
    public HttpResponse<VotoDTO> postUsuario(@RequestBody VotoDTO votoDTO){
        return null;
    }
}
