<#macro java_code>
<textarea name="code" class="java" rows="15" cols="50">
  <#nested>
</textarea>
</#macro>
<#macro xml_code>
<textarea name="code" class="xml" rows="15" cols="50">
  <#nested>
</textarea>
</#macro>
<#macro image_group id title>
<table>
	<tr>
		<td style="text-align:center">
			<b>${title}</b>
		</td>
	</tr>
	<tr>
		<td>
			<a rel="image_group" href="${page.examples}${id}.png" title="${title}"><img src="${page.examples}${id}_m.png" alt="${title}"/></a>
		</td>
	</tr>
</table>
</#macro>
<#macro example id title=false source_code=true file="pdf" file_ext="">
<#if title == true>
<h3 style="text-align:center">${id}</h3>
</#if>
<table style="margin-left:auto;margin-right:auto">
	<tr>
		<td>
			<a rel="image" href="${page.examples}${id?lower_case}.png" title="${id}"><img src="${page.examples}${id?lower_case}_m.png" alt="${id}"/></a>
		</td>
		<td>
			<a href="${page.examples}${id?lower_case}${file_ext}.${file}" title="${file} preview"><img class="preview" src="${page.path}images/${file}.png" alt="${file}" width="16" height="16"/></a>
			<a rel="image" href="${page.examples}${id?lower_case}.png"><img class="preview" src="${page.path}images/preview.png" alt="preview"/></a>
		</td>
		<#if source_code == true>
		<td>
			<a href="${page.examples}${id?lower_case}.html" title="example source code">${id}.java</a>
		</td>
		</#if>
	</tr>
</table>
</#macro>
<#macro examples previous next>
<table style="margin-left:auto;margin-right:auto">
	<tr>
		<#if previous!="">
		<td>
			<a href="${page.examples}${previous?lower_case}.html"><img class="preview" src="${page.path}images/left_arrow.png" alt="previous" width="48" height="48"/></a>
		</td>
		<td>
			<a href="${page.examples}${previous?lower_case}.html">${previous}</a>
		</td>
		</#if>			
		<td>
			<a href="${page.examples}examples.html">All examples</a>
		</td>
		<#if next!="">
		<td>
			<a href="${page.examples}${next?lower_case}.html">${next}</a>
		</td>
		<td>
			<a href="${page.examples}${next?lower_case}.html"><img class="preview" src="${page.path}images/right_arrow.png" alt="next" width="48" height="48"/></a>
		</td>
		</#if>
	</tr>
</table>
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
<#macro example_link id table=true>
<#if table == true>
<td style="text-align:center">
</#if>
	<a href="${page.examples}${id?lower_case}.html">${id}</a>
<#if table == true>
</td>
</#if>
</#macro>
<#macro example_preview id file="pdf" file_ext="">
<td>
<table class="example" style="margin-left:auto;margin-right:auto">
	<tr>
		<td style="text-align:center">
			<a rel="image" href="${page.examples}${id?lower_case}.png" title="${id}"><img class="preview" src="${page.examples}${id?lower_case}_s.png" alt="${id}"/></a>
		</td>
		<td>
			<a href="${page.examples}${id?lower_case}${file_ext}.${file}" title="${file} preview"><img class="preview" src="${page.path}images/${file}.png" alt="${file}" width="16" height="16"/></a>
			<a rel="image" href="${page.examples}${id?lower_case}.png"><img class="preview" src="${page.path}images/preview.png" alt="${id}"/></a>
		</td>
	</tr>
</table>
</td>
</#macro>