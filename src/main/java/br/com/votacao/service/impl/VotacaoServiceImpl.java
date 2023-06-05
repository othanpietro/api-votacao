package br.com.votacao.service.impl;


import br.com.votacao.exceptions.VotacaoException;
import br.com.votacao.model.UsuarioDTO;
import br.com.votacao.model.VotoDTO;
import br.com.votacao.model.entidadeDao.Secao;
import br.com.votacao.model.entidadeDao.Usuario;
import br.com.votacao.model.entidadeDao.Votacao;
import br.com.votacao.repositories.SecaoRepository;
import br.com.votacao.repositories.UsuarioRepository;
import br.com.votacao.repositories.VotacaoRepository;
import br.com.votacao.service.VotacaoService;
import br.com.votacao.utils.ValidaCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VotacaoServiceImpl implements VotacaoService {

    private final VotacaoRepository votacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SecaoRepository secaoRepository;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public VotoDTO enviarVoto(VotoDTO voto) {
        try {
            validaVoto(voto);

            if (voto.getVoto().length() > 1 ||
                    !(voto.getVoto().equalsIgnoreCase("s")
                            || voto.getVoto().equalsIgnoreCase("n"))) {
                throw new VotacaoException("Voto incorreto");
            }

            var secao = secaoRepository.findById(voto.getIdSecao());

            if (Objects.isNull(secao)) {
                throw new VotacaoException("Secão inexistente");
            }

            var cpf = ValidaCPF.removeMascara(voto.getUsuarioDTO().getCpf());

            if (!ValidaCPF.isCPF(cpf)) {
                throw new VotacaoException("CPF invalido.");
            }
            var usuario = usuarioRepository.findByCpf(cpf);

            if (Objects.nonNull(usuario)) {

                var votoExistente = votacaoRepository.findVotacaoBySecaoAndUsuario(secao, usuario);

                if (Objects.nonNull(votoExistente)) {
                    throw new VotacaoException("Usuario já votou nesta seção.");
                }

                var novoVoto = convertVotoDtoVotacao(voto, secao, usuario);

                return convertVotocaoVotoDto(votacaoRepository.save(novoVoto));
            }

            var novoUsuario = convertUsuarioDtoUsuario(voto.getUsuarioDTO());

            var usuarioCriado = usuarioRepository.save(novoUsuario);

            var novoVoto = convertVotoDtoVotacao(voto, secao, usuarioCriado);

            return convertVotocaoVotoDto(votacaoRepository.save(novoVoto));

        } catch (Exception e){
            throw new VotacaoException(e.getMessage());
        }
    }


    private Votacao convertVotoDtoVotacao(VotoDTO votoDTO, Secao secao, Usuario usuario){
        return Votacao
                .builder()
                .voto(votoDTO.getVoto())
                .secao(secao)
                .usuario(usuario)
                .build();
    }
    private void validaVoto(VotoDTO votoDTO){
        if(Objects.isNull(votoDTO) ||
                !StringUtils.hasText(votoDTO.getVoto()) ||
                Objects.isNull(votoDTO.getIdSecao()) ||
                Objects.isNull(votoDTO.getUsuarioDTO()) ||
                !StringUtils.hasText(votoDTO.getUsuarioDTO().getNome()) ||
                !StringUtils.hasText(votoDTO.getUsuarioDTO().getCpf())) {
            throw new VotacaoException("Voto incorreto.");
        }
    }
    private Usuario convertUsuarioDtoUsuario(UsuarioDTO usuarioDTO){
        return Usuario
                .builder()
                .nome(usuarioDTO.getNome())
                .cpf(ValidaCPF.removeMascara(usuarioDTO.getCpf()))
                .build();
    }
    private VotoDTO convertVotocaoVotoDto(Votacao votacao){
        return VotoDTO
                .builder()
                .voto(votacao.getVoto())
                .idSecao(votacao.getSecao().getId())
                .usuarioDTO(UsuarioDTO
                        .builder()
                        .cpf(ValidaCPF.imprimeCPF(votacao.getUsuario().getCpf()))
                        .nome(votacao.getUsuario().getNome())
                        .build())
                .build();
    }
}
