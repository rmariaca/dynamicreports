<#if page.hasCode == true>
<script type="text/javascript" src="${page.path}scripts/shCore.js"></script>
<#list page.codeClasses as item>
<script type="text/javascript" src="${page.path}scripts/shBrush${item}.js"></script>
</#list> 
<script type="text/javascript">dp.SyntaxHighlighter.HighlightAll('code');</script>
</#if>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-15862019-2");
pageTracker._trackPageview();
} catch(err) {}</script>