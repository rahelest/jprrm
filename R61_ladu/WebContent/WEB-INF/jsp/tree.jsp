<%@ page import="java.util.ArrayList"  %> 
<jsp:useBean id="tree" scope="request" type="java.util.ArrayList[]" />

<%
ArrayList names = tree[0];
ArrayList parents = tree[1];
for (String name : names) {
	a.
}
%>

<section>
	<aside>
		<ul>
			<li>Seadmed
				<ul>
					<li>Metallitööstuse masinad
						<ul>
							<li><a href="getbytype.jsp?t=9">Treipingid</a></li>
							<li><a href="getbytype.jsp?t=10">Freespingid</a></li>
						</ul>
					</li>
					<li><a href="getbytype.jsp?t=5">Puutöö masinad</a></li>
				</ul>
			</li>
			<li>Keemia
				<ul>
					<li><a href="getbytype.jsp?t=6">Värvid</a></li>
					<li><a href="getbytype.jsp?t=7">Lakid</a></li>
				</ul>
			</li>
			<li>Ehitusmaterjalid
				<ul>
					<li><a href="getbytype.jsp?t=8">Põrandakatted</a></li>
				</ul>
			</li>
		</ul>
	</aside>