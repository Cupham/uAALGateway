package utils;


import java.net.NetworkInterface;


import echowand.common.EOJ;
import echowand.common.EPC;
import echowand.net.Inet4Subnet;
import echowand.net.Node;
import echowand.service.Core;
import echowand.service.Service;
import echowand.service.result.GetResult;


public class EchonetConnect {
	public static Service service = null;
	public static float getTemp() {
		float rs = 0;
		try {
			if(service == null) {
				NetworkInterface nif = NetworkInterface.getByName("eno1");
				Core core = new Core(Inet4Subnet.startSubnet(nif));
				core.startService();
				service = new Service(core);
			}
			Node node = service.getRemoteNode("150.65.230.104");
			EOJ eoj = new EOJ("001101");
			EPC epc = EPC.xE0;
			GetResult result = service.doGet(node, eoj, epc, 1000);
			result.join();
			
			if(result.countData() != 1) {
				rs = -100;
			} else {
				for (int i=0; i <result.countData();i++) {
					rs = (result.getData(i).data.get(0)) << 0xff00 | (0x00ff & result.getData(i).data.get(1));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs/10;
	}
	
	public static void main(String[] args) {	
		System.out.println(getTemp());
		System.exit(0);
	} 

}
