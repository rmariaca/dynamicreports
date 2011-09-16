package net.sf.dynamicreports.report.components;

import java.util.List;

import net.sf.jasperreports.extensions.ExtensionsEnvironment;

public class CustomComponents {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static CustomComponentTransform<?, ?> getComponentTransform(Object component) {
		List<CustomComponentTransform> transforms = (List<CustomComponentTransform>) ExtensionsEnvironment.getExtensionsRegistry().getExtensions(CustomComponentTransform.class);
		for (CustomComponentTransform transform : transforms) {
			if (transform.isTransform(component)) {
				return transform;
			}
		}
		return null;
	}
}
