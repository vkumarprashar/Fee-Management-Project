package accountant;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import login.*;
import connect.*;

public class dashboardacc extends JFrame implements ActionListener 
{
	
	JPanel mainP=new JPanel(null);
	JPanel headP=new JPanel(null);
	JPanel sideP=new JPanel(null);
	JLabel clgname=new JLabel("<html>Chandigarh College of<br>"+"Engineering and Technology");
	JLabel clgimg=new JLabel();
	JButton home=new JButton("Home");
	JButton dashboard=new JButton("DashBoard");
	JButton managestud=new JButton ("<html>Manage Students<br>"+"Details");
	JButton manageacc=new JButton("Manage Accounts");
	JButton contactus=new JButton("Contact Admin");
	JButton logout=new JButton("LogOut");
	
	JLabel name=new JLabel("Name: -");
	static JTextField namef=new JTextField();
	JLabel id=new JLabel("Employee ID: -");
	public static JTextField idf=new JTextField();
	JLabel dob=new JLabel("Date of Birth: -");
	static JTextField dobf=new JTextField();
	JLabel contact=new JLabel("Contact No: -");
	static JTextField contactf=new JTextField();
	JLabel emailid=new JLabel("Email Id: -");
	static JTextField emailidf= new JTextField();
	JLabel password=new JLabel("Password:- ");
	JButton passwordb=new JButton("Change Password.. ?");

	JButton save= new JButton("Save Details");
	
	Connection con;
	PreparedStatement stat;
	ResultSet set;
	
	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN ="^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	Font fontb=new Font("Times New Roman", Font.BOLD,16);
	Font fontl=new Font("Times New Roman", Font.PLAIN,18);
	static String  accid, accname;
	public dashboardacc(String idt)
	{
		accid=idt;
	
		System.out.println(accid);
		add(mainP);
		add(headP);
		add(sideP);
		
		setSize(860,680);
		setTitle("Accountant");
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(220,20);
		setResizable(false);
		
		headP.setBounds(0, 0, 860, 160);
	    sideP.setBounds(0, 160, 195, 500);
	    mainP.setBounds(195, 160, 660, 500);
	 
	    mainP.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Your Personal Details", TitledBorder.CENTER, TitledBorder.TOP, new Font("Times New Roman",Font.BOLD,22), new Color(20, 75, 75)));
		headP.setBorder(new LineBorder(Color.black,1));
		sideP.setBorder(new LineBorder(Color.black,1));
		mainP.setBackground(Color.WHITE);
		sideP.setBackground(Color.white);
		headP.setBackground(Color.LIGHT_GRAY);
		
		headP.add(clgname);
		headP.add(clgimg);
		
		clgimg.setBounds(0,0 ,860 ,160 );
		try{
			Image img=ImageIO.read(getClass().getResource("/DSC_0003.JPG"));
			clgimg.setIcon(new ImageIcon(img));
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		clgname.setBounds(100,10 ,700 ,100 );
		clgname.setFont(new Font("Times New Roman",Font.BOLD,40));
		clgname.setForeground(Color.getHSBColor(88,70 , 89));
		
		
		sideP.add(home);
		sideP.add(dashboard);
		sideP.add(managestud);
		sideP.add(manageacc);
		sideP.add(contactus);
		sideP.add(logout);
		
		home.setBounds(15,185 ,165 ,40 );
		dashboard.setBounds(15,245 ,165 ,40 );
		managestud.setBounds(15,305 ,165 ,40 );
		manageacc.setBounds(15,365 ,165 ,40 );
		contactus.setBounds(15,425 ,165 ,40 );
		logout.setBounds(15,585 ,165 ,40 );

		home.setFont(fontb);
		dashboard.setFont(fontb);
		dashboard.setForeground(Color.red);
		managestud.setFont(fontb);
		manageacc.setFont(fontb);
		contactus.setFont(fontb);
		logout.setFont(fontb);
		
		mainP.add(name);
		mainP.add(namef);
		mainP.add(id);
		mainP.add(idf);
		mainP.add(contact);
		mainP.add(contactf);
		mainP.add(dob);
		mainP.add(dobf);
		mainP.add(emailid);	
		mainP.add(emailidf);
		mainP.add(password);
		mainP.add(passwordb);
		mainP.add(save);
		
		name.setBounds(170,50 ,110 ,30 );
		name.setFont(fontl);
		namef.setBounds(290,50 ,180 ,30 );
		namef.setEditable(false);
		id.setBounds(170, 100,120 ,30 );
		id.setFont(fontl);
		idf.setBounds(290,100 ,180 ,30 );
		idf.setEditable(false);
		dob.setBounds(170,150 ,110 ,30 );
		dob.setFont(fontl);
		dobf.setBounds(290,150 ,180 ,30 );
		dobf.setEditable(false);
		contact.setBounds(170,200 ,110 ,30 );
		contact.setFont(fontl);
		contactf.setBounds(290,200 ,180 ,30 );
		emailid.setBounds(170,250 ,110 ,30 );
		emailid.setFont(fontl);
		emailidf.setBounds(290,250 ,180 ,30 );
		password.setBounds(180,300 ,110 ,30 );
		password.setFont(fontl);
		passwordb.setBounds(290,300 ,180 ,30 );
		passwordb.setFont(fontb);
		save.setBounds(260,370 ,150 ,40);
		save.setFont(fontb);
		
		try{
			
			con=connect.connection();
			stat= con.prepareStatement("select * from accountant where employeeid=(?)");
			stat.setString(1, accid);
			set = stat.executeQuery();
			while(set.next())
			{
				namef.setText(""+(set.getString("name")));
				System.out.println(set.getString("name"));
				idf.setText(accid);
				dobf.setText(""+(set.getDate("dob")));
				contactf.setText(""+(set.getString("contactno")));
				emailidf.setText(""+(set.getString("emailid")));	
			}
			
		}catch(SQLException cnf)
		{
			cnf.printStackTrace();
		}
		
		accname=namef.getText();
		home.addActionListener(this);
		dashboard.addActionListener(this);
		managestud.addActionListener(this);
		manageacc.addActionListener(this);
		contactus.addActionListener(this);
		logout.addActionListener(this);
		passwordb.addActionListener(this);
		save.addActionListener(this);
	}
	
	public static void main(String[] args)
	{
		new dashboardacc(accid);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
	String ch=e.getActionCommand();
	
		if(ch.equals("Save Details"))
		{
			pattern = Pattern.compile(EMAIL_PATTERN);
	        if(emailidf.getText().matches(EMAIL_PATTERN))
	        {
			try{
				String contact= contactf.getText();
				String id=idf.getText();
				
				con=connect.connection();
				  stat = con.prepareStatement("UPDATE accountant SET contactno=?, emailid=? WHERE employeeid=?");
				  stat.setString(1,contact);
				  stat.setString(2, emailidf.getText());
				  stat.setString(3, id);
				
				int i=stat.executeUpdate();
				if(i>0)
				{
					JOptionPane.showMessageDialog(this,"Details Updated","Updated",JOptionPane.OK_OPTION);
				}
				
			}catch(SQLException sq)
			{
				sq.printStackTrace();
			}
		}  else
        {
        	JOptionPane.showMessageDialog(this, "Enter the valid Email Address","FORMAT NOT SUPPORTED!", JOptionPane.INFORMATION_MESSAGE);
        }
			
		}
	
		if(ch.equals("Change Password.. ?"))
		{
			new password(idf.getText());
		}
		
		if(ch.equals("Home"))
		{
			new home();
			this.dispose();
		}
		if(ch.equals("Contact Admin"))
		{
			new contactadmin();
			this.dispose();
		}
		if(ch.equals("<html>Manage Students<br>"+"Details"))
		{
			new managestud();
			this.dispose();
		}
		if(ch.equals("Manage Accounts"))
		{
			new manageacc();
			this.dispose();
		}
		if(ch.equals("LogOut"))
		{
			int i=JOptionPane.showConfirmDialog(this, "Are You SURE you want to Logout. ?", "Logout.?", JOptionPane.YES_NO_OPTION);
			if(i==0)
			{
				new login();
				this.dispose();
			}
			
		}
	}
	
	
}
