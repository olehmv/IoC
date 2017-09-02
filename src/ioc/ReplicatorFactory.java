package ioc;

public interface ReplicatorFactory {
	@Ref(mappings = { @Mapping(param = "input", ref = "source1"), @Mapping(param = "output", ref = "source2") })
	Replicator getForwardReplicator();

	@Ref(mappings = { @Mapping(param = "input", ref = "source1"), @Mapping(param = "output", ref = "source2") })
	Replicator getBackwardReplicator();
}
