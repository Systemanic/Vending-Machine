package RunVender;

import java.io.IOException;

abstract class pages {
    public static void layout(){

    }
}
//The stylings for the different pages
//note for it to work compile this file using javac -encoding UTF-16 pages.java
class pageOne extends pages {
    public static void landing() throws IOException {
        System.out.println("        ███╗   ██╗██╗   ██╗███████╗████████╗    ██╗   ██╗███████╗███╗   ██╗██████╗  ██████╗ ██████╗ ");
        System.out.println("        ████╗  ██║██║   ██║██╔════╝╚══██╔══╝    ██║   ██║██╔════╝████╗  ██║██╔══██╗██╔═══██╗██╔══██╗");
        System.out.println("        ██╔██╗ ██║██║   ██║███████╗   ██║       ██║   ██║█████╗  ██╔██╗ ██║██║  ██║██║   ██║██████╔╝");
        System.out.println("        ██║╚██╗██║██║   ██║╚════██║   ██║       ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║██║  ██║██║   ██║██╔══██╗");
        System.out.println("        ██║ ╚████║╚██████╔╝███████║   ██║        ╚████╔╝ ███████╗██║ ╚████║██████╔╝╚██████╔╝██║  ██║");
        System.out.println("        ╚═╝  ╚═══╝ ╚═════╝ ╚══════╝   ╚═╝         ╚═══╝  ╚══════╝╚═╝  ╚═══╝╚═════╝  ╚═════╝ ╚═╝  ╚═╝");
        System.out.println("");   
        System.out.println("");    
        System.out.println("NUST vending machine 1 ");  
        System.out.println("");                                                                                                                            
        System.out.println("Address: 13 Jackson Kaujeua Street, Windhoek, Namibia");
        System.out.println("");
        System.out.println(" ");
        System.out.println("welcome to the vending machine type buy to continue");
    }
}
class pageTwo extends pages{

    public static void customer() throws IOException {

        StockModule.ViewStock.viewAllStock();

    }
}
class pageThree extends pages{
    public static void admin() throws IOException {
        System.out.println("        ███╗   ██╗██╗   ██╗███████╗████████╗    ██╗   ██╗███████╗███╗   ██╗██████╗  ██████╗ ██████╗ ");
        System.out.println("        ████╗  ██║██║   ██║██╔════╝╚══██╔══╝    ██║   ██║██╔════╝████╗  ██║██╔══██╗██╔═══██╗██╔══██╗");
        System.out.println("        ██╔██╗ ██║██║   ██║███████╗   ██║       ██║   ██║█████╗  ██╔██╗ ██║██║  ██║██║   ██║██████╔╝");
        System.out.println("        ██║╚██╗██║██║   ██║╚════██║   ██║       ╚██╗ ██╔╝██╔══╝  ██║╚██╗██║██║  ██║██║   ██║██╔══██╗");
        System.out.println("        ██║ ╚████║╚██████╔╝███████║   ██║        ╚████╔╝ ███████╗██║ ╚████║██████╔╝╚██████╔╝██║  ██║");
        System.out.println("        ╚═╝  ╚═══╝ ╚═════╝ ╚══════╝   ╚═╝         ╚═══╝  ╚══════╝╚═╝  ╚═══╝╚═════╝  ╚═════╝ ╚═╝  ╚═╝");
        System.out.println("   █████╗ ██████╗ ███╗   ███╗██╗███╗   ██╗██╗███████╗████████╗██████╗  █████╗ ████████╗██╗ ██████╗ ███╗   ██╗");
        System.out.println("  ██╔══██╗██╔══██╗████╗ ████║██║████╗  ██║██║██╔════╝╚══██╔══╝██╔══██╗██╔══██╗╚══██╔══╝██║██╔═══██╗████╗  ██║");
        System.out.println("  ███████║██║  ██║██╔████╔██║██║██╔██╗ ██║██║███████╗   ██║   ██████╔╝███████║   ██║   ██║██║   ██║██╔██╗ ██║");
        System.out.println("  ██╔══██║██║  ██║██║╚██╔╝██║██║██║╚██╗██║██║╚════██║   ██║   ██╔══██╗██╔══██║   ██║   ██║██║   ██║██║╚██╗██║");
        System.out.println("  ██║  ██║██████╔╝██║ ╚═╝ ██║██║██║ ╚████║██║███████║   ██║   ██║  ██║██║  ██║   ██║   ██║╚██████╔╝██║ ╚████║");
        System.out.println("  ╚═╝  ╚═╝╚═════╝ ╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝");
        System.out.println("");
        System.out.println("");
        StockModule.AdminNav.runAdminNav();
    }
}

