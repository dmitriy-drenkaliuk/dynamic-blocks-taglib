Project demonstrates how to use the 'dynamic_elements' Grails TagLib described in `this article <http://sysgears.com/articles/how-dynamically-add-and-remove-single-type-blocks-html-elements-using-grails-tag-library/>`_
##########################################################################################################################################################################################################################

.. contents::

Overview
========

This is a project sample that demonstrates Grails tag library for dynamically placing of similar HTML blocks on GSP page. There are a test controller and a GSP view page that contain several examples of the tag library usages. With this tag library you have an ability to:

* dynamically add and remove single-type blocks of HTML elements on the page;

* set the minimum and maximum number of blocks;
* use separate GSP template as HTML block.

The tag library is described in details in the article: http://sysgears.com/articles/how-dynamically-add-and-remove-single-type-blocks-html-elements-using-grails-tag-library.

How to use the tag library in Grails project
============================================

1) Copy the following files to the specified directories:

 * DynamicElementsTagLib.groovy - to the *<project_root>/grails-app/<package_name>/taglib* directory

 * _add.gsp - to the *<project_root>/grails-app/views/partials/dynamicElements* directory
 * dynamicElements.js - to the *<project_root>/web-app/js* directory

2) Add JQuery library to your GSP page or layout:

::

    <g:javascript src="jquery/jquery-1.8.2.min.js"/>

3) Add tag on your GSP page, specify a HTML template and pass necessary parameters to the tag:

::

    <g:form controller="dynamicElements" action="submitAction" style="margin: 100px;">
        <p>Enter a full name and birthday:</p>
        <dynamic_elements:add itemId="fullName" min="2" max="5" addBtnId="addFullName" removeBtnLabel="Delete"
                              onComplete="makeDatePicker" limitReachedMsg="Limit is exceeded!">
            <g:textField name="firstName"/>
            <g:textField name="lastName"/>
            <g:textField name="birthday" id="birthday"/>
            <input id="addFullName" type="button" value="Add another name"/>
        </dynamic_elements:add>
    </g:form>

Library's tags description
==========================

The tag library provides single 'add' tag. This tag places user defined HTML template with the 'Add' button to the page. A click on the 'Add' button adds another copy of the HTML template on the page. Each copy has its own 'Remove' button. You can pass the following parameters to the tag in order to set up its behavior on GSP page:

* itemId - ID prefix for each copy of the template (the index number of the copy is added to the end of ID);

* template - the name of a GSP template that contains a HTML code for dynamic adding (the tag's body will be rendered if this parameter is missing);
* model - the model to apply the GSP template against;
* addBtnId - ID of the 'Add' button; the page must contain an element with this ID to provide custom 'Add' button; if the parameter is not specified, the default 'Add' button will be rendered;
* removeBtnLabel - label for the button that removes one item ('Remove' by default);
* min - number of items that are on the page by default; minimum number of items;
* max - maximum number of items that can be added on the page;
* limitReachedMsg - message that will be displayed when the maximum limit of items is reached;
* onComplete - the name of a JS function that must be executed after a new item is added (this function must receive the index number of a newly added item).

The only required parameter is *itemId*, all other parameters are optional.

Requirements
============

* Grails 1.3.7 - 2.1.0
* JQuery v1.5 or higher