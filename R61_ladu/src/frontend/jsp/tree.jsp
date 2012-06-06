<head>
	<link rel="stylesheet" href="css/nav.css">
	<style type="text/css">/*
		nav { display:block; margin:0 auto 20px; border:1px solid #222; position:relative; background-color:#6a6a6a; font:16px Tahoma, Sans-serif; }
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
		nav ul li:hover > ul {display:block;}
		nav ul li ul li:hover ul {display:block;}
		nav ul li ul li ul {position:absolute; left:20px;}*/
	</style>
</head>
<body>
<nav>
	<ul>
		<li>Seadmed
			<ul>
				<li>Metallitööstuse masinad
					<ul>
						<li><a href="searchbytype.jsp?t=9">Treipingid</a></li>
						<li><a href="searchbytype.jsp?t=10">Freespingid</a></li>
					</ul>
				</li>
				<li><a href="searchbytype.jsp?t=5">Puutöö masinad</a></li>
			</ul>
		</li>
		<li>Keemia
			<ul>
				<li><a href="searchbytype.jsp?t=6">Värvid</a></li>
				<li><a href="searchbytype.jsp?t=7">Lakid</a></li>
			</ul>
		</li>
		<li>Ehitusmaterjalid
			<ul>
				<li><a href="searchbytype.jsp?t=8">Põrandakatted</a></li>
			</ul>
		</li>
	</ul>
</nav>