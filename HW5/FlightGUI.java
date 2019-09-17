/**
 * FlightGUI represents seats on a plane. Provides
 * functionality for adding/removing passengers to/from seats
 * and highlighting all seats where passengers have special meals.
 */
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class FlightGUI extends JFrame implements ActionListener {
	private Flight fl; // flight to be represented

	// visual components - seats on plane represented by buttons
	private ArrayList<JButton> seatButton = new ArrayList<JButton>();
	private int rows; // number of rows on the plane
	private String[] columns; // labels on seats going across each row

	private JLabel lbFlightInfo = new JLabel(); // info on flight
	private JButton btnMeals = new JButton("Show Special Meals");
	private JButton btnExit = new JButton("Quit");

	// static variables and constants for use by all instances of the class
	private static Color lightBlue = new Color(176, 224, 230); // for empty seat
	private static int selectedSeat = -1; // currently selected seat on flight
	private static Font buttonFont; // for storing default button font
	private static Font specialButtonFont; // font for special meals buttons

	/**
	 * Sets up the window with GUI controls. Specified layout assumes between 2
	 * and 5 columns of seats
	 */
	public FlightGUI(Flight fl, String[] columns, int rows) {
		// set up window properties
		this.fl = fl;
		this.columns = columns;
		this.rows = rows;

		this.setTitle(fl.toString());
		this.setSize(1200, 250);
		this.setLocationRelativeTo(null); // Center the frame
		// close the window and exit when click the X
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 

		// Create panel for the buttons
		JPanel buttonsPanel[] = new JPanel[this.columns.length];
		int panel = 0;

		JButton j = new JButton(); // default button
		buttonFont = j.getFont(); // default font
		// special button shows seats whose passengers have special meals
		specialButtonFont = new Font("sansserif", Font.BOLD, 24);

		// Add seats to flight and add button for each seat
		int i = 0; // use value of i as index and name for seat
		for (int col = 0; col < this.columns.length; col++) {
			buttonsPanel[panel] = new JPanel();
			buttonsPanel[panel].setLayout(new GridLayout(1, rows));
			for (int row = 1; row <= this.rows; row++) {
				fl.addSeat(new Seat(row, this.columns[col]));
				j = new JButton(row + this.columns[col]);
				j.setName("" + i);
				j.setSize(10, 10);
				j.setBackground(lightBlue);
				buttonsPanel[panel].add(j);
				j.addActionListener(this);
				seatButton.add(j);
				i++;
			}
			panel++;
		}

		// assumes adding 2-5 panels of seats and one panel for aisle
		JPanel buttonsPanelAll = new JPanel();
		buttonsPanelAll.setLayout(new GridLayout(6, 1));
		for (i = 0; i < 2; i++) { // 2 rows of seats
			buttonsPanelAll.add(buttonsPanel[i]);
		}
		JPanel emptyPanel = new JPanel(new FlowLayout());
		buttonsPanelAll.add(emptyPanel);
		for (i = 2; i < columns.length; i++) { // up to 3 rows of seats
			buttonsPanelAll.add(buttonsPanel[i]);
		}

		this.setLayout(new BorderLayout());
		this.add(buttonsPanelAll, BorderLayout.CENTER);

		// add buttons to show special meals and to exit
		JPanel passPanel = new JPanel(new FlowLayout());
		passPanel.add(this.btnMeals);
		passPanel.add(this.btnExit);

		this.btnMeals.addActionListener(this);
		this.btnExit.addActionListener(this);

		this.add(passPanel, BorderLayout.SOUTH);

		// specify info on flight at top
		JPanel topPanel = new JPanel(new FlowLayout());

		// provide directions on using interface
		this.lbFlightInfo.setFont(new Font("sansserif", Font.BOLD, 14));
		this.lbFlightInfo
				.setText("Click on a seat to add/update or remove a passenger, "
						+ "hover to view passenger info");

		topPanel.add(lbFlightInfo);
		this.add(topPanel, BorderLayout.NORTH);

		this.setVisible(true);
	}

	/** show special meals */
	public void showSpecial(int[] specialSeats) {
		for (int i = 0; i < specialSeats.length; i++) {
			// seatButton.get(specialSeats[i]).setText("  X  ");
			seatButton.get(specialSeats[i]).setFont(specialButtonFont);
			seatButton.get(specialSeats[i]).setForeground(Color.cyan);
		}
	}

	/**
	 * public void actionPerformed(ActionEvent e) Method processes ActionEvents
	 * from buttons - all the seats, special meals, and exit
	 */
	public void actionPerformed(ActionEvent e) {
		Seat[] seats = fl.getSeats();
		PassengerList maintainPassengers;

		if (e.getSource() == this.btnExit) {
			System.exit(0);

			// highlight/hide special meals
		} else if (e.getSource() == this.btnMeals) {
			int[] specialSeats = fl.getSpecialSeats();
			if (specialSeats.length != 0) {
				if (this.btnMeals.getText().equals("Show Special Meals")) {
					this.showSpecial(specialSeats);
					this.btnMeals.setText("Hide Special Meals");
				} else {
					for (int i = 0; i < specialSeats.length; i++) {
						seatButton.get(specialSeats[i]).setFont(buttonFont);
						// new Font("dialog", Font.PLAIN, 12));
						seatButton.get(specialSeats[i]).setForeground(
								Color.black);
					}
					this.btnMeals.setText("Show Special Meals");
				}
			}

		} else {
			// clicked on a seat - add/update/delete
			if (selectedSeat != -1) {
				// set previously selected seat to correct color
				if (seats[selectedSeat].getPassenger() == null)
					this.seatButton.get(selectedSeat).setBackground(lightBlue);
				else
					this.seatButton.get(selectedSeat).setBackground(Color.red);
			}

			JButton btn = (JButton) e.getSource();
			btn.setBackground(Color.LIGHT_GRAY);
			// name on button is index
			selectedSeat = Integer.parseInt(btn.getName());
			// index of button clicked - would work too
			// selectedSeat = this.seatButton.indexOf(e.getSource());

			// open window to specify passenger into, options for
			// add/update and remove
			maintainPassengers = new PassengerList(seats[selectedSeat].getRow() +
					seats[selectedSeat].getSeatID());
			maintainPassengers.setVisible(true);
			// center the frame
			maintainPassengers.setLocationRelativeTo(null);

		}

	}

	/******************** inner class PassengerList *********************************/
	/** for opening new window to add/update or remove passengers */
	class PassengerList extends JFrame implements ActionListener {

		private JButton btnAdd = new JButton("Add/Update Passenger");
		private JButton btnRemove = new JButton("Remove Passenger");
		private JButton btnQuit = new JButton("Cancel");

		private JLabel lbFirst = new JLabel("First name:  ", JLabel.RIGHT);
		private JLabel lbLast = new JLabel("Last name:  ", JLabel.RIGHT);
		private JLabel lbMiddle = new JLabel("Middle name:  ", JLabel.RIGHT);
		private JTextField tfFirst = new JTextField(15);
		private JTextField tfLast = new JTextField(15);
		private JTextField tfMiddle = new JTextField(10);
		private JRadioButton jrSpecialMeal = new JRadioButton("Special meal",
				false);

		/** initialize user interface components */
		public PassengerList(String seat) {
			super("Passenger info for seat " + seat);
			this.setSize(2500, 175);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			Container contains = this.getContentPane();
			contains.setLayout(new BorderLayout());

			// buttons;
			JPanel bottom = new JPanel();
			bottom.add(btnAdd);
			bottom.add(btnRemove);
			bottom.add(btnQuit);
			this.add(bottom, BorderLayout.SOUTH);

			// labels
			this.lbFirst = new JLabel("First name:  ", JLabel.RIGHT);
			this.lbLast = new JLabel("Last name:  ", JLabel.RIGHT);
			this.lbMiddle = new JLabel("Middle name:  ", JLabel.RIGHT);

			// text fields
			this.tfFirst = new JTextField("", 8);
			this.tfLast = new JTextField("", 8);

			/** design UI */
			JPanel center = new JPanel();
			// 5 rows, 3 columns
			center.setLayout(new GridLayout(5, 3));
			// leave first row blank
			center.add(new JLabel(""));
			center.add(new JLabel(""));
			center.add(new JLabel(""));
			// last name
			center.add(this.lbLast);
			center.add(this.tfLast);
			center.add(new JLabel(""));
			// first name
			center.add(this.lbFirst);
			center.add(this.tfFirst);
			center.add(new JLabel(""));
			// middle initial
			center.add(this.lbMiddle);
			center.add(this.tfMiddle);
			center.add(new JLabel(""));
			// special meal
			center.add(new JLabel(""));
			center.add(this.jrSpecialMeal);

			contains.add(center, BorderLayout.CENTER);

			// add listeners
			this.btnAdd.addActionListener(this);
			this.btnQuit.addActionListener(this);
			this.btnRemove.addActionListener(this);

			// if there is a passenger in the seat, set
			// text fields in window to passenger info
			Passenger p = fl.getSeats()[selectedSeat].getPassenger();
			if (p != null) {
				// show passenger info
				this.tfFirst.setText(p.getFirstName());
				this.tfLast.setText(p.getLastName());
				this.tfMiddle.setText(p.getMiddleName());
				if (p.getSpecialMeal())
					this.jrSpecialMeal.setSelected(true);
			}
			this.tfLast.requestFocus();

		}

		/** listen for action events - add/update or delete */
		public void actionPerformed(ActionEvent a) {
			String first, last, middle; // name of passenger in selected seat
			boolean specialMeal;
			Seat[] seats = fl.getSeats();
			if (a.getSource() == btnQuit) {// preserve seat color before closing
				if (seats[selectedSeat].getPassenger() == null)
					seatButton.get(selectedSeat).setBackground(lightBlue);
				else
					seatButton.get(selectedSeat).setBackground(Color.red);
				this.setVisible(false);
			}
			/** Add new passenger to the list or update existing */
			else if (a.getSource() == this.btnAdd) {
				first = this.tfFirst.getText().trim();
				middle = this.tfMiddle.getText().trim();
				last = this.tfLast.getText().trim();
				specialMeal = this.jrSpecialMeal.isSelected();
				Seat s = seats[selectedSeat]; // current seat
				JButton jb = seatButton.get(selectedSeat); // selected button
				// assign passenger to seat
				if (first.length() != 0 && last.length() != 0) {
					if (middle.length() == 0) // no middle name
						s.addPassenger(new Passenger(last, first, specialMeal));
					else
						s.addPassenger(new Passenger(last, first, middle,
								specialMeal));

					// change properties of selected button to show there is a
					// passenger
					jb.setBackground(Color.red);
					// show tooltip with passenger info
					Passenger p = s.getPassenger();
					if (p != null)
						jb.setToolTipText(p.toString());

					// special meals display may need updating
					if (btnMeals.getText().equals("Hide Special Meals")) {
						if (!p.getSpecialMeal()) {
							seatButton.get(selectedSeat).setForeground(
									Color.black);
							seatButton.get(selectedSeat).setFont(buttonFont);
						} else {
							seatButton.get(selectedSeat).setForeground(
									Color.cyan);
							seatButton.get(selectedSeat).setFont(
									specialButtonFont);
						}
					}
				}
				this.setVisible(false); // close window

			} else if (a.getSource() == this.btnRemove) {
				// remove passenger, reset tooltip and seat appearance
				seats[selectedSeat].removePassenger();
				seatButton.get(selectedSeat).setToolTipText("");
				seatButton.get(selectedSeat).setFont(buttonFont);
				seatButton.get(selectedSeat).setBackground(lightBlue);
				seatButton.get(selectedSeat).setForeground(Color.black);

				this.setVisible(false); // close window
			}
		}
	}

	/********************* end of PassengerList inner class ***********************************/

	/** Main method in FlightGUI for launching visual interface */
	public static void main(String[] args) {
		// code needed to look the same on a Mac as on a Windows machine
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println(e.getMessage());
		}

	
		String[] columns = new String[] { "A", "B", "C", "D", "E" };
		int rows = 10;
		int numSeats = columns.length * rows;
		Flight f = new Flight("1544", "BOS Boston", "AUS Austin", numSeats);
		FlightGUI frame = new FlightGUI(f, columns, rows);
	}
}
