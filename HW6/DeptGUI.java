import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*; // library of components
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Interface for displaying a list of faculty members by type Options for adding
 * and removing faculty members Can promote and tenure tenure-track faculty Can
 * change union status of adjunct faculty
 */

// LEAVE THIS FILE UNCHANGED - do not submit

@SuppressWarnings("serial")
public class DeptGUI extends JFrame implements ActionListener, ListSelectionListener {

	private Department df;

	// Define instance vars for interface and label them as needed
	private JLabel selection = new JLabel("Faculty id:");
	private JTextField id = new JTextField();
	private JLabel cat = new JLabel("Category:");
	private JLabel status = new JLabel("Status:");

	// to display a scrollable list of faculty members
	private JList<Faculty> lstItems;
	private JScrollPane scrlPane;

	// to display a dropdown list of all faculty or by type
	private String[] category = { "ALL", "TENURE TRACK", "ADJUNCT" };

	private String[] tenureStatus = { "ALL", "TENURED", "NOT TENURED" };
	private String[] unionStatus = { "ALL", "UNION", "NON UNION" };

	private JComboBox<String> categoryList = new JComboBox<String>(category);
	private JComboBox<String> statusList = new JComboBox<String>(tenureStatus);

	// width and height of interface
	private int width = 600;
	private int height = 250;

	// buttons for adding and removing faculty,
	// promoting and giving tenure to tenure-track
	// switching union status for adjuncts
	private JButton btnAdd = new JButton("Add Faculty");
	private JButton btnRemove = new JButton("Remove Faculty");
	private JButton btnPromote = new JButton("Promote");
	private JButton btnTenure = new JButton("Tenure");
	private JButton btnUnion = new JButton("Switch Union Status");

	/** Constructor */
	public DeptGUI(Department df) {
		this.df = df;

		this.lstItems = new JList<Faculty>(df.getFacultyList());
		this.lstItems.setFont(new Font("Arial", Font.BOLD, 12));
		this.id.setEditable(false);

		// set initial selections
		if ((df.getFacultyList()).length > 0) { // check if anyone in list
			this.lstItems.setSelectedIndex(0);
			this.id.setText(df.getFacultyList()[0].getId() + "    ");
		} else
			this.id.setText("        "); // if no one there, add space to id

		this.scrlPane = new JScrollPane(lstItems);
		this.scrlPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.scrlPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		this.categoryList.setSelectedIndex(0);

		this.statusList.setSelectedIndex(0); // default to first item in list

		// add listeners for events in department list and faculty list
		this.categoryList.addActionListener(this); // faculty category options
		this.statusList.addActionListener(this); // tenure status or union
		this.lstItems.addListSelectionListener(this); // faculty list

		// add listeners for buttons
		this.btnAdd.addActionListener(this);
		this.btnRemove.addActionListener(this);
		this.btnPromote.addActionListener(this);
		this.btnTenure.addActionListener(this);
		this.btnUnion.addActionListener(this);

		// initialize status for buttons (enabled/disabled)
		this.reset();

		// set a few properties of the frame
		this.setSize(width, height);
		this.setTitle(this.df.getName());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null); // center the frame

		// Add components to the container pane of the frame
		// - get the content pane of the JFrame
		Container contentPane = this.getContentPane();

		// Tell the pane how to arrange components:
		// With BorderLayout, components are arranged in "directions" of
		// north, south, east, west, and center
		// With FlowLayout, components are arranged in centered rows
		// in the order in which they are added
		contentPane.setLayout(new BorderLayout());
		// top
		JPanel selectionPanel = new JPanel(new FlowLayout());
		selectionPanel.add(this.selection);
		selectionPanel.add(this.id);
		selectionPanel.add(this.cat);
		selectionPanel.add(this.categoryList);
		selectionPanel.add(this.status);
		selectionPanel.add(this.statusList);

		contentPane.add(selectionPanel, BorderLayout.NORTH);

		// middle
		contentPane.add(scrlPane, BorderLayout.CENTER);

		// bottom
		JPanel buttonsPanel = new JPanel(new FlowLayout());

		buttonsPanel.add(this.btnAdd);
		buttonsPanel.add(this.btnRemove);
		buttonsPanel.add(this.btnPromote);
		buttonsPanel.add(this.btnTenure);
		buttonsPanel.add(this.btnUnion);

		contentPane.add(buttonsPanel, BorderLayout.SOUTH);

		this.setVisible(true);
		this.statusList.setEnabled(false);
	}

	/** set button status for promotion and tenure */
	public void setStatus(FacultyType type) {
		// only show promote button if tenure track and not full
		// only show tenure button if tenure track and not tenured
		this.btnPromote.setEnabled(false);
		this.btnTenure.setEnabled(false);
		this.btnUnion.setEnabled(false);

		// FacultyType type = selected.getFacultyType();
		if (type instanceof TenureTrack) {
			String title = type.getTitle();
			this.btnPromote.setEnabled(!title.equalsIgnoreCase("full professor"));
			this.btnTenure.setEnabled(!((TenureTrack) type).getTenured());
		} else { // adjunct
			this.btnUnion.setEnabled(true);
		}
	}

	/**
	 * React to faculty list selection change by retrieving the selected item's id
	 * and displaying it in the id field
	 */
	public void valueChanged(ListSelectionEvent e) {

		Faculty selectedItem = (Faculty) lstItems.getSelectedValue();
		if (selectedItem == null) { // i.e. list has items
			lstItems.setSelectedIndex(0);
		} else {
			String selectedID = selectedItem.getId();
			id.setText(selectedID);
			// set button status for promotion and tenure based on FacultyType
			this.setStatus(selectedItem.getFacultyType());
		}
	}
	
	/**
	 * Display selected list of faculty
	 */
	public void updateDisplay(String catStat, String status) {
		if (catStat.equals("ALL"))
			lstItems.setListData(this.df.getFacultyList());
		else if (status.equals("TENURED")) // catStat must be tenure track
			// get only those faculty who are tenured
			lstItems.setListData(this.df.tenuredList().toArray(new Faculty[this.df.tenuredList().size()]));
		else if (status.equals("NOT TENURED")) // catStat must be tenure track
			lstItems.setListData(this.df.notTenuredList().toArray(new Faculty[this.df.notTenuredList().size()]));
		else if (status.equals("UNION")) // catStat must be adjunct
			// get only those faculty who are tenured
			lstItems.setListData(this.df.unionList().toArray(new Faculty[this.df.unionList().size()]));
		else if (status.equals("NON UNION")) // catStat must be adjunct
			lstItems.setListData(this.df.notUnionList().toArray(new Faculty[this.df.notUnionList().size()]));
		else if (catStat.equals("TENURE TRACK")) // status must be all
			lstItems.setListData(this.df.tenureTrackList().toArray(new Faculty[this.df.tenureTrackList().size()]));
		else if (catStat.equals("ADJUNCT")) // status must be all
			lstItems.setListData(this.df.adjunctList().toArray(new Faculty[this.df.adjunctList().size()]));

		// repaint the list
		lstItems.repaint();
		this.reset();
	}	
		
	/**
	 * Display selected list of faculty and reposition cursor in list
	 */
	public void updateDisplay(String catStat, String status, int place) {
		updateDisplay(catStat, status);

		// check if at end of list
		if (lstItems.getModel().getSize() - 1 < place)
			place--;
		// put cursor back in place
		lstItems.setSelectedIndex(place);
		if (place != -1)
			this.setStatus(lstItems.getSelectedValue().getFacultyType());
	}

	/** reset buttons to initial status if list empty; reposition cursor if not */
	public void reset() {
		if (lstItems.getModel().getSize() == 0) {
			// no one left in list - disable remove
			this.btnRemove.setEnabled(false);
			this.btnPromote.setEnabled(false);
			this.btnTenure.setEnabled(false);
			this.btnUnion.setEnabled(false);
			this.id.setText("        "); // if no one there, add space to id
		} else {
			this.lstItems.setSelectedIndex(0);
			this.setStatus(lstItems.getSelectedValue().getFacultyType());
			this.btnRemove.setEnabled(true);
		}
	}


	/**
	 * React to selecting different sets of faculty members to display
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void actionPerformed(ActionEvent e) {
		// keep track of category selected - Tenure Track or Adjunct
		String catStat = (String) this.categoryList.getSelectedItem();

		if (e.getSource() == this.categoryList) { // using drop-down menus for categories
			if (catStat.equals("ALL")) {
				this.statusList.setEnabled(false);
				// show all faculty
				this.updateDisplay(catStat, "");
			} else {
				this.statusList.setEnabled(true);
				if (catStat.equals("TENURE TRACK")) {
					// switch list options
					this.statusList.setModel(new DefaultComboBoxModel(this.tenureStatus));
				} else if (catStat.equals("ADJUNCT")) {
					// switch list options
					this.statusList.setModel(new DefaultComboBoxModel(this.unionStatus));
				}
				// following will cause an action event to the status list
				this.statusList.setSelectedIndex(0);
			}
			// drop-down menus for type
		} else if (e.getSource() == this.statusList) {
			// show faculty with tenure, non-tenure, union, non-union
			String status = (String) this.statusList.getSelectedItem();
			this.updateDisplay(catStat, status);
			// check if add faculty button pressed
		} else if (e.getSource() == this.btnAdd) {
			// add a new faculty member
			String id = JOptionPane.showInputDialog("Enter id");

			// verify id not already in list
			if (id != null) {
				if (df.findID(id) != null)
					JOptionPane.showMessageDialog(this, "Already in faculty list");

				else {
					FacultyAdd addGUI = new FacultyAdd(id);
					addGUI.setVisible(true);
					addGUI.setLocationRelativeTo(null); // center the frame

					// if list had been empty, re-enable buttons
					if (!this.btnRemove.isEnabled() && lstItems.getModel().getSize() != 0) {
						this.btnRemove.setEnabled(true);
						this.setStatus(lstItems.getSelectedValue().getFacultyType());
					}
				}
			}

		} else { // promote, tenure, union status, remove
			Faculty selectedItem = lstItems.getSelectedValue();
			FacultyType type = selectedItem.getFacultyType();

			// check if promotion button pressed
			if (e.getSource() == this.btnPromote) {

				// call promote method and if promotion occurs, redo list
				// and update button status
				if (type.promote()) {
					int savePlace = lstItems.getSelectedIndex();
					// update display
					String cat = (String) this.categoryList.getSelectedItem();
					String status = (String) this.statusList.getSelectedItem();
					this.updateDisplay(cat, status, savePlace);
				}
			// check if tenure button pressed
			} else if (e.getSource() == this.btnTenure) {
				// set status to tenured
				if (type instanceof TenureTrack) {
					((TenureTrack) type).setTenured(true);
					int savePlace = lstItems.getSelectedIndex();
					// update display
					String cat = (String) this.categoryList.getSelectedItem();
					String status = (String) this.statusList.getSelectedItem();
					this.updateDisplay(cat, status, savePlace);
				}

			// check if switch union status button presses
			} else if (e.getSource() == this.btnUnion) {
				// switch union status
				if (type instanceof Adjunct) {
					Adjunct inst = (Adjunct) type;
					if (inst.isUnionMember())
						inst.setUnionMember(false);
					else
						inst.setUnionMember(true);
					int savePlace = lstItems.getSelectedIndex();
					// update display
					String cat = (String) this.categoryList.getSelectedItem();
					String status = (String) this.statusList.getSelectedItem();
					this.updateDisplay(cat, status, savePlace);
				}

				// check if remove faculty button pressed
			} else if (e.getSource() == this.btnRemove) {
				if (lstItems.getModel().getSize() != 0) {

					// confirm remove
					String name = selectedItem.getLast() + ", " + selectedItem.getFirst();
					int confirm = JOptionPane.showConfirmDialog(this, "Remove " + name + "?", "Remove Faculty",
							JOptionPane.YES_NO_OPTION);
					// confirm == 0 -> yes, confirm== 1 -> no
					if (confirm == 0) {
						int savePlace = lstItems.getSelectedIndex();
						this.df.remove(selectedItem.getId());
						// update display
						String cat = (String) this.categoryList.getSelectedItem();
						String status = (String) this.statusList.getSelectedItem();
						this.updateDisplay(cat, status, savePlace);
					}
				}
			}
		}
	}

	/******************************************************************
	 * Inner class for opening new window to add faculty members *
	 ******************************************************************/
	class FacultyAdd extends JFrame implements ActionListener {
		JButton btnAdd, btnQuit;

		// faculty info - package access
		JLabel lblFirst, lblLast, lblTitle;
		JTextField tfFirst, tfLast;
		JRadioButton tenureTrack = new JRadioButton("Tenure Track", false);
		JRadioButton adjunct = new JRadioButton("Adjunct", false);
		ButtonGroup facultyTypeGroup = new ButtonGroup();
		String id; // unique id

		/** initialize user interface components */
		public FacultyAdd(String id) {
			super("Add Faculty");
			this.id = id;
			this.setSize(250, 165);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Container contains = this.getContentPane();
			contains.setLayout(new BorderLayout());

			// buttons
			this.btnAdd = new JButton("Add");
			this.btnQuit = new JButton("Cancel");
			JPanel bottom = new JPanel();
			bottom.add(btnAdd);
			bottom.add(btnQuit);
			this.add(bottom, BorderLayout.SOUTH);

			// labels
			this.lblFirst = new JLabel("First name:    ", JLabel.CENTER);
			this.lblLast = new JLabel("Last name:     ", JLabel.CENTER);

			// text fields
			this.tfFirst = new JTextField("", 8);
			this.tfLast = new JTextField("", 8);

			/** design UI */
			JPanel center = new JPanel();
			// 4 rows, 3 columns
			center.setLayout(new GridLayout(4, 3));
			center.add(lblFirst);
			center.add(tfFirst);
			center.add(lblLast);
			center.add(tfLast);

			center.add(new JLabel(""));
			center.add(new JLabel(""));

			center.add(tenureTrack);
			center.add(adjunct);
			// only allow one to be selected
			facultyTypeGroup.add(tenureTrack);
			facultyTypeGroup.add(adjunct);

			contains.add(center, BorderLayout.CENTER);

			// add listeners
			this.btnAdd.addActionListener(this);
			this.btnQuit.addActionListener(this);
			this.tenureTrack.addActionListener(this);
			this.adjunct.addActionListener(this);

		}

		// listen for action events
		public void actionPerformed(ActionEvent a) {
			if (a.getSource() == btnQuit) {// goodbye
				this.setVisible(false);
			}
			/** Add new faculty member to the list */
			else if (a.getSource() == this.btnAdd) {
				// check if have all info - could use exception handling instead
				boolean incomplete = (tfFirst.getText().trim().equals("") || tfLast.getText().trim().equals("")
						|| (!(this.tenureTrack.isSelected()) && !(this.adjunct.isSelected())));

				if (incomplete)
					JOptionPane.showMessageDialog(this, "Enter all required information");
				else {
					Faculty member;
					if (this.tenureTrack.isSelected())
						member = new Faculty(this.id, tfFirst.getText(), tfLast.getText(),
								new TenureTrack("Assistant Professor", false));
					else { // tenure-track
						member = new Faculty(this.id, tfFirst.getText(), tfLast.getText(), new Adjunct());
					}

					// add member and remove window
					df.add(member);
					this.setVisible(false);

					// update the main display
					String cat = (String) DeptGUI.this.categoryList.getSelectedItem();
					String stat = (String) DeptGUI.this.statusList.getSelectedItem();
					updateDisplay(cat, stat);
					lstItems.setSelectedValue(member, true);
				}
			}
		}
	}

	/** Execution starts here */
	public static void main(String[] args) {
		// code needed to look almost the same on a Mac as on a Windows machine
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println(e.getMessage());
		}

		// make a new instance of this GUI
		Department faculty = new Department("CIS");

		faculty.add(new Faculty("2456", "Walter", "Savitch", new TenureTrack("Associate Professor", false)));
		faculty.add(new Faculty("5326", "James", "Gosling", new TenureTrack("Full Professor", true)));
		faculty.add(new Faculty("1245", "Grace", "Hopper", new Adjunct()));
		faculty.add(new Faculty("4253", "Jonathan", "Nash", new TenureTrack("Assistant Professor", false)));
		faculty.add(new Faculty("6823", "George", "Thomas", new Adjunct(false)));

		new DeptGUI(faculty);
	}
}