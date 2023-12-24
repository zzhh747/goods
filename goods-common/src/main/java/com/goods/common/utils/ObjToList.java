package com.goods.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * projectName:com.goods.common.utils
 *
 * @author:张昊
 * @time:2023/12/23 17:22
 * description:.
 */
public class ObjToList {
    public static <T> List<T> objToList(Object obj, Class<T> cla) {
        List<T> list = new ArrayList<T>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                list.add(cla.cast(o));
            }
            return list;
        }
        return null;
    }
}
