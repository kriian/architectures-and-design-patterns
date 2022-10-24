package ru.hehnev.handler;

import ru.hehnev.serializer.ResponseSerializer;
import ru.hehnev.service.SocketService;

import java.lang.reflect.Constructor;
import java.util.List;

public class AnnotatedHandlerFactory {

    public static MethodHandler create(
            SocketService socketService,
            ResponseSerializer responseSerializer,
            List<Class<?>> classes) {

        MethodHandler prev = null;
        try {
            for (Class<?> clazz : classes) {
                Constructor<?> constructor = clazz.getConstructor(
                        String.class,
                        MethodHandler.class,
                        SocketService.class,
                        ResponseSerializer.class);
                prev = (MethodHandler) constructor.newInstance(
                        getMethod(clazz),
                        prev,
                        socketService,
                        responseSerializer);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prev;
    }

    private static String getMethod(Class<?> clazz) {
        return clazz.getAnnotation(Handler.class).method();
    }


}
