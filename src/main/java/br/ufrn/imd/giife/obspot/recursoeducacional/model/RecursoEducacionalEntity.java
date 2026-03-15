package br.ufrn.imd.giife.obspot.recursoeducacional.model;

import br.ufrn.imd.giife.obspot.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "recursos_educacionais")
public class RecursoEducacionalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    @Lob
    private String description;

    @Column()
    private String pathFile;

    @ManyToMany
    private Set<UserEntity> authors;

    @ManyToMany
    private Set<UserEntity> coAuthors;

    @ManyToMany
    private Set<UserEntity> teachers;

    @Enumerated(EnumType.STRING)
    private TipoRecursoEducacional tipoRecursoEducacional;

    // Utils

    @Override
    public boolean equals(Object object) {
        return object instanceof RecursoEducacionalEntity recurso
                && recurso.getTitle() != null
                && recurso.getTitle().equals(this.getTitle());
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    // Getters e setters

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

    public Set<UserEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<UserEntity> authors) {
        this.authors = authors;
    }

    public boolean addAuthor(UserEntity author) {
        return this.authors.add(author);
    }

    public boolean addAuthors(Collection<UserEntity> authors) {
        return this.authors.addAll(authors);
    }

    public boolean removeAuthor(UserEntity author) {
        return this.authors.remove(author);
    }

    public  boolean removeAuthors(Collection<UserEntity> authors) {
        return this.authors.removeAll(authors);
    }

    public Set<UserEntity> getCoAuthors() {
        return coAuthors;
    }

    public void setCoAuthors(Set<UserEntity> coAuthors) {
        this.coAuthors = coAuthors;
    }

    public boolean addCoAuthor(UserEntity coAuthor) {
        return this.coAuthors.add(coAuthor);
    }

    public boolean addCoAuthors(Collection<UserEntity> coAuthors) {
        return this.coAuthors.addAll(coAuthors);
    }

    public boolean removeCoAuthor(UserEntity coAuthor) {
        return this.coAuthors.remove(coAuthor);
    }

    public boolean removeCoAuthors(Collection<UserEntity> coAuthors) {
        return this.coAuthors.removeAll(coAuthors);
    }

    public Set<UserEntity> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<UserEntity> teachers) {
        this.teachers = teachers;
    }

    public boolean addTeacher(UserEntity teacher) {
        return this.teachers.add(teacher);
    }

    public boolean addTeachers(Collection<UserEntity> teachers) {
        return this.teachers.addAll(teachers);
    }

    public boolean removeTeacher(UserEntity teacher) {
        return this.teachers.remove(teacher);
    }

    public boolean removeTeachers(Collection<UserEntity> teachers) {
        return this.teachers.removeAll(teachers);
    }

    public TipoRecursoEducacional getTipoRecursoEducacional() {
        return tipoRecursoEducacional;
    }

    public void setTipoRecursoEducacional(TipoRecursoEducacional tipoRecursoEducacional) {
        this.tipoRecursoEducacional = tipoRecursoEducacional;
    }
}
