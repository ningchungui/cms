package org.yxm.cms.model;

/**
 * Created by yxm on 2017.01.03.
 */
public enum PageContentType {
  RICH_TEXT("richtext"), LINK("link"), GALLERY("gallery"), FORM("formtext");

  private String name;

  PageContentType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
