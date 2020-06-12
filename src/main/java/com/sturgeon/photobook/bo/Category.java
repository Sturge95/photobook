package com.sturgeon.photobook.bo;

import javax.persistence.*;

@Entity
@Table(schema = "content", name = "category")
public class Category {

    @Id
    @GeneratedValue(generator = "content.category_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "category_id", schema = "content", sequenceName = "content.category_id", allocationSize = 1)
    private Long id;
    private String name;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
