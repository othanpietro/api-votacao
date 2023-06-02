package br.com.votacao.service;

import br.com.votacao.model.SecaoDTO;

import java.util.List;

public interface SecaoService {

    public SecaoDTO abrirSecao(SecaoDTO secaoDTO);

    public List<SecaoDTO> getSecoes();

    public SecaoDTO getSecao(String id);

}
