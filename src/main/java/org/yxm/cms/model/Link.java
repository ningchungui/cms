package org.yxm.cms.model;

import com.sun.javafx.font.PrismFontFactory;

import javax.persistence.*;

/**
 * Created by yxm on 2017.01.04.
 */
@Entity
@Table(name = "t_link")
public class Link {

  private Integer id;
  private String title;
  private String url;
  private Page page;

  public Link() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
