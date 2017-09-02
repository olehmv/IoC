package ioc;

import javax.sql.DataSource;

public class ReplicatorC {
	private final DataSource input;
	private final DataSource output;

	public ReplicatorC(DataSource input, DataSource output) {
		this.input = input;
		this.output = output;
	}

	public void replicate() { // ... } }
	}
}