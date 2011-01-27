/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2011 Ricardo Mariaca
 * http://dynamicreports.sourceforge.net
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.assembly;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import net.sf.dynamicreports.Project;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author Ricardo Mariaca (dynamicreports@gmail.com)
 */
public class GenerateClasspath {
	
	public static void main(String[] args) throws Exception {
		Project project = new Project();
		Configuration cfg = new Configuration();		
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template temp = cfg.getTemplate("src/assemblies/eclipse_classpath.ftl");
		Map<String, Object> root = new HashMap<String, Object>();		
		root.put("project", project);		
		Writer out = new FileWriter(project.getOutputDirectory() + "/eclipse_classpath");
		temp.process(root, out);
		out.flush();
		
		project = new Project();
		cfg = new Configuration();		
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		temp = cfg.getTemplate("src/assemblies/netbeans_classpath.ftl");
		root = new HashMap<String, Object>();		
		root.put("project", project);		
		out = new FileWriter(project.getOutputDirectory() + "/netbeans_classpath");
		temp.process(root, out);
		out.flush();
	}
} 
