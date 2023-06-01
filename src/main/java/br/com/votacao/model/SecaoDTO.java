package br.com.votacao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecaoDTO {

    private LocalDateTime duracao;
    private PautaDTO pautaDTO;
}
