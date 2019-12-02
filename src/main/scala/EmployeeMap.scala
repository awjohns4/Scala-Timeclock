
class EmployeeMap {

  var employeeMap: Map[Int, Employee] = Map()

  def addEmployee(employee: Employee): Unit ={
    employeeMap += (employee.getEmployeeNumber -> employee)
  }

  def removeEmployee(employee: Employee): Boolean ={
    if(employeeMap.contains(employee.getEmployeeNumber)){
      employeeMap -= employee.getEmployeeNumber
      return true
    }
    else
      return false
  }

  def getEmployee(employeeNumber: Int): Option[Employee] ={
    return employeeMap.get(employeeNumber)
  }

  def getAllEmployees(): List[Employee] ={
    val employeeList = employeeMap.values.toList
    return employeeList
  }

  def getTotalTimeWorkedThisPeriod(): Double ={
    var totalTimeWorked: Double = 0.0
    employeeMap foreach (employee => totalTimeWorked += employee._2.getHoursWorkedThisPeriod)

    return totalTimeWorked
  }

  def getTotalTimeWorkedLastPeriod(): Double ={
    var totalTimeWorked: Double = 0.0
    employeeMap foreach (employee => totalTimeWorked += employee._2.getHoursWorkedLastPeriod)

    return totalTimeWorked
  }
}
