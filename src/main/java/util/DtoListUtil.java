package util;

import dto.LogDto;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DtoListUtil {
    public static List transelateList(List sourceList, Class dtoClass){
        List list = new ArrayList();
        Class sourceClass = sourceList.get(0).getClass();
        try {
            for(int i=0;i<sourceList.size();i++){
                Constructor constructor = dtoClass.getConstructor(sourceClass);
                 list.add(constructor.newInstance(sourceList.get(i)));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //注意暂时只能转换dto中属于原始类型的属性
    public static Object transelateDto(Object object,Object objectDTO)
    {
        Class sourceClass = object.getClass();
        Class dtoClass = objectDTO.getClass();

        Field[] sourceFields = sourceClass.getDeclaredFields();
        Method[] sourceMethods = sourceClass.getDeclaredMethods();

        Field[] dtoFields = dtoClass.getDeclaredFields();
        Method[] dtoMethods = dtoClass.getDeclaredMethods();

        for(Field sourceField:sourceFields){
            PropertyDescriptor sourcePD;
            PropertyDescriptor dtoPD;
            Method fieldGetMet;
            Method fieldSetMet;

            try {
                if(sourceField.getClass().isPrimitive()){
                    for(Field dtoField:sourceFields){
                        if(dtoField.equals(sourceField)){
                            sourcePD = new PropertyDescriptor(sourceField.getName(), sourceClass);
                            dtoPD = new PropertyDescriptor(dtoField.getName(), sourceClass);
                            fieldGetMet = sourcePD.getReadMethod();
                            fieldSetMet = dtoPD.getWriteMethod();
                            fieldSetMet.invoke(object,fieldGetMet.invoke(objectDTO, new Object[]{}));
                        }
                    }
                }

            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        return objectDTO;
    }
}
