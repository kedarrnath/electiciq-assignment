package Utils;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Utils {
    public static boolean checkSortingString(ArrayList<String> list) {
        ArrayList<String> copy = new ArrayList<>(list);

        Arrays.sort(copy.toArray());

        return copy.equals(list);
    }

    public static ArrayList<Double> numberFormatter(ArrayList<String> list) {

        ArrayList<Double> list1 = new ArrayList<Double>();

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).toString().contains("M") && !list.get(i).toString().contains("k"))
                list1.add(Double.valueOf(list.get(i)));
            else if (!list.get(i).toString().contains(".")) {
                char type1 = list.get(i).toString().charAt(list.get(i).toString().length() - 1);
                Integer num1 = Integer.parseInt(list.get(i).toString().substring(0, list.get(i).toString().length() - 1));
                switch (type1) {
                    case 'k':
                        list1.add(Double.valueOf(num1 * 1000));
                        break;
                    case 'M':
                        list1.add(Double.valueOf(num1 * 1000000));
                        break;
                }

            } else
                try {
                    double num = Double.parseDouble(list.get(i).toString());
                    System.out.println(list.get(i).toString());
                } catch (Exception e) {
                    char type = list.get(i).toString().charAt(list.get(i).toString().length() - 1);
                    double num = Double.parseDouble(list.get(i).toString().substring(0, list.get(i).toString().length() - 1));
                    switch (type) {
                        case 'K':
                            list1.add(num * 1000);
                            break;
                        case 'M':
                            list1.add(num * 1000000);
                            break;
                    }
                }
        }
        return list1;
    }

    public static boolean checkSortingDouble(ArrayList<Double> list) {
        ArrayList copy = new ArrayList(list);

        Arrays.sort(copy.toArray());

        return copy.equals(list);
    }

    public static boolean checkSortingFloat(ArrayList<Float> list) {
        ArrayList copy = new ArrayList(list);

        Arrays.sort(copy.toArray());

        return copy.equals(list);
    }
}





