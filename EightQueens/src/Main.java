import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //Test
        for (int i = 0; i < 15; i++) {
            long startTime = System.nanoTime();
            System.out.println("-------------------");
            System.out.println((i + 1) + ".Try");
            System.out.println("-------------------");
            hillClimbing();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            System.out.println("Duration : " + duration / Math.pow(10, 6) + " millisecond");
        }
    }

    public static int[] randomTableGenerator() {
        Random random = new Random();
        int[] pTable = new int[8];

        for (int i = 0; i < 8; i++) {
            int randomNUmber = random.nextInt(8) + 1;
            pTable[i] = randomNUmber;
        }
        return pTable;
    }

    public static int fitnessCalculator(int[] table) {
        int fitness = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = i + 1; j < table.length; j++) {
                if (table[j] == table[i]) {
                    fitness += 2;
                }
                if ((j - i) == Math.abs(table[i] - table[j])) {
                    fitness += 2;
                }
            }
        }
        return fitness;
    }

    public static int[] bestN(int[] table) {
        int[] BestT;
        BestT = table.clone();
        int BestF = fitnessCalculator(table);
        int[] tempT;

        for (int i = 0; i < 8; i++) {
            tempT = table.clone();
            for (int j = 1; j < 9; j++) {
                if (tempT[i] != j) {
                    tempT[i] = j;
                }
                int tempF = fitnessCalculator(tempT);
                if (tempF < BestF) {
                    BestT = tempT.clone();
                    BestF = tempF;
                }
            }
        }
        return BestT;
    }

    public static void hillClimbing() {
        int[] tempT = randomTableGenerator().clone();
        int randomCount = 0, moveCount = 0;
        boolean exitLoop = false;

        while (!exitLoop) {

            if (fitnessCalculator(tempT) > fitnessCalculator(bestN(tempT)) && fitnessCalculator(tempT) != 0) {
                tempT = bestN(tempT).clone();
                moveCount++;

            } else if (fitnessCalculator(tempT) <= fitnessCalculator(bestN(tempT)) && fitnessCalculator(tempT) != 0) {
                tempT = randomTableGenerator();
                randomCount++;

            } else {
                for (int i = 0; i < 8; i++) {
                    System.out.print(tempT[i]);
                }
                System.out.println(" ");
                System.out.println("Fitness: " + fitnessCalculator(tempT));
                System.out.println("Move Count: " + moveCount);
                System.out.println("Random Count: " + randomCount);
                exitLoop = true;

                for (int i = 0; i < 8; i++) {
                    for (int j = 1; j < 9; j++) {
                        if (tempT[i] == j) {
                            System.out.print(" Q ");
                        } else {
                            System.out.print(" - ");
                        }
                    }
                    System.out.println(" ");
                }
            }
        }
    }
}


