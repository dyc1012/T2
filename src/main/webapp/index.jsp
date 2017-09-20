<html>

<head>

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

<script>
	var submitForm;
	var clearForm;

	$(document).ready(function() {

		submitForm = function() {
			//$('#ff').form('submit');
			$('#ff').submit();
		};

		clearForm = function() {
			$('#ff').form('clear');
		};

	});
</script>
</head>

<body>
	<div class="easyui-panel" title="SignIn"
		style="width: 100%; max-width: 400px; padding: 30px 60px;">

		<form id="ff" method="post" action="login.do">

			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" label="Username:" id='username'
					name='username' labelPosition="top"
					data-options="prompt:'Enter username...'"
					style="width: 100%; height: 52px" />
			</div>

			<div style="margin-bottom: 20px">
				<input class="easyui-passwordbox" name='password' prompt="Password"
					iconWidth="28" style="width: 100%; height: 34px; padding: 10px" />
			</div>

			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" label="Checkcode:" id='inputCode'
					name="inputCode" labelPosition="top"
					style="width: 70%; height: 52px"> <img src="code"
					id="authImg" onClick="src='code?a='+Math.random()" />
			</div>

			<div style="margin-bottom: 20px">
				<input class="easyui-textbox" name="message"
					style="width: 100%; height: 60px"
					data-options="label:'Message:',multiline:true" />
			</div>
			<div style="margin-bottom: 20px">
				<select class="easyui-combobox" name="language" label="Language"
					style="width: 100%"><option value="ar">Arabic</option>
					<option value="bg">Bulgarian</option>
					<option value="ca">Catalan</option>
					<option value="zh-cht">Chinese Traditional</option>
					<option value="cs">Czech</option>
					<option value="da">Danish</option>
					<option value="nl">Dutch</option>
					<option value="en" selected="selected">English</option>
					<option value="et">Estonian</option>
					<option value="fi">Finnish</option>
					<option value="fr">French</option>
					<option value="de">German</option>
					<option value="vi">Vietnamese</option></select>
			</div>

		</form>

		<div style="text-align: center; padding: 5px 0">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitForm()" style="width: 80px">SignIn</a> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				onclick="clearForm()" style="width: 80px">Clear</a>
		</div>
	</div>

</body>
</html>
