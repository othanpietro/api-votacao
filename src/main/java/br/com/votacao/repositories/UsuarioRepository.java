package br.com.votacao.repositories;

import br.com.votacao.model.entidadeDao.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario>{

    Usuario findByCpf(String cpf);
}
