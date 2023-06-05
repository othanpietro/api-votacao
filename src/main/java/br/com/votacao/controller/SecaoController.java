package br.com.votacao.controller;


import br.com.votacao.model.SecaoDTO;
import br.com.votacao.service.SecaoService;

import javax.ws.rs.core.MediaType;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/secao")
public class SecaoController {
    private final SecaoService secaoService;

    public SecaoController(SecaoService secaoService) {
        this.secaoService = secaoService;
    }

    @ApiOperation(httpMethod = "POST", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, value = "Cria uma pauta e abri uma seção.")
        @ApiResponses(value = {@ApiResponse(code = 201, message = "Seção aberta com sucesso"),
            @ApiResponse(code = 500, message = "Não foi possível abrir a seção")})
        @PostMapping()
        public ResponseEntity<SecaoDTO> postSecao(@RequestBody SecaoDTO secaoDTO){
            return ResponseEntity.ok(secaoService.abrirSecao(secaoDTO));
        }
        @GetMapping()
        @ApiOperation(httpMethod = "GET", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, value = "Retona todas as seções")
        @ApiResponses(value = {@ApiResponse(code = 200, message = "Seções carregadas com sucesso."),
                @ApiResponse(code = 500, message = "Não foi possível carregar as seções.")})
        public ResponseEntity<List<SecaoDTO>> getSecoes(){
            return ResponseEntity.ok(secaoService.getSecoes());
        }
        @GetMapping("/{id}")
        @ApiOperation(httpMethod = "GET", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON, value = "Retona a seção pelo id")
        @ApiResponses(value = {@ApiResponse(code = 200, message = "Seção carregada com sucesso."),
                @ApiResponse(code = 500, message = "Não foi possível carregar a seção.")})
        public ResponseEntity<SecaoDTO> getSecao(@PathVariable String id){
            return ResponseEntity.ok(secaoService.getSecao(id));
        }

}
