package br.com.votacao.model.entidadeDao;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @Column
    @GeneratedValue
    private int id;

    public BaseEntity(int id) {this.id = id;}

}
