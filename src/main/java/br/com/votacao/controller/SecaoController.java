package br.com.votacao.controller;


import br.com.votacao.model.SecaoDTO;
import br.com.votacao.service.SecaoService;
import javax.ws.rs.core.Response;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secao")
public class SecaoController {
    private SecaoService secaoService;

        @PostMapping("/abrir")
        public Response postSecao(@RequestBody SecaoDTO secaoDTO){
            return Response
                    .status(Response.Status.CREATED)
                    .entity(secaoService.abrirSecao(secaoDTO))
                    .build();
        }
        @GetMapping()
        public Response getSecoes(){
            return Response
                    .status(Response.Status.OK)
                    .entity(secaoService.getSecoes())
                    .build();
        }
        @GetMapping("/{id}")
        public Response getSecao(@PathVariable String id){
            return Response
                    .status(Response.Status.OK)
                    .entity(secaoService.getSecao(id))
                    .build();
        }

}
