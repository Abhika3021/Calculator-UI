package calui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Calc
{
	JFrame fr=new JFrame("Calculator");
	JTextField tb=new JTextField("0");
	JButton [] bt=new JButton[20];
	JPanel pa=new JPanel();
	
	public Calc()
	{
		createFrame();
	}
	
	private void createFrame()
	{
		fr.setSize(400,400);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(3);
		fr.setLayout(null);
		addTextBox();
		fr.setVisible(true);
	}
	
	private void addTextBox()
	{
		tb.setBounds(20,20,350,40);
		fr.add(tb);
		tb.setFont(new Font("arial", 1, 20));
		tb.setHorizontalAlignment(4);
		tb.setEditable(false);
		tb.setBackground(Color.white);
		tb.setBorder(BorderFactory.createLineBorder(Color.black,1));
		addButtons();
	}
	
	private void addButtons()
	{
		pa.setBounds(20, 90, 350, 250);
		fr.add(pa);
		pa.setLayout(new GridLayout(5,4,5,5));
		String [] str= {"Back","CE","C","%","7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
		Font fo= new Font("arial", 0, 20);
		CalListener listener= new CalListener();
		for(int i=0; i<str.length; i++)
		{
			bt[i]=new JButton(str[i]);
			bt[i].addActionListener(listener);
			bt[i].setFont(fo);
			if(i==3 || i==7 || i==11 || i==15 || i==18 || i==19)
				bt[i].setForeground(Color.red);
			else
				bt[i].setForeground(Color.blue);
			pa.add(bt[i]);
		}
		bt[17].setFont(new Font("arial", 1,30));
	}
	
	class CalListener implements ActionListener
	{
		String op=null;
		int num1=0;
		String ab=null; 
		public void actionPerformed(ActionEvent e) 
		{
			JButton bb= (JButton)e.getSource();
			//code to get text from clicked button
			String v1=bb.getText(); //v1-> button value
			//code to get text from Textbox
			String v2=tb.getText(); //v2-> textbox value
			// condition for +,-,*,/
			if(bb==bt[7] || bb==bt[11] || bb==bt[15] || bb==bt[19])
			{
				cal();
				op="yes";
				num1=Integer.parseInt(tb.getText()); //textbox current value to num
				ab=v1; // reference of button
			}
			if(bb==bt[4] || bb==bt[5] || bb==bt[6] || bb==bt[8] || bb==bt[9] || bb==bt[10] || bb==bt[12] || bb==bt[13] || bb==bt[14] || bb==bt[16])
			{
				if(v2.equals("0") || op!=null)
				{
					//this code will replace the value of textbox
					tb.setText(v1);
					op=null;
				}
				else
				{
					//this code will concatenate value of textbox with the value button and set into textbox
					tb.setText(v2+v1);
				}
		     }
			if(bb==bt[0]) // Back button clicked
			{
				int x=Integer.parseInt(v2);
				x=x/10;
				tb.setText(String.valueOf(x));
			}
			if(bb==bt[1]) // CE button clicked
			{
				tb.setText("0");
			}
			if(bb==bt[2]) // C button clicked
			{
				tb.setText("0");
				op=null;
				ab=null;
				num1=0;
			}
			if(bb==bt[18])//user clicks =
			{
				cal();
			}
	 }
		
		private void cal()
		{
			if(ab==null)
				return;
			int res=0;
			int num2=Integer.parseInt(tb.getText());
			if(ab.equals("+"))
				res=num1+num2;
			if(ab.equals("-"))
				res=num1-num2;
			if(ab.equals("*"))
				res=num1*num2;
			if(ab.equals("/"))
				res=num1/num2;
				tb.setText(String.valueOf(res));
		}
  }
	
	public static void main(String[] args)
	{
		new Calc();
	}
}
	