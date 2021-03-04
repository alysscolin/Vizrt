import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class ProblemB {
    static int toRoyal = 1; //total number of royal. current value is 1 because the founder is royal
    final static int MAX_NUM = 50;
    static int[] inq = new int[MAX_NUM]; //check if the candidate has ever in the queue 1: yes, 0: not

    static People[] people = new People[MAX_NUM];

    /**
     *
     * @param name: the candidate name
     * @return the position in the array
     */
    static int find(String name) {
        //founder index is 1
        for (int i = 1; i <= toRoyal; i++) {
            if (name.equals(people[i].name)) {
                return i;
            }
        }
        ++toRoyal;
        people[toRoyal].name = name;
        return toRoyal;
    }

    /**
     *
     */
    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        //push the founder to the queue
        queue.offer(1);
        while (!queue.isEmpty()) {
            //pop up the element
            int x = queue.poll();
            inq[x] = 0;
            //current blood. half blood comes from father, another half comes from mother
            double now = (people[x].blood[0] + people[x].blood[1]) / 2;

            for (int i = 0; i < people[x].son.size(); i++)
            {
                // the son's position in the array
                int y = people[x].son.get(i);
                //check it is father or mother
                int relation = x == people[y].farther ? 0 : 1;
                //the previous blood of the son
                double past = people[y].blood[relation];
                //update the son's blood
                people[y].blood[relation] = now;
                if (past != people[y].blood[relation] && (inq[y] == 0))
                {
                    inq[y] = 1;
                    queue.offer(y);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //initialize the people array
        for (int i = 0; i < people.length; i++)
        {
            people[i] = new People();
        }
        //input file, absolute path
        String in = "C:\\Users\\youyuanc\\Downloads\\sample\\2.in";
        //read the input file
        BufferedReader reader = new BufferedReader(new FileReader(in));
        //read the first line of the file
        String line = reader.readLine();
        if (line == null) {
            System.out.println("The first line is null");
            return;
        }
        //split the first line
        String [] arr = line.split("\\s+");
        int n = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);

        //goto the next line to get the founder
        line = reader.readLine();
        //index from 1, index 1 is founder in the array
        people[1].name = line;
        people[1].blood[0] = 1.0;

        //read the next n lines to go through all relationships
        int j = 1;
        //save child, father and mother name
        String[] s = new String[3];
        while (j <= n && (line = reader.readLine()) != null) {
            s = line.split("\\s+");
            //find the son,father and mother's position
            int id1 = find(s[0]);
            int id2 = find(s[1]);
            int id3 = find(s[2]);
            //update the relations among son, father and mother
            people[id1].farther = id2;
            people[id1].mother = id3;
            people[id2].son.add(id1);
            people[id3].son.add(id1);

            ++j;
        }

        bfs();

        double maxBlood = 0;
        int id = 0;
        int k = 1;
        //go to next m lines to parse candidates
        //line = reader.readLine();
        while ((line = reader.readLine()) != null && k <=m ) {
            for (int i = 2; i <= toRoyal; i++) {
                if (line.equals(people[i].name)) {
                    if (people[i].blood[0] + people[i].blood[1] > maxBlood) {
                        maxBlood = people[i].blood[0] + people[i].blood[1];
                        id = i;
                    }
                    continue;
                }
            }
            ++k;
            //line = reader.readLine();
        }
        reader.close();

        System.out.println(people[id].name);

        return;
    }
}
