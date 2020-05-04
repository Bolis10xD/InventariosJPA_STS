package com.uatx.inventarios.model;

import javax.persistence.*;

@Entity
@Table(name = "imagenes")
@SequenceGenerator(name="imagen_seq")
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "imagen_seq")
    private Long id;

    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "mime_type", length = 20)
    private String mimeType;

    @Column(name = "datos", length = 5_000_000)
    private String dataBase64;


    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDataBase64() {
        return dataBase64;
    }

    public void setDataBase64(String dataBase64) {
        this.dataBase64 = dataBase64;
    }
}
