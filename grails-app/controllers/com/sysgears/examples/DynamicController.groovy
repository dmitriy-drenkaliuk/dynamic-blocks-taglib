package com.sysgears.examples

class DynamicController {

    /**
     * Renders a page with an ability to add HTML elements dynamically.
     */
    def index = {
    }

    /**
     * Is performed when the form with dynamically added elements is submitted.
     */
    def submitAction = {
        println(params as String)
        redirect(action: 'index')
    }
}