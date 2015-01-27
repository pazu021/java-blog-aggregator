<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp" %>
<table class="table table-striped table-hover table-bordered">
	<thead>
		<tr>
			<td>user name</td>
			<td>operations</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>
					<a href="/users/${user.id}.html"><c:out value="${user.name}" /></a> 
				</td>
				<td>
					<a href="/users/remove/${user.id}.html"  class="btn btn-danger triggerRemove">remove</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>


<!-- Modal -->
<div class="modal fade" id="modalRemove" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Remove User</h4>
      </div>
      <div class="modal-body">
        Really remove??
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger removeBtn">Remove</a>
      </div>
    </div>
  </div>
</div>


<script>
	$(document).ready(function() {
		$(".triggerRemove").click(function(e) {
			e.preventDefault();
			$("#modalRemove .removeBtn").attr("href", $(this).attr("href"));
			$("#modalRemove").modal();
		});
	});
</script>
