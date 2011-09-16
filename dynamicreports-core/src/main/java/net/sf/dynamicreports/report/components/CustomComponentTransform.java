package net.sf.dynamicreports.report.components;

import net.sf.dynamicreports.design.base.DRDesignGroup;
import net.sf.dynamicreports.design.constant.ResetType;
import net.sf.dynamicreports.design.definition.component.DRIDesignComponent;
import net.sf.dynamicreports.design.transformation.DesignTransformAccessor;
import net.sf.dynamicreports.jasper.transformation.JasperTransformAccessor;
import net.sf.dynamicreports.report.definition.component.DRIComponent;
import net.sf.jasperreports.engine.JRComponentElement;

public interface CustomComponentTransform<T extends DRIComponent, U extends DRIDesignComponent> {

	public boolean isTransform(Object component);

	public U designComponent(DesignTransformAccessor accessor, T component, ResetType resetType, DRDesignGroup resetGroup);

	public JRComponentElement jasperComponent(JasperTransformAccessor accessor, U component);

}
