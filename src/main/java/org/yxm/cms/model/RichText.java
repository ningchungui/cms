package org.yxm.cms.model;

import javax.persistence.*;

/**
 * Created by yxm on 2017.01.04.
 */
@Entity
@Table(name = "t_richtext")
public class RichText {
  private Integer id;
  private String content;
  private Page page;

  public RichText() {
  }

  @Id
  @GeneratedValue
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @OneToOne
  @JoinColumn(name = "page_id")
  public Page getPage() {
    return page;
  }

  public void setPage(Page page) {
    this.page = page;
  }
}
