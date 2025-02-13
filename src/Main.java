import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class Main {
    /**
     * function to validate each amount of color(must be from 0 to 255)
     * @param value
     * @return
     */
    public static int validate(int value) {
        if (value < 0) value = 0;
        else if (value > 255) value = 255;
        return value;
    }

    /**
     * function that adjusts the brightness (losers or increases it depending on the brightnessValue)
     * of the photo from inPath and outputs it in the outPath
     * @param inPath
     * @param outPath
     * @param brightnessValue
     * @throws IOException
     */
    public static void adjustBrightness(String inPath, String outPath, int brightnessValue) throws IOException {
        BufferedImage image = null;

        try {
            image = ImageIO.read(new File(inPath));

            System.out.println("Reading complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        int rgb[];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                rgb = image.getRaster().getPixel(i, j, new int[3]);
                int red = validate(rgb[0] + brightnessValue);
                int green = validate(rgb[1] + brightnessValue);
                int blue = validate(rgb[2] + brightnessValue);
                int arr[] = {red, green, blue};
                image.getRaster().setPixel(i, j, arr);
            }
        }
        try {
            ImageIO.write(image, "jpg", new File(outPath));
            System.out.println("Editing complete.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    /**
     * main function reads user input and adjusts picture accordingly
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path to the jpg image you want the brightness adjusted to: ");
        String inputPath = scanner.nextLine();
        System.out.println("Enter file path to the location where you want the edited jpg image to be saved: ");
        String outputPath = scanner.nextLine();
        System.out.println("Enter how much brighter / less brighter(-) you want the image to be (int): ");
        int brightnessValue = scanner.nextInt();
        adjustBrightness(inputPath, outputPath, brightnessValue);
    }
}