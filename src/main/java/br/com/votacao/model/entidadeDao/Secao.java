package br.com.votacao.model.entidadeDao;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import static br.com.votacao.AppConstants.ENTITY_SCHEMA;

@Entity
@Table(
        name = "secao_votacao",
        schema = ENTITY_SCHEMA)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Secao extends BaseEntity {

    @Column(name = "data_criacao")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime dataCriacao;

    @Column(name = "data_finalizacao")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime dataFinalizacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pauta")
    private Pauta pauta;

    @Column(name = "resultado_enviado")
    private boolean resultadoEnviado;
}
