import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_Menu extends JFrame{
    //SIZE DECLARATIONS
    private static final int FRAME_WIDTH=500;
    private static final int FRAME_HEIGHT=500;
    private static final int FRAME_X_ORIGIN=150;
    private static final int FRAME_Y_ORIGIN=250;
    
    
    //JAVA.SWING DECLARATIONS
    private JMenuBar menubar;
    private JMenu menu1, viewitem;
    private JMenuItem additem, delitem, indiview, viewall;
    private addmenuhandler addhandler;
    private delmenuhandler delhandler;
    private viewmenuhandler viewhandler;
    
    //CONSTRUCTOR --> ADDING OF COMPONENTS
    private void jmenucomp(){
            //MENU BAR
            menubar = new JMenuBar();
            menu1 = new JMenu("Student Management");
            menu1.setMnemonic(KeyEvent.VK_C);
            
            /*
             * ARRANGEMENTS OF MENU ITEMS
             * MENU ITEMS:
             * ADD STUDENT
             * DELETE STUDENT
             * VIEW STUDENT (SUBMENU)
             *          INDIVIDUAL VIEW
             *           VIEW ALL
             */

            additem = new JMenuItem("Add Student");
            addhandler = new addmenuhandler();
            additem.addActionListener(addhandler);
            
            delitem = new JMenuItem("Delete Student");
            delhandler = new delmenuhandler();
            delitem.addActionListener(delhandler);

            
            viewitem = new JMenu("View Student");
            indiview = new JMenuItem("Individual View");
            viewhandler = new viewmenuhandler();
            indiview.addActionListener(viewhandler);
            viewall = new JMenuItem("View All");
            viewitem.add(indiview);
            viewitem.add(viewall);

            menu1.add(additem);
            menu1.add(delitem);
            menu1.add(viewitem);
            menubar.add(menu1);
            setJMenuBar(menubar);

 }
 //FRAME SETTINGS
    public void framesettings(){
    setTitle("MY OPTIONS"); 
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    
    getContentPane().setBackground(new Color(255, 153 ,204));
 }

    //ADD STUDENT ACTION LISTENER
    private class addmenuhandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Add app = new Add();
            app.createComp();
            app.setComp();
       
    }
}
    //DELETE STUDENT ACTION LISTENER
    private class delmenuhandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Delete del = new Delete();
            del.createComp();
            del.setComp();

        }

    }
    //VIEW STUDENT ACTION LISTENER
    private class viewmenuhandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            View view = new View();
            view.createComp();
            view.setComp();
        }
    }

    //MAIN METHOD   
 public static void main(String[] args) {
    
    Main_Menu x  = new Main_Menu();
        x.jmenucomp(); 
        x.framesettings();
 }
}


