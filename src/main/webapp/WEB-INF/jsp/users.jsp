<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>
<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<td>user name</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>
					<a href="/users/${user.id}.html">${user.name}</a> 
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>