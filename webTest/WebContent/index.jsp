<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>GA'S WEB APPLY</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.0/jquery.min.js" ></script>
<script>
			var i = 1
			$(document).ready(function() {
				 //location to add new inputs
				  var to_Add_at = $(".inputs");
				  var add_button = $("input[name='add']");
				  //on click of button
				  $(add_button).click(function(e) {
				    e.preventDefault();
				    $(to_Add_at).append('<input type=text name='+i+' value='+i++ +'>'); //add input box
				  });
				});
		</script>
</head>
<body>
		<form action="/webTest/TestWeb" method="post">
	  		<input type="button"  value="add" name="add" />
	  		<div class="inputs"></div>
	  		<input type="submit" value="submit" />
		</form>
		<%
			String [] dataJsp = (String [])request.getAttribute("dataArray");
			if(dataJsp==null)
			{
				out.print("please input data...");
			}else
			{
				for(String s:dataJsp)
				{
					out.print(s+"&nbsp;&nbsp;");
				}
			}
		%>
		<%
		List<Map<String, String>> newLocLatitudes=(List<Map<String, String>>)request.getAttribute("dataMap");
		if(newLocLatitudes==null)
		{
			out.print("<br>");
		}else{
		String start="<img src=\" https://restapi.amap.com/v3/staticmap?&size=500*500&paths=3,0x808080,1,,:";
		String end="&key=0b6b6ed2e01de1b84a393fd4faa9ae84\">";
		out.print(start);
		for(int n=0;n<newLocLatitudes.size();n++)
		{
			out.print(newLocLatitudes.get(n).get("lng")+","+newLocLatitudes.get(n).get("lat")+";");
		}
		out.print(newLocLatitudes.get(0).get("lng")+","+newLocLatitudes.get(0).get("lat"));
		out.print(end);
		}
		 %>
</body>
</html>