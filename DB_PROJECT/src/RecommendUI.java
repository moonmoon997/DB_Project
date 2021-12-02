import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class RecommendUI extends JFrame{
	
	
	JButton recommend_btn = new JButton("메뉴추천");
	JButton favorite_btn = new JButton("즐겨찾기");
	JButton review_btn = new JButton("리뷰");
	JButton randomdice_btn=new JButton("랜덤추천");
	JTextField foodfield = new JTextField(30);
	JList reslist = new JList();
	DefaultListModel resmodel;
	JComboBox maincbb;
	JComboBox midcbb;
	
	public RecommendUI() throws SQLException{
		setTitle("메뉴추천프로그램");
		Container c = getContentPane();
		c.setLayout(null);
		setSize(600, 400);
		
		Foodlist fl=new Foodlist();
		Food[] food=fl.get_Foodlist();
		//
		List<String> mainc = new ArrayList<String>();
		mainc.add("");
		for(int i=0;i<food.length;i++) {
			if(!mainc.contains(food[i].get_maincatagory()))
				mainc.add(food[i].get_maincatagory());
		}
		Collections.sort(mainc);
		maincbb = new JComboBox(mainc.toArray());
		//
		List<String> midc = new ArrayList<String>();
		for(int i=0; i<food.length;i++) {
			if (food[i].get_maincatagory().equals(maincbb.getSelectedItem().toString()) && !midc.contains(food[i].get_middleclass()))
				midc.add(food[i].get_middleclass());
		}
		Collections.sort(midc);
		midcbb = new JComboBox(mainc.toArray());
		
		JLabel lb1=new JLabel("대분류 : ");
		JLabel lb2=new JLabel("중분류 : ");
		
		//�슂�냼 �쐞移섏��젙
		recommend_btn.setBounds(0,0,150,50);
		favorite_btn.setBounds(150,0,150,50);
		review_btn.setBounds(300,0,150,50);
		maincbb.setBounds(150,120,150,50);
		midcbb.setBounds(150,220,150,50);
		randomdice_btn.setBounds(380, 120,100,50);
		foodfield.setBounds(380,220,100,50);
		foodfield.setEnabled(false);
		//reslist.setBounds(480,220,100,50);
		lb1.setBounds(80,130,70,30);
		lb2.setBounds(80,230,70,30);
		
		//踰꾪듉�씠踰ㅽ듃�떖湲�
		recommend_btn.addActionListener(new ButtonAction());
		favorite_btn.addActionListener(new ButtonAction());
		review_btn.addActionListener(new ButtonAction());
		randomdice_btn.addActionListener(new ButtonAction());
		maincbb.addItemListener(new ItemAction());
		midcbb.addItemListener(new ItemAction());
		
		//�슂�냼 異붽�
		c.add(recommend_btn);
		c.add(favorite_btn);
		c.add(review_btn);
		c.add(randomdice_btn);
		c.add(maincbb);
		c.add(midcbb);			
		c.add(foodfield);		
		//c.add(reslist);
		c.add(lb1);
		c.add(lb2);
				
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private class ItemAction implements ItemListener{
		int kk=0;
		
		public void itemStateChanged(ItemEvent arg0) {
			kk++;
			if(kk%2==0) {
				midcbb.removeAllItems();
				try {
					Foodlist fl=new Foodlist();
					Food[] food = fl.get_Foodlist();					
					List<String> midc=new ArrayList<String>();
					midc.add("");
					for(int i=0; i<food.length; i++) {
						if(food[i].get_maincatagory().equals(maincbb.getSelectedItem().toString()) && !midc.contains(food[i].get_middleclass()))
							midc.add(food[i].get_middleclass());
					}
					for(int i=0; i<midc.toArray().length; i++) {
						midcbb.addItem(midc.toArray()[i]);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private class ButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//�옖�뜡踰꾪듉 �겢由�
			if(e.getSource()==randomdice_btn) {
				//control �겢�옒�뒪 �깮�꽦
				String maincatagory = maincbb.getSelectedItem().toString();
				String middleclass = midcbb.getSelectedItem().toString();
				RecommendSystem rs=new RecommendSystem();
				RestaurantSystem rss=new RestaurantSystem();
				try {
					String finalresult = rs.recommend(maincatagory, middleclass);
					String[] resresult = rss.recommend(maincatagory, middleclass);
					foodfield.setText(finalresult);
					//for (int i=0;i<resresult.length;i++) {
					//	resmodel.addElement(resresult[i].toString());
					//}
					//reslist = new JList(resresult);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
			
			////////////////�긽�떒踰꾪듉//////////////////////
			//�긽�떒 硫붾돱異붿쿇踰꾪듉 �겢由�
			else if(e.getSource()==recommend_btn) {
				try {
					RecommendUI RUI = new RecommendUI();
					dispose();
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
			//�긽�떒 利먭꺼李얘린踰꾪듉 �겢由�
			else if(e.getSource()==favorite_btn) {
				
			}
			//�긽�떒 由щ럭踰꾪듉 �겢由�
			else if(e.getSource()==review_btn) {
				try {
					ReviewUI RevUI = new ReviewUI();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		RecommendUI RUI = new RecommendUI();
	}
}
