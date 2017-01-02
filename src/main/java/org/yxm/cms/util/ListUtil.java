package org.yxm.cms.util;

import java.util.List;

/**
 * Created by yxm on 2016.12.31.
 */
public class ListUtil {

  public static Integer[] list2array(List<Integer> list) {
    Integer[] arr = new Integer[list.size()];
    int i = 0;
    for (Integer item : list) {
      arr[i] = list.get(i);
      i++;
    }
    return arr;
  }
}
