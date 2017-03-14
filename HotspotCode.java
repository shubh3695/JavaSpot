import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class GUICommand implements java.awt.event.ActionListener
{
  static JTextArea jssid;
  static JTextArea jpassword;
  JFrame jf = new JFrame();
  static JButton hon;
  static JButton hoff;
  
  GUICommand() { java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    jssid = new JTextArea();
    jpassword = new JTextArea();
    jf.setTitle("JavaSpot v1.0");
    java.awt.Font font = new java.awt.Font("Verdana", 1, 14);
    jf.setLocation(width / 4 - jf.getSize().width / 6, height / 8 - jf.getSize().height / 3);
    

    jssid.setFont(font);
    jssid.setForeground(java.awt.Color.GRAY);
    jpassword.setFont(font);
    jpassword.setForeground(java.awt.Color.GRAY);
    JScrollPane scrollPane = new JScrollPane(jssid);
    JScrollPane scrollOut = new JScrollPane(jpassword);
    scrollPane.setBounds(110, 100, 160, 25);
    scrollOut.setBounds(110, 160, 160, 25);
    jssid.setToolTipText("Enter SSID Name");
    jpassword.setToolTipText("Enter your Password");
    hon = new JButton("Switch On");
    hoff = new JButton("Switch Off");
    habout = new JButton("About the Developer");
    hconn = new JButton("Connected Devices");
    hon.setBounds(138, 250, 100, 30);
    hoff.setBounds(138, 310, 100, 30);
    hconn.setBounds(107, 370, 150, 30);
    habout.setBounds(200, 520, 160, 30);
    hon.addActionListener(this);
    hoff.addActionListener(this);
    habout.addActionListener(this);
    hconn.addActionListener(this);
    jf.add(hon);
    jf.add(hoff);
    jf.add(habout);
    jf.add(scrollOut);
    jf.add(scrollPane);
    jf.add(hconn);
    
    jf.setLayout(null);
    

    jf.setSize(400, 600);
    jf.setVisible(true); }
  
  static JButton habout;
  static JButton hconn;
  public static void main(String[] args) { new GUICommand(); }
  

  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == hon) {
      String line = "";String l = "";
      String ssidname = jssid.getText().trim();
      String pass = jssid.getText().trim();
      if (ssidname.isEmpty()) {
        JOptionPane.showMessageDialog(jf.getComponent(0), "Empty HotSpot Name");
      }
      else if (pass.isEmpty()) {
        JOptionPane.showMessageDialog(jf.getComponent(0), "Empty Password");
      }
      else {
        ProcessBuilder builder = new ProcessBuilder(new String[] {
          "cmd.exe", "/c", "netsh wlan set hostednetwork  mode=allow ssid=" + 
          ssidname + "  key = " + pass });
        try
        {
          Process p = builder.start();
          BufferedReader r1 = new BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
          for (;;)
          {
            line = r1.readLine();
            if (line == null) break;
            l = l + line + "\n";
          }
          
        }
        catch (Exception exp)
        {
          exp.printStackTrace();
          
          ProcessBuilder builder2 = new ProcessBuilder(new String[] {
            "cmd.exe", "/c", "netsh wlan start hostednetwork" });
          builder2.redirectErrorStream(true);
          try {
            Process p1 = builder2.start();
            BufferedReader r2 = new BufferedReader(new java.io.InputStreamReader(p1.getInputStream()));
            for (;;) {
              line = r2.readLine();
              if (line == null) break;
              l = l + line + "\n";
            }
          }
          catch (IOException exp) {
            exp.printStackTrace();
            
            JOptionPane.showMessageDialog(jf.getComponent(0), l);
          }
        }
      } } else { if (e.getSource() == hoff) {
        String line = "";String l = "";
        ProcessBuilder builder2 = new ProcessBuilder(new String[] {
          "cmd.exe", "/c", "netsh wlan stop hostednetwork" });
        builder2.redirectErrorStream(true);
        try
        {
          Process p1 = builder2.start();
          BufferedReader r2 = new BufferedReader(new java.io.InputStreamReader(p1.getInputStream()));
          for (;;) {
            line = r2.readLine();
            if (line == null) break;
            l = l + line;
          }
        }
        catch (IOException exp)
        {
          exp.printStackTrace();
          
          JOptionPane.showMessageDialog(jf.getComponent(0), l);
        }
      }
      if (e.getSource() == habout)
      {
        String l = "This amazing lightweight Hotspot application has been developed by\nShubham Saxena. He is presently pursuing BTech from KNIT Sultanpur. \nNow uninstall your Connectify and run this App.";
        

        JOptionPane.showMessageDialog(jf.getComponent(0), l);

      }
      else if (e.getSource() == hconn)
      {
        String line = "";String l = "";
        ProcessBuilder builder2 = new ProcessBuilder(new String[] {
          "cmd.exe", "/c", "netsh wlan show hostednetwork" });
        builder2.redirectErrorStream(true);
        try
        {
          Process p1 = builder2.start();
          BufferedReader r2 = new BufferedReader(new java.io.InputStreamReader(p1.getInputStream()));
          for (;;) {
            line = r2.readLine();
            if (line == null) break;
            l = l + line + "\n";
          }
        }
        catch (IOException exp)
        {
          exp.printStackTrace();
          
          JOptionPane.showMessageDialog(jf.getComponent(0), l);
        }
      }
    }
  }
}