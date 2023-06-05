package br.com.votacao.service.impl;

import br.com.votacao.model.PautaDTO;
import br.com.votacao.model.SecaoDTO;
import br.com.votacao.model.entidadeDao.Pauta;
import br.com.votacao.model.entidadeDao.Secao;
import br.com.votacao.repositories.PautaRepository;
import br.com.votacao.repositories.SecaoRepository;
import br.com.votacao.service.SecaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SecaoServiceImpl implements SecaoService {

    private final PautaRepository pautaRepository;
    private final SecaoRepository secaoRepository;

    @Override
    public SecaoDTO abrirSecao(SecaoDTO secaoDTO) {

        validaSecao(secaoDTO);
        Pauta pauta =  pautaRepository.save(convertPautaDtoToPauta(secaoDTO.getPautaDTO()));
        Secao secao =  convertSecaoDtoToSecao(secaoDTO, pauta);

        return convertSecaoToSecaoDto(secaoRepository.save(secao)) ;
    }

    @Override
    public List<SecaoDTO> getSecoes() {
       var secoes = secaoRepository.findAll();

        List<Secao> listSecao = StreamSupport.stream(secoes.spliterator(), false)
                .collect(Collectors.toList());

        return listSecao.stream().map(this::convertSecaoToSecaoDto)
                .collect(Collectors.toList());
    }

    @Override
    public SecaoDTO getSecao(String id) {
        if(Objects.nonNull(id)){
            Optional<Secao> secao  = secaoRepository.findById(id);
            if(secao.isPresent())
                return convertSecaoToSecaoDto(secao.get());
        }

        return null;
    }

    private boolean validaSecao(SecaoDTO secaoDTO){
        if(Objects.isNull(secaoDTO) ||
                Objects.isNull(secaoDTO.getPautaDTO()) ||
                Objects.isNull(secaoDTO.getPautaDTO().getDescricao()) ||
                Objects.isNull(secaoDTO.getPautaDTO().getNome())) {
            throw new RuntimeException("Objeto incorreto");
        }
        return true;
    }
    public Pauta convertPautaDtoToPauta(PautaDTO pautaDTO){
        return Pauta
                .builder()
                .descricao(pautaDTO.getDescricao())
                .build();
    }
    public Secao convertSecaoDtoToSecao(SecaoDTO secaoDTO, Pauta pauta){
        return Secao
                .builder()
                .dataCriacao(LocalDateTime.now())
                .pauta(pauta)
                .dataFinalizacao(Objects.nonNull(secaoDTO.getDuracao()) ? secaoDTO.getDuracao() : LocalDateTime.now().plusMinutes(1))
                .build();
    }

    public SecaoDTO convertSecaoToSecaoDto(Secao secao){
        return SecaoDTO
                .builder()
                .pautaDTO(PautaDTO
                        .builder()
                        .descricao(secao.getPauta().getDescricao())
                        .nome(secao.getPauta().getNome())
                        .build())
                .duracao(secao.getDataFinalizacao())
                .build();
    }
}
