
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.Color;

public class AdminPage extends JFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static queryManager q;
	private JPanel contentPane;
	private JTextField textFieldNameAddEvent;
	private JTextField textFieldDayAddEvent;
	private JTextField textFieldMonthAddEvent;
	private JTextField textFieldYearAddEvent;
	private JTextField textFieldLocation;
	private JTextField textFieldRequiredVolunteers;
	private JTable table;
	private JTextField textFieldSearch;
	private JTable tableSearch;
	private JTable table_2;
	private JTextField textFieldSponsorName;
	private JTextField textFieldAddUserName;
	private JTextField textFieldUserName;
	private JTextField textFieldAddParticipant;
	private JTextField textFieldEmailID;
	private JPasswordField passwordFieldAddUser;
	private JPasswordField passwordFieldAddUserRetype;
	private JPasswordField passwordFieldSignIn;
	private JPanel panelAdminMain;
	private JButton btnAddEvent;
	private JButton btnSearchEventsMain;
	private JButton btnAddVolunteers;
	private JButton btnAddSponsorsMain;
	private JButton btnAddUserMain;
	private JButton btnLogOutAdmin;
	private JPanel panelAddEvent;
	private JButton btnAddEvent_1;
	private JButton btnBack;
	private JPanel panelStaffMain;
	private JButton btnAddParticipant;
	private JButton btnLogOut;
	private JPanel panelAddUser;
	private JButton btnCreateUser;
	private JButton btnBackInAddUser;
	private JPanel panelLogin;
	private JButton btnSignIn;
	private JPanel panelAddSponsor;
	private JButton btnBackAddSponsor;
	private JButton btnAddSponsor;
	private JPanel panelAddVolunteer;
	private JButton btnBackAddVolunteer;
	private JButton btnAssignStaff;
	private JPanel panelSearchEvents;
	private JButton btnSearchEvents;
	private JButton btnBackInSearch;
	private eventTableModel eventModel;
	JComboBox<String> comboBoxAssignStaff;
	JPanel panelChooseEvent;
	JComboBox<String> comboBoxSelectEvent;
	JButton btnOk;
	JComboBox<String> comboBox;
	
	
	int currentEventID;
	String currentUser;
	private JTextField textFieldItemQuantity;
	private JButton btnNewButton;
	private JComboBox<String> comboBox_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel label;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		q = queryManager.getQueryManager();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public AdminPage() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		eventModel = new eventTableModel();
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		       
		       panelAddVolunteer = new JPanel();
		       panelAddVolunteer.setBounds(0, 0, 435, 260);
		       contentPane.add(panelAddVolunteer);
		       panelAddVolunteer.setLayout(null);
		       
		       table = new JTable();
		       JScrollPane scrollPaneAddVolunteer = new JScrollPane(table);
		       scrollPaneAddVolunteer.setBounds(0, 0, 435, 126);
		       panelAddVolunteer.add(scrollPaneAddVolunteer);
		       
		       btnBackAddVolunteer = new JButton("Back");
		       btnBackAddVolunteer.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		panelAddEvent.setVisible(false);
		       		panelAddSponsor.setVisible(false);
		       		panelAddUser.setVisible(false);
		       		panelAddVolunteer.setVisible(false);
		       		panelAdminMain.setVisible(true);
		       		panelLogin.setVisible(false);
		       		panelSearchEvents.setVisible(false);
		       		panelStaffMain.setVisible(false);
		       		panelChooseEvent.setVisible(false);
		       	}
		       });
		       btnBackAddVolunteer.setBounds(167, 215, 89, 23);
		       panelAddVolunteer.add(btnBackAddVolunteer);
		       
		       JLabel lblStaffName = new JLabel("Staff Name:");
		       lblStaffName.setHorizontalAlignment(SwingConstants.CENTER);
		       lblStaffName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		       lblStaffName.setBounds(26, 174, 89, 14);
		       panelAddVolunteer.add(lblStaffName);
		       
		       btnAssignStaff = new JButton("Assign Staff");
		       btnAssignStaff.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {
		       		try {
		       			q.assignUser((String)comboBoxAssignStaff.getSelectedItem(),(String)eventModel.getValueAt(table.getSelectedRow(), 0));
		       			JOptionPane.showMessageDialog(AdminPage.this, "Successfully Assigned.", "Success!", JOptionPane.INFORMATION_MESSAGE);
		       		} catch (SQLException e) {
		       			// TODO Auto-generated catch block
		       			e.printStackTrace();
		       		}
		       	}
		       });
		       btnAssignStaff.setFont(new Font("Tahoma", Font.PLAIN, 13));
		       btnAssignStaff.setBounds(281, 171, 126, 23);
		       panelAddVolunteer.add(btnAssignStaff);
		       
		        comboBoxAssignStaff = new JComboBox<String>();
		        comboBoxAssignStaff.setBounds(152, 171, 113, 20);
		        panelAddVolunteer.add(comboBoxAssignStaff);
		        panelAddVolunteer.setVisible(false);
		        panelAddVolunteer.setVisible(false);
		        table.setModel(eventModel);
		        
		        lblNewLabel_11 = new JLabel("");
		        lblNewLabel_11.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/staff.jpg")));
		        lblNewLabel_11.setBounds(0, 126, 435, 134);
		        panelAddVolunteer.add(lblNewLabel_11);
		        panelAddVolunteer.setVisible(false);
		       
		       panelAdminMain = new JPanel();
		       panelAdminMain.setBounds(0, 0, 435, 260);
		       contentPane.add(panelAdminMain);
		       panelAdminMain.setLayout(null);
		       
		       btnAddEvent = new JButton("Add Event");
		       btnAddEvent.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {
		       		if(arg0.getSource() == btnAddEvent)
		       		{
		       			panelAddEvent.setVisible(true);
		       			panelAddSponsor.setVisible(false);
		       			panelAddUser.setVisible(false);
		       			panelAddVolunteer.setVisible(false);
		       			panelAdminMain.setVisible(false);
		       			panelLogin.setVisible(false);
		       			panelSearchEvents.setVisible(false);
		       			panelStaffMain.setVisible(false);
		       			panelChooseEvent.setVisible(false);
		       		}
		       	}
		       });
		       btnAddEvent.setBounds(137, 50, 167, 23);
		       panelAdminMain.add(btnAddEvent);
		       
		       btnSearchEventsMain = new JButton("Search Events");
		       btnSearchEventsMain.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		panelAddEvent.setVisible(false);
		       		panelAddSponsor.setVisible(false);
		       		panelAddUser.setVisible(false);
		       		panelAddVolunteer.setVisible(false);
		       		panelAdminMain.setVisible(false);
		       		panelLogin.setVisible(false);
		       		panelSearchEvents.setVisible(true);
		       		panelStaffMain.setVisible(false);
		       		panelChooseEvent.setVisible(false);
		       		try {
		       			eventModel.refreshForAll("");
		       		} catch (SQLException e1) {
		       			// TODO Auto-generated catch block
		       			e1.printStackTrace();
		       		}
		       		eventModel.fireTableDataChanged();

		       	}
		       });
		       btnSearchEventsMain.setBounds(137, 84, 167, 23);
		       panelAdminMain.add(btnSearchEventsMain);
		       
		       btnAddVolunteers = new JButton("Assign Staff");
		       btnAddVolunteers.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent arg0) {
		       		panelAddEvent.setVisible(false);
		       		panelAddSponsor.setVisible(false);
		       		panelAddUser.setVisible(false);
		       		panelAddVolunteer.setVisible(true);
		       		panelAdminMain.setVisible(false);
		       		panelLogin.setVisible(false);
		       		panelSearchEvents.setVisible(false);
		       		panelStaffMain.setVisible(false);
		       		panelChooseEvent.setVisible(false);
		       		try {
		       			eventModel.refreshForAll("");
		       			eventModel.fireTableDataChanged();
		       			ResultSet rs = q.getUsers();
		       			comboBoxAssignStaff.removeAllItems();
		       			while(rs.next())
		       			{
		       				comboBoxAssignStaff.addItem(rs.getString(1));
		       			}
		       			comboBoxAssignStaff.setSelectedIndex(0);
		       		} catch (SQLException e) {
		       			// TODO Auto-generated catch block
		       			e.printStackTrace();
		       		}
		       	}
		       });
		       btnAddVolunteers.setBounds(137, 118, 167, 23);
		       panelAdminMain.add(btnAddVolunteers);
		       
		       btnAddSponsorsMain = new JButton("Add Sponsors");
		       btnAddSponsorsMain.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		panelAddEvent.setVisible(false);
		       		panelAddSponsor.setVisible(true);
		       		panelAddUser.setVisible(false);
		       		panelAddVolunteer.setVisible(false);
		       		panelAdminMain.setVisible(false);
		       		panelLogin.setVisible(false);
		       		panelSearchEvents.setVisible(false);
		       		panelStaffMain.setVisible(false);
		       		panelChooseEvent.setVisible(false);
		       		try {
		       			eventModel.refreshForAll("");
		       		} catch (SQLException e1) {
		       			// TODO Auto-generated catch block
		       			e1.printStackTrace();
		       		}
		       		eventModel.fireTableDataChanged();
		       	}
		       });
		       btnAddSponsorsMain.setBounds(137, 152, 167, 23);
		       panelAdminMain.add(btnAddSponsorsMain);
		       
		       btnAddUserMain = new JButton("Add New User");
		       btnAddUserMain.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		panelAddEvent.setVisible(false);
		       		panelAddSponsor.setVisible(false);
		       		panelAddUser.setVisible(true);
		       		panelAddVolunteer.setVisible(false);
		       		panelAdminMain.setVisible(false);
		       		panelLogin.setVisible(false);
		       		panelSearchEvents.setVisible(false);
		       		panelStaffMain.setVisible(false);
		       		panelChooseEvent.setVisible(false);
		       	}
		       });
		       btnAddUserMain.setBounds(137, 186, 167, 23);
		       panelAdminMain.add(btnAddUserMain);
		       
		       btnLogOutAdmin = new JButton("Log Out");
		       btnLogOutAdmin.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       		panelAddEvent.setVisible(false);
		       		panelAddSponsor.setVisible(false);
		       		panelAddUser.setVisible(false);
		       		panelAddVolunteer.setVisible(false);
		       		panelAdminMain.setVisible(false);
		       		panelLogin.setVisible(true);
		       		panelSearchEvents.setVisible(false);
		       		panelStaffMain.setVisible(false);
		       		panelChooseEvent.setVisible(false);
		       		System.exit(0);
		       	}
		       });
		       btnLogOutAdmin.setBounds(346, 226, 89, 23);
		       panelAdminMain.add(btnLogOutAdmin);
		       
		       lblNewLabel_2 = new JLabel("");
		       lblNewLabel_2.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/volunteers.png")));
		       lblNewLabel_2.setBounds(0, 0, 435, 260);
		       panelAdminMain.add(lblNewLabel_2);
		       panelAdminMain.setVisible(false);
		       panelAdminMain.setVisible(false);
		      
		       panelChooseEvent = new JPanel();
		       panelChooseEvent.setBounds(0, 0, 435, 260);
		       contentPane.add(panelChooseEvent);
		       panelChooseEvent.setLayout(null);
		       
		       lblNewLabel_7 = new JLabel("");
		       lblNewLabel_7.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/calendar.png")));
		       lblNewLabel_7.setBounds(20, 96, 112, 131);
		       panelChooseEvent.add(lblNewLabel_7);
		       
		       label = new JLabel("");
		       label.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/events.jpg")));
		       label.setBounds(283, 96, 121, 131);
		       panelChooseEvent.add(label);
		       
		        comboBoxSelectEvent = new JComboBox<String>();
		        comboBoxSelectEvent.setBounds(98, 53, 236, 20);
		        panelChooseEvent.add(comboBoxSelectEvent);
		        
		         btnOk = new JButton("OK");
		         btnOk.addActionListener(new ActionListener() {
		         	public void actionPerformed(ActionEvent arg0) {
		         		panelAddEvent.setVisible(false);
 					panelAddSponsor.setVisible(false);
 					panelAddUser.setVisible(false);
 					panelAddVolunteer.setVisible(false);
 					panelAdminMain.setVisible(false);
 					panelLogin.setVisible(false);
 					panelSearchEvents.setVisible(false);
 					panelStaffMain.setVisible(true);
 					panelChooseEvent.setVisible(false);
 					try {
						currentEventID = q.getEventIDfromName((String)comboBoxSelectEvent.getSelectedItem());
						ResultSet rs = q.getSponsorsForEvent(currentEventID);
						comboBox.removeAllItems();
						while(rs.next())
						{
							comboBox.addItem(rs.getString(1));
						}
								
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         	}
		         });
		         btnOk.setBounds(161, 116, 89, 23);
		         panelChooseEvent.add(btnOk);
		         
		         JLabel lblChooseEvent = new JLabel("Choose Event:");
		         lblChooseEvent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		         lblChooseEvent.setBounds(98, 25, 104, 14);
		         panelChooseEvent.add(lblChooseEvent);
		         
		         lblNewLabel_5 = new JLabel("");
		         lblNewLabel_5.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/eventbg.jpg")));
		         lblNewLabel_5.setBounds(0, 0, 435, 260);
		         panelChooseEvent.add(lblNewLabel_5);
		         panelChooseEvent.setVisible(false);
		      
		      panelLogin = new JPanel();
		      panelLogin.setBounds(0, 0, 435, 260);
		      contentPane.add(panelLogin);
		      panelLogin.setLayout(null);
		      
		      JLabel lblNewLabel_1 = new JLabel("User:");
		      lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		      lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblNewLabel_1.setBounds(75, 30, 66, 14);
		      panelLogin.add(lblNewLabel_1);
		      
		      textFieldUserName = new JTextField();
		      textFieldUserName.setBounds(162, 28, 128, 20);
		      panelLogin.add(textFieldUserName);
		      textFieldUserName.setColumns(10);
		      
		      JLabel lblPassword_1 = new JLabel("Password:");
		      lblPassword_1.setHorizontalAlignment(SwingConstants.CENTER);
		      lblPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblPassword_1.setBounds(75, 67, 66, 14);
		      panelLogin.add(lblPassword_1);
		      
		      btnSignIn = new JButton("Sign In");
		      btnSignIn.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      			try {
		      				if( q.isUser(textFieldUserName.getText(), new String(passwordFieldSignIn.getPassword())))
		      				{
		      					if(textFieldUserName.getText().equals("admin") )
		      					{
		      					panelAddEvent.setVisible(false);
		      					panelAddSponsor.setVisible(false);
		      					panelAddUser.setVisible(false);
		      					panelAddVolunteer.setVisible(false);
		      					panelAdminMain.setVisible(true);
		      					panelLogin.setVisible(false);
		      					panelSearchEvents.setVisible(false);
		      					panelStaffMain.setVisible(false);
		      					panelChooseEvent.setVisible(false);
		      					}
		      					else
		      					{
		      						
		      					panelAddEvent.setVisible(false);
		      					panelAddSponsor.setVisible(false);
		      					panelAddUser.setVisible(false);
		      					panelAddVolunteer.setVisible(false);
		      					panelAdminMain.setVisible(false);
		      					panelLogin.setVisible(false);
		      					panelSearchEvents.setVisible(false);
		      					panelStaffMain.setVisible(false);
		      					panelChooseEvent.setVisible(true);
		      					currentUser = textFieldUserName.getText();
		      					ResultSet rs = q.getEventNamesForUser(currentUser);
		      					comboBoxSelectEvent.removeAllItems();
		      					while(rs.next())
		      					{
		      						comboBoxSelectEvent.addItem(rs.getString(1));
		      					}
		      					}
		      					textFieldUserName.setText("");
		      					passwordFieldSignIn.setText("");
		      				}
		      				else
		      				{
		      					JOptionPane.showMessageDialog(AdminPage.this,
		      						    "Check Entered Password and Username.",
		      						    "Wrong Password",
		      						    JOptionPane.ERROR_MESSAGE);
		      				}
		      			} catch (SQLException e1) {
		      				// TODO Auto-generated catch block
		      				e1.printStackTrace();
		      			}
		      		
		      	
		      }});
		      btnSignIn.setBounds(181, 122, 89, 23);
		      panelLogin.add(btnSignIn);
		      
		      passwordFieldSignIn = new JPasswordField();
		      passwordFieldSignIn.setBounds(162, 67, 128, 18);
		      panelLogin.add(passwordFieldSignIn);
		      
		      lblNewLabel_6 = new JLabel("");
		      lblNewLabel_6.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/purpose.png")));
		      lblNewLabel_6.setBounds(0, 0, 435, 260);
		      panelLogin.add(lblNewLabel_6);
		      panelLogin.setVisible(false);
		      panelLogin.setVisible(true);
		      textFieldUserName.addKeyListener(this);
		      passwordFieldSignIn.addKeyListener(this);
		      panelLogin.setVisible(true);
		      
		      panelStaffMain = new JPanel();
		      panelStaffMain.setBounds(0, 0, 435, 260);
		      contentPane.add(panelStaffMain);
		      panelStaffMain.setLayout(null);
		      
		      JLabel lblParticipants = new JLabel("Participants");
		      lblParticipants.setForeground(new Color(255, 255, 255));
		      lblParticipants.setFont(new Font("Tahoma", Font.PLAIN, 17));
		      lblParticipants.setHorizontalAlignment(SwingConstants.CENTER);
		      lblParticipants.setBounds(32, 106, 97, 23);
		      panelStaffMain.add(lblParticipants);
		      
		      JLabel lblName_2 = new JLabel("Name:");
		      lblName_2.setForeground(new Color(255, 255, 255));
		      lblName_2.setHorizontalAlignment(SwingConstants.CENTER);
		      lblName_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblName_2.setBounds(10, 140, 46, 14);
		      panelStaffMain.add(lblName_2);
		      
		      textFieldAddParticipant = new JTextField();
		      textFieldAddParticipant.setBounds(101, 138, 158, 20);
		      panelStaffMain.add(textFieldAddParticipant);
		      textFieldAddParticipant.setColumns(10);
		      
		      JLabel lblNewLabel_3 = new JLabel("Email ID:");
		      lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		      lblNewLabel_3.setBounds(10, 176, 68, 14);
		      panelStaffMain.add(lblNewLabel_3);
		      
		      textFieldEmailID = new JTextField();
		      textFieldEmailID.setBounds(101, 174, 158, 20);
		      panelStaffMain.add(textFieldEmailID);
		      textFieldEmailID.setColumns(10);
		      
		      btnAddParticipant = new JButton("Add Participant");
		      btnAddParticipant.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		try {
					q.createParticipant(textFieldAddParticipant.getText(), textFieldEmailID.getText(), ""+currentEventID);
				textFieldAddParticipant.setText("");
				textFieldEmailID.setText("");
		      		} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      	}
		      });
		      btnAddParticipant.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      btnAddParticipant.setBounds(101, 205, 127, 23);
		      panelStaffMain.add(btnAddParticipant);
		      
		      btnLogOut = new JButton("Log Out");
		      btnLogOut.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		panelAddEvent.setVisible(false);
		      		panelAddSponsor.setVisible(false);
		      		panelAddUser.setVisible(false);
		      		panelAddVolunteer.setVisible(false);
		      		panelAdminMain.setVisible(false);
		      		panelLogin.setVisible(true);
		      		panelSearchEvents.setVisible(false);
		      		panelStaffMain.setVisible(false);
		      		panelChooseEvent.setVisible(false);
		      	}
		      });
		      btnLogOut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      btnLogOut.setBounds(336, 226, 89, 23);
		      panelStaffMain.add(btnLogOut);
		      
		      JLabel lblSponsors = new JLabel("Sponsors");
		      lblSponsors.setFont(new Font("Tahoma", Font.PLAIN, 17));
		      lblSponsors.setHorizontalAlignment(SwingConstants.CENTER);
		      lblSponsors.setBounds(32, 11, 81, 23);
		      panelStaffMain.add(lblSponsors);
		      
		      comboBox = new JComboBox<String>();
		      comboBox.setBounds(10, 45, 68, 20);
		      panelStaffMain.add(comboBox);
		      
		      textFieldItemQuantity = new JTextField();
		      textFieldItemQuantity.setBounds(180, 45, 110, 20);
		      panelStaffMain.add(textFieldItemQuantity);
		      textFieldItemQuantity.setColumns(10);
		      
		      JButton btnAdd = new JButton("Add");
		      btnAdd.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		String eventId = "" + currentEventID;
		      		String Sponsor = (String)comboBox.getSelectedItem();
		      		String Item = (String)comboBox_1.getSelectedItem();
		      		String quant = textFieldItemQuantity.getText();
		      		q.mongoInsert(Sponsor, eventId, Item, quant);
		      		textFieldItemQuantity.setText("");
		      	}
		      });
		      btnAdd.setBounds(310, 44, 57, 22);
		      panelStaffMain.add(btnAdd);
		      
		      comboBox_1 = new JComboBox<String>();
		      comboBox_1.setBounds(89, 45, 81, 21);
		      comboBox_1.setEditable(true);
		      panelStaffMain.add(comboBox_1);
		      
		      lblNewLabel_4 = new JLabel("");
		      lblNewLabel_4.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/hs.jpg")));
		      lblNewLabel_4.setBounds(0, 0, 435, 260);
		      panelStaffMain.add(lblNewLabel_4);
		      panelStaffMain.setVisible(false);
		      panelStaffMain.setVisible(false);
		      panelStaffMain.setVisible(false);
		      comboBox_1.addItem("Money");
		      comboBox_1.addItem("Food");
		      comboBox_1.addItem("Bags");
		      comboBox_1.addItem("Clothes");
		      comboBox_1.addItem("Books");
		      comboBox_1.addItem("Toys");
		      
		      panelAddUser = new JPanel();
		      panelAddUser.setBounds(0, 0, 435, 260);
		      contentPane.add(panelAddUser);
		      panelAddUser.setLayout(null);
		      
		      JLabel lblUsername = new JLabel("Username:");
		      lblUsername.setBounds(49, 32, 73, 14);
		      lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		      lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      panelAddUser.add(lblUsername);
		      
		      textFieldAddUserName = new JTextField();
		      textFieldAddUserName.setBounds(132, 30, 132, 20);
		      panelAddUser.add(textFieldAddUserName);
		      textFieldAddUserName.setColumns(10);
		      
		      JLabel lblPassword = new JLabel("Password:");
		      lblPassword.setBounds(49, 77, 73, 14);
		      lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		      lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      panelAddUser.add(lblPassword);
		      
		      JLabel lblRetypePassword = new JLabel("Retype Password:");
		      lblRetypePassword.setForeground(new Color(0, 0, 0));
		      lblRetypePassword.setBounds(10, 119, 112, 14);
		      lblRetypePassword.setHorizontalAlignment(SwingConstants.CENTER);
		      lblRetypePassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      panelAddUser.add(lblRetypePassword);
		      
		      btnCreateUser = new JButton("Create User");
		      btnCreateUser.setBounds(202, 158, 144, 23);
		      btnCreateUser.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		String a = new String(passwordFieldAddUser.getPassword());
		      		String b = new String(passwordFieldAddUserRetype.getPassword());
		      		if(a.equals(b)) {
		      			try {
		      				if(!textFieldAddUserName.getText().equals(q.getUser(textFieldAddUserName.getText())))
		      						{
		      							if(!textFieldAddUserName.getText().equals(""))
		      							{
		      								q.createUser(textFieldAddUserName.getText(), a);
		      								textFieldAddUserName.setText("");
		      								passwordFieldAddUser.setText("");
		      								passwordFieldAddUserRetype.setText("");
		      							}
		      						}
		      			} catch (SQLException e1) {
		      				// TODO Auto-generated catch block
		      				e1.printStackTrace();
		      			}
		      		}
		      	}
		      });
		      btnCreateUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      panelAddUser.add(btnCreateUser);
		      
		      btnBackInAddUser = new JButton("Back");
		      btnBackInAddUser.setBounds(85, 159, 89, 23);
		      btnBackInAddUser.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		panelAddEvent.setVisible(false);
		      		panelAddSponsor.setVisible(false);
		      		panelAddUser.setVisible(false);
		      		panelAddVolunteer.setVisible(false);
		      		panelAdminMain.setVisible(true);
		      		panelLogin.setVisible(false);
		      		panelSearchEvents.setVisible(false);
		      		panelStaffMain.setVisible(false);
		      		panelChooseEvent.setVisible(false);
		      	}
		      });
		      panelAddUser.add(btnBackInAddUser);
		      
		      passwordFieldAddUser = new JPasswordField();
		      passwordFieldAddUser.setBounds(132, 75, 132, 20);
		      panelAddUser.add(passwordFieldAddUser);
		      
		      passwordFieldAddUserRetype = new JPasswordField();
		      passwordFieldAddUserRetype.setBounds(132, 119, 132, 18);
		      panelAddUser.add(passwordFieldAddUserRetype);
		      
		      lblNewLabel_8 = new JLabel("");
		      lblNewLabel_8.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/vol.jpg")));
		      lblNewLabel_8.setBounds(0, 0, 435, 260);
		      panelAddUser.add(lblNewLabel_8);
		      panelAddUser.setVisible(false);
		      panelAddUser.setVisible(false);
		      panelAddUser.setVisible(false);
		      
		      panelSearchEvents = new JPanel();
		      panelSearchEvents.setBackground(new Color(153, 255, 204));
		      panelSearchEvents.setBounds(0, 0, 435, 260);
		      contentPane.add(panelSearchEvents);
		      panelSearchEvents.setLayout(null);
		      
		      textFieldSearch = new JTextField();
		      textFieldSearch.setBounds(21, 12, 126, 20);
		      panelSearchEvents.add(textFieldSearch);
		      textFieldSearch.setColumns(10);
		      
		      btnSearchEvents = new JButton("Search Events");
		      btnSearchEvents.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent arg0) {
		      		try {
		      			eventModel.refreshForAll(textFieldSearch.getText());
		      			eventModel.fireTableDataChanged();
		      		} catch (SQLException e) {
		      			// TODO Auto-generated catch block
		      			e.printStackTrace();
		      		}
		      	}
		      });
		      btnSearchEvents.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      btnSearchEvents.setBounds(157, 10, 126, 23);
		      panelSearchEvents.add(btnSearchEvents);
		      
		      tableSearch = new JTable();
		      JScrollPane scrollPaneSearch = new JScrollPane(tableSearch);
		      scrollPaneSearch.setBounds(0, 46, 435, 180);
		      panelSearchEvents.add(scrollPaneSearch);
		      
		      
		      
		      btnBackInSearch = new JButton("Back");
		      btnBackInSearch.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		panelAddEvent.setVisible(false);
		      		panelAddSponsor.setVisible(false);
		      		panelAddUser.setVisible(false);
		      		panelAddVolunteer.setVisible(false);
		      		panelAdminMain.setVisible(true);
		      		panelLogin.setVisible(false);
		      		panelSearchEvents.setVisible(false);
		      		panelStaffMain.setVisible(false);
		      		panelChooseEvent.setVisible(false);
		      	}
		      });
		      btnBackInSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      btnBackInSearch.setBounds(120, 237, 89, 23);
		      panelSearchEvents.add(btnBackInSearch);
		      panelSearchEvents.setVisible(false);
		      panelSearchEvents.setVisible(false);
		      tableSearch.setModel(eventModel);
		      
		      JCheckBox chckbxNewCheckBox = new JCheckBox("Only Future Events");
		      chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		      chckbxNewCheckBox.setBounds(289, 11, 140, 23);
		      panelSearchEvents.add(chckbxNewCheckBox);
		      
		      btnNewButton = new JButton("Summary");
		      btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      btnNewButton.setBounds(243, 237, 89, 23);
		      panelSearchEvents.add(btnNewButton);
		      panelSearchEvents.setVisible(false);
		      
		      
		      
		      panelAddEvent = new JPanel();
		      panelAddEvent.setBounds(0, 0, 435, 260);
		      contentPane.add(panelAddEvent);
		      panelAddEvent.setLayout(null);
		      
		      JLabel lblName = new JLabel("Name:");
		      lblName.setHorizontalAlignment(SwingConstants.CENTER);
		      lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblName.setBounds(79, 30, 44, 24);
		      panelAddEvent.add(lblName);
		      
		      textFieldNameAddEvent = new JTextField();
		      textFieldNameAddEvent.setBounds(168, 33, 168, 20);
		      panelAddEvent.add(textFieldNameAddEvent);
		      textFieldNameAddEvent.setColumns(10);
		      
		      JLabel lblNewLabel = new JLabel("Date\r\n:");
		      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		      lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblNewLabel.setBounds(77, 64, 46, 14);
		      panelAddEvent.add(lblNewLabel);
		      
		      textFieldDayAddEvent = new JTextField();
		      textFieldDayAddEvent.setBounds(168, 64, 32, 20);
		      panelAddEvent.add(textFieldDayAddEvent);
		      textFieldDayAddEvent.setColumns(10);
		      
		      textFieldMonthAddEvent = new JTextField();
		      textFieldMonthAddEvent.setBounds(210, 64, 32, 20);
		      panelAddEvent.add(textFieldMonthAddEvent);
		      textFieldMonthAddEvent.setColumns(10);
		      
		      textFieldYearAddEvent = new JTextField();
		      textFieldYearAddEvent.setBounds(254, 64, 44, 20);
		      panelAddEvent.add(textFieldYearAddEvent);
		      textFieldYearAddEvent.setColumns(10);
		      
		      JLabel lblLocation = new JLabel("Location:");
		      lblLocation.setHorizontalAlignment(SwingConstants.CENTER);
		      lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblLocation.setBounds(79, 97, 67, 14);
		      panelAddEvent.add(lblLocation);
		      
		      textFieldLocation = new JTextField();
		      textFieldLocation.setBounds(168, 95, 130, 20);
		      panelAddEvent.add(textFieldLocation);
		      textFieldLocation.setColumns(10);
		      
		      JLabel lblRequiredVolunteers = new JLabel("Required Volunteers:");
		      lblRequiredVolunteers.setHorizontalAlignment(SwingConstants.CENTER);
		      lblRequiredVolunteers.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblRequiredVolunteers.setBounds(20, 122, 123, 14);
		      panelAddEvent.add(lblRequiredVolunteers);
		      
		      textFieldRequiredVolunteers = new JTextField();
		      textFieldRequiredVolunteers.setBounds(168, 120, 53, 20);
		      panelAddEvent.add(textFieldRequiredVolunteers);
		      textFieldRequiredVolunteers.setColumns(10);
		      
	    btnAddEvent_1 = new JButton("Add Event");
	    btnAddEvent_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		try {
	    			if(textFieldNameAddEvent.getText().equals("") || textFieldDayAddEvent.getText().equals("") || textFieldMonthAddEvent.getText().equals("") || textFieldYearAddEvent.getText().equals("") || textFieldLocation.getText().equals("") || textFieldRequiredVolunteers.getText().equals(""))
	    			{
						JOptionPane.showMessageDialog(AdminPage.this,
							    "check if all fields are filled.",
							    "Empty Field/s",
							    JOptionPane.ERROR_MESSAGE);
	    			}
	    			else
	    			{
						q.createEvent(textFieldNameAddEvent.getText(), textFieldDayAddEvent.getText(), textFieldMonthAddEvent.getText(), textFieldYearAddEvent.getText(), textFieldLocation.getText(), textFieldRequiredVolunteers.getText());
						textFieldNameAddEvent.setText("");
						textFieldDayAddEvent.setText("");
						textFieldMonthAddEvent.setText("");
						textFieldYearAddEvent.setText("");
						textFieldLocation.setText("");
						textFieldRequiredVolunteers.setText("");
	    			}

				} catch (NumberFormatException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    });
	    btnAddEvent_1.setBounds(247, 172, 89, 23);
	    panelAddEvent.add(btnAddEvent_1);
	    
	    btnBack = new JButton("Back");
	    btnBack.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		panelAddEvent.setVisible(false);
				panelAddSponsor.setVisible(false);
				panelAddUser.setVisible(false);
				panelAddVolunteer.setVisible(false);
				panelAdminMain.setVisible(true);
				panelLogin.setVisible(false);
				panelSearchEvents.setVisible(false);
				panelChooseEvent.setVisible(false);
				panelStaffMain.setVisible(false);
				textFieldNameAddEvent.setText("");
				textFieldDayAddEvent.setText("");
				textFieldMonthAddEvent.setText("");
				textFieldYearAddEvent.setText("");
				textFieldLocation.setText("");
				textFieldRequiredVolunteers.setText("");
	    	}
	    });
	    btnBack.setBounds(104, 172, 89, 23);
	    panelAddEvent.add(btnBack);
	    
	    lblNewLabel_9 = new JLabel("");
	    lblNewLabel_9.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/plant.png")));
	    lblNewLabel_9.setBounds(0, 0, 435, 260);
	    panelAddEvent.add(lblNewLabel_9);
	    panelAddEvent.setVisible(false);
	    panelAddEvent.setVisible(false);
	    
	    
		panelAddEvent.setVisible(false);
		      
		      panelAddSponsor = new JPanel();
		      panelAddSponsor.setBounds(0, 0, 435, 260);
		      contentPane.add(panelAddSponsor);
		      panelAddSponsor.setLayout(null);
		      
		      table_2 = new JTable();
		      JScrollPane scrollPaneAddSponsor = new JScrollPane(table_2);
		      scrollPaneAddSponsor.setBounds(0, 0, 435, 160);
		      panelAddSponsor.add(scrollPaneAddSponsor);
		      
		      JLabel lblSponsorName = new JLabel("Sponsor Name:");
		      lblSponsorName.setHorizontalAlignment(SwingConstants.CENTER);
		      lblSponsorName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      lblSponsorName.setBounds(40, 171, 89, 14);
		      panelAddSponsor.add(lblSponsorName);
		      
		      textFieldSponsorName = new JTextField();
		      textFieldSponsorName.setBounds(151, 171, 125, 20);
		      panelAddSponsor.add(textFieldSponsorName);
		      textFieldSponsorName.setColumns(10);
		      
		      btnBackAddSponsor = new JButton("Back");
		      btnBackAddSponsor.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		panelAddEvent.setVisible(false);
		      		panelAddSponsor.setVisible(false);
		      		panelAddUser.setVisible(false);
		      		panelAddVolunteer.setVisible(false);
		      		panelAdminMain.setVisible(true);
		      		panelLogin.setVisible(false);
		      		panelSearchEvents.setVisible(false);
		      		panelStaffMain.setVisible(false);
		      		panelChooseEvent.setVisible(false);
		      	}
		      });
		      btnBackAddSponsor.setBounds(73, 228, 89, 23);
		      panelAddSponsor.add(btnBackAddSponsor);
		      
		      btnAddSponsor = new JButton("Add Sponsor");
		      btnAddSponsor.addActionListener(new ActionListener() {
		      	public void actionPerformed(ActionEvent e) {
		      		if(textFieldSponsorName.getText().equals("")) {
		      			JOptionPane.showMessageDialog(AdminPage.this,
		      				    "Check Sponsor Name",
		      				    "Insert a Sponsor",
		      				    JOptionPane.ERROR_MESSAGE);
		      		}
		      		else
		      		{
		      			String s=new String(textFieldSponsorName.getText());
		      			String b = (String)eventModel.getValueAt(table_2.getSelectedRow(), 0);
		      			try {
		      				q.createSponsor(s, b);
		      			} catch (SQLException e1) {
		      				// TODO Auto-generated catch block
		      				e1.printStackTrace();
		      			}
		      			textFieldSponsorName.setText("");
		      		}
		      			
		      	}
		      });
		      btnAddSponsor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		      btnAddSponsor.setBounds(176, 228, 133, 23);
		      panelAddSponsor.add(btnAddSponsor);
		      panelAddSponsor.setVisible(false);
		      panelAddSponsor.setVisible(false);
		      table_2.setModel(eventModel);
		      
		      lblNewLabel_10 = new JLabel("");
		      lblNewLabel_10.setIcon(new ImageIcon(AdminPage.class.getResource("/resources/spons.jpg")));
		      lblNewLabel_10.setBounds(0, 160, 435, 100);
		      panelAddSponsor.add(lblNewLabel_10);
		      panelAddSponsor.setVisible(false);
	    currentEventID = 0;
	    currentUser = "none";
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER && panelLogin.isVisible())
		{
			try {
				if( q.isUser(textFieldUserName.getText(), new String(passwordFieldSignIn.getPassword())))
				{
					if(textFieldUserName.getText().equals("admin") )
					{
					panelAddEvent.setVisible(false);
					panelAddSponsor.setVisible(false);
					panelAddUser.setVisible(false);
					panelAddVolunteer.setVisible(false);
					panelAdminMain.setVisible(true);
					panelLogin.setVisible(false);
					panelSearchEvents.setVisible(false);
					panelStaffMain.setVisible(false);
					panelChooseEvent.setVisible(false);
					}
					else
					{
						
						panelAddEvent.setVisible(false);
	 					panelAddSponsor.setVisible(false);
	 					panelAddUser.setVisible(false);
	 					panelAddVolunteer.setVisible(false);
	 					panelAdminMain.setVisible(false);
	 					panelLogin.setVisible(false);
	 					panelSearchEvents.setVisible(false);
	 					panelStaffMain.setVisible(false);
	 					panelChooseEvent.setVisible(true);
	 					currentUser = textFieldUserName.getText();
	 					ResultSet rs = q.getEventNamesForUser(currentUser);
	 					comboBoxSelectEvent.removeAllItems();
	 					while(rs.next())
	 					{
	 						comboBoxSelectEvent.addItem(rs.getString(1));
	 					}
	 					}
	 					textFieldUserName.setText("");
	 					passwordFieldSignIn.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(AdminPage.this,
						    "Check Entered Password and Username.",
						    "Wrong Password",
						    JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
