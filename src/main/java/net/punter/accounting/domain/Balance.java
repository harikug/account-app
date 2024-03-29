package net.punter.accounting.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Currency;

@Entity
@Table(name = "tb_account_balance")
@Getter
@Setter
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private BigDecimal amount = BigDecimal.ZERO;
    /**
     * Balance currency can not be modified ever...
     */
    @Column(updatable = false)
    @Setter(AccessLevel.NONE)
    @NotNull
    private Currency currency;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tb_account_id")
    private Account account;

    private Balance() {

    }

    public Balance(@NotNull Currency currency) {
        this.currency = currency;
    }

    public Balance(@NotNull BigDecimal amount, @NotNull Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public boolean isGreaterThanOrEquals(@NotNull BigDecimal amount) {
        if (this.amount.compareTo(amount) >= 0) {
            return true;
        }
        return false;
    }

}
