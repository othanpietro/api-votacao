package br.com.votacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO {

    private String voto;
    private UsuarioDTO usuarioDTO;
    private int idSecao;

}
