package ioc;

import java.util.HashMap;
import java.util.Map;

public class ReplicatorFactoryImpl implements ReplicatorFactory {
	private final Map<String, Object> ctx;

	public ReplicatorFactoryImpl(Map<String, Object> ctx) {
		this.ctx = ctx;
	}

	public Replicator getForwardReplicator() {
		Map<String, Object> childCtx = new HashMap<String, Object>();
		childCtx.put("input", ctx.get("source1"));
		childCtx.put("output", ctx.get("source2"));
		return (Replicator) Builder.buildWithConstructor("forwardReplicator", Replicator.class, childCtx);
	}

	@Override
	public Replicator getBackwardReplicator() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Object buildFactory(Class c, Map<String, Object> context) {
		return Proxy.newInstance(c.getClassLoader(), new Class[] { c }, new AutoWireInvocationHandler());
	}

}