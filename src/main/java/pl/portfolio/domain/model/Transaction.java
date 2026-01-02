package pl.portfolio.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private TransactionType transactionType;

    @JoinColumn
    @ManyToOne
    private Position position;

    @Column
    private double amount;

    @Column
    private double price;

    @Column
    private Date date;

}
