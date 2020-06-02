package system;

import com.mendix.core.actionmanagement.IActionRegistrator;

public class UserActionsRegistrar
{
  public void registerActions(IActionRegistrator registrator)
  {
    registrator.bundleComponentLoaded();
    registrator.registerUserAction(restdeeplinkmodule.actions.GetGUID.class);
    registrator.registerUserAction(restdeeplinkmodule.actions.GetUserFromSession.class);
    registrator.registerUserAction(restdeeplinkmodule.actions.StringFromResource.class);
    registrator.registerUserAction(system.actions.VerifyPassword.class);
  }
}
