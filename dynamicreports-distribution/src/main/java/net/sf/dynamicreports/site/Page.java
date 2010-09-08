package net.sf.dynamicreports.site;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class Page {
	private String version;
	private Date date;
	private String path;
	private String examples;
	private boolean sideBar;
	private String content;
	private boolean hasCode;
	private Set<String> codeClasses;
	private boolean hasImage;
	private boolean hasImageGroup;
	
	public Page(String name, String pageContent) throws Exception {		
		init();
		setPage(name, pageContent);
	}
	
	private void init() throws Exception {		
		version = System.getenv("version");
		date = new Date();
		path = "";
		examples = "examples/";
		sideBar = true;
		hasCode = false;
		codeClasses = new LinkedHashSet<String>();
		hasImage = false;
		hasImageGroup = false;
	}
	
	private void setPage(String name, String pageContent) throws Exception {
    if (pageContent.indexOf("<@java_code>") != -1) {
    	codeClasses.add("Java");
    }
    if (pageContent.indexOf("<@xml_code>") != -1) {
    	codeClasses.add("Xml");
    }
    hasCode = !codeClasses.isEmpty();
    hasImage = pageContent.indexOf("<@example") != -1;
    hasImageGroup = pageContent.indexOf("<@image_group") != -1;	
    content = "/" + name;
	}
	
	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getExamples() {
		return examples;
	}
	
	public void setExamples(String examples) {
		this.examples = examples;
	}
	
	public boolean isSideBar() {
		return sideBar;
	}

	public void setSideBar(boolean sideBar) {
		this.sideBar = sideBar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isHasCode() {
		return hasCode;
	}

	public void setHasCode(boolean hasCode) {
		this.hasCode = hasCode;
	}

	public Set<String> getCodeClasses() {
		return codeClasses;
	}

	public void setCodeClasses(Set<String> codeClasses) {
		this.codeClasses = codeClasses;
	}

	public boolean isHasImage() {
		return hasImage;
	}

	public void setHasImage(boolean hasImage) {
		this.hasImage = hasImage;
	}

	public boolean isHasImageGroup() {
		return hasImageGroup;
	}

	public void setHasImageGroup(boolean hasImageGroup) {
		this.hasImageGroup = hasImageGroup;
	}	
}
