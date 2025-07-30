import java.util.*;

public class StockMarketAnalyzer {
    static List<Integer> prices = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("=================================");
            System.out.println("      STOCK MARKET ANALYZER    ");
            System.out.println("=================================");
            System.out.println("1. View Stock Prices");
            System.out.println("2. Add New Price");
            System.out.println("3. Max Profit (Single Transaction)");
            System.out.println("4. Max Profit (Multiple Transactions)");
            System.out.println("5. Stock Span Analysis");
            System.out.println("6. Exit");
            System.out.print("\nEnter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> viewPrices();
                case 2 -> addPrice();
                case 3 -> maxProfitSingle();
                case 4 -> maxProfitMultiple();
                case 5 -> stockSpan();
                case 6 -> System.out.println("Exiting... Thank you for using Stock Market Analyzer!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 6);
    }

    static void viewPrices() {
        if (prices.isEmpty()) {
            System.out.println("No stock prices added yet.");
            return;
        }
        System.out.println("Current Stock Prices:");
        for (int i = 0; i < prices.size(); i++) {
            System.out.println("Day " + (i + 1) + ": ₹" + prices.get(i));
        }
    }

    static void addPrice() {
        System.out.print("Enter today's price: ");
        int price = sc.nextInt();
        prices.add(price);
        System.out.println("Price added successfully!\n");
    }

    static void maxProfitSingle() {
        if (prices.size() < 2) {
            System.out.println("Need at least 2 prices to calculate profit.");
            return;
        }
        int minPrice = prices.get(0);
        int maxProfit = 0;
        int buyDay = 0, sellDay = 0;

        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) - minPrice > maxProfit) {
                maxProfit = prices.get(i) - minPrice;
                sellDay = i;
            }
            if (prices.get(i) < minPrice) {
                minPrice = prices.get(i);
                buyDay = i;
            }
        }

        System.out.println("\uD83D\uDCC8 Maximum Profit (Single Transaction): ₹" + maxProfit);
        System.out.println("Buy on Day " + (buyDay + 1) + " at ₹" + prices.get(buyDay) + ", Sell on Day " + (sellDay + 1) + " at ₹" + prices.get(sellDay));
    }

    static void maxProfitMultiple() {
        if (prices.size() < 2) {
            System.out.println("Need at least 2 prices to calculate profit.");
            return;
        }
        int totalProfit = 0;
        System.out.println("\uD83D\uDCC8 Maximum Profit (Multiple Transactions):");
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) > prices.get(i - 1)) {
                int profit = prices.get(i) - prices.get(i - 1);
                totalProfit += profit;
                System.out.println("Transaction: Buy on Day " + i + " at ₹" + prices.get(i - 1) + " → Sell on Day " + (i + 1) + " at ₹" + prices.get(i) + " → Profit ₹" + profit);
            }
        }
        System.out.println("Total Profit: ₹" + totalProfit);
    }

    static void stockSpan() {
        if (prices.isEmpty()) {
            System.out.println("No prices to analyze.");
            return;
        }
        int n = prices.size();
        int[] span = new int[n];
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && prices.get(st.peek()) <= prices.get(i)) {
                st.pop();
            }
            span[i] = (st.isEmpty()) ? (i + 1) : (i - st.peek());
            st.push(i);
        }

        System.out.println("\uD83D\uDCCA Stock Span:");
        for (int i = 0; i < n; i++) {
            System.out.println("Day " + (i + 1) + ": " + span[i]);
        }
    }
}