package br.com.votacao.model.entidadeDao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static br.com.votacao.AppConstants.ENTITY_SCHEMA;

@Entity
@Table(
        name = "pauta",
        schema = ENTITY_SCHEMA)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pauta  extends BaseEntity {

    @Column(name = "descricao", nullable = false)
    private String descricao;

}
