package org.test.domain;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = DomainStr.CATEGORY)
@SuppressWarnings(DomainStr.UNUSED)
public class Category extends Domain {

    private static Log logger = LogFactory.getLog(Category.class);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = DomainStr.ID, columnDefinition = DomainStr.BIGINT$10$_UNSIGNED, unique = true, nullable = false)
	private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min=5, max=100)
    @Column(name = DomainStr.NAME, columnDefinition = DomainStr.VARCHAR$100$, unique = true, nullable = false)
	private String name;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    @NotNull
    @Valid
    @Embedded
    private Record record = new Record();
    public Record getRecord() {
        return record;
    }
    public void setRecord(Record record) {
        this.record = record;
    }

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
	private Set<Subcategory> subcategories = new HashSet<>();
    public Set<Subcategory> getSubcategories() {
        return this.subcategories;
    }
    public void setSubcategories(Set<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }

    public Category() {}

	public Category(String name) {
        logger.info(DomainStr.CREATING + Category.class);
		this.name = (name == null)? null : name.toUpperCase();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        return !(name != null ? !name.equals(category.name) : category.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", record=" + record +
                '}';
    }
}
