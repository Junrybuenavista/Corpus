import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class sem extends JPanel implements TableModelListener
{   
    j t1[];
    j t2[];
    j t3[];
    int count1=0;
    CardLayout card,card2;
    ResultSet set;
    Statement st;
    String Course,ID;
    JScrollPane scrolls;
    JPanel pit,pdcet,pcs;
    String DCET[]={"Tbl_1yr_1sem_dcet","Tbl_1yr_2sem_dcet","Tbl_2yr_1sem_dcet","Tbl_2yr_2sem_dcet"};
    String BSCS[]={"Tbl_1yr_1sem_bscs","Tbl_1yr_2sem_bscs","Tbl_2yr_1sem_bscs","Tbl_2yr_2sem_bscs","Tbl_3yr_1sem_bscs","Tbl_3yr_2sem_bscs","Tbl_3yr_Summr_bscs","Tbl_4yr_1sem_bscs","Tbl_4yr_2sem_bscs"};
    String BSIT[]={"Tbl_1yr_1sem_bsit","Tbl_1yr_2sem_bsit","Tbl_2yr_1sem_bsit","Tbl_2yr_2sem_bsit","Tbl_3yr_1sem_bsit","Tbl_3yr_2sem_bsit","Tbl_4yr_1sem_bsit","Tbl_4yr_2sem_bsit"};
    String SEM[]={"First Year First Semester","First Year Second Semester","Second Year First Semester","Second Year Second Semester","Third Year First Semester","Third Year Second Semester","Fourth Year First Semester","Fourth Year Second Semester"}; 
    String SEM2[]={"First Year First Semester","First Year Second Semester","Second Year First Semester","Second Year Second Semester","Third Year First Semester","Third Year Second Semester","                   Summer Class                 ","Fourth Year First Semester","Fourth Year Second Semester"}; 
    String vec1[][];
    String vec2[]={"Course Code","Descriptive Title","Lec","Lab","Prerequisite","Grade"};
    String Grades[]=new String[60];  
    PrintUtilities printme;
    JPanel printPanel1;
    JPanel printPanel2,StdPanel,panelnull,mainpanel;
    
    public JScrollPane gets(){return scrolls;}
	public sem(Statement st)	
	{
        this.st=st;
	 	t1=new j[8];
	    t2=new j[8];
	    t3=new j[9];
        card=new CardLayout();
        card2=new CardLayout();
        setBackground(Color.WHITE);
        setLayout(card);
        scrolls = new JScrollPane(this, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,  
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );  
                scrolls.setPreferredSize(new Dimension(100,100));
                
        
        
                printPanel1=new JPanel();
        		printPanel2=new JPanel();
        		panelnull=new JPanel();
        		mainpanel=new JPanel();
        		mainpanel.setLayout(card2);
        		mainpanel.add(scrolls,"this");
       		 	printPanel1.setBackground(Color.WHITE);
        		printPanel2.setBackground(Color.WHITE);
        		panelnull.setBackground(Color.WHITE); 
                
              
                
              pit=new JPanel();
			  pit.setLayout(new FlowLayout());
			  pit.setBackground(Color.WHITE);
			  init1();			  
			  setPanel(pit,BSIT,t1,8);
			   
			  
		      //add(pit,"it");				
			  
           add(panelnull,"null");
        
        
        
	 	
	}
	public JPanel getMainPanel(){return mainpanel;}
	public void showThis()
	{
	   card2.show(mainpanel,"this");	
	}
	public void showStd()
	{
	   card2.show(mainpanel,"Std");	
	}
	public void showNull()
	{
	   card.show(this,"null");	
	}
	public void getStdPanel(JPanel pan){StdPanel=pan;mainpanel.add(StdPanel,"Std");}
	public void print1(){printme.print();}
	public void setPanel(JPanel p,String arr[],j t[],int num1)
	{ 
	    try
	 	{
	 	 	
	     for(int i=0;i<num1;i++)
	     {	
	        t[i]=new j();
	        
	        
	        
	        
	       try{
	        	set=st.executeQuery("Select count(*) as n from "+arr[i]);
	        	set.next();vec1=new String[Integer.parseInt(set.getString("n"))][6];	        		 		
	 	    	set=st.executeQuery("Select * from "+arr[i]);
	          }catch(Exception e2){break;}
	        
	        
	        		 		
	 	    
	 	    int ctr=0;
	 	    while(set.next())
	 	    {
	 	    	vec1[ctr][0]=set.getString("Course_Code");
	 	    	vec1[ctr][1]=set.getString("Descriptive_Title");
	 	    	vec1[ctr][2]=set.getString("Lec");
	 	    	vec1[ctr][3]=set.getString("Lab");
	 	    	vec1[ctr][4]=set.getString("Prerequisite");
	 	        vec1[ctr][5]=Grades[count1];
	 	        
	 	    	ctr++;
	 	    	count1++;
	 	    
	 	    	
	 	    }
	 	    t[i].setData(vec1,vec2);
	 	    t[i].getTb().getModel().addTableModelListener(this);
	 	    t[i].setme();
	 	    
	 	        TableColumn column=t[i].getTb().getColumnModel().getColumn(1);
                column.setPreferredWidth(250);
                column=t[i].getTb().getColumnModel().getColumn(2);
                column.setPreferredWidth(20);
                column=t[i].getTb().getColumnModel().getColumn(3);
                column.setPreferredWidth(20);
                column=t[i].getTb().getColumnModel().getColumn(5);
                column.setPreferredWidth(30);
                column=t[i].getTb().getColumnModel().getColumn(0);
                column.setPreferredWidth(100);
                
	 	    //JPanel curr=printPanel1;
	 	    //if(num1>3)curr= printPanel2;
	 	    if(i<4)
	 	    {
	 	    if(num1==9)printPanel1.add(new JLabel(SEM2[i]+"                                         "));
		      	   else printPanel1.add(new JLabel(SEM[i]+"                                         "));
	 	     
	 	    printPanel1.add(JTable.createScrollPaneForTable(t[i].getTb()));
	 	    printPanel1.add(JTable.createScrollPaneForTable(t[i].getTb()));
	 	    }
	 	    else
	 	    {
	 	    if(num1==9)printPanel2.add(new JLabel(SEM2[i]+"                                         "));
		      	   else printPanel2.add(new JLabel(SEM[i]+"                                         "));
	 	    printPanel2.add(JTable.createScrollPaneForTable(t[i].getTb()));
	 	    printPanel2.add(JTable.createScrollPaneForTable(t[i].getTb()));
	 	    }
	 	    
	 	    
	 	    //*****************************************************
	 	     //if(num1==9){p.add(new JLabel(SEM2[i]+"                                         "));p.add(new JLabel(SEM2[i]+"                                         "));}
		     //  	   else {p.add(new JLabel(SEM[i]+"                                         "));p.add(new JLabel(SEM[i]+"                                         "));}
	 	     
	 	    //p.add(JTable.createScrollPaneForTable(t[i].getTb()));
	 	    //p.add(JTable.createScrollPaneForTable(t[i].getTb()));
	 	    
	     }
	     p.add(printPanel1);
	     p.add(printPanel2);
	     count1=0;
	    
	    }catch(Exception e){count1=0;e.printStackTrace();}
	     
	 
	}
	public JPanel getme1()
	{
	   return printPanel1;
	}
	public JPanel getme2()
	{	  
	   return printPanel2;
	}
	public void init1()
	{
		        printPanel1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(710,820);}};
        		printPanel2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(710,1000);}};
       		 	printPanel1.setBackground(Color.WHITE);
        		printPanel2.setBackground(Color.WHITE);  
	}
	public void setGrade(String Id,String Course)
	{
		ID=Id;this.Course=Course;
		try
		{
			if(Course.equals("BSIT"))
			{
				set=st.executeQuery("select * from Tbl_Grd_bsit where StudentID="+Id);
				set.next();
				for(int i=0;i<57;i++)
				{   
					Grades[i]=set.getString(i+2);
					System.out.println(Grades[i]);
				}
			  pit=new JPanel();
			  pit.setLayout(new FlowLayout());
			  pit.setBackground(Color.WHITE);
			  init1();			  
			  setPanel(pit,BSIT,t1,8);
			  
		      add(pit,"it");				
			  card.show(this,"it");
					  
			  
			  	
        		       		
			
			}
			if(Course.equals("DCET"))
			{
				set=st.executeQuery("select * from Tbl_Grd_dcet where StudentID="+Id);
				set.next();
				for(int i=0;i<23;i++)
				{   
				    
					Grades[i]=set.getString(i+2);
					
				}
				pdcet=new JPanel();
			    init1();
			    setPanel(pdcet,DCET,t2,4);
			    add(pdcet,"dcet");
			    pdcet.setBackground(Color.WHITE); 
				card.show(this,"dcet");
			}
			if(Course.equals("BSCS"))
			{
				set=st.executeQuery("select * from Tbl_Grd_bscs where StudentID="+Id);
				set.next();
				for(int i=0;i<60;i++)
				{   
					Grades[i]=set.getString(i+2);
					System.out.println(Grades[i]);
				}
				pcs=new JPanel();
				init1();
			    setPanel(pcs,BSCS,t3,9);			    
			    add(pcs,"cs");
			    pcs.setBackground(Color.WHITE); 
				card.show(this,"cs");
			}
			
		}catch(Exception ee){ee.printStackTrace();}
	}
	 public void tableChanged(TableModelEvent e)
           	  {
                    
       			    DefaultTableModel model = (DefaultTableModel)e.getSource();
       			    int row = e.getFirstRow();
                    int column = e.getColumn();
        			String columnName = model.getColumnName(column);
        			System.out.println("event");
        		    String data2 =(String) model.getValueAt(row,column);
        		    String data=convert(data2);
        		    
        		    if(data.equals("Invalid")){JOptionPane.showMessageDialog(null,"Invalid Input !");data="";}
        		   
        		    else{
        		          if(checkgrade(takeSpace(aaa(model.getValueAt(row,4)+"")))){JOptionPane.showMessageDialog(null,"Failed with his Pre Requisite !");data="";}
        		        }
        		    
        		 try{   
        		    if(Course.equals("BSIT")){st.execute("UPDATE  Tbl_Grd_bsit set "+takeSpace(model.getValueAt(row,0)+"")+"='"+data+"' where StudentID="+ID);}
        		    if(Course.equals("DCET")){st.execute("UPDATE  Tbl_Grd_dcet set "+takeSpace(model.getValueAt(row,0)+"")+"='"+data+"' where StudentID="+ID);}
        		    if(Course.equals("BSCS")){st.execute("UPDATE  Tbl_Grd_bscs set "+takeSpace(model.getValueAt(row,0)+"")+"='"+data+"' where StudentID="+ID);}
        		   }catch(Exception ee){ee.printStackTrace();JOptionPane.showMessageDialog(this,"Opps Naay Error !");}
        		    setGrade(ID,Course);
        		    
        		   
                    System.out.println(data);
     
              }
    public String  aaa(String ss){String temp="";
    for(int s=0;s<ss.length();s++)
    	{
    	if(ss.charAt(s)==';')break;
    	temp+=ss.charAt(s);
    	}
    return temp;}          
    public String convert(String ss)
    { String ret ="";
      
      double ss2=0;
      if(ss.equalsIgnoreCase("INC"))return "INC";
      if(ss.equalsIgnoreCase("Credited"))return "CREDITED";
      if(ss.equalsIgnoreCase("NG"))return "NG";
      if(ss.equalsIgnoreCase("drp"))return "DRP";
      if(ss.equalsIgnoreCase("trf"))return "TRF";
      try{
    	   ss2=Double.parseDouble(ss);
         }
       catch(Exception eee){return "Invalid";}   
    	if(ss2>100)ret ="Invalid";
    	else if(ss2>=98||ss2==1.00)ret ="1.00";
    	else if(ss2>=95||ss2==1.25)ret ="1.25";
    	else if(ss2>=92||ss2==1.50)ret ="1.50";
    	else if(ss2>=89||ss2==1.75)ret ="1.75";
    	else if(ss2>=86||ss2==2.00)ret ="2.00";
    	else if(ss2>=83||ss2==2.25)ret ="2.25";
    	else if(ss2>=80||ss2==2.50)ret ="2.50";
    	else if(ss2>=77||ss2==2.75)ret ="2.75";
    	else if(ss2>=75||ss2==3.00)ret ="3.00";
    	else if(ss2>=60||ss2==5.00)ret ="5.00";
    	else if(ss2==0)ret ="FAIL";
    	else ret ="Invalid";
     return ret;	
    }
    public boolean checkgrade(String ck)
    { boolean ret =false;
      
      System.out.println(ck);
    		try
    		{   if(Course.equals("BSCS"))
    		    set=st.executeQuery("select "+ck+" from Tbl_Grd_bscs where StudentID="+ID);
    		    if(Course.equals("DCET"))
    		    set=st.executeQuery("select "+ck+" from Tbl_Grd_dcet where StudentID="+ID);
    		    if(Course.equals("BSIT"))
    		    set=st.executeQuery("select "+ck+" from Tbl_Grd_bsit where StudentID="+ID);
				set.next();		
				String ds=set.getString(ck);
				if(ds.equals("FAIL"))return true;
				double dd=Double.parseDouble(ds);
				System.out.println(dd);
				if(dd==5)return true;		
    		}
    		catch(Exception ee){}
      return ret;	
    }          
	public String takeSpace(String ss)
	{
	    return ss.replace(' ','_');
		 
	}
	public Dimension getPreferredSize(){return new Dimension(710,1600);}
    
}
