package br.com.votacao.model.entidadeDao;

import lombok.*;

import javax.persistence.*;

import static br.com.votacao.AppConstants.ENTITY_SCHEMA;

@Entity
@Table(
        name = "votacao",
        schema = ENTITY_SCHEMA)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Votacao extends BaseEntity {

    @Column(name = "voto")
    private String voto;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_secao")
    private Secao secao;


}
