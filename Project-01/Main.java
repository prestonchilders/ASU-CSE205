//********************************************************************************************************
// CLASS: classname (classname.java)
//
// DESCRIPTION
// A description of the contents of this file.
//
// COURSE AND PROJECT INFO
// CSE205 Object Oriented Programming and Data Structures, semester and year
// Project Number: project-number
//
// AUTHOR
// your-name (your-email-addr)
//********************************************************************************************************

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class Main {

    static final int RUNS_UP = 1;
    static final int RUNS_DN = -1;

    public static void main(String[] args) throws FileNotFoundException {

        Main mainObject = new Main();
        mainObject.Run();

    }

    private void Run() throws FileNotFoundException {

        ArrayList<Integer> list = new ArrayList();
        String fname = "p01-in.txt";

        try {
            Scanner in = new Scanner(new File(fname));
            ArrayList<Integer> listRunsCount = new ArrayList();
            while (in.hasNext() == true) {

                String line = in.nextLine();

                for (int i = 0; i < line.length(); i++) {

                    if (!line.substring(i, i + 1).equals(" ")) {

                        String mElement = line.substring(i, i + 1);
                        Integer pElement = Integer.parseInt(mElement);
                        list.add(pElement);
                    }

                }

                ArrayList<Integer> listRunsUpCount;
                ArrayList<Integer> listRunsDnCount;

                listRunsUpCount = FindRuns(list, RUNS_UP);
                listRunsDnCount = FindRuns(list, RUNS_DN);

                listRunsCount = Merge(listRunsUpCount, listRunsDnCount);

            }
            Output("p01-runs.txt", listRunsCount);

        } catch (FileNotFoundException e) {

            System.out.println("Sorry, that file was not found.");
            System.exit(-1);
        }

    }

    private ArrayList<Integer> FindRuns(ArrayList<Integer> pList, int pDir) {

        ArrayList<Integer> listRunsCount = ArrayListCreate(pList.size(), 0);

        int i = 0;
        int k = 0;

        while (i < pList.size() - 1) {

            if (pDir == RUNS_UP && pList.get(i) < pList.get(i + 1)) {
                k++;
            } else if (pDir == RUNS_DN && pList.get(i) > pList.get(i + 1)) {
                k++;
            } else {
                if (k != 0) {

                    int j = listRunsCount.get(k);
                    listRunsCount.set(k, j + 1);
                    k = 0;
                }
            }
            i++;
        }
        if (k != 0) {

            int j = listRunsCount.get(k);
            listRunsCount.set(k, j + 1);
        }
        return listRunsCount;
    }

    private ArrayList<Integer> Merge(ArrayList<Integer> pListRunsUpCount, ArrayList<Integer> pListRunsDnCount) {

        ArrayList<Integer> listRunsCount = ArrayListCreate(pListRunsUpCount.size(), 0);

        for (int i = 0; i < listRunsCount.size() - 1; i++) {

            listRunsCount.set(i, pListRunsUpCount.get(i) + pListRunsDnCount.get(i));
        }
        return listRunsCount;
    }

    private ArrayList<Integer> ArrayListCreate(int pSize, int pInitValue) {

        ArrayList<Integer> list = new ArrayList();

        for (int i = 0; i <= pSize; i++) {
            list.add(pInitValue);
        }
        return list;
    }

    private void Output(String pFileName, ArrayList<Integer> pListRuns) throws FileNotFoundException {

        PrintWriter out = new PrintWriter(new File(pFileName));

        int sum = 0;

        for (int i = 0; i < pListRuns.size(); i++) {

            sum += pListRuns.get(i);
        }
        out.println("runs_total, " + sum);

        for (int k = 1; k <= pListRuns.size() - 1; k++) {

            if (pListRuns.get(k) != 0) {

                out.println("runs_" + k + " " + pListRuns.get(k));
            }

        }
        out.close();
    }
}
