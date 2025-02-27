import java.util.List;


class PropertyDetails {  // Tách ra rieng thanh PropertyDetails
    private String name;
    private double rentAmount;
    private String ownerName;
    private String location;

    public PropertyDetails(String name, double rentAmount, String ownerName, String location) {
        this.name = name;
        this.rentAmount = rentAmount;
        this.ownerName = ownerName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getLocation() {
        return location;
    }

    public double calculateYearlyRent() {
        return rentAmount * 12;
    }

    public String getPropertyType() {
        return rentAmount > 2000 ? "premium" : "standard";
    }
}

class Property {    // Changedd 
    private PropertyDetails propertyDetails;

    public Property(String name, double rentAmount, String ownerName, String location) {
        this.propertyDetails = new PropertyDetails(name, rentAmount, ownerName, location);
    }

    public PropertyDetails getPropertyDetails() {
        return propertyDetails;
    }
    
    public void printPropertyDetails() {
        System.out.println("Property: " + propertyDetails.getName());
        System.out.println("Rent Amount: $" + propertyDetails.getRentAmount());
        System.out.println("Owner: " + propertyDetails.getOwnerName());
        System.out.println("Location: " + propertyDetails.getLocation());
    }
}


class FinancialReport {     // Changed
    private String reportTitle;
    private List<Property> properties;

    public FinancialReport(String reportTitle, List<Property> properties) {
        this.reportTitle = reportTitle;
        this.properties = properties;
    }

    // Tách phần tính toán tổng tiền thuê ra ngoài
    private double calculateTotalRent() {
        double totalRent = 0;
        for (Property property : properties) {
            totalRent += property.getPropertyDetails().getRentAmount();
        }
        return totalRent;
    }

    public void generateReport() {
        System.out.println("Financial Report: " + reportTitle);
        System.out.println("----------------------------");

        for (Property property : properties) {
            property.printPropertyDetails();

            String propertyType = property.getPropertyDetails().getPropertyType();
            System.out.println("This is a " + propertyType + " property.");

            double yearlyRent = property.getPropertyDetails().calculateYearlyRent();
            System.out.println("Yearly Rent: $" + yearlyRent);
            System.out.println("--------------------");
        }

        double totalRent = calculateTotalRent();
        System.out.println("Total Rent Amount: $" + totalRent);
    }
}

public class ReportGenerator {   // Changed
    public static void main(String[] args) {
        Property property1 = new Property("Apartment A", 1500.0, "John Doe", "City Center");
        Property property2 = new Property("House B", 2000.0, "Jane Smith", "Suburb");
        Property property3 = new Property("Condo C", 1800.0, "Bob Johnson", "Downtown");

        FinancialReport financialReport = new FinancialReport("Monthly Rent Summary", List.of(property1, property2, property3));
        financialReport.generateReport();
    }
}