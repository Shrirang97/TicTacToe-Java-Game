import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TicTacToe extends JFrame implements ActionListener,Runnable{

	private static final long serialVersionUID = 1L;
	private static JButton b[] = new JButton[9]; 
	private static int sign,count=0;
	private Thread thread = new Thread(this);
	private static boolean running = false;
	private static int win=-1;
	
	TicTacToe()
	{
		setSize(608,640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setBackground(Color.BLACK);
		setLayout(null);
		
		JLabel m = new JLabel("Choose your option : ");
		m.setBounds(220,90,300,200);
		m.setFont(new Font("Arial", Font.PLAIN, 20));
		m.setForeground(Color.WHITE);
		add(m);
		
		JButton zero = new JButton("ZERO");
		zero.setBounds(170,220,120,60);
		zero.setFont(new Font("Arial", Font.PLAIN, 20));
		zero.setBackground(Color.GRAY);
		zero.setForeground(Color.WHITE);
		zero.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sign = 1;
				gameframe();
			}
		});
		
		JButton cross = new JButton("CROSS");
		cross.setBounds(330,220,120,60);
		cross.setFont(new Font("Arial", Font.PLAIN, 20));
		cross.setBackground(Color.GRAY);
		cross.setForeground(Color.WHITE);
		cross.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sign = 0;
				gameframe();
			}
		});
		
		add(zero);
		add(cross);
		setVisible(true);
		
	}
	
	private synchronized void gameframe()
	{
		getContentPane().removeAll();
		//getContentPane().setLayout(new GridLayout(3, 3));
		int x=0,y=0,i=0;
		for(int k=0;k<3;k++)
		{
			for(int h=0;h<3;h++)
			{
				b[i] = new JButton("");
				System.out.println(x+"\t"+y);
				b[i].setBounds(x, y, 200,200);
				b[i].setBackground(Color.BLACK);
				b[i].setForeground(Color.RED);
				b[i].setFont(new Font("Arial", Font.PLAIN, 100));
				b[i].addActionListener(this);
				add(b[i++]);
				x+=200;
			}
			x=0;
			y+=200;
			
	     }
		revalidate();
		repaint();
		running = true;
		thread.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AbstractButton b = (AbstractButton) e.getSource();
		if(b.getText()=="")
		   {
			count++;
			if(sign==1)
				b.setText(String.valueOf(--sign));
			else
				b.setText(String.valueOf(++sign));
			
		   }
		else
		   {
			   System.out.println("NOTHING HAPPENS!.....");
		   }
	}
    
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		System.out.println("Thread starts............");
		while(running)
		{
			if(count>=9)
				break;
			for(int i=0;i<7;i+=3)
			{
				String s1=b[i].getText(), s2=b[i+1].getText(), s3=b[i+2].getText();
				if(!(s1=="" || s2=="" || s3==""))
				{
					if(s1.equals(s2) && s2.equals(s3))
					{
						running = false;
						win = Integer.parseInt(s1);
						break;
					}
				}
			}
			
			
			for(int i=0;i<3;i++)
			{
				String s1=b[i].getText(), s2=b[i+3].getText(), s3=b[i+6].getText();
				if(!(s1=="" || s2=="" || s3==""))
				{
					if(s1.equals(s2) && s2.equals(s3))
					{
						running = false;
						win = Integer.parseInt(s1);
						break;
					}
				}
			}
			
			String s1=b[0].getText(), s2=b[4].getText(), s3=b[8].getText();
			if(!(s1=="" || s2=="" || s3==""))
			{
				if(s1.equals(s2) && s2.equals(s3))
				{
					running = false;
					win = Integer.parseInt(s1);
					break;
				}
			}
			
			s1=b[2].getText();
			s3=b[6].getText();
			if(!(s1=="" || s2=="" || s3==""))
			{
				if(s1.equals(s2) && s2.equals(s3))
				{
					running = false;
					win = Integer.parseInt(s1);
					break;
				}
			}
					
		}
		
		for(int i=0;i<9;i++)
			b[i].setEnabled(false);
		String s;
		if(win<0)
			s = "+++ DRAW +++";
		else
			s = "WINNER IS  "+win;
		JLabel m = new JLabel(s);
		m.setBounds(120,190,600,150);
		m.setFont(new Font("Arial", Font.PLAIN, 50));
		m.setForeground(Color.BLUE);
		getContentPane().removeAll();
		setLayout(null);
		add(m);
		revalidate();
		repaint();
		System.out.println("winner is  "+win);
		System.out.println("Thread stops...........");
		thread.stop();
	}
	
	public static void main(String[] args) throws Exception {
		new TicTacToe();
		
		

	}

	
	
}
