<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>Test page</title>
	<g:javascript>
		function check(elem) {
			$(elem).prev().val($(elem).attr('checked') ? 'on' : '');
		}

		function makeDatePicker(num) {
			$('#birthday' + num).datepicker();
		}
	</g:javascript>
</head>

<body>
<g:form controller="dynamicElements" action="submitAction" style="margin: 100px;">
	<p>Enter a full name and birthday:</p>
	<dynamic_elements:add itemId="fullName" min="2" max="5" addBtnId="addFullName" removeBtnLabel="Delete"
												onComplete="makeDatePicker" limitReachedMsg="Limit is exceeded!" template="/partials/elem"/>
	<input id="addFullName" type="button" value="Add another name"/>

	<br/><br/>

	<p>Checkboxes testing:</p>
	<dynamic_elements:add itemId="skills" min="1" max="5">
		<g:textField name="skill"/>
		<g:checkBox name="isVerified" onchange="check(this);"/>
	</dynamic_elements:add>

	<br/><br/>

	<p>Radio inputs testing:</p>
	<dynamic_elements:add itemId="radioInputsTest" min="4">
		<span>
			<label>Option1</label>
			<g:radio value="1" name="radioGroup"/>
			<label>Option2</label>
			<g:radio value="2" name="radioGroup"/>
		</span>
	</dynamic_elements:add>

	<br/><br/>

	<g:submitButton name="submit" value="Submit" style="display: block;"/>
</g:form>
</body>
</html>