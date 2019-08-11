/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package online.grisk.artemisa.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * @author Pablo Ríos Ramírez
 * @email pa.riosramirez@gmail.com
 * @web www.pabloriosramirez.com
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "business_tree", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_tree", "organization"})})
public class BusinessTree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tree", nullable = false)
    private Long idTree;
    @Basic(optional = false)
    @NotNull
    @Column(name = "organization", nullable = false)
    private long organization;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "titule", nullable = false, length = 100)
    private String titule;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tree")
    private Collection<NodeTree> nodeTreeCollection;

    public BusinessTree(Long idTree) {
        this.idTree = idTree;
    }

    public BusinessTree(Long idTree, String titule, boolean enabled, Date createdAt) {
        this.idTree = idTree;
        this.titule = titule;
        this.enabled = enabled;
        this.createdAt = createdAt;
    }

}