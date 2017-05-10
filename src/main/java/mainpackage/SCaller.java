package mainpackage;


/* More on how to use this class at: 
 * http://forge.universaal.org/wiki/support:Developer_Handbook_7 */
import org.universAAL.middleware.container.ModuleContext;
import org.universAAL.middleware.service.ServiceCaller;
import org.universAAL.middleware.service.ServiceResponse;

public class SCaller extends ServiceCaller {

	protected SCaller(ModuleContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void communicationChannelBroken() {
		// TODO Auto-generated method stub

	}

	public void handleResponse(String reqID, ServiceResponse response) {
		// TODO Auto-generated method stub

	}

}
