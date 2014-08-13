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
		println 'Radio group params:' +
				params.findAll {
					it.key.startsWith('radioGroup')
				}.sort {
					it.key
				}.collect {
					it.value
				}

		redirect(action: 'index')
	}
}