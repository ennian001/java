package array;

public class SortArray {
    public static void main(String[] args) {
        int array[] = {43,2,6,22,9};

        for (int i = 0 ; i<array.length-1;i++){
            for (int j =0 ; j< array.length - 1 - i;j++){
                if (array [j]<array[j+1]){
                    int t = array[j];
                    array[j] = array[j+1];
                    array[j+1] = t;
                }
            }
        }
        for (int i =0  ; i<array.length-1;i++){
            System.out.println(array[i]);
        }

    }


}
