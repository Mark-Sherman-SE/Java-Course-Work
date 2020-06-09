package main.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name = "operations")
public class Operation {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @ManyToOne(targetEntity = Article.class)
    @NotNull(message = "Articles are required")
    private Article article;

    @Column(name = "DEBIT")
    @NotNull(message = "Debit is required")
    private Double debit;

    @Column(name = "CREDIT")
    @NotNull(message = "Credit is required")
    private Double credit;

    @Column(name = "CREATE_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Date is required")
    private Timestamp create_date;

    @ManyToOne(targetEntity = Balance.class)
    @NotNull(message = "Balance is required")
    private Balance balance;

    public Operation() {

    }

    public Operation(Article article, Double debit, Double credit, Timestamp create_date, Balance balance) {
        this.article = article;
        this.debit = debit;
        this.credit = credit;
        this.create_date = create_date;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Operations{" +
                "id=" + id +
                ", articles=" + article +
                ", debit=" + debit +
                ", credit=" + credit +
                ", create_date=" + create_date +
                ", balance=" + balance +
                '}';
    }
}
