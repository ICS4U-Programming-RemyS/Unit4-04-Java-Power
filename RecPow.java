import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**.
*
* This program will read multiple lines of strings from the input file.
* If the data is valid then it will calculate the base to the exponent
* and write the result to an output file.
*
* @author Remy Skelton
* @version 1.0
* @since 2025-04-23
*/

final class RecPow {

    /**
     * This is a private constructor used to satisfy the
     * style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
    */
    private RecPow() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     */

    public static void main(final String[] args) throws Exception {
        // Print the welcome message
        System.out.println("Welcome to the Recursive Power program!");
        System.out.print("This program reads multiple lines");
        System.out.print(" of strings from input.txt, if valid it will ");
        System.out.print("calculate the power of the base to the exponent ");
        System.out.println("and writes the result to output.txt.");

        // Read from input.txt
        File inputFile = new File("input.txt");
        Scanner scanner = new Scanner(inputFile);

        // Make outputWriter to write to output.txt
        FileWriter outputWriter = new FileWriter("output.txt");

        // Create outputStr
        String outputStr = "";

        // While loop to read String from input.txt
        while (scanner.hasNextLine()) {

            // Set line equal to the next line
            String line = scanner.nextLine();

            // Split the line by spaces to see if there are multiple numbers
            String[] lineNumbers = line.split(" ");

            if (lineNumbers.length != 2) {
                // Add an error message
                outputStr += "Invalid: "
                        + line
                        + " is not a valid base and exponent.\n";
                continue;

            } else {
                // Get the base and exponent from the line
                String baseStr = lineNumbers[0];
                String exponentStr = lineNumbers[1];
                // Check if the base is a valid integer
                try {
                    // Make the string into an integer
                    int base = Integer.parseInt(baseStr);
                    int exponent = Integer.parseInt(exponentStr);

                    // Check if the exponent is negative
                    if (exponent < 0) {
                        // Add an error message
                        outputStr += "Invalid: "
                        + line
                        + " is not a valid base and exponent.\n";

                        // Restarts the loop if the number is negative
                        continue;
                    } else {
                        // Call the recPow method
                        int valuePow = recPow(base, exponent);

                        // Add the power result to the output string
                        outputStr += base
                                + "^" + exponent
                                + " = " + valuePow
                        + "\n";
                    }
                } catch (NumberFormatException e) {
                    // Add an error message
                    outputStr += "Invalid: "
                    + line
                    + " is not a valid base and exponent.\n";
                    continue;
                }
            }
        }

        // Write the output string to output.txt
        outputWriter.write(outputStr);

        // Print the power to the console
        System.out.println("Power written to output.txt.");

        // Close the scanner
        scanner.close();

        // Close the output writer
        outputWriter.close();
    }

    /**.
     * This method calculates the power of the base to the exponent.
     *
     * @param base The base
     * @param exponent The exponent
     * @return The power of the base to the exponent
     */
    public static int recPow(final int base, final int exponent) {
        // Base Case: check if the exponent is 0
        if (exponent == 0) {
            // Return 1 as any number to the power of 0 is 1
            return 1;
        } else {
            // Recursive Case: return the base times the
            // power of the base to the exponent - 1
            return base * recPow(base, exponent - 1);
        }
    }
}
