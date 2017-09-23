<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>LoginSuccess</title>

<title>Basic TextBox - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5.2/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5.2/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="jquery-easyui-1.5.2/demo/demo.css">
<script type="text/javascript" src="jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="jquery-easyui-1.5.2/jquery.easyui.min.js"></script>

</head>
<body>
	${msg} &nbsp;&nbsp;&nbsp;
	<a href="<%=request.getContextPath()%>/index.jsp" class="easyui-linkbutton"
		data-options="iconCls:'icon-back'">Back</a>
	<HR />

	<div>
		<table id="tt" class="easyui-datagrid"
			style="width: 350px; height: 650px"
			data-options="singleSelect:true, rownumbers:true, autoRowHeight:false, pagination:true,
			pageSize:20, url:'getAllUsers', method:'get' ">
			<thead>
				<tr>
					<th data-options="field:'id', width:80">ID</th>
					<th data-options="field:'name', width:120">Name</th>
					<th data-options="field:'age', width:80">Age</th>
					<th data-options="field:'cksdfsdfsd',checkbox:true"></th>
				</tr>
			</thead>
		</table>
	</div>

	<div style="margin: 10px 0;">
		<span>Selection Mode: </span> <select
			onchange="$('#tt').datagrid({singleSelect:(this.value==0)})">
			<option value="0">Single Row</option>
			<option value="1">Multiple Rows</option>
		</select><br />

	</div>

</body>
</html>