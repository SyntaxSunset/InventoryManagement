package mainPack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;



public class Sales {

	private JFrame frame;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales window = new Sales();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sales() {
		initialize();
		BuildConnection();
		loadTable5();
		loadTable6();
		
	}
	
	
	Connection con;
    PreparedStatement prestm;
    ResultSet rst;
    
	public void BuildConnection() {
	 	         
	        try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/InventoryManagement", "root", "Sayan2004#");
				System.out.println("Established connection");
			} catch (SQLException e) {
				
				e.printStackTrace();
			}    
	}
	
	
	public void loadTable5()
	{
		try {
			
			prestm = con.prepareStatement("select *from Sold ");
			rst = prestm.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rst));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void loadTable6()
	{
		try {
			
			prestm = con.prepareStatement("select *from Sales ");
			rst = prestm.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(rst));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 898, 603);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 224));
		panel.setBounds(6, 6, 886, 563);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblSales = new JLabel("Sales");
		lblSales.setFont(new Font("Maku", Font.BOLD, 40));
		lblSales.setBounds(368, 0, 91, 68);
		panel.add(lblSales);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 240));
		panel_1.setBounds(19, 97, 405, 192);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(6, 6, 393, 180);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(new Color(255, 255, 240));
		panel_1_1.setBounds(459, 97, 408, 192);
		panel.add(panel_1_1);
		panel_1_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(6, 6, 396, 180);
		panel_1_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblNewLabel_1 = new JLabel("Sold Product");
		lblNewLabel_1.setFont(new Font("Mali", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(19, 69, 82, 16);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(19, 298, 405, 227);
		panel.add(scrollPane_2);
		
		JTextArea area1 = new JTextArea();
		scrollPane_2.setViewportView(area1);
		
		area1.setLineWrap(true);
		area1.setWrapStyleWord(true);
		
		JLabel lblNewLabel_1_1 = new JLabel("Orders");
		lblNewLabel_1_1.setFont(new Font("Mali", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(459, 69, 61, 16);
		panel.add(lblNewLabel_1_1);
	
		JButton btnNetSold = new JButton("Net Sold");
		btnNetSold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			        prestm = con.prepareStatement("SELECT SUM(total) AS net_sold FROM Sold");
			        rst = prestm.executeQuery();       
			        
			        if (rst.next()) {
			        	double netSold = rst.getDouble("net_sold");
			        	area1.setText(""); 
			            area1.setText("----------------------------------------------\n");
			            area1.setText("                	   Total Order Amount               \n");
			            area1.setText("----------------------------------------------\n");
			            area1.setText("Total Sold Amount: Rs" + netSold);
			            
			            
			        }
			        area1.setCaretPosition(0);
			    } catch (SQLException e1) {
			        e1.printStackTrace();
			    }
				
			}
		});
		btnNetSold.setOpaque(true);
		btnNetSold.setForeground(Color.WHITE);
		btnNetSold.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 14));
		btnNetSold.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNetSold.setBackground(new Color(0, 128, 128));
		btnNetSold.setBounds(509, 337, 139, 49);
		panel.add(btnNetSold);
		
		JButton btnNetBought = new JButton("Net Bought");
		btnNetBought.setOpaque(true);
		btnNetBought.setForeground(Color.WHITE);
		btnNetBought.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 14));
		btnNetBought.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnNetBought.setBackground(new Color(0, 128, 128));
		btnNetBought.setBounds(509, 440, 139, 49);
		panel.add(btnNetBought);
		
		JButton btnAnalyse = new JButton("Analyse");
		btnAnalyse.setOpaque(true);
		btnAnalyse.setForeground(Color.WHITE);
		btnAnalyse.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 14));
		btnAnalyse.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnAnalyse.setBackground(new Color(0, 128, 128));
		btnAnalyse.setBounds(689, 390, 139, 49);
		panel.add(btnAnalyse);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Buttons b = new Buttons();
				b.setVisible(true);
			}
		});
		btnBack.setOpaque(true);
		btnBack.setIcon(new ImageIcon(Sales.class.getResource("/images/Exit.png")));
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Nanum Myeongjo", Font.PLAIN, 14));
		btnBack.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnBack.setBackground(new Color(153, 102, 51));
		btnBack.setBounds(19, 22, 114, 35);
		panel.add(btnBack);
		
	}

	public void setVisible(boolean b) {
		frame.setVisible(b);
		
	}
}
