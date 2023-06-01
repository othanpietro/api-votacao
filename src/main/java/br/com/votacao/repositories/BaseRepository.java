package br.com.votacao.repositories;

import br.com.votacao.model.entidadeDao.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T extends BaseEntity> extends CrudRepository<T, String> {
}
