package RunVender;
abstract class navigation {
    public void clearing(){
        System.out.print("\033[H\033[2J");
        System.out.flush();               
        /*This clears the console before a new screen is displayed 
        this makes it look like you are navigating between pages*/
    }
}
class transition extends navigation{

}
