package com.sysgears.examples

class DynamicElementsTagLib {

    /**
     * Namespace for current taglib.
     */
    static namespace = "dynamic_elements"

    /**
     * Tag for dynamic adding of block of HTML elements on the page.
     * Renders GSP template with additional elements, passes necessary parameters to this template.
     *
     * @attr itemId          REQUIRED The suffix for the IDs of additional elements and the prefix for the IDs of dynamically added items (the number of an item is added to the end of ID)
     * @attr template        OPTIONAL The name of the GSP template; the tag's body will be rendered if this parameter is missing
     * @attr model           OPTIONAL The model for the template that is used
     * @attr addBtnId        OPTIONAL ID of the "Add" button; if this parameter is not specified, the default "Add" button will be rendered
     * @attr removeBtnLabel  OPTIONAL The label for the button that removes item ("Remove" by default)
     * @attr min             OPTIONAL The number of items that are in the page by default and cannot be removed
     * @attr max             OPTIONAL The maximum number of items that can be added to the page
     * @attr limitReachedMsg OPTIONAL The message that will be displayed when the maximum limit of items is reached
     * @attr onComplete      OPTIONAL The name of JS function that must be executed after adding a new item; this function must receive the index number of an item
     */
    def add = { attrs, body ->
        // check the existence of the itemId attribute
        def id = attrs.itemId
        if (!id) throw new IllegalArgumentException("[id] attribute must be specified to for <dynamic_elements:add>!")

        // verify the min and the max attributes
        def min
        def max
        try {
            min = attrs.min as int
            max = attrs.max as int
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("[min] and [max] attributes must be integer numbers!")
        }
        if (min && max && max < min) throw new IllegalArgumentException("[min] attribute must be less than [max]!")

        // prepare template for new items
        def elem = attrs.template ? render(template: attrs.template, model: attrs.model) : body()
        elem = elem.replaceAll('\n', '') // make template single-lined in order to correct pass it as a parameter to the JS function in the GSP-template

        // render GSP-template
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