package databaseModels;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Bubeník (bubenda1)
 */
@Entity
@Table(name = "java_persons")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JavaPersons.findAll", query = "SELECT j FROM JavaPersons j")
    , @NamedQuery(name = "JavaPersons.findById", query = "SELECT j FROM JavaPersons j WHERE j.id = :id")
    , @NamedQuery(name = "JavaPersons.findByName", query = "SELECT j FROM JavaPersons j WHERE j.name = :name")
    , @NamedQuery(name = "JavaPersons.findBySurname", query = "SELECT j FROM JavaPersons j WHERE j.surname = :surname")
    , @NamedQuery(name = "JavaPersons.findByBirthdate", query = "SELECT j FROM JavaPersons j WHERE j.birthdate = :birthdate")
    , @NamedQuery(name = "JavaPersons.findByAddress", query = "SELECT j FROM JavaPersons j WHERE j.address = :address")})
public class JavaPersons implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Basic(optional = false)
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthdate;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private Collection<JavaPersonsBanks> javaPersonsBanksCollection;

    public JavaPersons() {
    }

    public JavaPersons(Integer id) {
        this.id = id;
    }

    public JavaPersons(Integer id, String name, String surname, Date birthdate, String address) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthdate() {
        SimpleDateFormat formatter = new SimpleDateFormat("d.M.yyyy");
        return formatter.format(birthdate);
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    public Collection<JavaPersonsBanks> getJavaPersonsBanksCollection() {
        return javaPersonsBanksCollection;
    }

    public void setJavaPersonsBanksCollection(Collection<JavaPersonsBanks> javaPersonsBanksCollection) {
        this.javaPersonsBanksCollection = javaPersonsBanksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JavaPersons)) {
            return false;
        }
        JavaPersons other = (JavaPersons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbModel.JavaPersons[ id=" + id + " ]";
    }

}
