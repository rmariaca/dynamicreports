<#macro java_code>
<textarea name="code" class="java">
  <#nested>
</textarea>
</#macro>
<#macro xml_code>
<textarea name="code" class="xml">
  <#nested>
</textarea>
</#macro>
<#macro image_group id title>
<table>
	<tbody>
		<tr>
			<td>
				<center><b>${title}</b></center>
			</td>
		</tr>
		<tr>
			<td>
				<a rel="image_group" href="${page.examples}${id}.png" title="${title}"><img src="${page.examples}${id}_m.png"/></a>
			</td>
		</tr>
	</tbody>
</table>
</#macro>
<#macro example id title=false source_code=true>
<center>
<#if title == true>
<h3>${id}</h3>
</#if>
<table>
	<tbody>
		<tr>
			<td>
				<a id="image" href="${page.examples}${id?lower_case}.png" title="${id}"><img src="${page.examples}${id?lower_case}_m.png"/></a>
			</td>
			<td>
				<a href="${page.examples}${id?lower_case}.pdf" title="pdf preview"><img class="preview" src="${page.path}images/acrobat.png"/></a>
				<a id="image" href="${page.examples}${id?lower_case}.png"><img class="preview" src="${page.path}images/preview.png"/></a>
			</td>
			<#if source_code == true>
			<td>
				<a href="${page.examples}${id?lower_case}.html" title="example source code">${id}.java</a>
			</td>
			</#if>
		</tr>
	</tbody>
</table>
</center>
</#macro>
<#macro examples previous next>
<center>
<table>
	<tbody>
		<tr>
			<#if previous!="">
			<td>
				<a href="${page.examples}${previous?lower_case}.html"><img class="preview" align="middle" src="${page.path}images/left_arrow.png" width="48" height="48"/>${previous}</a>
			</td>
			</#if>			
			<td>
				<a href="${page.examples}examples.html">All examples</a>
			</td>
			<#if next!="">
			<td>
				<a href="${page.examples}${next?lower_case}.html">${next}<img class="preview" align="middle" src="${page.path}images/right_arrow.png" width="48" height="48"/></a>
			</td>
			</#if>
		</tr>
	</tbody>
</table>
</center>
</#macro>
<#macro report_builder title="ReportBuilder" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/ReportBuilder.html${method}">${title}</a></#macro>
<#macro jasper_report_builder title="JasperReportBuilder" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/jasper/builder/JasperReportBuilder.html${method}">${title}</a></#macro>
<#macro dynamic_reports title="DynamicReports" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/DynamicReports.html${method}">${title}</a></#macro>
<#macro column_builders title="ColumnBuilders" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/column/ColumnBuilders.html${method}">${title}</a></#macro>
<#macro component_builders title="ComponentBuilders" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/component/ComponentBuilders.html${method}">${title}</a></#macro>
<#macro style_builders title="StyleBuilders" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/style/StyleBuilders.html${method}">${title}</a></#macro>
<#macro subtotal_builders title="SubtotalBuilders" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/subtotal/SubtotalBuilders.html${method}">${title}</a></#macro>
<#macro chart_builders title="ChartBuilders" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/chart/ChartBuilders.html${method}">${title}</a></#macro>
<#macro expression_builders title="ExpressionBuilders" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/expression/ExpressionBuilders.html${method}">${title}</a></#macro>
<#macro condition_builders title="ConditionBuilders" method="">
<a href="${page.path}apidocs/net/sf/dynamicreports/report/builder/condition/ConditionBuilders.html${method}">${title}</a></#macro>
<#macro example_link id preview=true>
<#if preview == true>
<tr>
	<td>
		<a href="${page.examples}${id?lower_case}.html">${id}</a>
	</td>
	<td>
		<center>
		<a id="image" href="${page.examples}${id?lower_case}.png" title="${id}"><img class="preview" src="${page.examples}${id?lower_case}_s.png"/></a>
		</center>
	</td>
	<td>
		<a href="${page.examples}${id?lower_case}.pdf" title="pdf preview"><img class="preview" src="${page.path}images/acrobat.png"/></a>
		<a id="image" href="${page.examples}${id?lower_case}.png"><img class="preview" src="${page.path}images/preview.png"/></a>
	</td>
</tr>
<#else>
<a href="${page.examples}${id?lower_case}.html">${id}</a>
</#if>
</#macro>