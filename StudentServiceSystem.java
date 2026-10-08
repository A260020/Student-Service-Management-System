import java.util.ArrayList;
import javax.swing.*;
public class StudentServiceSystem {

    private ArrayList<ServiceRequest> requests;

    public StudentServiceSystem() {
    requests = new ArrayList<>();
}
   public void addRequest(ServiceRequest request) {
    requests.add(request);
}
public ServiceRequest findByRequestId(String requestId) {

    for (ServiceRequest request : requests) {

        if (request.getRequestId().equals(requestId)) {
            return request;
        }

    }

    return null;
}
public ServiceRequest findByStudentId(String studentId) {

    for (ServiceRequest request : requests) {

        if (request.getStudent().getStudentId().equals(studentId)) {
            return request;
        }
    }

    return null;
}
 public void createGUI() {

    JFrame frame = new JFrame("Student Service Management System");

    frame.setSize(600, 500);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JLabel studentIdLabel = new JLabel("Student ID:");
JTextField studentIdField = new JTextField(20);

JLabel nameLabel = new JLabel("Name:");
JTextField nameField = new JTextField(20);

JLabel emailLabel = new JLabel("Email:");
JTextField emailField = new JTextField(20);

JLabel contactLabel = new JLabel("Contact Number:");
JTextField contactField = new JTextField(20);

JLabel serviceLabel = new JLabel("Service Type:");
String[] services = {
        "Academic Enquiry",
        "IT Support",
        "Assessment Support",
        "General Enquiry"
};
JComboBox<String> serviceBox = new JComboBox<>(services);

JLabel descriptionLabel = new JLabel("Description:");
JTextArea descriptionArea = new JTextArea(5, 30);
descriptionArea.setLineWrap(true);
descriptionArea.setWrapStyleWord(true);
JTextArea requestDisplayArea = new JTextArea(10, 40);
requestDisplayArea.setEditable(false);
requestDisplayArea.setLineWrap(true);
requestDisplayArea.setWrapStyleWord(true);
panel.add(studentIdLabel);
panel.add(studentIdField);

panel.add(nameLabel);
panel.add(nameField);

panel.add(emailLabel);
panel.add(emailField);

panel.add(contactLabel);
panel.add(contactField);

panel.add(serviceLabel);
panel.add(serviceBox);

panel.add(descriptionLabel);
panel.add(descriptionArea);
panel.add(new JScrollPane(descriptionArea));
panel.add(requestDisplayArea);
panel.add(new JScrollPane(requestDisplayArea));
JButton submitButton = new JButton("Submit Request");
JButton viewAllButton = new JButton("View All Requests");
JLabel statusLabel = new JLabel("Status:");

String[] statuses = {
        "Pending",
        "In Progress",
        "Resolved"
};

JComboBox<String> statusBox = new JComboBox<>(statuses);

panel.add(statusLabel);
panel.add(statusBox);

panel.add(submitButton);
panel.add(viewAllButton);
viewAllButton.addActionListener(e -> {

    requestDisplayArea.setText("");

    for (ServiceRequest request : requests) {

        requestDisplayArea.append(
                "Request ID: " + request.getRequestId()
                + "\nStudent ID: " + request.getStudent().getStudentId()
                + "\nStudent Name: " + request.getStudent().getName()
                + "\nService Type: " + request.getServiceType()
                + "\nDescription: " + request.getDescription()
                + "\nStatus: " + request.getStatus()
                + "\n-------------------------\n"
        );
    }
});
JButton updateStatusButton = new JButton("Update Status");
JLabel searchLabel = new JLabel("Search:");
JLabel studentSearchLabel = new JLabel("Student ID:");

JTextField studentSearchField = new JTextField(20);

JButton studentSearchButton = new JButton("Search Student");

panel.add(studentSearchLabel);
panel.add(studentSearchField);
panel.add(studentSearchButton);

JTextField searchField = new JTextField(20);

JButton searchButton = new JButton("Search");

panel.add(searchLabel);
panel.add(searchField);
panel.add(searchButton);
studentSearchButton.addActionListener(e -> {


    String studentId = studentSearchField.getText().trim();

   if (studentId.isEmpty()) {
        JOptionPane.showMessageDialog(
                frame,
                "Please enter a Request ID."
        );
        return;
    }

    ServiceRequest found = findByStudentId(studentId);

    if (found == null) {
        JOptionPane.showMessageDialog(
                frame,
                "Request not found."
        );
        return;
    }

    requestDisplayArea.setText(
            "Request ID: " + found.getRequestId()
            + "\nStudent ID: " + found.getStudent().getStudentId()
            + "\nStudent Name: " + found.getStudent().getName()
            + "\nService Type: " + found.getServiceType()
            + "\nDescription: " + found.getDescription()
            + "\nStatus: " + found.getStatus()
    );
});
    

panel.add(updateStatusButton);
updateStatusButton.addActionListener(e -> {

    String requestId = searchField.getText().trim();

    if (requestId.isEmpty()) {
        JOptionPane.showMessageDialog(
                frame,
                "Please enter a Request ID in the Search field first."
        );
        return;
    }

    ServiceRequest found = findByRequestId(requestId);

    if (found == null) {
        JOptionPane.showMessageDialog(
                frame,
                "Request not found."
        );
        return;
    }

    String newStatus = (String) statusBox.getSelectedItem();

    found.setStatus(newStatus);

    requestDisplayArea.setText(
            "Request ID: " + found.getRequestId()
            + "\nStudent ID: " + found.getStudent().getStudentId()
            + "\nStudent Name: " + found.getStudent().getName()
            + "\nService Type: " + found.getServiceType()
            + "\nDescription: " + found.getDescription()
            + "\nStatus: " + found.getStatus()
    );

    JOptionPane.showMessageDialog(
            frame,
            "Request status updated successfully!"
    );
});
submitButton.addActionListener(e -> {

    String studentId = studentIdField.getText().trim();
    String name = nameField.getText().trim();
    String email = emailField.getText().trim();
    String contact = contactField.getText().trim();
    String serviceType = (String) serviceBox.getSelectedItem();
    String description = descriptionArea.getText().trim();
    if (studentId.isEmpty() || name.isEmpty() || email.isEmpty()
            || contact.isEmpty() || description.isEmpty()) {

        JOptionPane.showMessageDialog(
                frame,
                "Please fill in all required fields."
        );

        return;
    }
    if (!email.contains("@") || !email.contains(".")) {

    JOptionPane.showMessageDialog(
            frame,
            "Please enter a valid email address."
    );

    return;
}
if (!contact.matches("\\d+")) {

    JOptionPane.showMessageDialog(
            frame,
            "Contact number should contain digits only."
    );

    return;
}

    String requestId = "REQ" + (requests.size() + 1);

    Student student = new Student(
            studentId,
            name,
            email,
            contact
    );

    ServiceRequest request = new ServiceRequest(
            requestId,
            student,
            serviceType,
            description,
            "Pending"
    );

    addRequest(request);
    requestDisplayArea.setText(
        "Request ID: " + request.getRequestId()
        + "\nStudent ID: " + request.getStudent().getStudentId()
        + "\nStudent Name: " + request.getStudent().getName()
        + "\nService Type: " + request.getServiceType()
        + "\nDescription: " + request.getDescription()
        + "\nStatus: " + request.getStatus()
);

    JOptionPane.showMessageDialog(
            frame,
            "Request submitted successfully!\nRequest ID: " + requestId
    );

});

frame.add(panel);
frame.setVisible(true);
}



public static void main(String[] args) {

        
     System.out.println("Student Service Management System");
       
    Student student = new Student(
            "S001",
            "Paramjeet",
            "paramjeet@email.com",
            "0412345678"
    );

    ServiceRequest request = new ServiceRequest(
        "REQ001",
        student,
        "IT Support",
        "My computer is not connecting to the college Wi-Fi.",
        "Pending"
    );

     StudentServiceSystem system = new StudentServiceSystem();

     system.addRequest(request);

     ServiceRequest foundRequest = system.findByRequestId("REQ001");

     System.out.println(foundRequest.getRequestId());
     System.out.println("Opening GUI now...");
     system.createGUI();

     
    
}

}    