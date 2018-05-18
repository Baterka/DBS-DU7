package dbModel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author David Bubeník (bubenda1)
 */
@Entity
@Table(name = "java_persons_banks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JavaPersonsBanks.findAll", query = "SELECT j FROM JavaPersonsBanks j")
    , @NamedQuery(name = "JavaPersonsBanks.findById", query = "SELECT j FROM JavaPersonsBanks j WHERE j.id = :id")
    , @NamedQuery(name = "JavaPersonsBanks.findByAssigned", query = "SELECT j FROM JavaPersonsBanks j WHERE j.assigned = :assigned")})
public class JavaPersonsBanks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "assigned")
    @Temporal(TemporalType.DATE)
    private Date assigned;
    @JoinColumn(name = "bank", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JavaBanks bank;
    @JoinColumn(name = "person", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private JavaPersons person;

    public JavaPersonsBanks() {
    }

    public JavaPersonsBanks(Integer id) {
        this.id = id;
    }

    public JavaPersonsBanks(Integer id, Date assigned) {
        this.id = id;
        this.assigned = assigned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAssigned() {
        return assigned;
    }

    public void setAssigned(Date assigned) {
        this.assigned = assigned;
    }

    public JavaBanks getBank() {
        return bank;
    }

    public void setBank(JavaBanks bank) {
        this.bank = bank;
    }

    public JavaPersons getPerson() {
        return person;
    }

    public void setPerson(JavaPersons person) {
        this.person = person;
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
        if (!(object instanceof JavaPersonsBanks)) {
            return false;
        }
        JavaPersonsBanks other = (JavaPersonsBanks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbModel.JavaPersonsBanks[ id=" + id + " ]";
    }
    
}
