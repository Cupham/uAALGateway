package samples.display;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

public class DeviceRenderer extends JPanel implements ListCellRenderer<DevicetoDisplay>{
	private JLabel lbDevID = new JLabel();
	private JLabel lbDevType = new JLabel();
	private JLabel lbDevIcon = new JLabel();
	private JLabel lbDevLocation = new JLabel();
	private JLabel lbIsOn = new JLabel();
	private JLabel lbDevData = new JLabel();
	private JToggleButton btnStatus = new JToggleButton("Power");
	
	
	
	public DeviceRenderer() {
		setLayout(new BorderLayout(5,5));
		JPanel textPanel =  new JPanel(new GridLayout(0,2));
		textPanel.add(lbDevID);
		textPanel.add(lbDevData);
		textPanel.add(btnStatus);
		textPanel.add(lbDevLocation);
		JPanel iconPanel = new JPanel(new BorderLayout());
		iconPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		iconPanel.add(lbDevIcon);
		add(iconPanel,BorderLayout.WEST);
		add(textPanel,BorderLayout.CENTER);
	}


	@Override
	public Component getListCellRendererComponent(
			JList<? extends DevicetoDisplay> list, DevicetoDisplay value,
			int index, boolean isSelected, boolean cellHasFocus) {
			//lbDevIcon.setIcon(new ImageIcon(getClass().getResource(
			//	   "/uAALGateway" +value.getDeviceIcon() +".png")));
		
			lbDevIcon.setIcon(new ImageIcon(getClass().getResource(value.getDeviceIcon() +".jpg")));
			lbDevID.setText(value.getDeviceID());
			btnStatus.setSelected(value.isON());
			lbDevLocation.setText(value.getDeviceLocation());
			lbDevData.setText(value.getDeviceData());
			return this;
	}
	

}
