import java.awt.Container;
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
import java.awt.GridLayout;

public class Add extends JFrame{

         // SIZE DECLARATIONS
            private static final int FRAME_WIDTH=500;
            private static final int FRAME_HEIGHT=500;
            private static final int FRAME_X_ORIGIN=150;
            private static final int FRAME_Y_ORIGIN=250;
        
         //JAVA SWING DECLARATIONS
            private JLabel studIDlabel, fnlabel, lnlabel, bdaylabel, genderlabel;
            private JButton buttonsave, buttonclear;
            private JTextField Tfstudid, Tffn, Tfln, Tfbday, TfGender;
            private savebuthandler savehandler;
            private clearbuthhandler clearhandler;
            
        //JSON DECLARATIONS
            static JSONObject folder;
            static JSONArray list;
            static JSONObject info;
            static JSONParser jsonParser;
            static {
                jsonParser = new JSONParser();
                folder = new JSONObject();
                list = new JSONArray();    
           }

    //CONSTRUCTOR
    public void createComp(){
        
        //INSTANTIATIONS (LABELS)
        studIDlabel = new JLabel("Student ID: ");
        fnlabel = new JLabel("First Name: ");
        lnlabel = new JLabel("Last Name: ");
        bdaylabel = new JLabel("Birthday: ");
        genderlabel = new JLabel("Gender: ");
        
        //INSTANTIATIONS (TEXTFIELDS)
        Tfstudid = new JTextField(10);
        Tffn = new JTextField(10);
        Tfln = new JTextField(10);
        Tfbday = new JTextField(10);
        TfGender =  new JTextField(10);
       
        //INSTANTIATIONS (BUTTONS)
        buttonclear = new JButton("Clear");
        buttonsave = new JButton("Save");
    }
    //SET COMPONENTS
    public void setComp(){
        Container pane =  getContentPane();

        pane.setLayout(new GridLayout(6, 2));
        //ADD COMPONENTS  !!LEFT TO RIGHT!!
        pane.add(studIDlabel); //LEFT SIDE
        pane.add(Tfstudid); //RIGHT SIDE
        pane.add(fnlabel); //LEFT SIDE... SO ON AND SO FORTH
        pane.add(Tffn);
        pane.add(lnlabel);
        pane.add(Tfln);
        pane.add(bdaylabel);
        pane.add(Tfbday);
        pane.add(genderlabel);
        pane.add(TfGender);
        pane.add(buttonsave);
        pane.add(buttonclear);
        
        //ACTION LISTENERS
        savehandler = new savebuthandler();
        buttonsave.addActionListener(savehandler);

        clearhandler = new clearbuthhandler();
        buttonclear.addActionListener(clearhandler);
    }
    //FRAME
    public Add(){
        setTitle("Add");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }
    // ADD STUDENT CODE UPON CLICKING SAVE BUTTON
    private class savebuthandler implements ActionListener{
        //ACTION PERFORMED
        public void actionPerformed(ActionEvent arg0) {
            try {
                  /*
                   * CHECKS IF STUDENT ALREADY EXISTS
                   * JSON.reader() method to return the contents of the file as a JSONArray object
                   * and then assign it to the list object before adding the new object.
                   */
                    list = JSON.reader();
                        /*
                         * EXISTS IS SET TO FALSE BY DEFAULT
                         * ONLY IF THE STUDENT ID IS FOUND IN THE JSON FILE, EXISTS WILL BE SET TO TRUE
                         */
                        boolean exists = false;
                        for (int i = 0; i < list.size(); i++) {
                            JSONObject infoObj = (JSONObject) list.get(i);
                            String getid = (String) infoObj.get("id");
                            //PRINTLINE IS A TERMINAL CHECKER
                            System.out.println("checking");
                            if (getid.equals(Tfstudid.getText())) {
                                exists = true;
                                break;
                            }   
                           }  
                            /*
                             * IF STUDENT DOES EXIST "exists = true", WILL NOT PROCEED TO SAVE, THEN WILL DISPLAY A WARNING MESSAGE
                             * ELSE "exists = false" WILL PROCEED TO SAVE STUDENT IN JSON FILE, THEN WILL DISPLAY A SUCCESS MESSAGE
                             */
                            if (exists) {
                                JOptionPane.showMessageDialog(null,"Student Already Exists!","EXISTS!",JOptionPane.WARNING_MESSAGE);
                                System.out.println("Exists!");
                            }else{
                                info = new JSONObject();
                                
                                info.put("id", Tfstudid.getText());
                                info.put("firstname", Tffn.getText());
                                info.put("lastname",Tfln.getText());
                                info.put("birthday", Tfbday.getText());
                                info.put("gender", TfGender.getText());
                                
                                list.add(info);
                                JSON.folder.put("folder", list);
                                
                                JSON.writer();
                                JOptionPane.showMessageDialog(null,"Student Saved Successfully!","SAVED",JOptionPane.PLAIN_MESSAGE);
                            }
                                    } catch (IOException | ParseException | org.json.simple.parser.ParseException e) {
                                        e.printStackTrace();
                                    }
                                 
                            }
                        }
        
    //CLEAR BUTTON
    private class clearbuthhandler implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            Tfstudid.setText("");
            Tffn.setText("");
            Tfln.setText("");
            Tfbday.setText("");
            TfGender.setText("");
        }
    }

   //MAIN METHOD
    public static void main(String[] args){
       Add add = new Add();
        add.createComp();
        add.setComp();
    }
}
