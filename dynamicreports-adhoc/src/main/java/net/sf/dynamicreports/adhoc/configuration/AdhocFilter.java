package net.sf.dynamicreports.adhoc.configuration;

import java.util.ArrayList;
import java.util.List;

public class AdhocFilter {
	private List<AdhocRestriction> restrictions;

	public AdhocFilter() {
		restrictions = new ArrayList<AdhocRestriction>();
	}

	public List<AdhocRestriction> getRestrictions() {
		return restrictions;
	}

	public void addRestriction(AdhocRestriction restriction) {
		this.restrictions.add(restriction);
	}

	public void setRestrictions(List<AdhocRestriction> restrictions) {
		this.restrictions = restrictions;
	}

}
