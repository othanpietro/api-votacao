package br.com.votacao.repositories;

import br.com.votacao.model.entidadeDao.Secao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecaoRepository extends BaseRepository<Secao>{
    Secao findById(int id);

    List<Secao> findAll();
}
