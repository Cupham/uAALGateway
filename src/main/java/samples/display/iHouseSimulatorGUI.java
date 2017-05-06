package samples.display;

import java.awt.BorderLayout;
import java.awt.HeadlessException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


public class iHouseSimulatorGUI extends JFrame{
	private int width = 450;
	private int height= 500;
	private JList<DevicetoDisplay> deviceList;
	
	public iHouseSimulatorGUI() throws HeadlessException {
		add(createMainPanel());
		
		setTitle("iHouse Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JScrollPane(deviceList = createListDevice()),BorderLayout.CENTER);
		return panel;
	}
	
	private JList<DevicetoDisplay> createListDevice() {
		DefaultListModel<DevicetoDisplay> model = new DefaultListModel<>();
		model.addElement(new DevicetoDisplay("192.168.1.22", "TempSensor","temperatureSensor",
											 "Kitchen",true, "40"));
		model.addElement(new DevicetoDisplay("192.168.1.33", "HudSensor","humiditysensor",
				 "Kitchen",true, "41"));
		JList<DevicetoDisplay> rs = new JList<DevicetoDisplay>(model);
		rs.setCellRenderer(new DeviceRenderer());
		return rs;
	}
	
	public static void main(String[] args) {
		new iHouseSimulatorGUI();
	}
	
	

}
