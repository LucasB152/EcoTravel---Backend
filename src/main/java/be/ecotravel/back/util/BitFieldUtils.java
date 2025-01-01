package be.ecotravel.back.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BitFieldUtils {

    public static String[] toArray(String[] sourceArray, int value) {
        if (sourceArray == null || sourceArray.length == 0) {
            return new String[0];
        }



        List<String> selectedValue = new ArrayList<>();
        for (int i = 0; i < sourceArray.length; i++) {
            if ((value & (1 << i)) != 0) {
                selectedValue.add(sourceArray[i]);
            }
        }
        return selectedValue.toArray(new String[0]);
    }

    public static int toNumber(String[] sourceArray, String[] selectedValues) {
        if (sourceArray == null || sourceArray.length == 0 || selectedValues == null || selectedValues.length == 0) {
            return 0;
        }

        sourceArray = Arrays.stream(sourceArray)
                .map(String::toLowerCase)
                .toArray(String[]::new);

        selectedValues = Arrays.stream(selectedValues)
                .map(String::toLowerCase)
                .toArray(String[]::new);

        int bitCount = 0;
        for (String selectedValue : selectedValues) {
            for (int i = 0; i < sourceArray.length; i++) {
                if (sourceArray[i].equals(selectedValue)) {
                    bitCount |= (1 << i);
                }
            }
        }

        return bitCount;
    }

}
