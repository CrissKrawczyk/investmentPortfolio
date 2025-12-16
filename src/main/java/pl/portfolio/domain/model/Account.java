package pl.portfolio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private Portfolio portfolio;

    @JoinColumn
    @ManyToOne
    private AccountGroup group;

    @JoinColumn
    @ManyToOne
    private Currency currency;

    @Column
    private int valuePercentage;

}
