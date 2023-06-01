package br.com.votacao.controller;


import br.com.votacao.model.PautaDTO;
import br.com.votacao.model.SecaoDTO;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;
@RestController
@RequestMapping("/secao")
public class SecaoController {

        @PostMapping("/abrir")
        public HttpResponse<SecaoDTO> postSecao(@RequestBody SecaoDTO secaoDTO){
            return null;
        }
        @GetMapping()
        public HttpResponse<List<SecaoDTO>> getSecao(){
            return null;
        }
        @GetMapping("/{id}")
        public HttpResponse<SecaoDTO> getSecao(@PathVariable String id){
            return null;
        }

}
