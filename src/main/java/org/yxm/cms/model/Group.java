package org.yxm.cms.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by yxm on 2016.12.29.
 */
@Entity
@Table(name = "t_group")
public class Group {

  private Integer id;
  @NotEmpty
  private String name;
  @NotEmpty
  private String description;

  public Group() {
  }

  public Group(Integer id, String name, String description) {
    this.id = id;
    this.name = name;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
