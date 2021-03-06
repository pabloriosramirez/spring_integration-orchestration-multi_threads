/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.grisk.artemisa.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author Pablo Ríos Ramírez
 * @email pa.riosramirez@gmail.com
 * @web www.pabloriosramirez.com
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "variable", schema = "public")
public class Variable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_variable", nullable = false)
    private Long idVariable;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "coordinate", nullable = false, length = 50)
    private String coordinate;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "default_value", nullable = false, length = 100)
    private String defaultValue;

    @JsonBackReference
    @ManyToMany(mappedBy = "variableCollection")
    private Collection<Dataintegration> dataintegrationCollection;

    @JoinColumn(name = "type_variable", referencedColumnName = "id_type_variable", nullable = false)
    @ManyToOne(optional = false)
    private TypeVariable typeVariable;

    @Basic(optional = false)
    @NotNull
    @Column(name = "bureau", nullable = false)
    private boolean bureau;

    public Variable(Long idVariable) {
        this.idVariable = idVariable;
    }

    public Variable(String name, String code, String coordinate, String defaultValue, boolean bureau) {
        this.idVariable = idVariable;
        this.name = name;
        this.code = code;
        this.coordinate = coordinate;
        this.defaultValue = defaultValue;
        this.bureau = bureau;
    }

    public Variable(@NotNull @Size(min = 1, max = 100) String name, @NotNull @Size(min = 1, max = 50) String code, @NotNull @Size(min = 1, max = 50) String coordinate, @NotNull @Size(min = 1, max = 100) String defaultValue, Collection<Dataintegration> dataintegrationCollection, TypeVariable typeVariable, @NotNull boolean bureau) {
        this.name = name;
        this.code = code;
        this.coordinate = coordinate;
        this.defaultValue = defaultValue;
        this.dataintegrationCollection = dataintegrationCollection;
        this.typeVariable = typeVariable;
        this.bureau = bureau;
    }

    public Variable(@NotNull @Size(min = 1, max = 100) String name, @NotNull @Size(min = 1, max = 50) String code, @NotNull @Size(min = 1, max = 50) String coordinate, @NotNull @Size(min = 1, max = 100) String defaultValue, TypeVariable typeVariable, @NotNull boolean bureau) {
        this.name = name;
        this.code = code;
        this.coordinate = coordinate;
        this.defaultValue = defaultValue;
        this.typeVariable = typeVariable;
        this.bureau = bureau;
    }
}
