<div id="count_${id}" style="display: none;">0</div>
<div id="parent_${id}"></div>
<g:if test="${!addBtnId}"><input id="add_${id}" type="button" value="Add"/></g:if>

<script type="text/javascript">
	$(function() {
		// import the add.js file if it's needed
		if (!window['addItem']) {
			$('<script type="text/javascript">').attr('src', "${resource(dir: 'js', file: 'dynamicElements.js')}").appendTo('head');
		}

		// get the "Add" button
		var addButton = ${addBtnId ? "\$('#$addBtnId')" : "\$('#add_$id')"};

		// bind event handler to the "click" JS event for the "Add" button
		addButton.click(function() {
			addItem('${id}', '${elem}', ${min}, ${max}, '${onComplete}', '${limitReachedMsg}', '${removeBtnLabel}');
		});

		// add the first elements
		for (var i = 0; i < ${min}; i++) {
			addButton.click();
		}
	});
</script>