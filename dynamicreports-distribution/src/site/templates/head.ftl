<head>

<#if page.description != "">
<meta name="Description" content="${page.description}" />
</#if>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<#if page.keywords != "">
<meta name="keywords" content="${page.keywords}" />
</#if>

<link rel="stylesheet" href="${page.path}styles/style.css" type="text/css" />
<script type="text/javascript" src="scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
$(function(){
    $('a[href$="download.html"]').click(function(){
        window.open(this.href);
        return false;
    });
});
</script>

<#if page.hasCode == true>
<link type="text/css" rel="stylesheet" href="${page.path}styles/SyntaxHighlighter.css"/>
</#if>

<#if page.hasImage == true>
<link rel="stylesheet" type="text/css" href="${page.path}fancybox/jquery.fancybox-1.3.1.css" media="screen" />  
<script type="text/javascript" src="${page.path}scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${page.path}fancybox/jquery.fancybox-1.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("a[rel=image]").fancybox({
			'showNavArrows'	: false,
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
//<![CDATA[
	$(document).ready(function() {
		$("a[rel=image_group]").fancybox({
			'transitionIn'		: 'elastic',
			'transitionOut'		: 'elastic',
			'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
				return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
			}
		});
	});
//]]>
</script>
</#if>

<title>DynamicReports - ${page.title}</title>
	
</head>