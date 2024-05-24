import java.awt.*;
import javax.swing.*;
import java.awt.print.*;

public class PrintUtilities implements Printable{

    private Component componentToBePrinted;
    private Component componentToBePrinted2;
    private double scale;
    private String corz,Cname,No;
    String ss[]={"BACHELOR OF SCIENCE IN INFORMATION TECHNOLOGY","BACHELOR OF SCIENCE IN COMPUTER SCIENCE","DIPLOMA IN COMPUTER & ELECTRONICS TECHNOLOGY"};
    public static void printComponent(String Cname,String No,String corz,Component c,Component d,double scale) {
        new PrintUtilities(Cname,No,corz,c,d,scale).print();
    }

    public PrintUtilities(String Cname,String No,String corz,Component componentToBePrinted,Component componentToBePrinted2, double scale) {
        this.componentToBePrinted = componentToBePrinted;
        this.componentToBePrinted2 = componentToBePrinted2;
        this.scale = scale;
        this.corz=corz;
        this.Cname=Cname;
        this.No=No;
    
    }

    public void print() {
        PrinterJob printJob = PrinterJob.getPrinterJob();
        printJob.setPrintable(this);
        if (printJob.printDialog())
            try {
                printJob.print();
            } catch(PrinterException pe) {
                System.out.println("Error printing: " + pe);
            }
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) 
    {     
    	    int numpage=1;
    	    if(corz.equals("DCET"))numpage=0;  
    	    if(componentToBePrinted==null)numpage=0;
    	    
        	if (pageIndex > numpage ) 
        	{
               return(NO_SUCH_PAGE);
        	} 
        	else 
        	{
           		 Graphics2D g2d = (Graphics2D)g;
           		 g2d.scale(scale,scale);
           		 
           	
           		  ImageIcon printImage = new ImageIcon("corpus.jpg");
                 
                   g.drawImage(printImage.getImage(), 40, 5,60,70, null); 
                  
           		 
           		 g2d.translate(pageFormat.getImageableX()+40,pageFormat.getImageableY()+120);
                 if(pageIndex==0)componentToBePrinted.print(g2d);
                 else componentToBePrinted2.print(g2d);
                 g2d.setFont(new Font("Arial",Font.BOLD,15));
                 if(corz.equals("BSIT"))g2d.drawString(ss[0],150,-80);
                 if(corz.equals("BSCS"))g2d.drawString(ss[1],150,-80);
                 if(corz.equals("DCET"))g2d.drawString(ss[2],150,-80);
                 
                 
             
			    
			     //g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,(float)0.3));
                
                
                 
                 
                 	                
                 g2d.drawString("Name : "+Cname,0,-20);g2d.drawString("Students No : "+No,420,-20);
                 g2d.drawLine(0,-15,750,-15);
                 
            
            
            return(PAGE_EXISTS);
       		}
    }

}
