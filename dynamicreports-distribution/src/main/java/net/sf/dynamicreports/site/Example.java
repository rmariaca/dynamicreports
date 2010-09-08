package net.sf.dynamicreports.site;

public class Example {
	private String name;
	private String path;
	private Class<? extends Object> design;
	
	public Example(String name, String path, Class<? extends Object> design) {
		this.name = name;
		this.path = path;
		this.design = design;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPath() {
		return path;
	}
	
	public Class<? extends Object> getDesign() {
		return design;
	}
}
