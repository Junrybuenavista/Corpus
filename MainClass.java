import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class MainClass extends JFrame implements ActionListener
{
	JMenu menu;
	JMenuItem i1,i2,i3;
	Connection conn;
    Statement st;
    AddStd std;
    sem sem;
    Search src;
    JPanel p1;
    CardLayout lay;
    login log;
    ViewEdit edit;
    public void actionPerformed(ActionEvent e)
    {
    	
    	if(e.getSource()==i1)
    	{
    		lay.show(p1,"c1");
    		src.clearTxt();
    		sem.showThis();
    		sem.showNull();
    	}
    	if(e.getSource()==i2)
    	{
    		lay.show(p1,"c2");
    		sem.showThis();
    		sem.showNull();
    	}
    	if(e.getSource()==i3)
    	{
    		lay.show(p1,"c3");
    		sem.showStd();
    	}
    }
    
	public MainClass()
	{   
	    
	     
	    p1=new JPanel(){public Dimension getPreferredSize()
    	{
    		return new Dimension(1000,100);
    	}};
    	lay=new CardLayout();
    	p1.setLayout(lay);
        addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
        System.exit(0);}});
        
        setLayout(new BorderLayout());
        
        try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Database.mdb;DriverID=22;READONLY=true;) ","",""); 
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 st=conn.createStatement();
	      }catch(Exception e){e.printStackTrace();}
	    
	    
	    
	    std=new AddStd(st);
	    sem=new sem(st);
	    src=new Search(sem,st);
	    edit=new ViewEdit(sem,st);
	    
	    menu=new JMenu("Menu");  
	    i1=new JMenuItem("Find Student");
	    i2=new JMenuItem("Add Student");
	    i3=new JMenuItem("View Student");
	    menu.add(i1);menu.add(i2);menu.add(i3);  
	 
	    
	    i1.addActionListener(this);
	    i2.addActionListener(this);
	    i3.addActionListener(this);
	    
	    p1.add(src,"c1");
	    p1.add(std,"c2");
	    p1.add(edit,"c3");
	    add(p1,BorderLayout.NORTH);
	    add(sem.getMainPanel(),BorderLayout.CENTER);
	    JMenuBar bar=new JMenuBar();
	    bar.add(menu);
	    
	    
	    
	    setJMenuBar(bar);
		show();
		setLocation(200,20);
		setSize(800,700);
		setResizable(false);
		log=new login(this,st);
	}
	public static void main(String args[])
	{
		new MainClass();
	}
}