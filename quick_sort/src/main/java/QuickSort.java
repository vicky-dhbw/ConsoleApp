public class QuickSort {
    private static final QuickSort quickSortInstance=new QuickSort();
    public Port port;
    private String[] values;
    public QuickSort(){
        port= new Port();
    }

    public static QuickSort getInstance(){
        return quickSortInstance;
    }
    public void innerSort(String[] values) {
        if (values == null || values.length == 0) {
            return;
        }
        this.values = values;
        int length = values.length;
        quickSort(0, length - 1);
    }

    public void quickSort(int minimum, int maximum) {
        int i = minimum;
        int j = maximum;
        String pivot = values[minimum + (maximum - minimum) / 2];

        while (i <= j) {
            while (values[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (values[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        if (minimum < j) {
            quickSort(minimum, j);
        }

        if (i < maximum) {
            quickSort(i, maximum);
        }
    }

    public void swap(int i, int j) {
        String temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }


    public class Port implements IQuickSort{

        @Override
        public void sort(String[] values) {
            innerSort(values);
        }
    }
}
