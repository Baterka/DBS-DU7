package dbModel;

import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author David Bubeník (bubenda1)
 */
@Entity
@Table(name = "java_banks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JavaBanks.findAll", query = "SELECT j FROM JavaBanks j")
    , @NamedQuery(name = "JavaBanks.findById", query = "SELECT j FROM JavaBanks j WHERE j.id = :id")
    , @NamedQuery(name = "JavaBanks.findByName", query = "SELECT j FROM JavaBanks j WHERE j.name = :name")
    , @NamedQuery(name = "JavaBanks.findByCodename", query = "SELECT j FROM JavaBanks j WHERE j.codename = :codename")
    , @NamedQuery(name = "JavaBanks.findByAddress", query = "SELECT j FROM JavaBanks j WHERE j.address = :address")})
public class JavaBanks implements Serializable {

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
    @Column(name = "codename")
    private String codename;
    @Basic(optional = false)
    @Column(name = "address")
    private String address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bank")
    private Collection<JavaPersonsBanks> javaPersonsBanksCollection;

    public JavaBanks() {
    }

    public JavaBanks(Integer id) {
        this.id = id;
    }

    public JavaBanks(Integer id, String name, String codename, String address) {
        this.id = id;
        this.name = name;
        this.codename = codename;
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

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
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
        if (!(object instanceof JavaBanks)) {
            return false;
        }
        JavaBanks other = (JavaBanks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbModel.JavaBanks[ id=" + id + " ]";
    }
    
}
