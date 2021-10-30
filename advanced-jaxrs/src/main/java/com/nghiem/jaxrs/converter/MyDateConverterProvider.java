package com.nghiem.jaxrs.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Calendar;

import com.nghiem.jaxrs.entity.MyDate;

import jakarta.ws.rs.ext.ParamConverter;
import jakarta.ws.rs.ext.ParamConverterProvider;
import jakarta.ws.rs.ext.Provider;

@Provider
public class MyDateConverterProvider implements ParamConverterProvider {

    @Override
    public <T> ParamConverter<T> getConverter(Class<T> rawType, Type genericType, Annotation[] annotations) {
        if (rawType.getName().equals(MyDate.class.getName())) {
            return new ParamConverter<T>() {

                @Override
                public T fromString(String dateStr) {
                    Calendar calendar = Calendar.getInstance();
                    switch (dateStr) {
                    case "tomorrow":
                        calendar.add(Calendar.DATE, 1);
                        break;
                    case "yesterday":
                        calendar.add(Calendar.DATE, -1);
                        break;
                    default:
                            
                    }
                    MyDate date = new MyDate(calendar.get(Calendar.DATE), calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
                    return rawType.cast(date);
                }

                @Override
                public String toString(T value) {
                    return value.toString();
                }
            };
        }
        return null;
    }
}
