package net.sf.dynamicreports.site;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.examples.complex.AbstractReportMain;
import net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExamplesAspect {	
	private static String name = "";
		
	@Around("show()")
	public JasperReportBuilder show(ProceedingJoinPoint pjp) throws Throwable {
		JasperReportBuilder reportBuilder = (JasperReportBuilder) pjp.getTarget();
		GenerateSite.generateExampleImage(name, reportBuilder);
		return reportBuilder;
	}
	
	@Pointcut("execution(public net.sf.dynamicreports.jasper.builder.JasperReportBuilder net.sf.dynamicreports.jasper.builder.JasperReportBuilder.show())")
	public void show() {
	}
	
	@Around("build()")
	public void build(ProceedingJoinPoint pjp) throws Throwable {
		Class<? extends Object> design = pjp.getTarget().getClass();
		name = design.getSimpleName().replaceAll("Main", "");
		if (pjp.getTarget() instanceof AbstractReportMain<?, ?>) {
			design = Class.forName(design.getName().replaceAll("Main", "Design"));
		}
		String path = design.getName().substring(Templates.class.getPackage().getName().length() + 1).split("\\.")[0];
		GenerateSite.addExample(name, path, design);
		pjp.proceed();
	}
	
	@Pointcut("execution(void net.sf.dynamicreports.examples..build())")
	public void build() {
	}
	
	@Around("toPdf()")
	public JasperReportBuilder toPdf(ProceedingJoinPoint pjp) throws Throwable {
		JasperReportBuilder reportBuilder = (JasperReportBuilder) pjp.getTarget();
		GenerateSite.generateExampleImage(name, reportBuilder, (JasperPdfExporterBuilder) pjp.getArgs()[0]);
		return reportBuilder;
	}
	
	@Pointcut("execution(public net.sf.dynamicreports.jasper.builder.JasperReportBuilder net.sf.dynamicreports.jasper.builder.JasperReportBuilder.toPdf(*))")
	public void toPdf() {
	}
	
	@Around("toConcatenatedPdf()")
	public JasperConcatenatedReportBuilder toConcatenatedPdf(ProceedingJoinPoint pjp) throws Throwable {
		JasperConcatenatedReportBuilder reportBuilder = (JasperConcatenatedReportBuilder) pjp.getTarget();
		GenerateSite.generateExampleImage(name, reportBuilder, (JasperPdfExporterBuilder) pjp.getArgs()[0]);
		return reportBuilder;
	}
	
	@Pointcut("execution(public net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder net.sf.dynamicreports.jasper.builder.JasperConcatenatedReportBuilder.toPdf(*))")
	public void toConcatenatedPdf() {
	}
}
