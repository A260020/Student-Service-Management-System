public class ServiceRequest {
  
  
     String requestId;
     Student student;
     String serviceType;
     String description;
     String status;


     
    ServiceRequest(String requestId, Student student, String serviceType,
                      String description, String status) {

     this.requestId = requestId;
     this.student = student;
     this.serviceType = serviceType;
     this.description = description;
     this.status = status;
                      
                      
    }            
    
public String getRequestId() {
    return requestId;
}
public Student getStudent() {
    return student;
}
public String getServiceType() {
    return serviceType;
}public String getDescription() {
    return description;
}
public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}

}