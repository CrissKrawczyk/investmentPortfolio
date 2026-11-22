package pl.portfolio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JoinColumn
    @ManyToOne
    private Account account;

    @Column
    private String name;

    @OneToMany(mappedBy = "position", orphanRemoval = true)
    private List<Transaction> transactions;

    @Column
    private Integer valuePercentage;

    @JoinColumn
    @ManyToOne
    private Currency currency;

}
