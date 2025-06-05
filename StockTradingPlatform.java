import java.util.*;

public class StockTradingPlatform {
    static Map<String, Double> market = new HashMap<>();
    static Map<String, Integer> portfolio = new HashMap<>();
    static double balance = 10000.0;  // Initial balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initMarket();

        int choice;
        do {
            System.out.println("\n==== Stock Trading Platform ====");
            System.out.println("1. View Market Prices");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewMarket();
                    break;
                case 2:
                    buyStock(scanner);
                    break;
                case 3:
                    sellStock(scanner);
                    break;
                case 4:
                    viewPortfolio();
                    break;
                case 5:
                    System.out.println("Thank you for trading!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        scanner.close();
    }

    static void initMarket() {
        market.put("AAPL", 180.0);
        market.put("GOOGL", 2900.0);
        market.put("MSFT", 320.0);
        market.put("TSLA", 700.0);
        market.put("AMZN", 3300.0);
    }

    static void viewMarket() {
        System.out.println("\n--- Market Prices ---");
        for (String stock : market.keySet()) {
            System.out.println(stock + ": $" + market.get(stock));
        }
    }

    static void buyStock(Scanner scanner) {
        System.out.print("Enter stock symbol to buy: ");
        String stock = scanner.next().toUpperCase();

        if (!market.containsKey(stock)) {
            System.out.println("Invalid stock symbol.");
            return;
        }

        System.out.print("Enter quantity to buy: ");
        int qty = scanner.nextInt();
        double price = market.get(stock) * qty;

        if (price > balance) {
            System.out.println("Insufficient balance.");
            return;
        }

        balance -= price;
        portfolio.put(stock, portfolio.getOrDefault(stock, 0) + qty);
        System.out.println("Purchased " + qty + " of " + stock);
    }

    static void sellStock(Scanner scanner) {
        System.out.print("Enter stock symbol to sell: ");
        String stock = scanner.next().toUpperCase();

        if (!portfolio.containsKey(stock)) {
            System.out.println("You don’t own this stock.");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int qty = scanner.nextInt();
        int owned = portfolio.get(stock);

        if (qty > owned) {
            System.out.println("You don’t have enough shares.");
            return;
        }

        double price = market.get(stock) * qty;
        balance += price;

        if (qty == owned) {
            portfolio.remove(stock);
        } else {
            portfolio.put(stock, owned - qty);
        }

        System.out.println("Sold " + qty + " of " + stock);
    }

    static void viewPortfolio() {
        System.out.println("\n--- Your Portfolio ---");
        for (String stock : portfolio.keySet()) {
            int qty = portfolio.get(stock);
            double value = market.get(stock) * qty;
            System.out.println(stock + ": " + qty + " shares | Value: $" + value);
        }
        System.out.println("Balance: $" + balance);
    }
}
