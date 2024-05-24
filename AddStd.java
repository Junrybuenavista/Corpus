import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class AddStd extends JPanel implements ActionListener
{   
    JTextField txName,txLastName,txMail,txTel,txStdNo,txAdd;
    JComboBox txCorz;
    Statement st;
    ResultSet set;
	public AddStd(Statement st)
	{
		this.st=st;
		JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(250,80);}};
		JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(250,80);}};
		JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(250,80);}};
		p1.setLayout(new GridLayout(3,2));
		p2.setLayout(new GridLayout(3,2));
		p3.setLayout(new GridLayout(3,2));
		
				
		txName=new JTextField();
		txLastName=new JTextField();
		txMail=new JTextField();
		txTel=new JTextField();
		txStdNo=new JTextField();
		txAdd=new JTextField();
		String com[]={"BSIT","DCET","BSCS"};
		txCorz=new JComboBox(com);
	
		
		
		p1.add(new JLabel("First Name"));
		p1.add(txName);
		p1.add(new JLabel("Last Name"));
		p1.add(txLastName);
		p1.add(new JLabel("MI"));
		p1.add(txAdd);
		
		
		p2.add(new JLabel("Contact No"));
		p2.add(txTel);
		p2.add(new JLabel("Student No"));
		p2.add(txStdNo);
		
		p2.add(new JLabel("Course"));
		p2.add(txCorz);
		p3.add(new JLabel("Email Address"));
		p3.add(txMail);
		txMail.addActionListener(this);
		
	    add(p1);add(p2);add(p3);
	
	}
	public void actionPerformed(ActionEvent e)
	{
	  try{	
		  st.execute("INSERT INTO Tbl_Studnt_Info(Fname,Lname,MI,Email,ContactNo,StudentNo,Course) values ('"+txName.getText()+"','"+txLastName.getText()+"','"+txAdd.getText()+"','"+txMail.getText()+"','"+txTel.getText()+"','"+txStdNo.getText()+"','"+txCorz.getSelectedItem()+"')");
		  
		  set=st.executeQuery("Select ID from Tbl_Studnt_Info where Fname='"+txName.getText()+"' and Lname='"+txLastName.getText()+"'");
		  set.next();String ID=set.getString("ID");
		  
		  if(txCorz.getSelectedItem().equals("BSIT"))
		  {
		  st.execute("INSERT INTO Tbl_Grd_bsit(StudentID) values("+ID+")");
		  }
		  if(txCorz.getSelectedItem().equals("DCET"))
		  {
		  st.execute("INSERT INTO Tbl_Grd_dcet(StudentID) values("+ID+")");
		  }
		  if(txCorz.getSelectedItem().equals("BSCS"))
		  {
		  st.execute("INSERT INTO Tbl_Grd_bscs(StudentID) values("+ID+")");
		  } 
		  JOptionPane.showMessageDialog(this,"New Student Successfuly Added !");
	     }catch(Exception ee){ ee.printStackTrace();JOptionPane.showMessageDialog(this,"Error Input !");}
	     txName.setText("");
		 txLastName.setText("");
		 txMail.setText("");
		 txTel.setText("");
		 txStdNo.setText("");
		 txAdd.setText("");
		
	}
	public Dimension getPreferredSize()
    	{
    		return new Dimension(800,82);
    	}
}