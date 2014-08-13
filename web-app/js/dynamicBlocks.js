/*
 * Adds a new item.
 */
function addItem(id, elem, min, max, onComplete, limitMessage, removeBtnLabel) {
	// checks if we have reached maximum number of elements
	if (!max || $('[id^=' + id + ']').length < max) {
		// increments the item counter
		var $countElem = $('#count_' + id);
		var num = parseInt($countElem.html()) + 1;
		$countElem.html(num);
		// creates new item and adds the index number to it
		var $newElem = $('<div></div>').html(elem).attr({'id' : id + num}).css('margin', '5px');
		// creates the "Remove" button
		var $removeButton = $('<input type="button"/>').appendTo($newElem);
		$removeButton.attr({'id' : 'remove_' + id, 'value' : removeBtnLabel ? removeBtnLabel : 'Remove', 'disabled' : 'disabled'});
		// binds handler to the 'click' JS event of the "Remove" button
		$removeButton.click(function() {
			removeItem(id, num, min);
		});
		// changes IDs of all elements inside new item
		indexItem($newElem, num);
		indexRadioGroup($newElem, num);
		// appends new item to the parent element
		$('#parent_' + id).append($newElem);
		// enables "Remove" buttons if there are more than minimum number of elements on the page
		if ($('[id^=' + id + ']').length > min) {
			$('[id^=remove_' + id + ']').removeAttr('disabled');
		}
		// executes the 'onComplete' JS function if it exists
		if (window[onComplete] instanceof Function) {
			window[onComplete](num);
		}
	} else {
		// displays a message if the maximum limit is reached
		alert(limitMessage ? limitMessage : 'You cannot add more elements.');
	}
}

/*
 * Removes an item.
 */
function removeItem(id, num, min) {
	$('#' + id + num).remove();
	if ($('[id^=' + id + ']').length <= min) {
		$('[id^=remove_' + id + ']').attr('disabled', 'disabled');
	}
}

/*
 * Changes ID of every item's child by adding index number to it.
 */
function indexItem($elem, num) {
	$elem.children().each(function() {
		var nodeId = $(this).attr('id');
		if (nodeId) {
			$(this).attr('id', nodeId + num);
		}
		indexItem($(this), num);
	});
}

/*
 * Changes names of radio inputs by adding the index number to it.
 */
function indexRadioGroup($elem, num) {
	$elem.find('input[type=radio]').each(function() {
		$(this).attr('name', $(this).attr('name') + num);
	});
}