package ru.hehnev.handler;

import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScanningClassesForPresenceAnnotationHandler {


    public static List<Class<?>> scan(String packaging) {
        Reflections reflections = new Reflections(packaging);
        List<Class<?>> classes = new ArrayList<>(reflections.getTypesAnnotatedWith(Handler.class));
        classes.sort(Comparator.comparingInt(ScanningClassesForPresenceAnnotationHandler::getOrder).reversed());
        return classes;
    }

    private static int getOrder(Class<?> clazz) {
        return clazz.getAnnotation(Handler.class).order();
    }
}
