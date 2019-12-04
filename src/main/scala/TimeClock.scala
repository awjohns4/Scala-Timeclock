import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.event.ActionEvent
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.control.TextArea
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
    var Kathy = new Employee("Kathy", 4, "Manager")

    employeeMap.addEmployee(Dan)
    employeeMap.addEmployee(John)
    employeeMap.addEmployee(Lee)
    employeeMap.addEmployee(Kathy)

    var employeeList = employeeMap.getAllEmployees()

    val clockedInLabel = new Label("")
    val selectEmployeeLabel = new Label("Select an employee")
    val reportOutputArea = new TextArea("Select an employee")
    reportOutputArea.setEditable(false)
    val reportChoiceBox = new ChoiceBox[Option[Employee]]

    for(i <- 0 to employeeList.length){
      reportChoiceBox.getItems.add(employeeMap.getEmployee(i))
    }

    val employeeNumber = new TextField
    val homePunchButton = new Button("Punch Time")
    val homeReportButton = new Button("Time report")
    val timePunchButton = new Button("Punch Time")
    val getReportButton = new Button("Get Report")
    val returnButton = new Button("Return")

    val homeHBox = new HBox(10, homePunchButton, homeReportButton)
    homeHBox.setAlignment(Pos.CENTER)

    val homeVBox = new VBox(10, homeHBox, clockedInLabel)
    homeVBox.setAlignment(Pos.CENTER)

    val timePunchVBox = new VBox(10, employeeNumber, timePunchButton)
    timePunchVBox.setAlignment(Pos.CENTER)

    val reportVBox = new VBox(10, selectEmployeeLabel, reportChoiceBox, getReportButton, reportOutputArea, returnButton)
    reportVBox.setAlignment(Pos.CENTER)

    val homeScene = new Scene(homeVBox, 800,600)
    val punchScene = new Scene(timePunchVBox, 800, 600)
    val reportScene = new Scene(reportVBox, 800, 600)

    homePunchButton.setOnAction(ActionEvent =>{
      primaryStage.setScene(punchScene)
    })

    homeReportButton.setOnAction(ActionEvent =>{
      primaryStage.setScene(reportScene)

      returnButton.setOnAction(ActionEvent =>{
        primaryStage.setScene(homeScene)
      })
    })

    getReportButton.setOnAction(ActionEvent =>{
      var hoursWorkedThis = reportChoiceBox.getValue.get.getHoursWorkedThisPeriod
      var hoursWorkedLast = reportChoiceBox.getValue.get.getHoursWorkedLastPeriod
      var minutesWorkedThis = reportChoiceBox.getValue.get.getMinutesWorkedThisPeriod
      var minutesWorkedLast = reportChoiceBox.getValue.get.getMinutesWorkedLastPeriod

      reportOutputArea.setText("Hours worked this period: " + hoursWorkedThis + "\nMinutes worked this period:  " + minutesWorkedThis +
                                  "\nHours worked last period: " + hoursWorkedLast + "\nMinutes worked last period: " + minutesWorkedLast)
      print("Hours worked this period: " + hoursWorkedThis + "\nMinutes worked this period:  " + minutesWorkedThis +
        "\nHours worked last period: " + hoursWorkedLast + "\nMinutes worked last period: " + minutesWorkedLast)


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

