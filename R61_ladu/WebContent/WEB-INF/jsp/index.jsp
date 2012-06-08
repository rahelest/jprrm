<head>
	<link rel="stylesheet" href="css/nav.css">
	<style type="text/css">
		body {font:16px Tahoma, Sans-serif;}
		nav { display:block; margin:0 auto 20px auto; border:1px solid #222; position:relative; background-color:#6a6a6a; width:1000px}
		nav ul { padding:0; margin:0; }
		nav li { position:relative; float:left; list-style-type:none; }
		nav ul:after { content:"."; display:block; height:0; clear:both; visibility:hidden; }
		nav li a { display:block; padding:10px 20px; border-left:1px solid #999; border-right:1px solid #222; color:#eee; text-decoration:none; }
		nav li a:hover { outline:none; text-decoration:underline; }
		nav li:first-child a { border-left:none; }
		nav li.last a { border-right:none; }
		nav a span { display:block; float:right; margin-left:5px; }
		nav ul ul { display:none; width:100%; position:absolute; left:0; background:#6a6a6a; }
		nav ul ul li { float:none; }
		nav ul ul a { padding:5px 10px; border-left:none; border-right:none; font-size:14px; border-top: 1px solid white;}
		nav ul ul a:hover { background-color:#555; }
		nav ul li:hover ul {display:block;}
		
		section {margin: 0 auto; width:1000px;}
		aside {width: 250px;}
		aside, details {display: inline-block; padding: 10px; vertical-align: top; max-width: 705px;}
	</style>
</head>
<body>
<nav>
	<ul>
		<li><a class="topnav" href="#">Tooted</a>
			<ul class="subnav1">
				<li><a href="otsi_tuubiga.jsp">Vaata tootepuud</a></li>
				<li><a href="otsi_tuubita.jsp">Otsi ilma tüübita</a></li>
			</ul>
		</li>
		<li><a class="topnav" href="#">Laod</a>
			<ul class="subnav2">
				<li><a href="#">Võta arvele</a></li>
				<li><a href="#">Kanna maha</a></li>
				<li><a href="#">Liiguta</a></li>
			</ul>
		</li>
		<li><a class="topnav" href="#">Hinnakirjad</a>
			<ul class="subnav3">
				<li><a href="#">Loo, Muuda Kustuta</a></li>
				<li><a href="#">Kliendid</a></li>
				<li><a href="#">Tooted</a></li>
			</ul>
		</li>
		<li>
			<a class="topnav" href="#">Päringud</a>
		</li>
	</ul>
</nav>
<jsp:include page="tree.jsp" />
	<details>
		<h3>TOOTE LISAMINE</h3>
		<table>
			<tr> <td> Nimetus </td> <td> <input /> </td> </tr>
			<tr> <td> Kirjeldus </td> <td> <input /> </td> </tr>
			<tr> <td> Müügihind </td> <td> <input /> </td> </tr>
			<tr> <td> Tüüp </td> <td> <input /> </td> </tr>
			<tr> <td> Atribuudid: </td> <td> <input /> </td> </tr>


		</table>
	</details>
</section>

