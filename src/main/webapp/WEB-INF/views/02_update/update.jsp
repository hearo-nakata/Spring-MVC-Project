<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html lang="en" >

<head>
  <meta charset="UTF-8">

    <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <title>Update</title>
  <link rel="stylesheet" href="resources/css/style.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">

</head>

<body>

            <!-- Default form contact -->
<form:form modelAttribute="lessonlistForm" class="text-center border border-light p-5" action="#!">

	 <!-- タイトル部分 ユーザーIDを『』内に表示 -->
    <p class="h4 mb-4 bg-info text-white rounded">Update User: 『 ${lessonlistForm.userId} 』</p>

    <!-- userFirstName -->
    <label>FirstName</label>
    <form:input path= "userFirstName" class="form-control mb-4" placeholder="FirstName"/>

    <!-- userLastName -->
    <label>LastName</label>
    <form:input path= "userLastName" class="form-control mb-4" placeholder="LastName"/>

    <!-- lesson1st -->
	<label>Lesson1st</label>
	<form:select path="lesson1st" class="form-control mb-4">
		<form:options items="${language}" itemValue="language" itemLabel="language"/>
	</form:select>

    <!-- lesson2nd -->
    <label>Lesson2nd</label>
	<form:select path="lesson2nd" class="form-control mb-4">
		<form:options items="${language}" itemValue="language" itemLabel="language"/>
	</form:select>

    <!-- delete -->
    <div class="custom-control custom-checkbox mb-4">
        <form:checkbox path="deleteFlg"/>
        <form:label path="">このIDを削除</form:label>
    </div>

    <!-- Send button -->
    <form:button class="btn btn-info btn-block">更新</form:button>

    <form:hidden path="userId"/>

</form:form>
<!-- Default form contact -->

</body>

</html>