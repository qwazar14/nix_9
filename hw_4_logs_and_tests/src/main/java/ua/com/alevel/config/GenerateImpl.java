package ua.com.alevel.config;

import org.reflections.Reflections;

import java.util.Set;

public class GenerateImpl {

    private static final Reflections scan = new Reflections("ua.com.alevel");

    public static <IFC> IFC generateImplementation(Class<IFC> ifc) {
        if (ifc.isInterface()) {
            Set<Class<? extends IFC>> implementations = scan.getSubTypesOf(ifc);
            for (Class<? extends IFC> implementation : implementations) {
                if (implementation.isAnnotationPresent(ActiveClass.class)) {
                    try {
                        return implementation.getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }
}