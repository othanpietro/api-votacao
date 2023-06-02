package br.com.votacao.controller;

import br.com.votacao.model.VotoDTO;
import br.com.votacao.service.VotacaoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/votacao")
public class VotacaoController {

    private VotacaoService votacaoService;
    @PostMapping()
    public Response postUsuario(@RequestBody VotoDTO votoDTO) {
        return Response
                .status(Response.Status.CREATED)
                .entity(votacaoService.enviarVoto(votoDTO))
                .build();
    }
}