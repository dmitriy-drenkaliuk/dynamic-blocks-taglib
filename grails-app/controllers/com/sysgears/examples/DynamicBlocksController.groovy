package com.sysgears.examples

class DynamicBlocksController {

	/**
	 * Renders a page with an ability to add HTML elements dynamically.
	 */
	def index() {
	}

	/**
	 * Prints parameters taken from a form with dynamically added items and redirects to the index page.
	 */
	def submitAction() {
		println(params as String)

		redirect(action: 'index')
	}
}