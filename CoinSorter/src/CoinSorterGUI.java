import java.awt.event.WindowEvent;
import java.util.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import static javax.swing.JOptionPane.showInputDialog;

// Creating new class CoinSorterGUI.
// Implementing Action Listener in order to activate the code for the GUI.
public class CoinSorterGUI implements ActionListener {
	private String currency;
	private int minCoinIn;
	private int maxCoinIn;
	private List<Integer> coinList;


	public CoinSorterGUI(String currency, int minCoinIn, int maxCoinIn, ArrayList<Integer> coinList) {
		Collections.sort(coinList);
		Collections.reverse(coinList);
		this.currency = currency;
		this.minCoinIn = minCoinIn;
		this.maxCoinIn = maxCoinIn;
		this.coinList = coinList;
	}
	
	public void setCurrency(String currency) 
	{
		this.currency = currency;
	}
	
	public void setMinCoinIn(int minCoinIn) 
	{
		this.minCoinIn = minCoinIn;
	}
	
	public void setMaxCoinIn(int maxCoinIn) 
	{
		this.maxCoinIn = maxCoinIn;
	}
	
	public String getCurrency() 
	{
		return this.currency;
	}
	
	public int getMinCoinIn()
	{
		return this.minCoinIn;
	}
	
	public int getMaxCoinIn()
	{
		return this.maxCoinIn;
	}
	
	public String printCoinList() {
		StringBuilder sb = new StringBuilder();
		sb.append("The current coin denominations are in circulation: ");
		for (int coin: coinList ) {
			sb.append(coin + ", ");
		}
		sb.setLength(sb.length()-2);
		return sb.toString();
	}
	
	public String coinCalculator(int amount, int coin) {
		
		int numOfCoins = amount / coin;
		int remainder = amount % coin; 
		
		return new String("A total of " + numOfCoins + " x " + 
		coin + "p coins can be exchanged, with a remainder of " + remainder + "p.");
	}
	
	
	public String multiCoinCalculator(int amount, int coin) {
		StringBuilder sb = new StringBuilder();
	
		sb.append("The coins exchanged are:");
		for (int c: coinList) {
			if (c == coin){
				sb.append(" " + 0 + " x " + coin + "p,");
				continue;
			}
			int numOfCoins = amount / c;
			amount = amount % c;
			sb.append(" " + numOfCoins + " x " + c + "p,");
		}
		sb.append(" with a remainder of " + amount + "p");
		
		return sb.toString();
		
	}
	
	
	public String displayProgramConfigs() {
		return new String("Currency: " + this.currency + 
				"\nMinimum value: " + this.minCoinIn + 
				"\nMaximum value: " + this.maxCoinIn);
		
	}
		
	
	// Using Java Swing to create the GUI Main Menu.
	public void GUI() {
		JFrame frame = new JFrame();
		frame.setTitle("***Coin Sorter - Main Menu***");
		frame.setSize(800, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btn1 = new JButton("Coin Calculator");
		btn1.addActionListener(e -> coinCalculatorBox());
		JButton btn2 = new JButton("Multiply Coin Calculator");
		btn2.addActionListener(e -> multiCoinCalculatorBox());
		JButton btn3 = new JButton("Print Coin List");
		btn3.addActionListener(e -> infoBox(printCoinList(), "Coin List"));
		JButton btn4 = new JButton("Set details");
		btn4.addActionListener(e -> setSubMenuGUI(frame));
		JButton btn5 = new JButton("Display Program Configuration");
		btn5.addActionListener(e -> infoBox(displayProgramConfigs(), "Program Configurations"));
		JButton btn6 = new JButton("Quit");
		btn6.addActionListener(e -> frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING)));
	
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 3));
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		panel.add(btn5);
		panel.add(btn6);
		
		frame.add(panel);
		
		frame.setVisible(true);
	
	
	}

	private void coinCalculatorBox() {
		try {
			int amount = Integer.parseInt(JOptionPane.showInputDialog("Please enter an amount between "
			+ this.minCoinIn + " and " + this.maxCoinIn + ": "));
			int coin = Integer.parseInt(JOptionPane.showInputDialog("Please enter a coin to use: "));

			infoBox(coinCalculator(amount, coin), "Coin Calculator");
		} catch (NumberFormatException e) {
			infoBox("Only number values can be accepted", "Error");
		}
	}

	private void multiCoinCalculatorBox() {
		try {
			int amount = Integer.parseInt(JOptionPane.showInputDialog("Please enter an amount between "
					+ this.minCoinIn + " and " + this.maxCoinIn + ": "));
			int coin = Integer.parseInt(JOptionPane.showInputDialog("Please enter a coin to exclude: "));

			infoBox(multiCoinCalculator(amount, coin), "Multi Coin Calculator");
		} catch (NumberFormatException e) {
			infoBox("Only number values can be accepted", "Error");
		}
	}

	public static void infoBox(String infoMessage, String titleBar)
	{
		JOptionPane.showMessageDialog(null, infoMessage,
				titleBar, JOptionPane.INFORMATION_MESSAGE);
	}

	// Using Java Swing to create Sub Menu. 
	// Able to return to the Main Menu. 
	public void setSubMenuGUI(JFrame frame) {

		frame.setEnabled(false);
		JFrame subframe = new JFrame();
		subframe.setTitle("***Set Details Sub-Menu***");
		subframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		subframe.setSize(800, 250);

		JButton sbtn1 = new JButton("Please enter the new currency: ");
		sbtn1.addActionListener(e -> setCurrencyBox());

		JButton sbtn2 = new JButton("Please enter the new minimum coin in: ");
		sbtn2.addActionListener(e -> setMinCoinInBox());

		JButton sbtn3 = new JButton("Please enter the new maximum coin in: ");
		sbtn3.addActionListener(e -> setMaxCoinInBox());

		JButton sbtn4 = new JButton("Return to main menu");
		sbtn4.addActionListener(e -> returnToMainMenu(frame, subframe));

		subframe.dispose();

		JPanel subpanel = new JPanel();
		subpanel.setLayout(new GridLayout(2, 3));
		subpanel.add(sbtn1);
		subpanel.add(sbtn2);
		subpanel.add(sbtn3);
		subpanel.add(sbtn4);

		subframe.add(subpanel);

		subframe.setVisible(true);
	}

	private void setCurrencyBox() {
		try {
			String currency = JOptionPane.showInputDialog("Please enter a new currency: ");

			if (currency != null) {
				this.setCurrency(currency);

				infoBox("The new currency: " + currency, "Currency Updated");
			} else {
				infoBox("The new currency cannot be null", "Error");
			}

		} catch (Exception e) {
			infoBox("Something went wrong: " + e.toString(), "Error");
		}
	}

	private void setMinCoinInBox() {
		try {
			int minCoin = Integer.parseInt(JOptionPane.showInputDialog("Please enter a new minimum coin in value: "));
			this.setMinCoinIn(minCoin);

			infoBox("The new minimum coin value is: " + minCoin, "Min Coin Updated");
		} catch (NumberFormatException e) {
			infoBox("Only number values can be accepted", "Error");
		}
	}

	private void setMaxCoinInBox() {
		try {
			int maxCoin = Integer.parseInt(JOptionPane.showInputDialog("Please enter a new maximum coin in value: "));
			this.setMaxCoinIn(maxCoin);

			infoBox("The new maximum coin value is: " + maxCoin, "Max Coin Updated");
		} catch (NumberFormatException e) {
			infoBox("Only number values can be accepted", "Error");
		}
	}


	private void returnToMainMenu(JFrame frame, JFrame subframe) {
		subframe.dispose();
		frame.setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	
	}
}
	

