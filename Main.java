import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // User input for alpha
        System.out.print("Enter the base address (alpha): ");
        int alpha = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // User input for esize
        System.out.print("Enter the size of each element (esize): ");
        int esize = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        // User input for dimensions
        System.out.print("Enter the dimensions separated by spaces: ");
        String dimensionsInput = scanner.nextLine();
        String[] dimensionsStr = dimensionsInput.trim().split("\\s+");
        int numDimensions = dimensionsStr.length;
        int[] dimensions = new int[numDimensions];
        int totalElements = 1; // Initialize total number of elements
        for (int i = 0; i < numDimensions; i++) {
            dimensions[i] = Integer.parseInt(dimensionsStr[i]);
            totalElements *= dimensions[i]; // Update total number of elements
        }
        
        // Displaying the dimensions
        System.out.print("Your record is: A");
        for (int i = 0; i < dimensions.length; i++) {
            System.out.print("[" + dimensions[i] + "]");
        }
        System.out.println(); // New line after printing dimensions
        System.out.println(); // New line after displaying dimensions
        
        // Displaying the total number of elements
        System.out.println("Total number of elements: " + totalElements);
        
        // Label for address calculation
        System.out.println("Address Calculation");
        
        // User input for indices
        System.out.print("Enter the indices separated by spaces: ");
        String indicesInput = scanner.nextLine();
        String[] indicesStr = indicesInput.trim().split("\\s+");
        int[] indices = new int[numDimensions];
        for (int i = 0; i < numDimensions; i++) {
            indices[i] = Integer.parseInt(indicesStr[i]);
        }
        
        // Finding the address based on user input indices
        int address = computeAddress(alpha, esize, dimensions, indices);
        
        // Displaying the original address separately
        System.out.println("Address of A" + formatIndices(indices) + ": " + address);
        
        scanner.close();
    }
    
    // Method to format indices for display
    public static String formatIndices(int[] indices) {
        StringBuilder sb = new StringBuilder();
        for (int index : indices) {
            sb.append("[").append(index).append("]");
        }
        return sb.toString();
    }
    
    // Method to compute the memory address for a given index
    public static int computeAddress(int alpha, int esize, int[] dimensions, int[] indices) {
        int address = alpha;
        int stride = esize;
        for (int i = dimensions.length - 1; i >= 0; i--) {
            address += indices[i] * stride;
            stride *= dimensions[i];
        }
        return address;
    }
}