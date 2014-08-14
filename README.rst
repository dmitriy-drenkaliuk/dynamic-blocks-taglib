Project that demonstrates how to use the tag library described in the post:
###########################################################################

`Dynamically add and remove HTML blocks with Grails tag library and JQuery <http://sysgears.com/articles/dynamically-add-and-remove-html-blocks-with-grails-tag-library-and-jquery/>`_
``````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````````

.. contents::
   :local:

Overview
========

This is a project sample that demonstrates Grails tag library for dynamic placing of identical HTML blocks to a GSP. The tag library can be used when you want to give user an option to dynamically add extra inputs for additional information. With this tag library, you are able to:

* dynamically add and remove single-type blocks of HTML elements;

* set the minimum and maximum number of blocks;
* use separate GSP template as a HTML block.

The tag library is described in detail in this article: http://sysgears.com/articles/dynamically-add-and-remove-html-blocks-with-grails-tag-library-and-jquery/.

Tags description
================

The tag library provides a single 'block' tag and you can pass the following parameters to it in order to set up its behavior:

* itemId - the prefix for the item id (every item id consists of the prefix and the index number)

* template - the name of a GSP template that contains HTML code for every item (if missing, the tag's body will be used)
* model - the model passed to the GSP template
* addBtnId - The id of the 'add' button, the page must contain an element with this id to provide custom 'add' button; if isn't specified, the default 'add' button will be rendered
* removeBtnLabel - the label of the 'remove' button that is rendered for each item (defaults to 'Remove')
* min - the minimum number of items (the number of items that are rendered by default)
* max - the maximum number of items that can be added to the page
* limitReachedMsg - the message displayed when the limit is reached
* onComplete - the name of a JS function that will be executed right after a new item is added (must accept the item index number)

The only required parameter is *itemId*, all other parameters are optional.

How to use the tag library in a Grails project
==============================================

1) Copy the following files to the specified directories:

   * DynamicBlocksTagLib.groovy - to the *<project_root>/grails-app/<package_name>/taglib* directory

   * _add.gsp - to the *<project_root>/grails-app/views/partials/dynamicBlocks* directory
   * dynamicBlocks.js - to the *<project_root>/web-app/js* directory

2) Add JQuery library to your GSP page or layout:

::

    <g:javascript src="jquery/jquery-1.8.2.min.js"/>

3) Add the *dynamic:block* tag to your GSP page, specify HTML template and pass necessary parameters to the tag:

::

    <g:form controller="dynamicBlocks" action="submitAction" style="margin: 100px;">
        <p>Enter a full name and birthday:</p>
        <dynamic:block itemId="fullName" min="2" max="5" addBtnId="addFullName" removeBtnLabel="Delete"
                       onComplete="makeDatePicker" limitReachedMsg="Limit is exceeded!">
            <g:textField name="firstName"/>
            <g:textField name="lastName"/>
            <g:textField name="birthday" id="birthday"/>
            <input id="addFullName" type="button" value="Add another name"/>
        </dynamic:block>
    </g:form>

Requirements
============

* Grails 1.3.7 or higher
* JQuery v1.5 or higher
