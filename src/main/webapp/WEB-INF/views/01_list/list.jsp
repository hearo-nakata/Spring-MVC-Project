<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html lang="en" >

<head>
  <meta charset="UTF-8">

  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

   <!-- Bootstrap CSS -->
   <!-- link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" -->

  <title>List</title>

    <link rel="stylesheet" href="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.css"/>
    <script src="https://cdn.datatables.net/t/bs-3.3.6/jqc-1.12.0,dt-1.10.11/datatables.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function ($) {
            $.extend( $.fn.dataTable.defaults, {
                language: {
                url: "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
                }
            });

            $('#dtDynamicVerticalScrollExample').DataTable({
                "scrollY": "260px",
                "scrollCollapse": true,
            });
            $('.dataTables_length').addClass('bs-select');
        });
    </script>
 </head>

 <body>
   <form:form modelAttribute="lessonList">
        <div class="container-fluid">
        <div class="row">
        	<div class="col" style="display: flex; justify-content: flex-end;">
           		<input type="submit" name="logout" class="btn btn-primary" value="ログアウト"/>
        	</div>
        </div>

        <div class="row">
        	<div class="col">
         		<table id="dtDynamicVerticalScrollExample" class="datatables table table-striped table-bordered table-sm" cellspacing="0" width="100%">
          			<thead>
			            <tr>
			              <th scope="col">userId</th>
			              <th scope="col">userFirstName</th>
			              <th scope="col">userLastName</th>
			              <th scope="col">lesson1st</th>
			              <th scope="col">lesson2nd</th>
			            </tr>
			          </thead>
			          <tbody>
            			<c:forEach items="${lessonList}" var="listRow" varStatus="status">
            		<tr>
		                <td>${listRow.userId}</td>
		                <td>${listRow.userFirstName}</td>
		                <td>${listRow.userLastName}</td>
		                <td>${listRow.lesson1st}</td>
		                <td>${listRow.lesson2nd}</td>
		                <td>@mdo</td>
		            </tr>
			            </c:forEach>
			        </tbody>
			       </table>
			     </div>
			 </div>
    	</div>
     </form:form>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>

</html>