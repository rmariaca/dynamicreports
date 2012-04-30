package net.sf.dynamicreports.adhoc.configuration;

public class AdhocConfiguration {
	private AdhocReport report;
	private AdhocFilter filter;

	public AdhocReport getReport() {
		return report;
	}

	public void setReport(AdhocReport report) {
		this.report = report;
	}

	public AdhocFilter getFilter() {
		return filter;
	}

	public void setFilter(AdhocFilter filter) {
		this.filter = filter;
	}

}
