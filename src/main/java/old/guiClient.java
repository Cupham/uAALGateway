package old;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class guiClient extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 int redScoreAmount = 0;
	    int blueScoreAmount = 0;

	    JPanel ipPanel, operationPanel, localtionPanel, tempPanel;
	    JLabel IPLabel, txtIP, operationLabel, txtOperationStatus,
	    	   locationLabel, txtLocation, tempLabel, txtTemperature;
	    JButton redButton, blueButton, resetButton;

	    public JPanel createContentPane (){

	        // We create a bottom JPanel to place everything on.
	        JPanel totalGUI = new JPanel();
	        totalGUI.setLayout(null);

	        // Creation of a Panel to contain ip Address
	        ipPanel = new JPanel();
	        ipPanel.setLayout(null);
	        ipPanel.setLocation(0, 10);
	        ipPanel.setSize(250, 30);
	        totalGUI.add(ipPanel);

	        IPLabel = new JLabel("IP adress: ");
	        IPLabel.setLocation(0, 0);
	        IPLabel.setSize(150, 30);
	        IPLabel.setHorizontalAlignment(0);
	        IPLabel.setForeground(Color.blue);
	        ipPanel.add(IPLabel);

	        txtIP = new JLabel("#NA");
	        txtIP.setLocation(100, 0);
	        txtIP.setSize(180, 30);
	        txtIP.setHorizontalAlignment(0);
	        txtIP.setForeground(Color.blue);
	        ipPanel.add(txtIP);
	        


	        // Creation of a Panel to contain operation status.
	        operationPanel = new JPanel();
	        operationPanel.setLayout(null);
	        operationPanel.setLocation(0, 40);
	        operationPanel.setSize(250, 30);
	        totalGUI.add(operationPanel);
	        
	        // Operation Status     
	        operationLabel = new JLabel("Status: ");
	        operationLabel.setLocation(0, 0);
	        operationLabel.setSize(150, 30);
	        operationLabel.setHorizontalAlignment(0);
	        operationLabel.setForeground(Color.gray);
	        operationPanel.add(operationLabel);

	        txtOperationStatus = new JLabel("#NA");
	        txtOperationStatus.setLocation(100, 0);
	        txtOperationStatus.setSize(180, 30);
	        txtOperationStatus.setHorizontalAlignment(0);
	        txtOperationStatus.setForeground(Color.gray);
	        operationPanel.add(txtOperationStatus);
	        
	        // Creation of a Panel to contain location infor
	        localtionPanel = new JPanel();
	        localtionPanel.setLayout(null);
	        localtionPanel.setLocation(0, 70);
	        localtionPanel.setSize(250, 30);
	        totalGUI.add(localtionPanel);
	        
	        // Location information     
	        locationLabel = new JLabel("Location: ");
	        locationLabel.setLocation(0, 0);
	        locationLabel.setSize(150, 30);
	        locationLabel.setHorizontalAlignment(0);
	        locationLabel.setForeground(Color.BLACK);
	        localtionPanel.add(locationLabel);

	        txtLocation = new JLabel("#NA");
	        txtLocation.setLocation(100, 0);
	        txtLocation.setSize(180, 30);
	        txtLocation.setHorizontalAlignment(0);
	        txtLocation.setForeground(Color.BLACK);
	        localtionPanel.add(txtLocation);
	        
	        // Creation of a Panel to contain temperature infor
	        tempPanel = new JPanel();
	        tempPanel.setLayout(null);
	        tempPanel.setLocation(0, 100);
	        tempPanel.setSize(250, 30);
	        totalGUI.add(tempPanel);
	        
	        // Location information     
	        tempLabel = new JLabel("Current Temp: ");
	        tempLabel.setLocation(0, 0);
	        tempLabel.setSize(150, 30);
	        tempLabel.setHorizontalAlignment(0);
	        tempLabel.setForeground(Color.orange);
	        tempPanel.add(tempLabel);

	        txtTemperature = new JLabel("#NA");
	        txtTemperature.setLocation(100, 0);
	        txtTemperature.setSize(180, 30);
	        txtTemperature.setHorizontalAlignment(0);
	        txtTemperature.setForeground(Color.orange);
	        tempPanel.add(txtTemperature);
	        
	        totalGUI.setOpaque(true);
	        return totalGUI;
	    }

	    // This is the new ActionPerformed Method.
	    // It catches any events with an ActionListener attached.
	    // Using an if statement, we can determine which button was pressed
	    // and change the appropriate values in our GUI.
	    public void actionPerformed(ActionEvent e) {

	    }

	    public void createAndShowGUI(String name,String ip, String location, boolean status, float temp, JPanel panel) {

	        JFrame.setDefaultLookAndFeelDecorated(true);
	        JFrame frame = new JFrame("Temperator Sensor: " + name);
	        txtIP.setText(ip);
	        txtLocation.setText(location);
	        if(status) {
	        	txtOperationStatus.setText("ON");
	        	operationLabel.setForeground(Color.red);
	        } else {
	        	txtOperationStatus.setText("OFF");
	        	operationLabel.setForeground(Color.gray);
	        }
	        txtTemperature.setText(temp+ " *C");

	        //Create and set up the content pane.
	        frame.setContentPane(panel);

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(350, 170);
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	 guiClient demo = new guiClient();
	                demo.createAndShowGUI("1","150.231.60.1","living room",true,23,demo.createContentPane());
	            }
	        });
	    }
}
