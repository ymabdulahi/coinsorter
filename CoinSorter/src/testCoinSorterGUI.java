import java.util.*;

import javax.swing.*;

public class testCoinSorterGUI {

private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		ArrayList<Integer> coinList = new ArrayList<>();
		coinList.add(200);
		coinList.add(100);
		coinList.add(50);
		coinList.add(20);
		coinList.add(10);
		CoinSorterGUI cs = new CoinSorterGUI("£", 0, 10000, coinList);
		cs.GUI();
		// Calling the GUI constructor
	}
}
		

