package org.yxm.cms.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by yxm on 2016.12.28.
 */
@Entity
@Table(name = "t_user")
public class User {

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

  public User() {
  }

  public User(Integer id, String username, String password, String email, Boolean sex, Boolean isAdmin, Date createDate) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.sex = sex;
    this.isAdmin = isAdmin;
    this.createDate = createDate;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
