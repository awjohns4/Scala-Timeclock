import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.stage.Stage


object TimeClock{
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[Main], args: _*)
  }
}

class Main extends Application {
  override def start(primaryStage: Stage): Unit = {

    var employeeMap = new EmployeeMap

    var Dan = new Employee("Dan", 1, "Manager")
    var John = new Employee("John", 2, "Associate")
    var Lee = new Employee("Lee", 3, "Associate")

    employeeMap.addEmployee(Dan)
    employeeMap.addEmployee(John)
    employeeMap.addEmployee(Lee)

    var employeeList = employeeMap.getAllEmployees()

    val clockedInLabel = new Label("")
    val selectEmployeeLabel = new Label("Select an employee")
    val reportOutputLabel = new Label("")
    val reportChoiceBox = new ChoiceBox[Option[Employee]]

    for(i <- 0 to employeeList.length){
      reportChoiceBox.getItems.add(employeeMap.getEmployee(i))
    }

    val employeeNumber = new TextField
    val homePunchButton = new Button("Punch Time")
    val homeReportButton = new Button("Time report")
    val timePunchButton = new Button("Punch Time")

    val homeHBox = new HBox(10, homePunchButton, homeReportButton)
    homeHBox.setAlignment(Pos.CENTER)

    val homeVBox = new VBox(10, homeHBox, clockedInLabel)
    homeVBox.setAlignment(Pos.CENTER)

    val timePunchVBox = new VBox(10, employeeNumber, timePunchButton)
    timePunchVBox.setAlignment(Pos.CENTER)

    val reportVBox = new VBox(10, selectEmployeeLabel, reportChoiceBox,reportOutputLabel)
    reportVBox.setAlignment(Pos.CENTER)

    val homeScene = new Scene(homeVBox)

    homePunchButton.setOnAction(ActionEvent =>{
      val punchScene = new Scene(timePunchVBox)
      primaryStage.setScene(punchScene)
    })

    homeReportButton.setOnAction(ActionEvent =>{
      val reportScene = new Scene(reportVBox)
      primaryStage.setScene(reportScene)
    })

    timePunchButton.setOnAction(ActionEvent =>{
      var text = employeeNumber.getText
      employeeMap.getEmployee(Integer.parseInt(text)).get.punchTime()
      if(employeeMap.getEmployee(Integer.parseInt(text)).get.getClockedIn){
        println( employeeMap.getEmployee(Integer.parseInt(text)).get.getEmployeeName+" Was punched in")
        clockedInLabel.setText(employeeMap.getEmployee(Integer.parseInt(text)).get.getEmployeeName+" Was punched in")
      }
      else {
        println( employeeMap.getEmployee(Integer.parseInt(text)).get.getEmployeeName+" Was punched out")
        clockedInLabel.setText(employeeMap.getEmployee(Integer.parseInt(text)).get.getEmployeeName+" Was punched out")
      }

      primaryStage.setScene(homeScene)

    })

    primaryStage.setScene(homeScene)
    primaryStage.show()
  }
}

