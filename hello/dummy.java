package com.test;

public class dummy {
    public static void main(String[] args) {

        int [][] queries = {{0,2},{2,3}};

        int [] nums = {4,3,1,6};

        int queriesLength = queries.length;
        boolean [] status = new boolean[queries.length];
        int min = 0;
        int max = 0;
        boolean statusVal = true;


        for (int i = 0; i < queriesLength; i++) {
            int[] subQArr = queries[i];
            status[i] = true;

            if (i == 0) {
                min = subQArr[0];
                max = subQArr[1];

                for (int j = subQArr[0] + 1; j <= subQArr[1]; j++) {
                    int prev = j - 1;

                    if (((nums[prev] % 2 == 0) && (nums[j] % 2 == 0))
                            || ((nums[prev] % 2 != 0) && (nums[j] % 2 != 0))) {
                        status[i] = false;
                        statusVal = false;
                        break;
                    }

                }
            } else {
                if ((subQArr[0] > min) && (subQArr[1] < max)) {
                    status[i] = statusVal;
                }
                if (((((subQArr[0] > min) && (subQArr[0] < max)) || ((subQArr[1] > min) && (subQArr[1] < max)))
                        && ((subQArr[0] < min) && (subQArr[1] > max)))
                        && statusVal == false) {
                    status[i] = statusVal;
                    if(subQArr[0] < min){
                        min = subQArr[0];
                    }

                    if(subQArr[1] > max){
                        max = subQArr[1];
                    }
                }
                if ((subQArr[0] < min) && (subQArr[1] < max) && statusVal == true) {
                    for (int j = subQArr[0] + 1; j <= min; j++) {
                        int prev = j - 1;

                        if (((nums[prev] % 2 == 0) && (nums[j] % 2 == 0))
                                || ((nums[prev] % 2 != 0) && (nums[j] % 2 != 0))) {
                            status[i] = false;
                            statusVal = false;
                            break;
                        }

                    }

                    min = subQArr[0];
                }

                if ((subQArr[0] > min) && (subQArr[1] > max) && statusVal == true) {
                    for (int j = max + 1; j <= subQArr[1]; j++) {
                        int prev = j - 1;

                        if (((nums[prev] % 2 == 0) && (nums[j] % 2 == 0))
                                || ((nums[prev] % 2 != 0) && (nums[j] % 2 != 0))) {
                            status[i] = false;
                            statusVal = false;
                            break;
                        }

                    }

                    max = subQArr[1];
                }

                if (((subQArr[0] < min) && (subQArr[1] < min)) || ((subQArr[0] > max) && (subQArr[1] > max))) {

                    for (int j = subQArr[0] + 1; j <= subQArr[1]; j++) {
                        int prev = j - 1;

                        if (((nums[prev] % 2 == 0) && (nums[j] % 2 == 0))
                                || ((nums[prev] % 2 != 0) && (nums[j] % 2 != 0))) {
                            status[i] = false;
                            // statusVal = false;
                            break;
                        }

                    }
                }
            }

        }

        for (int i = 0; i < status.length; i++) {
            System.out.println(status[i]);
        }
    }

}
