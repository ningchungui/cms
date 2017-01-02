package org.yxm.cms.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by yxm on 2016.12.29.
 */
@Entity
@Table(name = "t_permission")
public class Permission {

    private Integer id;
    @NotEmpty
    private String name;
    private String description;
    @NotEmpty
    private String url;

    public Permission() {
    }

    public Permission(String name, String url, String description) {
        this.name = name;
        this.url = url;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
