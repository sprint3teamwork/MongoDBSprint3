package org.example.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.example.model.domain.*;
import org.example.model.domain.entity.Product;
import org.example.model.repository.DBInteraction;

public class Application {

    static Scanner sc = new Scanner(System.in);
    static FlowerShop flowerShop = new FlowerShop("");
    static ProductFactory productFactory = new ProductFactory();
    static DBInteraction db = new DBInteraction();

    public static void boot() {
        int option = -1;

        db.tablesInitializer();
        loadData();

        do {
            option = menu();
            switch (option) {
                case 0 -> closeApplication();
                case 1 -> createFlowerShop();
                case 2 -> addTree();
                case 3 -> addFlower();
                case 4 -> addDecoration();
                case 5 -> showStock();
                case 6 -> removeTree();
                case 7 -> removeFlower();
                case 8 -> removeDecoration();
                case 9 -> printStockQuantities();
                case 10 -> printStockTotalValue();
                case 11 -> buy();
                case 12 -> invoiceLog();
                case 13 -> printTotalEarnings();
            }
        } while (option != 0);

    }

    public static int menu() {
        int option = -1;

        System.out.print("MENU:\n"
                + "1.Create Flower Shop                 8.Remove Decortion\n"
                + "2.Add Tree item                      9.Quantity by stock type\n"
                + "3.Add Flower item                    10.FlowerShop Total Value\n"
                + "4.Add Decoration                     11.Start Buy\n"
                + "5.Show Stock                         12.Invoice Log\n"
                + "6.Remove Tree                        13.Total Earnings from Sales\n"
                + "7.Remove Flower                      CLOSE (press 0)\n\n");


        option = sc.nextInt();
        sc.nextLine();

        while (option < 0 || option > 13) {
            System.out.print("The number introduced isn't supported.\nTry again: ");
            option = sc.nextInt();
            sc.nextLine();
        }
        return option;
    }

    public static void loadData() {
        List<Product> stockList = db.getStockData();
        List<Invoice> invoiceList = db.getInvoiceData();

        for (Product product : stockList) {
            flowerShop.addStock(product);
        }
        for (Invoice invoice : invoiceList) {
            invoice.setProductList(loadInvoiceProducts(invoice.getId()));
            flowerShop.addInvoice(invoice);
        }
    }

    public static List<Product> loadInvoiceProducts(int invoiceId) {
        List<Product> soldProducts = db.getProductsSoldData();
        List<Product> invoiceProducts = new ArrayList<>();

        for (Product product : soldProducts) {
            if (product.getInvoiceId() == invoiceId) {
                invoiceProducts.add(product);
            }
        }
        return invoiceProducts;
    }

    public static void closeApplication() {
        System.out.println("See You Soon!");
        sc.close();
    }

    public static void createFlowerShop() {
        String name = "";

        if (db.stockDataIsEmpty() & flowerShop.getName().length() < 1) {
            System.out.println("Introduce the flower shop name: ");
            name = sc.nextLine();
            flowerShop.setName(name);
            System.out.println("The flower-shop " + name + " was created successfully!\n");

        } else if (db.stockDataIsEmpty() & flowerShop.getStockList().isEmpty() & flowerShop.getInvoiceLog().isEmpty()) {
            System.out.println("You alreday have a flower-shop. Just start adding stock. Jeez...\n");

        } else {
            System.out.println("You already created a flower-shop, you greedy bastard! Press 5 and you'll see the records\n");
        }

    }

    public static int searchList(int id, String type) {        //returns -1 if product is not in list.
        int index = -1;

        if (type.equalsIgnoreCase("product")) {
            index = searchStock(id);
        } else if (type.equalsIgnoreCase("invoice")) {
            index = searchInvoiceLog(id);
        }
        return index;
    }

    public static int searchStock(int id) {
        int counter = 0;
        int index = -1;

        while (counter < flowerShop.getStockList().size()) {
            if (flowerShop.getStockList().get(counter).getId() == id) {
                index = counter;
                counter = flowerShop.getStockList().size();
            }
            counter++;
        }
        return index;
    }

    public static int searchInvoiceLog(int id) {
        int counter = 0;
        int index = -1;

        while (counter < flowerShop.getInvoiceLog().size()) {
            if (flowerShop.getInvoiceLog().get(counter).getId() == id) {
                index = counter;
                counter = flowerShop.getInvoiceLog().size();
            }
            counter++;
        }
        return index;
    }

    public static void removeTree() {
        if (flowerShop.getProductMap().get("Tree") == 0) {
            System.out.println("There are no trees in stock.\n");
        } else {
            int id = removerPrompt("Tree");
            int listIndex = searchList(id, "product");

            if (flowerShop.getStockList().get(listIndex) != null) {
                Product product = flowerShop.getStockList().get(listIndex);
                flowerShop.removeStock(flowerShop.getStockList().get(listIndex));
                db.removeTree(product);
                System.out.println("The item '" + product.getName() + "' has been removed from the list.\n");
            } else {
                System.out.println("The tree with Id# " + id + " does not exist.\n");
            }
        }

    }

    public static void removeFlower() {
        if (flowerShop.getProductMap().get("Flower") == 0) {
            System.out.println("There are no flowers in stock.\n");
        } else {
            int id = removerPrompt("Flower");
            int listIndex = searchList(id, "product");

            if (flowerShop.getStockList().get(listIndex) != null) {
                Product product = flowerShop.getStockList().get(listIndex);
                flowerShop.removeStock(flowerShop.getStockList().get(listIndex));
                db.removeFlower(product);
                System.out.println("The item '" + product.getName() + "' has been removed from the list.\n");
            } else {
                System.out.println("The flower with Id# " + id + " does not exist.\n");
            }
        }

    }

    public static void removeDecoration() {
        if (flowerShop.getProductMap().get("Decoration") == 0) {
            System.out.println("There are no decoration items in stock.\n");
        } else {
            int id = removerPrompt("Decoration");
            int listIndex = searchList(id, "product");

            if (flowerShop.getStockList().get(listIndex) != null) {
                Product product = flowerShop.getStockList().get(listIndex);
                flowerShop.removeStock(flowerShop.getStockList().get(listIndex));
                db.removeDecoration(product);
                System.out.println("The item '" + product.getName() + "' has been removed from the list.\n");
            } else {
                System.out.println("The decoration with Id# " + id + " does not exist.\n");
            }
        }
    }

    public static int removerPrompt(String type) {
        int idSelected;
        int productPosition;

        System.out.println("Here are all the '" + type + "' items. Type the id of the item you want to remove");
        showStockByType(type);
        System.out.print("Id: ");
        idSelected = sc.nextInt();
        sc.nextLine();
        productPosition = searchList(idSelected, "product");

        while (productPosition == -1) {
            productPosition = searchList(idSelected, "product");
            System.out.println("Id mismatch.\nEnter Id again: ");
            idSelected = sc.nextInt();
        }

        return idSelected;
    }

    public static void showStockByType(String type) {
        for (int i = 0; i < flowerShop.getStockList().size(); i++) {
            if (flowerShop.getStockList().get(i).getType().equalsIgnoreCase(type)) {
                System.out.println(flowerShop.getStockList().get(i).toString());
            }
        }
        System.out.println("\n");
    }

    public static void addTree() {
        String name = "";
        float price = 0.0f;
        float height = 0.0f;

        System.out.println("What's the tree species?");
        name = sc.nextLine();
        System.out.println("The price will be?");
        price = Float.parseFloat(sc.next());
        System.out.println("And it's height?");
        height = Float.parseFloat(sc.next());
        sc.nextLine();
        Product p = productFactory.createProduct("Tree", name, price, height);
        flowerShop.addStock(p);
        db.insertTree(p);
        System.out.println("The item '" + p.getName() + "' was created succesfully!\n");
    }

    public static void addFlower() {
        String name = "";
        float price = 0.0f;
        String color = "";

        System.out.println("What's the flower species?");
        name = sc.nextLine();
        System.out.println("The price will be?");
        price = Float.parseFloat(sc.next());
        sc.nextLine();
        System.out.println("And it's color is?");
        color = sc.nextLine();
        Product p = productFactory.createProduct("Flower", name, price, color);
        flowerShop.addStock(p);
        db.insertFlower(p);
        System.out.println("The item '" + p.getName() + "' was created succesfully!\n");
    }

    public static void addDecoration() {
        String name = "";
        float price = 0.0f;
        int option = 0;
        boolean isWood;

        System.out.println("What's the item's name?");
        name = sc.nextLine();
        System.out.println("The price will be?");
        price = Float.parseFloat(sc.next());
        System.out.println("And it is made of wood(1) or plastic?(2)");
        option = sc.nextInt();
        sc.nextLine();
        isWood = option == 1;//if option == 1,isWood = true, if not is plastic
        Product p = productFactory.createProduct("Decoration", name, price, isWood);
        flowerShop.addStock(p);
        db.insertDecoration(p);
        System.out.println("The item '" + p.getName() + "' was created succesfully!\n");
    }

    public static void showStock() {
        flowerShop.showStock();
    }

    public static void startBuy() {
        int option = 1;
        int invoiceId;
        int invoicePosition;
        Invoice invoice = new Invoice();
        db.insertInvoice(invoice);

        flowerShop.addInvoice(invoice);
        invoicePosition = searchList(invoice.getId(), "invoice");
        invoice = flowerShop.getInvoiceLog().get(invoicePosition);

        while (option == 1) {
            System.out.println("Here is the product list. Type the id of the product you want to buy");
            invoice = saleLoop(invoice);
            System.out.println("Do you want to keep shopping?\nYES(1)      NO(2)       (3)[Delete product from cart]\n");
            System.out.println("Response (numerical): ");
            option = sc.nextInt();
            sc.nextLine();

            if (option == 3 && invoice.getProductList().size() > 0) {
                deleteProductFromInvoice(invoice);
                System.out.println("Do you want to keep shopping?\nYES(1)      NO(2)       (3)[Delete product from cart]\n");
                System.out.println("Response (numerical): ");
                option = sc.nextInt();
                sc.nextLine();
            }

            if (option == 3 && invoice.getProductList().size() == 0) {
                System.out.println("Your shopping cart is empty\nDo you want to keep shopping?\n" +
                        "YES(1)      NO(2)\n");
                System.out.println("Response (numerical): ");
                option = sc.nextInt();
                sc.nextLine();
            }
        }

        if (invoice.getProductList().size() > 0) {
            flowerShop.setTotalEarnings(flowerShop.getTotalEarnings() + invoice.getTotalSale());
            db.updateInvocie(invoice);
            System.out.println("\nRECEIPT:");
            System.out.println(invoice.toString() + "\nInvoice archived.\n");
        } else {
            flowerShop.removeInvoice(invoice);
            System.out.println("\nInvoice record deleted from InvoiceLog.\n");
        }
    }

    public static void buy() {
        if (flowerShop.getStockList().size() == 0) {
            System.out.println("There are no products in stock.\n");
        } else {
            startBuy();
        }
    }

    public static Invoice saleLoop(Invoice invoice) {
        int idSelected;
        int productPosition = -1;

        if (flowerShop.getStockList().size() == 0) {
            System.out.println("There are no more products in stock.\n");
        } else {

            flowerShop.showStock();
            System.out.print("Id: ");
            idSelected = sc.nextInt();
            sc.nextLine();
            productPosition = searchList(idSelected, "product");

            while (productPosition == -1) {
                System.out.println("Id mismatch.\nEnter Id again: ");
                idSelected = sc.nextInt();
                sc.nextLine();
                productPosition = searchList(idSelected, "product");
            }

            invoice.addProduct(flowerShop.getStockList().get(productPosition));
            flowerShop.getStockList().get(productPosition).setInvoiceId(invoice.getId());//set invoice_id to product
            db.updateProduct(flowerShop.getStockList().get(productPosition));//update product on SQL table
            flowerShop.removeStock(flowerShop.getStockList().get(productPosition));
            System.out.println("Shopping cart:\n");
            System.out.println(invoice.toString() + "\n");

            return invoice;
        }
        return invoice;
    }

    public static void deleteProductFromInvoice(Invoice invoice) {
        int numberSelected;
        Product p;

        System.out.println("Select the number of the product you want to delete from the invoice:\n");
        System.out.print(invoice.toString() + "\nProduct number: ");
        numberSelected = sc.nextInt();
        sc.nextLine();
        p = invoice.getProductList().get(numberSelected - 1);
        invoice.removeProduct(p);
        flowerShop.addStock(p);
        System.out.println("\n'" + p.getName() + "' was deleted succesfully.\n");
    }

    public static void invoiceLog() {
        if (flowerShop.getInvoiceLog().size() == 0) {
            System.out.println("There are no invoices archived yet.\n");
        } else {
            flowerShop.showInvoiceList();
            System.out.println();
        }
    }

    public static void printTotalEarnings() {
        System.out.println("The total earnings of " + flowerShop.getName() + " is: "
                + flowerShop.getTotalEarnings() + "€.\n");
    }

    public static void printStockTotalValue() {
        System.out.println("The total stock value of the flowershop is: " + flowerShop.getStockTotalValue() + "€.\n");
    }

    public static void printStockQuantities() {
        flowerShop.getProductMap().forEach((key, value) -> System.out.println("The total amount of "
                + key + " in stock is: " + value));
        System.out.print("\n");
    }
}

