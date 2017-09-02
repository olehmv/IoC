package ioc;

import javax.sql.DataSource;

public class Replicator {
	private DataSource input;
	private DataSource output;


	public void setInput(DataSource input) {
		this.input = input;
	}

	public void setOutput(DataSource output) {
		this.output = output;
	}

	public void replicate() {
	}// ... } }
}