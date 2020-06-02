// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package restdeeplinkmodule.proxies.constants;

import com.mendix.core.Core;

public class Constants
{
	// These are the constants for the RESTDeeplinkModule module

	/**
	* The basic mendix login.html will not allow for continuation to your deeplink page after logging in. Only users with a session will directly go to the deeplink page.
	* 
	* This module supports continuation after logging in, in combination with the MendixSSO module. When an MendixSSO login page is set in this constant continuation after logging in should work. The MendixSSO module can be found in the Mendix App Store.
	*/
	public static java.lang.String getLoginRedirectLocation()
	{
		return (java.lang.String)Core.getConfiguration().getConstantValue("RESTDeeplinkModule.LoginRedirectLocation");
	}
}