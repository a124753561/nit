[#assign shiro=JspTaglibs["/WEB-INF/tld/shiro.tld"] /]
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>${message("admin.main.title")} - Powered By rsico</title>
<meta name="author" content="rsico Team" />
<meta name="copyright" content="rsico" />
<link href="${base}/resources/admin/css/common.css" rel="stylesheet" type="text/css" />
<link href="${base}/resources/admin/css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${base}/resources/common/js/jquery.js"></script>
<style type="text/css">
*{
	font: 12px tahoma, Arial, Verdana, sans-serif;
}
html, body {
	width: 100%;
	height: 100%;
	overflow: hidden;
}
</style>
<script type="text/javascript">
$().ready(function() {

	var $nav = $("#nav a:not(:last)");
	var $menu = $("#menu dl");
	var $menuItem = $("#menu a");
	
	$nav.click(function() {
		var $this = $(this);
		$nav.removeClass("current");
		$this.addClass("current");
		var $currentMenu = $($this.attr("href"));
		$menu.hide();
		$currentMenu.show();
		return false;
	});
	
	$menuItem.click(function() {
		var $this = $(this);
		$menuItem.removeClass("current");
		$this.addClass("current");
	});

});
</script>
<body>
	<script type="text/javascript">
		if (self != top) {
			top.location = self.location;
		};
	</script>
	<table class="main">
		<tr>
			<th class="logo">
				<a href="main.jhtml">
					<img src="${base}/resources/admin/images/header_logo.gif" alt="rsico" />
				</a>
			</th>
			<th>
				<div id="nav" class="nav">
					<ul>
						<li>
							<a href="#product">${message("admin.main.productNav")}</a>
						</li>
						<li>
							<a href="#member">${message("admin.main.memberNav")}</a>
						</li>
						<li>
							<a href="#system">${message("admin.main.systemNav")}</a>
						</li>
						<li>
							<a href="${base}/" target="_blank">${message("admin.main.home")}</a>
						</li>
					</ul>
				</div>
				<div class="link">
					<a href="http://www.rsico.cn" target="_blank">${message("admin.main.official")}</a>|
					<a href="http://bbs.rsico.cn" target="_blank">${message("admin.main.bbs")}</a>|
					<a href="http://www.rsico.cn/about.html" target="_blank">${message("admin.main.about")}</a>
				</div>
				<div class="link">
					<strong>[@shiro.principal /]</strong>
					${message("admin.main.hello")}!
					<a href="../profile/edit.jhtml" target="iframe">[${message("admin.main.profile")}]</a>
					<a href="../logout.jsp" target="_top">[${message("admin.main.logout")}]</a>
				</div>
			</th>
		</tr>
		<tr>
			<td id="menu" class="menu">
				<dl id="product" class="default">
					<dt>${message("admin.main.productGroup")}</dt>
					<dd>
						<a href="../product/list.jhtml" target="iframe">${message("admin.main.product")}</a>
					</dd>
				</dl>	
				<dl id="member">
					<dt>${message("admin.main.memberGroup")}</dt>
					<dd>
						<a href="../tenant_category/list.jhtml" target="iframe">商家分类</a>
					</dd>
				</dl>
				<dl id="system">
					<dt>${message("admin.main.systemGroup")}</dt>
					<dd>
						<a href="../setting/edit.jhtml" target="iframe">${message("admin.main.setting")}</a>
					</dd>
				</dl>		
			</td>
			<td>
				<iframe id="iframe" name="iframe" src="index.jhtml" frameborder="0"></iframe>
			</td>
		</tr>
	</table>
</body>
</html>