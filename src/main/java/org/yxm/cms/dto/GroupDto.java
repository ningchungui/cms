package org.yxm.cms.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.yxm.cms.model.Group;

/**
 * Created by yxm on 2016.12.30.
 */
public class GroupDto {

  private Integer id;
  @NotEmpty
  private String name;
  @NotEmpty
  private String description;
  private Integer[] pids;

  public GroupDto() {
  }

  public GroupDto(Group group, Integer[] pids) {
    this.setId(group.getId());
    this.setName(group.getName());
    this.setDescription(group.getDescription());
    this.setPids(pids);
  }

  public Group getGroup() {
    return new Group(this.getId(), this.getName(), this.getDescription());
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer[] getPids() {
    return pids;
  }

  public void setPids(Integer[] pids) {
    this.pids = pids;
  }
}
