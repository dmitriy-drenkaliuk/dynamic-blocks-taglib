package com.sysgears.examples

class DynamicElementsTagLib {

	/**
	 * Namespace for current taglib.
	 */
	static namespace = "dynamic_elements"

	/**
	 * Tag that allows to add identical HTML blocks to the page dynamically.
	 * Renders GSP template that contains additional HTML elements and necessary JavaScript functions.
	 *
	 * @attr itemId          REQUIRED Prefix for ID of each block (a block ID consists of the itemId + index number of the block)
	 * @attr template        OPTIONAL Name of a GSP template that contains HTML code for every dynamic block (if missing, the tag's body will be used instead)
	 * @attr model           OPTIONAL Model to apply the GSP template against
	 * @attr addBtnId        OPTIONAL ID of the 'Add' button; the page must contain an element with this ID to provide custom 'Add' button; if not specified, the default 'Add' button will be rendered
	 * @attr removeBtnLabel  OPTIONAL Label of the button that is rendered with every item and allows to remove it (defaults to 'Remove')
	 * @attr min             OPTIONAL Number of items that are on the page by default; minimum number of items
	 * @attr max             OPTIONAL Maximum number of items that can be added to the page
	 * @attr limitReachedMsg OPTIONAL Message displayed when the maximum limit of items is reached
	 * @attr onComplete      OPTIONAL Name of a JS function that will be executed after adding of new block (must accept index number of a newly added item)
	 */
	def add = { attrs, body ->
		// check if the itemId attribute is passed to the tag
		def id = attrs.itemId
		if (!id) throw new IllegalArgumentException("[id] attribute must be specified to for <dynamic_elements:add>!")

		// validate the min and max attributes
		def min
		def max
		try {
			min = attrs.min ? attrs.min as int : null
			max = attrs.max ? attrs.max as int : null
		} catch (NumberFormatException nfe) {
			throw new IllegalArgumentException("[min] and [max] attributes must be integer numbers!")
		}
		if (min && max && max < min) throw new IllegalArgumentException("[min] attribute must be less than [max]!")

		// prepare template for new items
		def elem = attrs.template ? render(template: attrs.template, model: attrs.model) : body()
		elem = elem.replaceAll('\n', '') // make the template single-lined in order to pass it as a parameter to JS function that adds new items
		elem = elem.encodeAsJavaScript() // make the template able to pass into a JS function

		// render GSP template with auxiliary HTML and JS code
		out << render(template: "/partials/dynamicElements/add", model: [
				id: id,
				elem: elem,
				addBtnId: attrs.addBtnId,
				removeBtnLabel: attrs.removeBtnLabel,
				min: min ?: 0,
				max: max ?: 0,
				limitReachedMsg: attrs.limitReachedMsg,
				onComplete: attrs.onComplete])
	}
}