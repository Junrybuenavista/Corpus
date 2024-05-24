
import javax.swing.*;import javax.swing.table.DefaultTableModel;
import java.awt.*;import java.util.*;
import java.awt.event.*;





 class j extends JPanel 
        
    	{

    	JTable tb;                    
    	 DefaultTableModel mod;        
    	
    
    		public j()
    			{
                
    		  mod=new DefaultTableModel();
	    	  tb=new JTable(mod)
	    	  {  
  				public boolean isCellEditable(int row, int column){  
    
    				if(row < getRowCount() && column == 5)
    				{
    					 return true;
    				}  
    				return false;  
  					}  
		        };
    			
              	

             



                
    			tb.setFont(new Font("Curz MT",Font.BOLD,10));
              	tb.setRowHeight(20);
                tb.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN);
                add(JTable.createScrollPaneForTable(tb));


    			}
    			public DefaultTableModel getMod(){return mod;}
    				public void insert(String []a)
    					{
    						mod.insertRow(mod.getRowCount(), a);
    					}
    				public void delete(int a)
    					{
    						mod.removeRow(a);
    					}
    				public int getcount(){return mod.getRowCount();}
    		    	public String getValue(int r,int c){return (String)mod.getValueAt(r,c);}
    		    	public void setValue(int r,int c,String s){mod.setValueAt(s,r,c);}
    		        public void setData(String[][] ss,String[] s){mod.setDataVector(ss,s);}
    		//public Dimension getPreferredSize(){return new Dimension(710,200);}
           public void setme()
           	{
           		tb.setPreferredScrollableViewportSize(new Dimension(700,getcount()*20));
           	}
      
           	public int getHR(){return tb.getRowHeight();}
           	public JTable getTb(){return tb;}
           	
           	

    	  
	

    
   }