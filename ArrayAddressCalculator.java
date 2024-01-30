import java.util.Arrays;
import java.util.Scanner;

public class ArrayAddressCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for alpha and esize
        int alpha = getUserInput("Enter the base address (alpha): ", scanner);
        int esize = getUserInput("Enter the size of each element (esize): ", scanner);

        // User input for dimensions
        int[] dimensions = getDimensionsFromUser(scanner);

        // Displaying the dimensions
        displayDimensions(dimensions);

        // Displaying the total number of elements
        int totalElements = calculateTotalElements(dimensions);
        System.out.println("Total number of elements: " + totalElements);
        System.out.println();

        // Label for address calculation
        System.out.println("Address Calculation");

        // User input for indices
        int[] indices = getIndicesFromUser(scanner, dimensions.length);

        // Finding the address based on user input indices
        int address = computeAddress(alpha, esize, dimensions, indices);

        // Displaying the original address separately
        System.out.println("Address of A" + formatIndices(indices) + ": " + address);

        scanner.close();
    }

    private static int getUserInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private static int[] getDimensionsFromUser(Scanner scanner) {
        System.out.print("Enter the dimensions separated by spaces: ");
        scanner.nextLine(); // Consume newline
        String dimensionsInput = scanner.nextLine();
        return Arrays.stream(dimensionsInput.trim().split("\\s+"))
                     .mapToInt(Integer::parseInt)
                     .toArray();
    }

    private static void displayDimensions(int[] dimensions) {
        System.out.print("Your record is: A");
        for (int dimension : dimensions) {
            System.out.print("[" + dimension + "]");
        }
        System.out.println();
    }

    private static int calculateTotalElements(int[] dimensions) {
        return Arrays.stream(dimensions).reduce(1, (a, b) -> a * b);
    }

    private static int[] getIndicesFromUser(Scanner scanner, int numDimensions) {
        System.out.print("Enter the indices separated by spaces: ");
        String indicesInput = scanner.nextLine();
        return Arrays.stream(indicesInput.trim().split("\\s+"))
                     .mapToInt(Integer::parseInt)
                     .limit(numDimensions)
                     .toArray();
    }

    private static String formatIndices(int[] indices) {
        return Arrays.stream(indices)
                     .mapToObj(index -> "[" + index + "]")
                     .reduce("", (a, b) -> a + b);
    }

    private static int computeAddress(int alpha, int esize, int[] dimensions, int[] indices) {
        int address = alpha;
        int stride = esize;
        for (int i = dimensions.length - 1; i >= 0; i--) {
            address += indices[i] * stride;
            stride *= dimensions[i];
        }
        return address;
    }
}