package ioc;

import javax.sql.DataSource;

public class ReplicatorRef {
	private final DataSource input;
	private final DataSource output;

	ReplicatorRef(@Ref("input") DataSource input, @Ref("output") DataSource output) {
		this.input = input;
		this.output = output;
	}
} // ...
