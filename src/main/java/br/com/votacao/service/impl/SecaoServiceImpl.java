package br.com.votacao.service.impl;

import br.com.votacao.exceptions.SecaoException;
import br.com.votacao.model.PautaDTO;
import br.com.votacao.model.ResultadoDTO;
import br.com.votacao.model.SecaoDTO;
import br.com.votacao.model.entidadeDao.Pauta;
import br.com.votacao.model.entidadeDao.Secao;
import br.com.votacao.repositories.PautaRepository;
import br.com.votacao.repositories.SecaoRepository;
import br.com.votacao.repositories.VotacaoRepository;
import br.com.votacao.service.SecaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecaoServiceImpl implements SecaoService {

    private final PautaRepository pautaRepository;
    private final SecaoRepository secaoRepository;
    private final VotacaoRepository votacaoRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public SecaoDTO abrirSecao(SecaoDTO secaoDTO) {
        try {
            validaSecao(secaoDTO);
            if (Objects.nonNull(secaoDTO.getDuracao())) {
                if (secaoDTO.getDuracao().isBefore(LocalDateTime.now())) {
                    throw new SecaoException("Duração da seção menor do que a data atual.");
                }
            }
            Pauta pauta = pautaRepository.save(convertPautaDtoToPauta(secaoDTO.getPautaDTO()));
            Secao secao = convertSecaoDtoToSecao(secaoDTO, pauta);

            return convertSecaoToSecaoDto(secaoRepository.save(secao));
        }catch (Exception e){
            throw new SecaoException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<SecaoDTO> getSecoes() {
        try {
            var secoes = secaoRepository.findAll();

            return secoes.stream().map(this::convertSecaoToSecaoDto)
                    .collect(Collectors.toList());
        } catch (Exception e){
            throw new SecaoException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public SecaoDTO getSecao(int id) {
        try {

            Secao secao = secaoRepository.findById(id);
            if (Objects.nonNull(secao))
                return convertSecaoToSecaoDto(secao);

            return null;
        }catch (Exception e){
            throw new SecaoException(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public ResultadoDTO getResultadoSecao(int id) {
        try {
            Secao secao = secaoRepository.findById(id);

            if (Objects.nonNull(secao)) {
                var votacao = votacaoRepository.findBySecao(secao);

                if (!votacao.isEmpty()) {

                    var votosSim = votacao.stream().filter(voto -> voto.getVoto().equalsIgnoreCase("s"))
                            .collect(Collectors.toList());
                    var votosNao = votacao.stream().filter(voto -> voto.getVoto().equalsIgnoreCase("n"))
                            .collect(Collectors.toList());

                    return ResultadoDTO
                            .builder()
                            .prazoFinal(secao.getDataFinalizacao())
                            .votosNAO(votosNao.size())
                            .votosSIM(votosSim.size())
                            .pauta(PautaDTO
                                    .builder()
                                    .nome(secao.getPauta().getNome())
                                    .descricao(secao.getPauta().getDescricao())
                                    .build())
                            .build();
                }
            }

            throw new SecaoException("Não foi possivel contabilizar os votos");

        } catch (Exception e){
            throw new SecaoException(e.getMessage());
        }
    }

    private boolean validaSecao(SecaoDTO secaoDTO){
        if(Objects.isNull(secaoDTO) ||
                Objects.isNull(secaoDTO.getPautaDTO()) ||
                !StringUtils.hasText(secaoDTO.getPautaDTO().getDescricao()) ||
                !StringUtils.hasText(secaoDTO.getPautaDTO().getNome())) {
            throw new SecaoException("Objeto incorreto");
        }
        return true;
    }
    public Pauta convertPautaDtoToPauta(PautaDTO pautaDTO){
        return Pauta
                .builder()
                .descricao(pautaDTO.getDescricao())
                .nome(pautaDTO.getNome())
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
                .idSecao(secao.getId())
                .build();
    }
}
