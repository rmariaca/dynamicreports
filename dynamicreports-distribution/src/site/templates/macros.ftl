<#macro java_code>
<pre class="brush:java">
  <#nested>
 
</pre>
</#macro>

<#macro xml_code>
<pre class="brush:xml">
  <#nested>
 
</pre>
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
<h4 style="text-align:center">${id}</h4>
</#if>
<table style="margin-left:auto;margin-right:auto">
	<tr>
		<td style="border:none">
			<a rel="image" href="${page.examples}${id?lower_case}.png" title="${id}"><img src="${page.examples}${id?lower_case}_m.png" alt="${id}"/></a>
		</td>
		<td style="border:none">
			<a href="${page.examples}${id?lower_case}${file_ext}.${file}" title="${file} preview"><img src="${page.path}images/site/${file}.png" alt="${file}" width="16" height="16"/></a>
			<a rel="image" href="${page.examples}${id?lower_case}.png"><img src="${page.path}images/site/preview.png" alt="preview"/></a>
		</td>
		<#if source_code == true>
		<td style="border:none">
			<a href="${page.examples}${id?lower_case}" title="example source code">${id}.java</a>
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
			<a href="${page.examples}${previous?lower_case}"><img src="${page.path}images/site/left_arrow.png" alt="previous" width="48" height="48"/></a>
		</td>
		<td>
			<a href="${page.examples}${previous?lower_case}">${previous}</a>
		</td>
		</#if>			
		<td>
			<a href="${page.examples}examples">All examples</a>
		</td>
		<#if next!="">
		<td>
			<a href="${page.examples}${next?lower_case}">${next}</a>
		</td>
		<td>
			<a href="${page.examples}${next?lower_case}"><img src="${page.path}images/site/right_arrow.png" alt="next" width="48" height="48"/></a>
		</td>
		</#if>
	</tr>
</table>
</#macro>

<#macro example_link id table=true>
<#if table == true>
<td style="text-align:center;border:none">
</#if>
	<a href="${page.examples}${id?lower_case}">${id}</a>
<#if table == true>
</td>
</#if>
</#macro>

<#macro example_preview id file="pdf" file_ext="">
<td style="text-align:center;border:none">
	<a rel="image" href="${page.examples}${id?lower_case}.png" title="${id}"><img src="${page.examples}${id?lower_case}_s.png" alt="${id}"/></a>
	<a href="${page.examples}${id?lower_case}${file_ext}.${file}" title="${file} preview"><img src="${page.path}images/site/${file}.png" alt="${file}" width="16" height="16"/></a>
	<a rel="image" href="${page.examples}${id?lower_case}.png"><img src="${page.path}images/site/preview.png" alt="${id}"/></a>
</td>
</#macro>