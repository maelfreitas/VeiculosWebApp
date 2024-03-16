package com.cadastro.morador;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "registro")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 40)
    private String placaR;
    @Column(nullable = false, length = 40)
    private Date dataR;
    @Column(nullable = false, length = 40)
    private String horaR;
    @Column(nullable = false, length = 40)
    private String tipoR;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlacaR() {
        return placaR;
    }

    public void setPlacaR(String placaR) {
        this.placaR = placaR;
    }

    public Date getDataR() {
        return dataR;
    }

    public void setDataR(Date dataR) {
        this.dataR = dataR;
    }

    public String getHoraR() {
        return horaR;
    }

    public void setHoraR(String horaR) {
        this.horaR = horaR;
    }

    public String getTipoR() {
        return tipoR;
    }

    public void setTipoR(String tipoR) {
        this.tipoR = tipoR;
    }
}