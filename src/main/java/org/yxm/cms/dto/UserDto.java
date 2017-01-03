package org.yxm.cms.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.yxm.cms.model.User;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by yxm on 2016.12.30.
 */
public class UserDto {
  private Integer id;
  @NotEmpty
  private String username;
  @NotEmpty
  private String password;
  @NotEmpty
  @Email
  private String email;
  @NotNull
  private Boolean sex;
  @NotNull
  private Boolean isAdmin;
  private Date createDate;

  private Integer[] pids;
  private Integer[] gids;

  public UserDto() {
  }

  public UserDto(User user, Integer[] pids, Integer[] gids) {
    this.setId(user.getId());
    this.setUsername(user.getUsername());
    this.setPassword(user.getPassword());
    this.setEmail(user.getEmail());
    this.setSex(user.getSex());
    this.setAdmin(user.getAdmin());
    this.setCreateDate(user.getCreateDate());

    this.setPids(pids);
    this.setGids(gids);
  }

  public User getUser() {
    User user = new User(id, username, password, email, sex, isAdmin, createDate);
    return user;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getSex() {
    return sex;
  }

  public void setSex(Boolean sex) {
    this.sex = sex;
  }

  public Boolean getAdmin() {
    return isAdmin;
  }

  public void setAdmin(Boolean admin) {
    isAdmin = admin;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Integer[] getPids() {
    return pids;
  }

  public void setPids(Integer[] pids) {
    this.pids = pids;
  }

  public Integer[] getGids() {
    return gids;
  }

  public void setGids(Integer[] gids) {
    this.gids = gids;
  }
}
