<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<form action="${action}" method="POST">
	<input type="hidden" id="id" name= "id" value="${id}" />
</form>

<script>
	$(document).ready(function() {
		$("form").submit();
	});
</script>