import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;
public class Search extends JPanel implements ActionListener ,ListSelectionListener, TextListener
{   
    TextField tx;
    JButton b2;
    sem sem;
    Statement st;
    ResultSet set;
    JList list;
    String ID;
    String Course,Lname,Fname;
    String Cname,StdNo;
    JLabel l1;
	public Search(sem sem,Statement st)
	{
		this.sem=sem;
		this.st=st;
		
		b2=new JButton("Print");
    	//b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //b1.setBorderPainted(false);
  	    //b1.setContentAreaFilled(false);
        //b1.setFocusPainted(false);
        
	    b2.addActionListener(this);
	    
        list=new JList();
		list.setFixedCellWidth(280);
		list.setVisibleRowCount(4);
		list.addListSelectionListener(this);
				
		tx=new TextField(20);
		tx.addTextListener(this);
		JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(350,200);}};

	
		JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(300,200);}};
	
	
		//p1.add(new JLabel(new ImageIcon("STI.jpg")));
	    p1.add(new JLabel("Search Student"));p1.add(tx);p1.add(b2);
	    
	    p2.add(new JScrollPane(list));
	    add(p1);add(p2);
	
	}
	public void clearTxt(){tx.setText("");}
//	public void textValueChanged(TextEvent e)
   //       	{
    //         try
     //         { 
     //          set=st.executeQuery("Select shelf_no from shelf_tbl where shelf LIKE '"+tx.getText()+"%'");
     //         }catch(Exception ee){}
              
   //       	}
     public void actionPerformed(ActionEvent e)
     {
     		  
   				PrintUtilities.printComponent(Cname,StdNo,Course,sem.getme1(),sem.getme2(),0.8);
   			
     }   
        	
	public void valueChanged(ListSelectionEvent ee)
	{
		
	  
	  try
	  {
	   StringTokenizer tok=new StringTokenizer(list.getSelectedValue()+"",",");
	   String selected2=tok.nextToken();String selected=tok.nextToken().trim();	
	   set=st.executeQuery("select * from Tbl_Studnt_Info where Lname='"+selected2+"' and Fname='"+selected+"'");
	   set.next(); ID=set.getString("Id"); Course=set.getString("Course");StdNo=set.getString("StudentNo");
	   Cname=set.getString("Lname")+", "+set.getString("Fname")+" "+set.getString("MI")+".";
	     	 
   	   sem.setGrade(ID,Course);
	   
      }catch(Exception eee){}
	}
	
	public Dimension getPreferredSize()
    	{
    		return new Dimension(1000,800);
    	}
   public void textValueChanged(TextEvent e)   
   {
   	
   	  try
   	  {
   	  	
   	  	String listdata[]=new String[0]; 
   	  	list.setListData(listdata);
   	  	if(tx.getText().equals("")){sem.showNull();return;}
   	  	
   	  	
   	  	set=st.executeQuery("Select count(*) as n from Tbl_Studnt_Info where Lname LIKE '"+tx.getText()+"%'");set.next();
   	  	int stncnt=Integer.parseInt(set.getString("n"));
   	  	listdata =new String[stncnt]; 	  	
   	  	
   	  		set=st.executeQuery("Select * from Tbl_Studnt_Info where Lname LIKE '"+tx.getText()+"%'");
   	  		int countlist=0;
   	  		while(set.next())
   	  		{   Lname=set.getString("Lname");
   	  		    Fname=set.getString("Fname");
   	  		  
   	  			listdata[countlist]=Lname+", "+Fname;
   	  			countlist++;
   	  		}
   	  	   list.setListData(listdata);
   	     
   	   
   	  }catch(Exception ee){ee.printStackTrace();JOptionPane.showMessageDialog(this,"Student Not Found ! ");}
   	
   	
   }

}