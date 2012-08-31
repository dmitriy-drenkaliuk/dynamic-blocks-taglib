/*
 * Dynamically adds new element on a form.
 */
function addItemDynamically(id, elem, min, max, onComplete, limitMessage, removeBtnLabel) {
	if (!max || $('[id^=' + id + ']').length < max) {
		var countElem = $('#count_' + id);
		var num = parseInt(countElem.html()) + 1;
		countElem.html(num);
		var newElem = $('<div></div>').html(elem).attr({'id' : id + num}).css('margin', '5px');
		var removeButton = $('<input type="button"/>').appendTo(newElem);
		removeButton.attr({'id' : 'remove_' + id, 'value' : removeBtnLabel ? removeBtnLabel : 'Remove', 'disabled' : 'disabled'});
		removeButton.click(function() {
			removeItemDynamically(id, num, min);
		});
		changeIds(newElem, num);
		changeRadioNames(newElem, num);
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
 * Dynamically removes element from a page.
 */
function removeItemDynamically(id, num, min) {
	$('#' + id + num).remove();
	if ($('[id^=' + id + ']').length <= min) {
		$('[id^=remove_' + id + ']').attr('disabled', 'disabled');
	}
}

/*
 * Changes IDs for each element's child by adding index number to it.
 */
function changeIds(elem, num) {
	elem.children().each(function() {
		var nodeId = $(this).attr('id');
		if (nodeId) {
			$(this).attr('id', nodeId + num);
		}
		changeIds($(this), num);
	});
}

/*
 * Changes names of radio inputs by adding index number to it.
 */
function changeRadioNames(elem, num) {
	elem.find('input[type=radio]').each(function() {
		$(this).attr('name', $(this).attr('name') + num);
	});
}