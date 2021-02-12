import java.util.*;

public class ZipJavaVersion {
    public static void main(String[] args) {
        List<String> employees = Arrays.asList("Denny", "Claudette", "Peter");
        List<String> shirtSizes = Arrays.asList("large", "x-large", "medium");
        Map<String, String> employeeShirtSizes = new HashMap<>();
        for (int i = 0; i < employees.size(); i++) {
            employeeShirtSizes.put(employees.get(i), shirtSizes.get(i));
        }
        List<String> formattedSwagOrders = new ArrayList<>();
        for (Map.Entry<String, String> shirtSize : employeeShirtSizes.entrySet()) {
            formattedSwagOrders.add(String.format("%s, shirt size: %s",
                    shirtSize.getKey(), shirtSize.getValue()));
        }
    }
}
