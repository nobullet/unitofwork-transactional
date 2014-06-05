package models;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * User entity.
 */
@Entity
@Table(name = "users", indexes = {
    @Index(name = "nameVisitedIndex", columnList = "name,visited")
})
@NamedQueries({
    @NamedQuery(name = "User.byName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.all", query = "SELECT u FROM User u ORDER BY name")
})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(length = 255, nullable = false, updatable = false)
    String name;

    @Column(nullable = false, updatable = true)
    long visited;

    public User() {
    }

    public User(String name) {
        Preconditions.checkNotNull(name);
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVisited() {
        return visited;
    }

    public void setVisited(long visited) {
        this.visited = visited;
    }
    
    public void visit() {
        visited++;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return this.id == other.id;
    }
}
