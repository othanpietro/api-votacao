package br.com.votacao.controller;

import br.com.votacao.model.VotoDTO;
import br.com.votacao.service.VotacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/votacao")
public class VotacaoController {

    private final VotacaoService votacaoService;

    public VotacaoController(VotacaoService votacaoService) {
        this.votacaoService = votacaoService;
    }

    @ApiOperation(httpMethod = "POST", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, value = "Envia voto em uma seção.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Voto enviado com sucesso"),
            @ApiResponse(code = 500, message = "Não foi possível enviar voto")})
    @PostMapping()
    public ResponseEntity<VotoDTO> postEnviaVoto(@RequestBody VotoDTO votoDTO) {
        return ResponseEntity.ok(votacaoService.enviarVoto(votoDTO));
    }
}