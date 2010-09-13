<head>

<meta name="Description" content="DynamicReports is an open source Java reporting library based on JasperReports. The main benefit of this library is a dynamic report design and no need for a visual report designer." />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta name="Distribution" content="Global" />
<meta name="Robots" content="index,follow" />
<meta name="keywords" content="dynamicreports,jasperreports,dynamic jasper,dynamic report,jasperreports api,dynamic report design,dynamic report for java,runtime generation of jasperreports,dynamic reporting in Java,generate report at runtime">

<link rel="stylesheet" href="${page.path}styles/style.css" type="text/css" />
<#if page.hasCode == true>
<link type="text/css" rel="stylesheet" href="${page.path}styles/SyntaxHighlighter.css"/>
</#if>
<#if page.hasImage == true>
<link rel="stylesheet" type="text/css" href="${page.path}fancybox/jquery.fancybox-1.3.1.css" media="screen" />  

<script type="text/javascript" src="${page.path}scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${page.path}fancybox/jquery.fancybox-1.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("a#image").fancybox({
			'transitionIn'	: 'elastic',
			'transitionOut'	: 'elastic'
		});
	});
</script>
</#if>
<#if page.hasImageGroup == true>
<link rel="stylesheet" type="text/css" href="${page.path}fancybox/jquery.fancybox-1.3.1.css" media="screen" />  

<script type="text/javascript" src="${page.path}scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${page.path}fancybox/jquery.mousewheel-3.0.2.pack.js"></script>
<script type="text/javascript" src="${page.path}fancybox/jquery.fancybox-1.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[rel=image_group]").fancybox({
			'transitionIn'		: 'elastic',
			'transitionOut'		: 'elastic',
			'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
				return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
			}
		});
	});
</script>
</#if>

<title>DynamicReports</title>
	
</head>