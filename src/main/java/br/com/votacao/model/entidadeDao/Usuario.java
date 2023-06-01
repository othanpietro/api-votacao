package br.com.votacao.model.entidadeDao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import static br.com.votacao.AppConstants.ENTITY_SCHEMA;

@Entity
@Table(
        name = "usuario",
        schema = ENTITY_SCHEMA)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Usuario extends BaseEntity {

    @Column(name = "nome")
    private String nome;

}
