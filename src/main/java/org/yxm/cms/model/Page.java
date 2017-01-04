package org.yxm.cms.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by yxm on 2017.01.03.
 */
@Entity
@Table(name = "t_page")
public class Page {
  private Integer id;
  private String title;
  private String description;
  private Boolean status; //是否可见
  private PageContentType contentType;
  private Boolean inTopMenu;
  private Boolean inLeftMenu;
  private Integer order;
  private Page parent;
  private RichText richText;
  private Link link;

  public Page() {
  }

  public Page(String title, String description, Boolean status, PageContentType contentType,
              Boolean inTopMenu, Boolean inLeftMenu, Integer order, Page parent) {
    this.title = title;
    this.description = description;
    this.status = status;
    this.contentType = contentType;
    this.inTopMenu = inTopMenu;
    this.inLeftMenu = inLeftMenu;
    this.order = order;
    this.parent = parent;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getStatus() {
    return status;
  }

  public void setStatus(Boolean status) {
    this.status = status;
  }

  @Column(name = "content_type")
  @Enumerated(EnumType.STRING)
  public PageContentType getContentType() {
    return contentType;
  }

  public void setContentType(PageContentType contentType) {
    this.contentType = contentType;
  }

  public Boolean getInTopMenu() {
    return inTopMenu;
  }

  public void setInTopMenu(Boolean inTopMenu) {
    this.inTopMenu = inTopMenu;
  }

  public Boolean getInLeftMenu() {
    return inLeftMenu;
  }

  public void setInLeftMenu(Boolean inLeftMenu) {
    this.inLeftMenu = inLeftMenu;
  }

  @Column(name = "_order")
  public Integer getOrder() {
    return order;
  }

  public void setOrder(Integer order) {
    this.order = order;
  }

  @ManyToOne
  @JoinColumn(name = "pid")
  public Page getParent() {
    return parent;
  }

  public void setParent(Page parent) {
    this.parent = parent;
  }

  @OneToOne
  @JoinColumn(name = "richtext_id")
  public RichText getRichText() {
    return richText;
  }

  public void setRichText(RichText richText) {
    this.richText = richText;
  }

  @OneToOne
  @JoinColumn(name = "link_id")
  public Link getLink() {
    return link;
  }

  public void setLink(Link link) {
    this.link = link;
  }
}
