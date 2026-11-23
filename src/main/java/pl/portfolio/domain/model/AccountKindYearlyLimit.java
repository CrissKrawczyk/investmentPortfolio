package pl.portfolio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class AccountKindYearlyLimit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private int limitYear;

    @Column
    private float limitValue;

    @JoinColumn
    @ManyToOne
    private AccountKind kind;

}
