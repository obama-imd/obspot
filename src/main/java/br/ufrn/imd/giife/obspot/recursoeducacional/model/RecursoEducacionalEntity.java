package br.ufrn.imd.giife.obspot.recursoeducacional.model;

import br.ufrn.imd.giife.obspot.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "recursos_educacionais")
public class RecursoEducacionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String pathFile;

    @ManyToMany
    private List<UserEntity> authors;

    @ManyToMany
    private List<UserEntity> coAuthors;

    @ManyToMany
    private List<UserEntity> teachers;

    @Enumerated(EnumType.STRING)
    private TipoRecursoEducacional tipoRecursoEducacional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public List<UserEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<UserEntity> authors) {
        this.authors = authors;
    }

    public List<UserEntity> getCoAuthors() {
        return coAuthors;
    }

    public void setCoAuthors(List<UserEntity> coAuthors) {
        this.coAuthors = coAuthors;
    }

    public List<UserEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<UserEntity> teachers) {
        this.teachers = teachers;
    }

    public TipoRecursoEducacional getTipoRecursoEducacional() {
        return tipoRecursoEducacional;
    }

    public void setTipoRecursoEducacional(TipoRecursoEducacional tipoRecursoEducacional) {
        this.tipoRecursoEducacional = tipoRecursoEducacional;
    }
}
