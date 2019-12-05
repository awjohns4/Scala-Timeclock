import javafx.application.Application
import javafx.beans.value.ChangeListener
import javafx.event.ActionEvent
import javafx.geometry.{Insets, Pos}
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ChoiceBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.control.TextArea
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.StageStyle


object TimeClock{
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[Main], args: _*)
  }
}

class Main extends Application {
  override def start(primaryStage: Stage): Unit = {

    var buttonStyle = "-fx-background-color: darkslateblue; -fx-text-fill: white;"
    var textStyle = "-fx-font: normal bold 20px 'sans-serif' "
    primaryStage.centerOnScreen()

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

    val employeeNumber = new TextField("Enter Employee ID")
    val homePunchButton = new Button("Punch Time")
    val homeReportButton = new Button("Time Report")
    val timePunchButton = new Button("Punch Time")
    val getReportButton = new Button("Get Report")
    val returnButton = new Button("Return")


    homePunchButton.setStyle(buttonStyle)
    homePunchButton.setPrefSize(200,100)

    homeReportButton.setStyle(buttonStyle)
    homeReportButton.setPrefSize(200, 100)

    timePunchButton.setStyle(buttonStyle)
    timePunchButton.setPrefSize(200, 100)

    getReportButton.setStyle(buttonStyle)
    getReportButton.setPrefSize(200,100)

    returnButton.setStyle(buttonStyle)
    returnButton.setPrefSize(200, 100)

    employeeNumber.setStyle(textStyle)
    reportOutputArea.setStyle(textStyle)
    clockedInLabel.setStyle(textStyle)
    selectEmployeeLabel.setStyle(textStyle)

    val homeVBoxTitle = new Label("Welcome to Work!")
    homeVBoxTitle.setStyle(textStyle)

    val timePunchVBoxTitle = new Label("Please Enter Your Employee ID:");
    timePunchVBoxTitle.setStyle(textStyle)

    val spacing = 20

    val homeHBox = new HBox(spacing, homePunchButton, homeReportButton)
    homeHBox.setAlignment(Pos.CENTER)

    val homeVBox = new VBox(spacing, homeVBoxTitle, homeHBox, clockedInLabel)
    homeVBox.setAlignment(Pos.CENTER)

    val timePunchVBox = new VBox(spacing, timePunchVBoxTitle, employeeNumber, timePunchButton, returnButton)
    timePunchVBox.setPadding(new Insets(20))
    timePunchVBox.setAlignment(Pos.CENTER)

    val reportVBox = new VBox(spacing, selectEmployeeLabel, reportChoiceBox, getReportButton, reportOutputArea, returnButton)
    reportVBox.setAlignment(Pos.CENTER)

    val pageWidth = 800
    val pageHeight = 600

    val homeScene = new Scene(homeVBox, pageWidth,pageHeight)
    val punchScene = new Scene(timePunchVBox, pageWidth, pageHeight)
    val reportScene = new Scene(reportVBox, pageWidth, pageHeight)

    homePunchButton.setOnAction(ActionEvent =>{
      primaryStage.setTitle("Punch")
      primaryStage.setScene(punchScene)
    })

    homeReportButton.setOnAction(ActionEvent =>{
      primaryStage.setTitle("Report")
      primaryStage.setScene(reportScene)
    })

    returnButton.setOnAction(ActionEvent =>{
      primaryStage.setTitle("Home")
      primaryStage.setScene(homeScene)
    })

    getReportButton.setOnAction(ActionEvent =>{
      try {
        var hoursWorkedThis = reportChoiceBox.getValue.get.getHoursWorkedThisPeriod
        var hoursWorkedLast = reportChoiceBox.getValue.get.getHoursWorkedLastPeriod
        var minutesWorkedThis = reportChoiceBox.getValue.get.getMinutesWorkedThisPeriod
        var minutesWorkedLast = reportChoiceBox.getValue.get.getMinutesWorkedLastPeriod
        var employee = reportChoiceBox.getValue.get

        reportOutputArea.setText(employee.employeeName +
          "\nID: " + employee.employeeNumber +
          "\nHours worked this period: " + hoursWorkedThis +
          "\nMinutes worked this period:  " + minutesWorkedThis +
          "\nHours worked last period: " + hoursWorkedLast +
          "\nMinutes worked last period: " + minutesWorkedLast)
        print("Hours worked this period: " + hoursWorkedThis +
          "\nMinutes worked this period:  " + minutesWorkedThis +
          "\nHours worked last period: " + hoursWorkedLast +
          "\nMinutes worked last period: " + minutesWorkedLast)
      }catch{
        case ex: NullPointerException=>{
          reportOutputArea.setText("Please select an employee")
        }
      }
    })

    timePunchButton.setOnAction(ActionEvent =>{
      var text = employeeNumber.getText
      try {
        employeeMap.getEmployee(Integer.parseInt(text)).get.punchTime()
        if (employeeMap.getEmployee(Integer.parseInt(text)).get.getClockedIn) {
          println(employeeMap.getEmployee(Integer.parseInt(text)).get.getEmployeeName + " Was punched in")
          clockedInLabel.setText(employeeMap.getEmployee(Integer.parseInt(text)).get.getEmployeeName + " Was punched in")
        }
        else {
          println(employeeMap.getEmployee(Integer.parseInt(text)).get.getEmployeeName + " Was punched out")
          clockedInLabel.setText(employeeMap.getEmployee(Integer.parseInt(text)).get.getEmployeeName + " Was punched out")
        }
      }catch {
        case ex: NoSuchElementException => {
          println("Employee not found, please try again")
          clockedInLabel.setText("Employee not found, please try again")
        }
        case ex: NumberFormatException=>{
          println("Not a valid input, please try again")
          clockedInLabel.setText("Not a valid input, please try again")
        }
      }
      primaryStage.setTitle("Home")
      primaryStage.setScene(homeScene)
    })

    primaryStage.setTitle("Home")
    primaryStage.setScene(homeScene)
    primaryStage.show()
  }
}

