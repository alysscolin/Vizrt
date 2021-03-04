import java.io.*;

public class ProblemA {
    /**
     *
     * @param line: the line in input file
     * @return String array
     */
    String[] parseLine(String line) {
        if (line == null)
            return null;
        //split a line to array with three elements
        String arr[] = line.split("\\s+");
        if (arr.length != 3)
        {
            System.out.println("There are not 3 elements in this line");
            return null;
        }
        //check all of these 3 elements are valid numbers
        for (String str : arr)
        {
            try {
                Integer.parseInt(str);
            }
        catch (NumberFormatException e)
            {
                return null;
            }
        }
        return arr;
    }

    /**
     *
     * @param in: full path of input file
     * @param out: full path of output file
     * @throws FileNotFoundException
     */
    void solution(String in, String out) throws FileNotFoundException {
        if (in == null || in.isEmpty()) {
            System.out.println("Input file not exist");
            return;
        }

        File fOut = new File(out);
        FileOutputStream fOs = new FileOutputStream(fOut);
        BufferedWriter bW = new BufferedWriter(new OutputStreamWriter(fOs));

        BufferedReader reader;
        try {
            int nums[] = new int[3];

            reader = new BufferedReader(new FileReader(in));
            //this is the first line, we should check if the number is more than 0
            String line = reader.readLine();
            if (line == null || Integer.parseInt(line) <= 0)
            {
                System.out.println("invalid case number, it should be a positive integer.");
                return;
            }

            //goto the second line
            line = reader.readLine();

            while (line != null) {
                //parse the line
                String arr[] = parseLine(line);
                //check the revenue
                for (int i = 0; i < arr.length; i++)
                {
                    nums[i] = Integer.parseInt(arr[i]);
                }
                //write line to output file
                if (nums[1] - nums[2] > nums[0])
                    bW.write("advertise");
                else if (nums[1] - nums[2] == nums[0])
                    bW.write("does not matter");
                else
                    bW.write("do not advertise");
                bW.newLine();
                //read next line
                line = reader.readLine();
            }
            bW.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        ProblemA a = new ProblemA();
        a.solution("C:\\Users\\youyuanc\\Downloads\\JavaTest\\ProblemA\\sample\\1.in",
                "C:\\Users\\youyuanc\\Downloads\\JavaTest\\ProblemA\\sample\\1.ans");
    }
}
