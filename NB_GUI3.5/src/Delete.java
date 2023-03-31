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

public class Delete extends JFrame{

        //SIZE DECLARATION
         private static final int FRAME_WIDTH=500;
         private static final int FRAME_HEIGHT=500;
         private static final int FRAME_X_ORIGIN=150;
         private static final int FRAME_Y_ORIGIN=250;

        //JAVA SWING DECLARATIONS
         private JLabel studiddellabel;
         private JTextField Tfstudel;
         private JButton buttondelete;
         private deletebuthandler delhandler;

        //JSON DECLARATIONS
         static JSONObject folder;
         static JSONArray list;
         static JSONObject info;
         static{
            folder = new JSONObject();
            list = new JSONArray();
            info = new JSONObject();
         }

        //CONSTRUCTOR
        public void createComp(){
            //INSTANTIATIONS (LABEL, TEXTFIELD, BUTTON)
            studiddellabel = new JLabel("Student ID to Delete: ");
            Tfstudel = new JTextField(10);
            buttondelete = new JButton("Delete");
        }
        //SET COMPONENTS
        public void setComp(){
            Container pane = getContentPane();
            
            pane.setLayout(new GridLayout(2,2));
            //ADD COMPONENTS  !!LEFT TO RIGHT!!
            pane.add(studiddellabel); //LEFT SIDE
            pane.add(Tfstudel); //RIGHT SIDE... SO ON AND SO FORTH
            pane.add(buttondelete);
            
            //ACTION LISTENERS
            delhandler = new deletebuthandler();
            buttondelete.addActionListener(delhandler);

        
        }
        //FRAME
        public Delete(){
            setTitle("Delete");
            setSize(FRAME_WIDTH,FRAME_HEIGHT);
            setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
            setDefaultCloseOperation(HIDE_ON_CLOSE);
            setVisible(true);
        }

        // DELETE STUDENT CODE UPON CLICKING DELETE BUTTON
        private class deletebuthandler implements ActionListener{
            // delete VARIABLE IS USED TO GET THE INDEX OF THE STUDENT TO BE DELETED
            int delete;
            @Override
            public void actionPerformed(ActionEvent e) {
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
                    delete  = 0;    
                        for (int i = 0; i < list.size(); i++, delete++) {  
                        JSONObject infoObj = (JSONObject) list.get(i);
                        String getid = (String) infoObj.get("id");
                        //PRINTLINE IS A TERMINAL CHECKER
                        System.out.println("checking");
                        if (getid.equals(Tfstudel.getText())) {  
                            exists = true;
                            break;
                        }
                    }  
                    /*
                     * IF STUDENT DOES EXIST "exists = true", WILL PROCEED COMFRIMATION OPTION
                     * ELSE "exists = false" WILL DISPLAY STUDENT DOES NOT EXIST
                     */
                     if (exists) {
                        new confirmation();
                        }else{
                        JOptionPane.showMessageDialog(null,"Student Does not Exist!","ERROR!",JOptionPane.WARNING_MESSAGE);
                        }
                    // TERMINAL CHECKER
                    System.out.println(exists);
                    } catch (IOException | ParseException | org.json.simple.parser.ParseException e1) {
                        e1.printStackTrace();
                    }   
                }
            //CONFIRMATION DELETE
            private class confirmation {
            JFrame f = new JFrame();
            confirmation(){
            int result = JOptionPane.showConfirmDialog(f,"Are you sure you want to delete this student?","Confirmation",JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                            list.remove(delete);
                        try {
                            JSON.writer();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } 
            //MAIN METHOD
            public static void main(String[] args) {
                Delete del = new Delete();
                    del.createComp();
                    del.setComp();
            }
    }