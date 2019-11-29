class Employee(name: String, number: Int, role: String) {

  var employeeName: String = name
  var employeeNumber: Int = number
  var timeWorkedThisPeriod: Double = 0.0
  var timeWorkedLastPeriod: Double = 0.0
  var employeeRole: String = role

  def setEmployeeName(name: String): Unit  ={
    employeeName = name
  }

  def getEmployeeName: String = employeeName

  def setEmployeeNumber(number: Int): Unit ={
    employeeNumber = number
  }

  def getEmployeeNumber: Int = employeeNumber

  def setTimeWorkedThisPeriod(time: Double): Unit ={
    timeWorkedThisPeriod = time
  }

  def getTimeWorkedThisPeriod: Double = timeWorkedThisPeriod

  def setTimeWorkedLastPeriod(time: Double): Unit ={
    timeWorkedLastPeriod = time
  }

  def getTimeWorkedLastPeriod: Double = timeWorkedLastPeriod

  def setEmployeeRole(role: String): Unit ={
    employeeRole = role
  }

  def getEmployeeRole: String = employeeRole

  def addTimeWorked(time: Double): Unit ={
    timeWorkedThisPeriod += time
  }

  def subtractTimeWorked(time: Double): Unit ={
    timeWorkedThisPeriod -= time
  }

  def newPayPeriod(): Unit ={
    timeWorkedLastPeriod = timeWorkedThisPeriod
    timeWorkedThisPeriod = 0.0
  }

  def clearTimeWorked(): Unit ={
    timeWorkedThisPeriod = 0.0
    timeWorkedLastPeriod = 0.0
  }



}
