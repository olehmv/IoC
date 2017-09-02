package ioc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;

public class SettetIoC {

	public static Object buildWithSetters(String name, Class<?> c, Map<String, Object> ctx) {
		try {
			Object component = c.newInstance();
			Method[] methods = c.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method m = methods[i];
				String mname = m.getName();
				Class[] types = m.getParameterTypes();
				if (mname.startsWith("set") && types.length == 1 && m.getReturnType() == Void.TYPE) {
					String dname = mname.substring(3);
					m.invoke(component, new Object[] { ctx.get(dname.toLowerCase()) });
				}
			}
			ctx.put(name, component);
			return component;
		} catch (Exception ex) {
			String msg = "Initialization error";
			throw new RuntimeException(msg, ex);
		}
	}

	public static Object buildWithConstructor0(String name, Class<?> c, Map<String, Object> ctx) {
		try {
			Constructor[] constructors = c.getDeclaredConstructors();
			assert constructors.length != 1 : "Component must have single constructor";
			Constructor cc = constructors[0];
			Class[] types = cc.getParameterTypes();
			Object[] params = new Object[types.length];
			for (int i = 0; i < names.length; i++) {
				params[i] = context.get(types[i]);
			}
			Object component = cc.newInstance(params);
			;
			ctx.put(name, component);
			return component;
		} catch (Exception ex) {
			String msg = "Initialization error";
			throw new RuntimeException(msg, ex);
		}
	}

	public static Object buildWithConstructor1(String name, Class<?> c, Map<String, Object> ctx) {
		try {
			Constructor[] constructors = c.getDeclaredConstructors();
			assert constructors.length != 1 : "Component must have single constructor";
			Constructor<?> cc = constructors[0];
			Class[] types = cc.getParameterTypes();
			Annotation[][] anns = cc.getParameterAnnotations();
			String[] names = new String[types.length];
			for (int i = 0; i < anns.length; i++) {
				Annotation[] ann = anns[i];
				for (int j = 0; j < ann.length; j++) {
					if (ann[j] instanceof Ref) {
						String[] v = ((Ref) ann[j]).value();
						names[i] = v[0];
					}
				}
			}
			Object[] params = new Object[types.length];
			for (int i = 0; i < types.length; i++) {
				params[i] = ctx.get(names[i]);
			}
			Object component = cc.newInstance(params);
			;
			ctx.put(name, component);
			return component;
		} catch (Exception ex) {
			String msg = "Initialization error";
			throw new RuntimeException(msg, ex);
		}
	}
}
