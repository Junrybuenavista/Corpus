 import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;


public class ViewEdit extends JPanel implements  TextListener,TableModelListener
{   
    TextField tx;   
    sem sem;
    Statement st;
    ResultSet set;
    Panelstd table;
    String vec1[][]=new String[0][0];
    String ss[]={"","Name","Last Name","M.I","Email","Contact No.","Student No.","Course"};
    boolean tEvent=false;
	public ViewEdit(sem sem,Statement st)
	{
		
		this.sem=sem;
		sem.getStdPanel(stdpanel());
		this.st=st;				
		tx=new TextField(20);
		tx.addTextListener(this);	
	    add(new JLabel("Search Student"));add(tx);
	    
	    
	}
	
    public void clearTxt(){tx.setText("");}
	public void tableChanged(TableModelEvent e)
	   	{
	   		if(tEvent==false)return;
		            DefaultTableModel model = (DefaultTableModel)e.getSource();
       			    int row = e.getFirstRow();
                    int column = e.getColumn();
        			String columnName = model.getColumnName(column);       			
        		    String data2 =(String) model.getValueAt(row,column);
        		    String MyId=table.getValue(table.getTb().getSelectedRow(),0);
		            System.out.println(swp(columnName)+"  "+data2+"   "+columnName+"   "+MyId);
		            
		            try
		            {
		            st.execute("UPDATE  Tbl_Studnt_Info set "+swp(columnName)+"='"+data2+"' where ID="+MyId);
		            }catch(Exception ee){ee.printStackTrace();}
		}
	public String swp(String sss)
	{    
	    String ret="";
		if(sss.equals(""))ret="ID";
		if(sss.equals("Name"))ret="Fname";
		if(sss.equals("Last Name"))ret="Lname";
		if(sss.equals("M.I"))ret="MI";
		if(sss.equals("Email"))ret= "Email";
		if(sss.equals("Contact No."))ret= "ContactNo";
		if(sss.equals("Student No."))ret= "StudentNo";
		
		return ret;
	}	
	public Dimension getPreferredSize()
    	{
    		return new Dimension(1000,800);
    	}
   public JPanel stdpanel(){table=new Panelstd(); table.setData(vec1,ss);table.getTb().getModel().addTableModelListener(this); return table.getme();} 	
   public void textValueChanged(TextEvent e)   
   {
   	  
   	  try
   	  {
   	  	while(table.getTb().getRowCount()!=0)
                     		{
                                    tEvent=false;
                     	        	table.delete(0);
                     		}

   	  
   	  	set=st.executeQuery("Select * from Tbl_Studnt_Info where Lname LIKE '"+tx.getText()+"%'");
   	  	String vec[]=new String[8];
	    while(set.next())
	    {
	        vec[0]=set.getString("ID");
	        vec[1]=set.getString("Fname");
	        vec[2]=set.getString("Lname");
	        vec[3]=set.getString("MI");
	        vec[4]=set.getString("Email");
	        vec[5]=set.getString("ContactNo");
	        vec[6]=set.getString("StudentNo");
	        vec[7]=set.getString("Course");
	        tEvent=false;
	        table.insert(vec);
	            	
	    }
   	     
   	   tEvent=true;
   	   }catch(Exception ee){ee.printStackTrace();}
   	
   	
   }
   class Panelstd extends JPanel 
   {
   	
   	     JTable tb;                    
    	 DefaultTableModel mod;        
    	
   	     public Panelstd()
   	     {
   	     	   
   	     	   setLayout(new BorderLayout());
   	     	   mod=new DefaultTableModel();
	    	   tb=new JTable(mod){  
  				public boolean isCellEditable(int row, int column){  
    
    				if(row < getRowCount() && column ==0||row < getRowCount() && column ==7)
    				{
    					 return false;
    				}
    			 
    				return true;  
  					}  
		        };
	    	    
	    	    tb.setFont(new Font("Curz MT",Font.BOLD,10));
              	tb.setRowHeight(20);
                tb.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN);
                add(JTable.createScrollPaneForTable(tb),BorderLayout.CENTER);
   	     }
   	    public void insert(String [] a)
    					{
    						mod.insertRow(mod.getRowCount(), a);
    					}
   	    public void setData(String[][] ss,String[] s){mod.setDataVector(ss,s);}
   	    public JPanel getme(){return this;}
   	    public JTable getTb(){return tb;}
   	    public void delete(int a){mod.removeRow(a);}
   	   	public String getValue(int r,int c){return (String)mod.getValueAt(r,c);}
   }

}