<#include "macros.ftl">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<#include "head.ftl">

<body>

<#if page.sideBar == true>	
<div id="wrap">
<#else>	
<div id="wrap" class="code">
</#if>
	
	<#include "header.ftl">
		
	<#include "menu.ftl">					
			
	<div id="content-wrap">
			
		<#if page.sideBar == true>	
			<#include "sidebar.ftl">

			<div id="main">
		<#else>	
			<div id="main" class="code">
		</#if>	
			<h2>${page.title}</h2>
			<#include page.content>

			</div>
		
	</div>

	<#include "footer.ftl">					

</div>

<#include "scripts.ftl">

</body>
</html>