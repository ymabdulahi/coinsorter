import java.util.*;
// Allows the program to import built in packages in java.

// Creating main class of Coin Sorter.
public class CoinSorter {
	
	// Giving the main attribute, whilst giving each attribute a private keyword (access modifier).
	private String currency;
	private int minCoinIn;
	private int maxCoinIn;
	private ArrayList<Integer> coinList;
	
	// Creating main constructor, and passing through parameters whilst calling the main methods. 
	public CoinSorter(String currency, int minCoinIn, int maxCoinIn, ArrayList<Integer> coinList) {
		
		// Collection provide the architecture to manipulate the List by keeping it in the correct order. 
		Collections.sort(coinList);
		Collections.reverse(coinList);
		this.currency = currency;
		this.minCoinIn = minCoinIn;
		this.maxCoinIn = maxCoinIn;
		this.coinList = coinList;
		
	}

	public CoinSorter(){}
	
	// Using the set methods set it in the brief.
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
	
	// Using get methods set in the brief.
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
	
	// Using StringBuilder to allow modifications. 
	public String printCoinList() {
		StringBuilder sb = new StringBuilder();
		sb.append("The current coin denominations are in circulation: ");
		for (int coin: coinList ) {
			sb.append(coin + ", ");
		}
		sb.setLength(sb.length()-2);
		return sb.toString();
	}
	
	// Create calculator which return the maximum number of coins the input coin type 
	// that can be exchanged, in addition to the remainder as a string.
	public String coinCalculator(int amount, int coin) {
		
		int numOfCoins = amount / coin;
		int remainder = amount % coin; 
		
		return new String("A total of " + numOfCoins + " x " + 
		coin + "p coins can be exchanged, with a remainder of " + remainder + "p.");
	}
	
	// Using StringBuilder to allow modifications. Using the for loop to exclude the input coin type. 
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
 }