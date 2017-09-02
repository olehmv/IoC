package ioc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public final class AutoWireInvocationHandler implements InvocationHandler {
	private Map<String, Object> services = new HashMap<String, Object>();

	public AutoWireInvocationHandler(Class c, Map<String, Object> ctx) throws Exception {
		Method[] methods = c.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			Ref ref = m.getAnnotation(Ref.class);
			if (ref != null) {
				services.put(m.getName(), createInstance(m.getReturnType(), ref.mappings(), ctx));
			}
		}
	}

	private Object createInstance(Class<?> type, Mapping[] mappings, Map<String, Object> ctx) {
		Map<String, Object> childCtx = new HashMap<String, Object>();
		for (int i = 0; i < mappings.length; i++) {
			Mapping m = mappings[i];
			childCtx.put(m.param(), ctx.get(m.ref()));
		}
		return Builder.buildWithConstructor(type, childCtx);
	}

	public Object invoke(Object proxy, Method m, Object[] args) {
		return services.get(m.getName());
	}
}