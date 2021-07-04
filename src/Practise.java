import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Practise {
    public boolean oddOneOut(String[] arr) {
        /*One difrend from array */

        int[] temp = new int[arr.length];

        for( int i = 0; i < arr.length;i++){
            temp[i] = arr[i].length();
        }

        int count = 0;

        if (temp[0] == temp[1] && temp[0] != temp[2])
            count++;
        if (temp[0] == temp[2] && temp[0] != temp[1])
            count++;
        if (temp[1] == temp[2] && temp[0] != temp[1])
            count++;

        for (int i = 3; i < temp.length; i++)
            if (temp[i] != temp[i - 1])
                count++;


        if( count == 1){
            return true;
        }else {
            return false;
        }

    }

    public static String specialReverseString(String str) {
        /*revers String kepping all spice*/
        String reverse = new StringBuilder(str).reverse().toString().replaceAll(" ", "");
        StringBuilder s = new StringBuilder();
        int n = 0;
        for(char c : str.toCharArray()){
            if( Character.isUpperCase(c)){
                s.append(Character.toUpperCase(reverse.charAt(n)));
            }else if(!Character.isSpaceChar(c)){
                s.append(Character.toLowerCase(reverse.charAt(n)));
            }else{
                s.append(c);
                n--;
            }
            n++;
        }
        return s.toString();
    }
/*remove element from arrey*/
    public static String[] removeTheElement(String[] arr, int index)
    {
        if (arr == null
                || index < 0
                || index >= arr.length) {

            return arr;
        }
        String[] anotherArray = new String[arr.length - 1];

        for (int i = 0, k = 0; i < arr.length; i++) {

            if (i == index) {
                continue;
            }
            anotherArray[k++] = arr[i];
        }
        return anotherArray;
    }
    /*add to array*/
    public int[] add_element(int n, int myarray[], int ele)
    {
        int i;

        int newArray[] = new int[n + 1];
        //copy original array into new array
        for (i = 0; i < n; i++)
        newArray[i] = myarray[i];

        //add element to the new array
        newArray[n] = ele;

        return newArray;
    }

    /*MAtrix and creat mnatrix */
    public static int[][] increment(int r, int c, String[] numArray) {
        int[][] matrix = new int[r][c];
        for(int i = 0; i < numArray.length;i++){
            char[] temp = numArray[i].toCharArray();
            int temmpNum = Character.getNumericValue(temp[0]);
            if(temp[1] == 'r'){
                for(int j = 0; j < c;j++){
                    matrix[temmpNum][j] = matrix[temmpNum][j] + 1;
                }
            }else if(temp[1] == 'c'){
                for(int j = 0; j < r;j++){
                    matrix[j][temmpNum] = matrix[j][temmpNum] + 1;
                }
            }
        }
        return matrix;
    }
/*print matrix*/
    public static void printMatrix(int[][] matrix, int matrixRow, int matrixCol){
        System.out.println("Your Matrix is : ");

        for (int i = 0; i < matrixRow; i++)
        {
            for (int j = 0; j < matrixCol; j++)
            {
                System.out.print(matrix[i][j]+"\t");
            }

            System.out.println();
        }
    }

    public static int longestRun(int[] arr) {
        int run = 1;
        int up, down;
        up = down = 1;

        for(int i = 0; i < arr.length - 1; i++){
            if(arr[i] == arr[i + 1] + 1){
                up++;
                if(up > run) run = up;
            }
            else up = 1;

            if(arr[i] == arr[i + 1] - 1){
                down++;
                if(down > run) run = down;
            }
            else down = 1;
        }

        return run;
    }

    public static String hiddenAnagram(String t, String p) {
        t = t.toLowerCase().replaceAll("[^a-z]", "");
        p = p.toLowerCase().replaceAll(" ", "");

        for(int i = 0; i < t.length() - p.length() +1; i++){
            if(p.indexOf(t.charAt(i)) > -1){
                String subT = t.substring(i, i + p.length());
                if(isAnagram(subT, p)){ return subT;}
            }
        }
        return "noutfond";
    }
/*anagram */
    private static Boolean isAnagram(String str1, String str2){
        StringBuilder s = new StringBuilder(str2);
        for(int i=0; i<str1.length(); i++){
            int in = s.indexOf(String.valueOf(str1.charAt(i)));
            if(in == -1){
                return false;
            }

            s.setCharAt(in, '-');
        }

        return true;
    }
    /*enrition*/
    public static String encryption(String s) {
        String noSpace = s.replaceAll(" ", "");
        double length = (double) noSpace.length();
        double rows = Math.floor(Math.sqrt(length));
        double columnes = (rows >= Math.sqrt(length)) ? rows : rows + 1;
        StringBuilder result = new StringBuilder();

        for( int j = 0; j < columnes; j++){
            for( int i = j; i <length;i+=columnes){
                result.append(noSpace.charAt(i));
            }
            result.append(' ');
        }

        return result.toString().trim();

    }
    /*String URl params*/
    public static String stripUrlParams(String url, String[] paramsToStrip) {

        if(!url.contains("?")){
            return url;
        }

        String[] parts = url.split("\\?");
        StringBuilder sb = new StringBuilder(parts[0]);
        String[] params = parts[1].split("&");
        HashMap<String,String> map = new HashMap<>();

        for(String param : params){
            String[] parm = param.split("=");
            map.put(parm[0],parm[1]);
        }

        LinkedHashSet<String> strip = new LinkedHashSet<>();

        if(paramsToStrip != null){
            strip.addAll(Arrays.asList(paramsToStrip));
        }

        sb.append("?");
        int k = 1;

        for(String key : map.keySet()){
            if(!strip.contains(key)){
                if(k>1)
                    sb.append("&");
                sb.append(key).append("=").append(map.get(key));
                k++;
            }
        }

        return sb.toString();

    }





    public static String[] splitStringEvery(String s, int interval) {
        int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
        String[] result = new String[arrayLength];

        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        } //Add the last bit
        result[lastIndex] = s.substring(j);

        return result;
    }
/*reverse map*/
    public static Map<Object, Integer> countRepititions(Object[] e) {

        Map<Object,Integer> map = new HashMap<Object, Integer>();
        LinkedList ob = new LinkedList(Arrays.asList(e));


        for(int i = 0; i < ob.size();i++){
            int count = 0;
            for( int j = 0; j < ob.size();j++){
                if(ob.get(i) == ob.get(j)){
                    count++;
                }
            }
            map.put(ob.get(i),count);
            count = 0;
        }
        LinkedHashMap<Object, Integer> reverseSortedMap = new LinkedHashMap<>();

//Use Comparator.reverseOrder() for reverse ordering
        map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;
    }
/*asscendong numbers*/
    public static String generateWord(int n) {
        return fibWord(n, 2, "b, a");
    }
    public static String fibWord(int n, int idx, String str) {
        if (n < 2) return "invalid";
        if (idx == n) return str;
        String[] s = str.split(", ");
        int len = s.length;
        str += ", " + s[len-1] + s[len-2];
        return fibWord(n, idx+1, str);
    }

    public static boolean ascending(String str) {
        out: for (int digits = 1; digits < str.length() / 2 + 1; digits++) {
            boolean failed = false;
            if (str.length() % digits != 0) continue;
            int[] numbers = new int[str.length() / digits];
            for (int i = 0; i < numbers.length; i++)
                numbers[i] = Integer.parseInt(str.substring(i * digits, i * digits + digits));
            for (int i = 0; i < numbers.length - 1; i++)
                if (numbers[i] + 1 != numbers[i + 1]) continue out;
            return true;
        }
        return false;
    }
/*Comper to sorting reverse (lexicographically)*/
    public static String reverseSort(String str) {
        String[] strSplit = str.split(" ");

        for(int i = strSplit.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (strSplit[i].toLowerCase().compareTo(strSplit[j].toLowerCase()) > 0) {
                    String temp = strSplit[i];
                    strSplit[i] = strSplit[j];
                    strSplit[j] = temp;
                }
            }
        }

        Arrays.sort(strSplit, Comparator.comparingInt(String::length).reversed());

        String result = "";

        for(int i = 0; i < strSplit.length; i++) {
            result += strSplit[i] + " ";
        }

        return result.trim();
    }
/*remove string */
    public static String[] removeLetters(String[] letters, String word) {
        LinkedList<String> list = new LinkedList<String>();
        for (String l : letters) list.add(l);
        for (char c : word.toCharArray()) list.remove("" + c);
        return list.toArray(new String[list.size()]);
    }
    /*List to array*/
    public int[] toIntArray(List<Integer> intList){
        return intList.stream().mapToInt(Integer::intValue).toArray();
    }
/*contein exakly string w kolejnosci literowej*/
    public static boolean canComplete(String initial, String word) {
        String regex = String.join( ".*", initial.split(""));
        return word.matches(regex);
    }
    /*contein string */

    public static boolean canvComplete(String initial, String word) {

        int worldLenght = word.length();

        for(int i = 0; i < initial.length(); i++){
            word = word.replaceFirst(String.valueOf(initial.charAt(i)), "");
        }

        return worldLenght - initial.length() == word.length();


    }
/*ECG*/
    public static int ecgSequenceIndex(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int last = 2;
        int i = 3;
        while (true) {
            if (!list.contains(i) && (gcd(i, last) != 1)) {
                list.add(i);
                last = i;
                if (list.contains(n)) {
                    return list.indexOf(n);
                }
                i = 2; //reset back to beginning
            }
            i++;
        }
    }
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }
/*Time zone */
    public static String timeDifference(String cityA, String timestamp, String cityB) {

        Map<String, Integer> timeZones = new HashMap<>();
        timeZones.put("Los Angeles", -480);
        timeZones.put("New York", -300);
        timeZones.put("Caracas", -270);
        timeZones.put("Buenos Aires", -180);
        timeZones.put("London", 0);
        timeZones.put("Rome", 60);
        timeZones.put("Moscow", 180);
        timeZones.put("Tehran", 210);
        timeZones.put("New Delhi", 330);
        timeZones.put("Beijing", 480);
        timeZones.put("Canberra", 600);

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy HH:mm", Locale.ENGLISH);
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy-M-d HH:mm");
        Date date = null;
        try {
            date = sdf.parse(timestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, (timeZones.get(cityB) - timeZones.get(cityA)));
        String newTime = df2.format(cal.getTime());

        return newTime;
    }

/*sqer num */
    public static int squares(int a, int b) {

        int count = 0;

        for (int i = a; i <= b; i++) {

            int number = i;

            int sqrt = (int) Math.sqrt(number);
            if (sqrt * sqrt == number) {
                count++;
            }
        }

        return count;

    }

}


