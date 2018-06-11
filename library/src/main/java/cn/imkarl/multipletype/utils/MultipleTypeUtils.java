package cn.imkarl.multipletype.utils;

import android.support.annotation.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * MultipleType相关工具类
 * @author imkarl
 */
public class MultipleTypeUtils {

    @NonNull
    public static <E> Class<E> findActualTypeArgumentsBySuperclass(@NonNull final Class adapterItemClass) {
        Type superClass = adapterItemClass.getGenericSuperclass();
        while (superClass instanceof Class
                && !((Class)superClass).isInstance(adapterItemClass)) {
            superClass = ((Class)superClass).getGenericSuperclass();
        }
        //noinspection ConstantConditions
        final ParameterizedType parameterized = (ParameterizedType) superClass;
        final Type type = GsonTypes.canonicalize(parameterized.getActualTypeArguments()[0]);
        //noinspection unchecked
        Class<E> typeClass = (Class<E>) GsonTypes.getRawType(type);
        if (typeClass == null) {
            throw new IllegalArgumentException("Unknown type: "+adapterItemClass);
        }
        return typeClass;
    }

}
