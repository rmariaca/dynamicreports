package net.sf.dynamicreports.test.design;

import net.sf.dynamicreports.report.builder.ReportBuilder;

public class TestReportBuilder extends ReportBuilder<TestReportBuilder> {
	private static final long serialVersionUID = 1L;

	public TestReportBuilder() {
		getObject().setTemplateDesign(new TestTemplateDesign());
	}
}
