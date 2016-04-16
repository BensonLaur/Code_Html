package com.uiComponent.filesSelect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dataStructure.Attribute;

import static com.uiComponent.filesSelect.FileSelectPanel.htmlAttribute;

public class FontDemoPanel extends JPanel{
	public static int panelSize_X =429;
	public static int panelSize_Y =446;
	
	private int baseFactor = 11;
	private int ScaleFactor = 2;
	
	private int X_start = 10;
	private int Y_start = 20;
	private int space_X = 1;						
	private int lineDistance_Y = 1;
	private int tab_X = space_X *4;
	private int currentX = X_start;
	private int currentY = Y_start;
	
	private String baseColor = "black";
    private String  keywordColor = "purple";
    private String commentColor="gray";
    private String stringColor="blue";
    private int FontSize= 3;
    private String FontStyle = "宋体";
    
	String s_line1 = new String("-------------------------   java   --------------------------");
	String s_import = new String("import");
	String s_package = new String("package ;");
	String s_comment1 = new String("/*  comment 1  */");
	String s_public_s  = new String("public static");
	String s_void_main = new String("void main (String[] args){");
	
	String s_System_o_p = new String("System.out.println(");
	String s_hello_c_h = new String("\" Hello Code_Html \"");
	String s_endofPrintln = new String(");");

	String s_comment2 = new String("//  comment 2");
	String s_endofMain = new String("}");
	
	String s_line2 = new String("--------------------------   c++   --------------------------");
	String s_include = new String("# include <iostream>");
	String s_define = new String("# define MAXNUM 100");
	String s_int = new String("int");
	String s_main = new String("main() {");
	String s_for = new String("for( ");
	String s_i = new String(" = 0 ; i < MAXNUM ; i ++ ) {");
	String s_cout = new String("cout ");
	String s_out = new String("<< i << endl ;");
	String s_endof_for_main = new String("}");
    
    public FontDemoPanel(){
    	setNewAttribute(htmlAttribute);
    	this.revalidate();
    }
    
    public void update(){
    	setNewAttribute(htmlAttribute);
    	this.repaint();	// 此步骤不可少，因为调用此update()函数后，repaint() 线程较先执行
    	this.revalidate();
    }
    
    private void setNewAttribute(Attribute newAttribute){
    	baseColor = newAttribute.getBaseColor();
        keywordColor = newAttribute.getKeywordColor();
        commentColor=newAttribute.getCommentColor();
        stringColor=newAttribute.getStringColor();
       FontSize= newAttribute.getFontSize();
       FontStyle = newAttribute.getFontStyle();
    }
    
    protected void paintComponent(Graphics g){
    	
    	space_X = FontSize * 3;
    	tab_X = space_X * 4;
    	currentX = X_start;
    	currentY = Y_start;
    	
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, this.getWidth()-2, this.getHeight()-2);
    	
    	Font baseFont = new Font(FontStyle,Font.PLAIN, htmlSize(FontSize));
    	Font keywordFont = new Font(FontStyle,Font.BOLD, htmlSize(FontSize));
    	Font StringFont = new Font(FontStyle,Font.PLAIN, htmlSize(FontSize));
    	Font commentFont = new Font(FontStyle,Font.PLAIN, htmlSize(FontSize));
    	
    	Font titleFont = new Font("Times New Roman",Font.BOLD,htmlSize(3));
    	g.setFont(titleFont);
    	FontMetrics fm_title = g.getFontMetrics(g.getFont());
    	int TilteLineDistance_Y =  fm_title.getHeight();
    	g.setFont(keywordFont);
    	FontMetrics fm = g.getFontMetrics(g.getFont());
    	lineDistance_Y = fm.getHeight();
    	
    	
    	// draw titleString "-------------- java ---------------"
    	g.setColor(Color.BLACK); 	g.setFont(titleFont);
    	g.drawString(s_line1, currentX, currentY);
    	currentX = X_start;			 currentY +=  fm_title.getDescent();
    	
    	currentY += fm.getLeading();
    	currentY += fm.getAscent();      
    	// draw java Code
    	g.setColor(htmlColor(keywordColor)); 	g.setFont(keywordFont);
    	g.drawString(s_import, currentX, currentY);													
    	currentX += fm.stringWidth(s_import); currentX += space_X;
    	
    	g.setColor(htmlColor(baseColor)); 				g.setFont(baseFont);
    	g.drawString(s_package, currentX, currentY);
    	currentX = X_start;
    	currentY+= lineDistance_Y;
    	
    	g.setColor(htmlColor(commentColor)); 	g.setFont(commentFont);
    	g.drawString(s_comment1, currentX, currentY);
    	currentX = X_start;
    	currentY+= lineDistance_Y;
    	
    	g.setColor(htmlColor(keywordColor)); 	g.setFont(keywordFont);
    	g.drawString(s_public_s, currentX, currentY);
    	currentX += fm.stringWidth(s_public_s);   currentX += space_X;

    	g.setColor(htmlColor(baseColor)); 	g.setFont(commentFont);
    	g.drawString(s_void_main, currentX, currentY);
    	currentX = X_start;   currentX += tab_X;
    	currentY+= lineDistance_Y;
    	
    	g.setColor(htmlColor(baseColor)); 	g.setFont(commentFont);
    	g.drawString(s_System_o_p, currentX, currentY);
    	currentX += fm.stringWidth(s_System_o_p);  
    	
    	g.setColor(htmlColor(stringColor)); 	g.setFont(commentFont);
    	g.drawString(s_hello_c_h, currentX, currentY);
    	currentX += fm.stringWidth(s_hello_c_h);  
    	
    	g.setColor(htmlColor(baseColor)); 	g.setFont(commentFont);
    	g.drawString(s_endofPrintln, currentX, currentY);
    	currentX = X_start;  
    	currentY+= lineDistance_Y;
    	
    	g.setColor(htmlColor(commentColor)); 	g.setFont(commentFont);
    	g.drawString(s_comment2, currentX, currentY);
    	currentY+= lineDistance_Y;
    	
    	g.setColor(htmlColor(baseColor)); 	g.setFont(commentFont);
    	g.drawString(s_endofMain, currentX, currentY);
    	currentX = X_start;  
    	currentY+= lineDistance_Y;
    	
    	
    	// draw titleString "-------------- C++ ---------------"
    	g.setColor(Color.BLACK); 	g.setFont(titleFont);
    	g.drawString(s_line2, currentX, currentY);
    	currentX = X_start;			 currentY += fm_title.getDescent();

    	// draw c++ Code
    	currentY += fm.getLeading();
    	currentY += fm.getAscent();   

    	g.setColor(htmlColor(commentColor)); 	g.setFont(baseFont);
    	g.drawString(s_include, currentX, currentY);
    	currentX = X_start;	
    	currentY += lineDistance_Y;
    	
    	g.setColor(htmlColor(commentColor)); 	g.setFont(baseFont);
    	g.drawString(s_define, currentX, currentY);
    	currentX = X_start;			
    	currentY += lineDistance_Y;

    	g.setColor(htmlColor(keywordColor)); 	g.setFont(keywordFont);
    	g.drawString(s_int, currentX, currentY);
    	currentX += fm.stringWidth(s_int);   currentX += space_X;

    	g.setColor(htmlColor(baseColor)); 	g.setFont(baseFont);
    	g.drawString(s_main, currentX, currentY);
    	currentX = X_start;	currentX += tab_X;
    	currentY += lineDistance_Y;

    	g.setColor(htmlColor(baseColor)); 	g.setFont(baseFont);
    	g.drawString(s_for, currentX, currentY);
    	currentX += fm.stringWidth(s_for);

    	g.setColor(htmlColor(keywordColor)); 	g.setFont(keywordFont);
    	g.drawString(s_int, currentX, currentY);
    	currentX += fm.stringWidth(s_int); 
    	
    	g.setColor(htmlColor(baseColor)); 	g.setFont(baseFont);
    	g.drawString(s_i, currentX, currentY);
    	currentX =X_start;
    	currentY += lineDistance_Y;

    	g.setColor(htmlColor(baseColor)); 	g.setFont(commentFont);
    	g.drawString("\t", currentX, currentY);
    	currentX += tab_X;
    	
    	g.setColor(htmlColor(baseColor)); 	g.setFont(baseFont);
    	g.drawString(s_cout+s_out, currentX, currentY);
    	currentX =X_start;    	currentX += tab_X;
    	currentY += lineDistance_Y;

    	
    	g.setColor(htmlColor(baseColor)); 	g.setFont(baseFont);
    	g.drawString(s_endof_for_main, currentX, currentY);
    	currentX =X_start;
    	currentY += lineDistance_Y;
    	
    	g.setColor(htmlColor(baseColor)); 	g.setFont(baseFont);
    	g.drawString(s_endof_for_main, currentX, currentY);
    	currentX =X_start;
    	currentY += lineDistance_Y;
    	
    	
    	if(panelSize_X < 5+fm_title.stringWidth(s_line1)) panelSize_X = 5+fm_title.stringWidth(s_line1);
    	if (panelSize_X < 5+fm.stringWidth(s_void_main)+180)  panelSize_X = 5+fm.stringWidth("\t"+s_void_main)+180;
    	panelSize_Y = 10+fm_title.getDescent() * 2+ fm.getLeading() * 2 +fm.getAscent() * 2 + TilteLineDistance_Y * 2 + lineDistance_Y * 14;
    	
    	//System.out.println("panelSize_X = " + panelSize_X +"   panelSize_Y = " +panelSize_Y +"         stringwidth="+fm.stringWidth("\t"+s_void_main)+"       <---in ----" );

    	this.revalidate();
    }
    
    private int htmlSize(int size){
    	int adjustFactor = ( size == 7 ? size+1 : size );
    	int h_size = baseFactor+size * (ScaleFactor+ adjustFactor /2);
    	return h_size;
    }
    
    private Color htmlColor(String color){
    	switch(color){
    	case "red":	
    		return Color.RED;
    	case "black":
    		return Color.BLACK;
    	case "purple":	
    		return Color.decode(Integer.valueOf("960096",16).toString()) ;
    	case "green":	
    		return Color.GREEN;
    	case "yellow":	
    		return Color.YELLOW;
    	case "blue":	
    		return Color.BLUE;
    	case "cyan":	
    		return Color.CYAN;
    	case "gray":	
    		return Color.GRAY;
    	default :
    		return Color.BLACK;
    	}
    }
    
    /*public static void main(String  []args){
    	JFrame f = new JFrame();
    	f.setSize(400, 400);
    	f.add(new FontDemoShow());
    	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	f.setVisible(true);
    }*/
}
