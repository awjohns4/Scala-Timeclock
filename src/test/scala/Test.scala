object Test {


  def main(args: Array[String]): Unit ={

    var Dan = new Employee("Dan", 1, "Manager")
    var John = new Employee("John", 2, "Associate")
    var Lee = new Employee("Lee", 3, "Associate")

    var eMap = new EmployeeMap

    eMap.addEmployee(Dan)
    eMap.addEmployee(John)
    eMap.addEmployee(Lee)

    for(x <- 1 to 3){
      print(eMap.getEmployee(x).get.getEmployeeRole)
    }

  }
}
