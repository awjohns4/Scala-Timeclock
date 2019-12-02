import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Employee(name: String, number: Int, role: String) {

  var employeeName: String = name
  var employeeNumber: Int = number
  var hoursWorkedThisPeriod: Int = 0
  var minutesWorkedThisPeriod: Int = 0
  var hoursWorkedLastPeriod: Int = 0
  var minutesWorkedLastPeriod: Int = 0
  var employeeRole: String = role
  var startTime: Array[String] = new Array[String](2)
  var clockedIn: Boolean = false

  def setEmployeeName(name: String): Unit  ={
    employeeName = name
  }

  def getEmployeeName: String = employeeName

  def setEmployeeNumber(number: Int): Unit ={
    employeeNumber = number
  }

  def getEmployeeNumber: Int = employeeNumber

  def setHoursWorkedThisPeriod(time: Int): Unit ={
    hoursWorkedThisPeriod = time
  }

  def getHoursWorkedThisPeriod: Int = hoursWorkedThisPeriod

  def setMinutesWorkedThisPeriod(time: Int): Unit ={
    minutesWorkedThisPeriod = time
  }

  def getMinutesWorkedThisPeriod: Int = minutesWorkedThisPeriod

  def setMinutesWorkedLastPeriod(time: Int): Unit ={
    minutesWorkedLastPeriod = time
  }

  def getMinutesWorkedLastPeriod: Int = minutesWorkedLastPeriod

  def setHoursWorkedLastPeriod(time: Int): Unit ={
    hoursWorkedLastPeriod = time
  }

  def getHoursWorkedLastPeriod: Int = hoursWorkedLastPeriod

  def setEmployeeRole(role: String): Unit ={
    employeeRole = role
  }

  def getEmployeeRole: String = employeeRole

  def setClockedIn(clockedIn: Boolean): Unit ={
    this.clockedIn = clockedIn
  }

  def getClockedIn: Boolean = clockedIn

  def addTimeWorked(time: Int): Unit ={
    hoursWorkedThisPeriod += time
  }

  def subtractTimeWorked(time: Int): Unit ={
    hoursWorkedThisPeriod -= time
  }

  def newPayPeriod(): Unit ={
    hoursWorkedLastPeriod = hoursWorkedThisPeriod
    minutesWorkedLastPeriod = minutesWorkedLastPeriod
    hoursWorkedThisPeriod = 0
    minutesWorkedThisPeriod = 0
  }

  def clearTimeWorked(): Unit ={
    hoursWorkedThisPeriod = 0
    hoursWorkedLastPeriod = 0
    minutesWorkedThisPeriod = 0
    minutesWorkedLastPeriod = 0
  }

  def punchTime(): Boolean ={
    if(!clockedIn){
      clockedIn = true
      startTime = LocalDateTime.now.format(DateTimeFormatter.ofPattern("HH:mm")).split(":")
      true
    }
    else {
      clockedIn = false
      val endTime = LocalDateTime.now.format(DateTimeFormatter.ofPattern("HH:mm")).split(":")
      hoursWorkedThisPeriod += endTime(0).toInt - startTime(0).toInt
      minutesWorkedThisPeriod += endTime(1).toInt - startTime(1).toInt
      false
    }
  }

  override def toString: String = employeeName

}
