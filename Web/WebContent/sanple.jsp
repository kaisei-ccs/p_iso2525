<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String GetNo = request.getParameter("t1");
	int SetNo = 0;
	SetNo = Integer.parseInt(GetNo);
    for (int i = 0; i < SetNo; i++) {
%>
<table>
<caption>
<strong>
出品表jsp<%= i %>
</strong>
</caption>
<tr>
<th>出品表番号</th>
<th width="150">学籍番号</th>
<th width="200">名前</th>
</tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番11
</td></tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番244444
</td></tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番3
</td></tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番4
</td></tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番5
</td></tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番6
</td></tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番7
</td></tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番8
</td></tr>
<tr><td bgcolor="#FFFFFF" valign="top" width="150">
項番9
</td></tr>
</table>
<%
    }
%>