package br.com.votacao.repositories;

import br.com.votacao.model.entidadeDao.Secao;
import br.com.votacao.model.entidadeDao.Usuario;
import br.com.votacao.model.entidadeDao.Votacao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotacaoRepository extends BaseRepository<Votacao>{

    Votacao findVotacaoBySecaoAndUsuario(Secao secao, Usuario usuario);
    List<Votacao> findBySecao(Secao secao);
}
