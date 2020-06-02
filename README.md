# REST Deeplink Module

This document is intended to help you set up the DeeplinkAPI Published REST service document included in this module for your own Mendix app.

## What is a deep link?

Normally, Mendix apps just have a single URL from which you can navigate to various pages in your app, while the address bar doesn't change. A deep link is a URL that points to a specific page in your app without having to navigate there via the home page. An example of a deep link would be:

`https://restdeeplinkmodule-sandbox.mxapps.io/link/example/parameter1/example/parameter2/parameter3`

## Why this module?

The classic [Deeplink module](https://appstore.home.mendix.com/link/app/43/Mendix/Deep-link-module) and the native mendix bookmarkable URL functionality only allow for a single parameter in the deeplink path. The REST Deeplink Module takes advantage of native REST functionality in Mendix to allow you to define deeplinks with multiple parameters and custom path structure. 

## Enabling bookmarkable URLs for your target pages

In Studio Pro, open the Properties pane of each page you want to be able to open via a deep link. Find the General > **URL** property. The path you enter here should contain `{Id}`, which is a unique identifier for the object you are displaying on the page.

For example, your target page has a top level data view with a page parameter of type 'Order'. In the URL field, you would type: `/order/{Id}`.

## Creating your own version of the DeeplinkAPI Published REST service definition

Do not edit the DeeplinkAPI definition in the `USE_ME` folder. Instead, create a duplicate of it and place it in a new module that you name something like `DeeplinkAPI_Implementation`. Reason being that if there ever is an update of the REST Deeplink Module and you download it from the App Store, the service definition will be overwritten by the example again. It is important to keep the custom authentication method in place, else the logic for redirecting users will break.

## Setting up deeplinks

In your `DeepLinkApi_Implementation` file (or whaterver you named it), add a resource and name it to what you like to be the first item in your deeplink path. Next add a [GET operation](https://docs.mendix.com/refguide/published-rest-operation) to this resource for each page you want to create a deeplink for. In the GET operation you can specify multiple [path parameters](https://docs.mendix.com/refguide/published-rest-path-parameters) and add custom path structure to make your operation path specific and intuitive. Next, copy the `REST_GetExamplePage` to your implementation folder and link it to one of your operations. Change the name of the `REST_GetExamplePage` to something that makes sense in your context and edit the `Retrieve ExampleLinkEntity from database` and `Status_303_SeeOther` activities. Set the `Location` parameter in the `Status_303_SeeOther` microflow to the bookmarkable URL of your page, for instance `'/p/order/' + $EntityGUID`.

## Setting up security

For security reasons the module is setup in such a way that everthing without a session will be redirected to a login-page you can set in the `LoginRedirectLocation`. This way users do not receive information through http responses if they are not authorized. This would have happened if you only setup page access.

Add the `DeeplinkAPIModuleRole` to each user role that should be allowed to use deeplinks. For more information about user roles, see the [reference guide](https://docs.mendix.com/refguide/security#4-user-roles-vs-module-roles). 

## Redirecting to a login page

Please set the `LoginRedirectLocation` constant to your login page.

### For apps with local authentication
Move the resources/login.js file into the theme/js folder. This way, the continuation parameter will be picked up.

### In combination with the [MendixSSO module]
In combination with the [MendixSSO module](https://appstore.home.mendix.com/link/app/111349/) the REST Deeplink Module supports continuation after logging in. When an MendixSSO login page is set in the `LoginRedirectLocation` constant, continuation after logging in should work. The MendixSSO module can be found in the Mendix App Store.

In case a page is not found the module will return a 404 status. If desired you can edit the HTML output in `resources/404.html`.

## Running the example

The module constains an example deeplink flow for testing purposes. All the related documents are stored in the `ExampleSetup` folder. To set up the example flow the `ASU_ensureData` microflow has to be added to your [after startup microflow](https://docs.mendix.com/refguide/project-settings#3-3-after-startup). 
