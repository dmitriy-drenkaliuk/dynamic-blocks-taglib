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

		params.radioGroup = params.findAll { it.key.startsWith('radioGroup') }.sort { it.key }.collect { it.value }
		println 'Radio group params:' + params.radioGroup

		params.isVerified = params.isVerified.inject([]) { res, entry ->
			entry.key.startsWith('_') ? (res + [params.isVerified[entry.key.replace('_', '')]]) : res
		}
		println 'Checkbox params: ' + params.isVerified

		redirect(action: 'index')
	}
}