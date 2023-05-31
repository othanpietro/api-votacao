package br.com.votacao.controller;

import br.com.votacao.model.PautaDTO;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    @PostMapping()
    public HttpResponse<PautaDTO> postPauta(@RequestBody PautaDTO pautaDTO){
        return null;
    }
    @GetMapping()
    public HttpResponse<List<PautaDTO>> getPautas(){
        return null;
    }
    @GetMapping("/{id}")
    public HttpResponse<PautaDTO> getPauta(@PathVariable String id){
        return null;
    }
}
