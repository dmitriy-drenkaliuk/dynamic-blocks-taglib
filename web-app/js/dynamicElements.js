/*
 * Adds new element to the form.
 */
function addItem(id, elem, min, max, onComplete, limitMessage, removeBtnLabel) {
	// check the maximum limit of elements
	if (!max || $('[id^=' + id + ']').length < max) {
		// find an ID counter and increment it
		var countElem = $('#count_' + id);
		var num = parseInt(countElem.html()) + 1;
		countElem.html(num);
		// create new element and add index number to it
		var newElem = $('<div></div>').html(elem).attr({'id' : id + num}).css('margin', '5px');
		// create the "Remove" button
		var removeButton = $('<input type="button"/>').appendTo(newElem);
		removeButton.attr({'id' : 'remove_' + id, 'value' : removeBtnLabel ? removeBtnLabel : 'Remove', 'disabled' : 'disabled'});
		// bind handler to the 'click' JS event for the "Remove" button
		removeButton.click(function() {
			removeItem(id, num, min);
		});
		// change IDs for new element’s children
		indexItem(newElem, num);
		indexRadioGroup(newElem, num);
		// append new element to the parent
		$('#parent_' + id).append(newElem);
		// enable "Remove" buttons if there are greater than minimum number of elements on the page
		if ($('[id^=' + id + ']').length > min) {
			$('[id^=remove_' + id + ']').removeAttr('disabled');
		}
		// execute onComplete JS function if it exists
		if (window[onComplete] instanceof Function) {
			window[onComplete](num);
		}
	} else {
		// show a message if maximum limit is reached
		alert(limitMessage ? limitMessage : 'You cannot add more elements.');
	}
}

/*
 * Removes element from a page.
 */
function removeItem(id, num, min) {
	$('#' + id + num).remove();
	if ($('[id^=' + id + ']').length <= min) {
		$('[id^=remove_' + id + ']').attr('disabled', 'disabled');
	}
}

/*
 * Changes IDs for each element's child by adding index number to it.
 */
function indexItem(elem, num) {
	elem.children().each(function() {
		var nodeId = $(this).attr('id');
		if (nodeId) {
			$(this).attr('id', nodeId + num);
		}
		indexItem($(this), num);
	});
}

/*
 * Changes names of radio inputs by adding index number to it.
 */
function indexRadioGroup(elem, num) {
	elem.find('input[type=radio]').each(function() {
		$(this).attr('name', $(this).attr('name') + num);
	});
}