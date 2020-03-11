package PP02CourseWork;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class test {
    public static void main(String[] args) throws IOException {


        ArrayList<String> a = new ArrayList<>(10);
        ArrayList<String> b = new ArrayList<>(10);
        a.add("A54");
        a.add("A12");
        a.add("A16");
        a.add("A7");

        b.add("Ram");
        b.add("Sri");
        b.add("adc");
        b.add("fr");

        FileWriter fr = new FileWriter("D:/BookedNames.txt");
        BufferedWriter br = new BufferedWriter(fr);
        PrintWriter out = new PrintWriter(br);
        for (int i = 0; i < b.size(); i++) {
            if (b.get(i) != null)
                out.write(b.get(i)+"-"+a.get(i));
            out.write("\n");
        }
        out.close();

//        FileWriter fr2 = new FileWriter("D:/BookedSeats.txt");
//        BufferedWriter br2 = new BufferedWriter(fr);
//        PrintWriter out2 = new PrintWriter(br);
//        for (int i = 0; i < b.size(); i++) {
//            if ( != null)
//                out.write(a.get(i));
//            out.write("\n");
//        }
//        out.close();

//        File s = new File("D:/BookedNames.txt");
//        BufferedReader br3 = new BufferedReader(new FileReader(s));
//        String st;
//        ArrayList<String> list = new ArrayList<String>();
//        while (s.hasNext()) {
//            list.add(s.next());
//        }
//        s.close();
//        while ((st = br3.readLine()) != null)
//            List<String> a.add ()
//            System.out.println(st);
//    }
    }
}
