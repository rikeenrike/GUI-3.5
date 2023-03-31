import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class View extends JFrame {
      
       //SIZE DECLARATION
       private static final int FRAME_WIDTH=500;
       private static final int FRAME_HEIGHT=500;
       private static final int FRAME_X_ORIGIN=150;
       private static final int FRAME_Y_ORIGIN=250;

       //JAVA SWING DECLARATIONS
       private JLabel studidviewlabel;
       private JTextField Tfstudview;
       private JButton buttonview;
       private viewbuthandler viewhandler;
      
       //JSON DECLARATIONS
       static JSONObject folder;
       static JSONArray list;
       static JSONObject info;
       static JSONParser jsonParser;
       static {
              jsonParser = new JSONParser();
              folder = new JSONObject();
              list = new JSONArray();
              info = new JSONObject();
         }
        //CONSTRUCTOR
      public void createComp(){
        //INSTANTIATIONS (LABEL, TEXTFIELD, BUTTON)
          studidviewlabel = new JLabel("Student ID to View: ");
          Tfstudview = new JTextField(10);
          buttonview = new JButton("View");
      }
      //SET COMPONENTS
      public void setComp(){
          Container pane = getContentPane();
          
          pane.setLayout(new GridLayout(2,2));
        //ADD COMPONENTS  !!LEFT TO RIGHT!!
          pane.add(studidviewlabel);//LEFT SIDE
          pane.add(Tfstudview);//RIGHT SIDE... SO ON AND SO FORTH
          pane.add(buttonview);
        //ACTION LISTENERS
          viewhandler = new viewbuthandler();
          buttonview.addActionListener(viewhandler);
      }
      //FRAME
      public View(){
          setTitle("View");
          setSize(FRAME_WIDTH,FRAME_HEIGHT);
          setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
          setDefaultCloseOperation(HIDE_ON_CLOSE);
          setVisible(true);
      }
     // VIEW STUDENT CODE UPON CLICKING VIEW BUTTON 
      private class viewbuthandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            /*
             * CHECKS IF STUDENT ALREADY EXISTS
             * JSON.reader() method to return the contents of the file as a JSONArray object
             * and then assign it to the list object before adding the new object.
             */
            try {
                list = JSON.reader();
            } catch (IOException | ParseException | org.json.simple.parser.ParseException e1) {
                e1.printStackTrace();
            }
             /*
              * EXISTS IS SET TO FALSE BY DEFAULT
              * ONLY IF THE STUDENT ID IS FOUND IN THE JSON FILE, EXISTS WILL BE SET TO TRUE
              */
            boolean exists = false;
            // toview VARIABLE IS USED TO GET THE INDEX OF THE STUDENT TO BE VIEWED
            int toview = 0;
            for (int i = 0; i < list.size(); i++, toview++) {
                JSONObject infoObj = (JSONObject) list.get(i);
                String sampleid = (String) infoObj.get("id");
                //PRINTLINE IS A TERMINAL CHECKER
                System.out.println("checking");
                if (sampleid.equals(Tfstudview.getText())) {     
                    exists = true;
                    break;
                }
            }
            /*
             * IF STUDENT DOES EXIST "exists = true", WILL PROCEED TO GET THE STUDENT'S INFO
             * ELSE "exists = false" WILL DISPLAY STUDENT DOES NOT EXIST
             */
            if (exists) {
                // TYPECASTING JSONARRAY(INDEX) TO JSONOBJECT studinfo
                JSONObject studinfo = (JSONObject) list.get(toview);  
                // TYPECASTING JSONOBJECT studinfo TO STRING using the key
                String getID = (String) studinfo.get("id");
                String getfn = (String) studinfo.get("firstname");
                String getln = (String) studinfo.get("lastname");
                String getbday = (String) studinfo.get("birthday");
                String getgender = (String) studinfo.get("gender"); 
                //TERMINAL CHECKER
                System.out.println(getID + " " + getfn + " " + getln + " " + getbday + " " + getgender);
                
                // CALL VIEWTAB
                ViewTAB view = new ViewTAB();
                view.createComp();
                view.setComp();
                // SET THE INFO TO THE VIEWTAB class, settoview method
                ViewTAB.settoview(getID, getfn, getln, getbday, getgender);

        } else {
            JOptionPane.showMessageDialog(null, "Student ID does not exist!");
        }
      } 
}
    //MAIN METHOD
      public static void main(String[] args) {
          View view = new View();
              view.createComp();
              view.setComp();
      }

}
