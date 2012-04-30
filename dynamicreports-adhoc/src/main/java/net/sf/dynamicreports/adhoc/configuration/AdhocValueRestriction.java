package net.sf.dynamicreports.adhoc.configuration;

import java.util.ArrayList;
import java.util.List;

public class AdhocValueRestriction extends AdhocRestriction {
	private String name;
	private AdhocValueOperator operator;
	private List<String> values;

	public AdhocValueRestriction() {
		values = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AdhocValueOperator getOperator() {
		return operator;
	}

	public void setOperator(AdhocValueOperator operator) {
		this.operator = operator;
	}

	public List<String> getValues() {
		return values;
	}

	public void addValue(String value) {
		this.values.add(value);
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

}
