package com.sysgears.examples

class DynamicBlocksTagLib {

	/**
	 * Tag library namespace.
	 */
	static namespace = "dynamic"

	/**
	 * Renders the template that allows to dynamically add and remove identical HTML blocks (hereinafter "items").
	 *
	 * @attr itemId          REQUIRED The prefix for the item id (every item id consists of the prefix and the index number)
	 * @attr template        OPTIONAL The name of a GSP template that contains HTML code for every item (if missing, the tag's body will be used)
	 * @attr model           OPTIONAL The model passed to the GSP template
	 * @attr addBtnId        OPTIONAL The id of the 'add' button, the page must contain an element with this id to provide custom 'add' button;
	 *                                if isn't specified, the default 'add' button will be rendered
	 * @attr removeBtnLabel  OPTIONAL The label of the 'remove' button that is rendered for each item (defaults to 'Remove')
	 * @attr min             OPTIONAL The minimum number of items (the number of items that are rendered by default)
	 * @attr max             OPTIONAL The maximum number of items that can be added to the page
	 * @attr limitReachedMsg OPTIONAL The message displayed when the limit is reached
	 * @attr onComplete      OPTIONAL The name of a JS function that will be executed right after a new item is added (must accept the item index number)
	 */
	def block = { attrs, body ->
		// checks if the itemId attribute is passed to the tag
		def id = attrs.itemId
		if (!id) throwTagError("[id] attribute must be specified to for <dynamic:block>!")

		// validates the min and max attributes
		def min
		def max
		try {
			min = attrs.min ? attrs.min as int : null
			max = attrs.max ? attrs.max as int : null
		} catch (NumberFormatException nfe) {
			throwTagError("[min] and [max] attributes must be integer numbers!")
		}
		if (min && max && max < min) throwTagError("[min] attribute must be less than [max]!")

		// prepares template for new items
		def elem = attrs.template ? render(template: attrs.template, model: attrs.model) : body()
		elem = elem.replaceAll('\n', '') // makes the template single-lined in order to pass it as a parameter to JS function that adds new items
		elem = elem.encodeAsJavaScript() // makes the template able to pass into a JS function

		// renders GSP template with auxiliary HTML and JS code
		out << render(template: "/partials/dynamicBlocks/add", model: [
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