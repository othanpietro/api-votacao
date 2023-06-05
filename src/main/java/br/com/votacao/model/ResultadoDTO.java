package br.com.votacao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoDTO {

    private int votosSIM;
    private int votosNAO;
    private PautaDTO pauta;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime prazoFinal;
}
