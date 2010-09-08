<head>

<meta name="Description" content="DynamicReports open source project" />
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta name="Distribution" content="Global" />
<meta name="Robots" content="index,follow" />

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