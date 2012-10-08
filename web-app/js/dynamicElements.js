/*
 * Adds new element to the form.
 */
function addItem(id, elem, min, max, onComplete, limitMessage, removeBtnLabel) {
	if (!max || $('[id^=' + id + ']').length < max) {
		var countElem = $('#count_' + id);
		var num = parseInt(countElem.html()) + 1;
		countElem.html(num);
		var newElem = $('<div></div>').html(elem).attr({'id' : id + num}).css('margin', '5px');
		var removeButton = $('<input type="button"/>').appendTo(newElem);
		removeButton.attr({'id' : 'remove_' + id, 'value' : removeBtnLabel ? removeBtnLabel : 'Remove', 'disabled' : 'disabled'});
		removeButton.click(function() {
			removeItem(id, num, min);
		});
		indexItem(newElem, num);
		indexRadioGroup(newElem, num);
		$('#parent_' + id).append(newElem);
		if ($('[id^=' + id + ']').length > min) {
			$('[id^=remove_' + id + ']').removeAttr('disabled');
		}
		if (window[onComplete] instanceof Function) {
			window[onComplete](num);
		}
	} else {
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