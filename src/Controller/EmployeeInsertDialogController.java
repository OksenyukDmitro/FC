package Controller;

import DB.DB;
import POJO.Employee;
import POJO.MyAlert;
import POJO.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class EmployeeInsertDialogController {

    @FXML
    private TextField NameField;
    @FXML
    private TextField SurnameField;
    @FXML
    private TextField CountryField;
    @FXML
    private TextField PhoneField;
    @FXML
    private TextField SignField;
    @FXML
    private TextField EndField;
    @FXML
    private TextField SalaryField;
    @FXML
    private TextField PostField;
    @FXML
    private TextField BdayField;




    private Stage dialogStage;
    private Employee employee;
    private boolean okClicked = false;
    private boolean isUpdate = false;


    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param player
     */
   /* public void setPerson(Player player) {
        this.player = player;
        if(player.getBday() != 0)
        isUpdate = true;
        NameField.setText(Integer.toString(player.getIdN()));
        SurnameField.setText(player.getTitle());
        CountryField.setText(Integer.toString(player.getYearN()));
        DirectorField.setText(player.getDirectorN());

    }*/

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }




    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (NameField.getText() == null || NameField.getText().length() == 0) {
            errorMessage += "ID!\n";
        }
        if (SurnameField.getText() == null || SurnameField.getText().length() == 0) {
            errorMessage += "Title !\n";
        }
        if (CountryField.getText() == null || CountryField.getText().length() == 0) {
            errorMessage += "Year!\n";
        }

        if (PhoneField.getText() == null || PhoneField.getText().length() == 0) {
            errorMessage += "director!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(SalaryField.getText());
                Integer.parseInt(PhoneField.getText());

                //Integer.parseInt(CountryField.getText());
                // Integer.parseInt(CountryField.getText());


            } catch (NumberFormatException e) {
                errorMessage += "ID і year - ЧИСЛАААА!\n";
            }
        }




        if (errorMessage.length() == 0) {
            employee = new Employee(0,
                    NameField.getText().toString()
                    ,SurnameField.getText().toString() ,
                    BdayField.getText().toString() ,
                    CountryField.getText() ,
                    SignField.getText().toString(),
                    EndField.getText().toString(),
                    Integer.parseInt(SalaryField.getText()),
                    Integer.parseInt(PhoneField.getText()),
                    PostField.getText()
            );
            return true;
        } else {
            // Show the error message.

            MyAlert.ShowAlertError(errorMessage, dialogStage);
            return false;
        }
    }
    @FXML
    public void AddClicked() throws SQLException {
        // Initialize the person table with the two columns.
        if(this.isInputValid()){

            int count = DB.insertDB(employee);
            if(count > 0 ){
                MyAlert.ShowAlertInfo("Кількість рядків які було встановлено " + count , dialogStage);
            }

            dialogStage.close();
        }}


    @FXML
    public void CencelClicked() {
        dialogStage.close();
    }
}
