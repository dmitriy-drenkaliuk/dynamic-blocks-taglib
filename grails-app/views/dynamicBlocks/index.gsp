<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
	<meta name="layout" content="main"/>
	<title>'dynamic:block' tag examples</title>
	<g:javascript>
		function initDatePicker(num) {
			$('#birthday' + num).datepicker();
		}
	</g:javascript>
</head>

<body>

<g:form controller="dynamicBlocks" action="submitAction" style="margin: 100px;">
	<p>The 'dynamic:block' tag with callback function:</p>
	<table cellpadding="0" cellspacing="0" id="usersHeadTable">
		<tr><td>First name</td><td>Last name</td><td>Birthday</td></tr>
	</table>
	<dynamic:block itemId="fullName" min="2" max="5" addBtnId="addFullName" removeBtnLabel="Delete"
						  onComplete="initDatePicker" limitReachedMsg="Limit is exceeded!" template="/partials/elem"/>
	<input id="addFullName" type="button" value="Add user"/>

	<br/><br/>

	<p>The 'dynamic:block' tag with checkboxes:</p>
	<dynamic:block itemId="skills" min="1" max="5">
		<g:textField name="skill" placeholder="Skill name"/>
		<g:checkBox name="isVerified"/>
	</dynamic:block>

	<br/><br/>

	<p>The 'dynamic:block' tag with radio inputs:</p>
	<dynamic:block itemId="radioInputsTest" min="4">
		<span>
			<label>Option1</label>
			<g:radio value="1" name="radioGroup" checked="checked"/>
			<label>Option2</label>
			<g:radio value="2" name="radioGroup"/>
		</span>
	</dynamic:block>

	<br/><br/>

	<g:submitButton name="submit" value="Submit" style="display: block;"/>
</g:form>

</body>
</html>