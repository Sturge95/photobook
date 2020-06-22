package com.sturgeon.photobook.bo;

import javax.persistence.*;
import java.util.List;

@Table(schema = "content", name = "image")
@Entity
public class Image {

    @Id
    @GeneratedValue(generator = "content.image_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "image_id", schema = "content", sequenceName = "image_id", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    private String fileName;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "content", name = "image_category")
    private List<Category> categories;

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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
