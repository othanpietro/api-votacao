package br.com.votacao.service.impl;


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

import java.util.InputMismatchException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class VotocaoServiceImpl implements VotacaoService {

    private final VotacaoRepository votacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final SecaoRepository secaoRepository;
    @Override
    public VotoDTO enviarVoto(VotoDTO voto) {

        validaVoto(voto);

        if(voto.getVoto().length() > 1){
            throw new RuntimeException("Voto incorreto");
        }

        var secao = secaoRepository.findById(String.valueOf(voto.getIdSecao()));

        if(secao.isEmpty()){
            throw new RuntimeException("Secão inexistente");
        }

        var cpf = ValidaCPF.removeMascara(voto.getUsuarioDTO().getCpf());

        if(!ValidaCPF.isCPF(cpf)){
            throw new InputMismatchException("CPF invalido.");
        }
        var usuario = usuarioRepository.findByCpf(cpf);

        if(Objects.nonNull(usuario)) {

            var votoExistente = votacaoRepository.findVotacaoBySecaoAndUsuario(secao.get(), usuario);

            if (Objects.nonNull(votoExistente)) {
                throw new InputMismatchException("Usuario já votou nesta seção.");
            }

            var novoVoto = convertVotoDtoVotacao(voto, secao.get(), usuario);

            return convertVotocaoVotoDto(votacaoRepository.save(novoVoto));
        }

        var novoUsuario = convertUsuarioDtoUsuario(voto.getUsuarioDTO());

        var usuarioCriado = usuarioRepository.save(novoUsuario);

        var novoVoto = convertVotoDtoVotacao(voto, secao.get(), usuarioCriado);

        return convertVotocaoVotoDto(votacaoRepository.save(novoVoto));
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
                Objects.isNull(votoDTO.getVoto()) ||
                Objects.isNull(votoDTO.getIdSecao()) ||
                Objects.isNull(votoDTO.getUsuarioDTO()) ||
                Objects.isNull(votoDTO.getUsuarioDTO().getNome()) ||
                Objects.isNull(votoDTO.getUsuarioDTO().getCpf())) {
            throw new RuntimeException("Voto incorreto.");
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