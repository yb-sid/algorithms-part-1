package week1.ungraded_assignments.social_net_conn;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class SocialNetworkConnectivity {
    public static void main(String[] args)  {
        // read input file
        Path path = Paths.get("src/week1/ungraded_assignments/friend_timestamp.txt");
        try(Stream<String> stream = Files.lines(path)){
            int N = (int)stream.count();
            WeightedQuickUnionUF unionFind = new WeightedQuickUnionUF(N);
            AtomicInteger num_components = new AtomicInteger(N);
            int earliestTime = getEarliesTime(path, unionFind, num_components);
            if(earliestTime>=0){
                StdOut.println("earliest time at which all are connected is :" + earliestTime);
            }

        }catch (IOException e){
            StdOut.println("IOException "+e.getMessage());
        }
    }

    private static int getEarliesTime(Path path, WeightedQuickUnionUF unionFind, AtomicInteger num_components) {
        try(Stream<String> lines = Files.lines(path)){
            return  lines.map(line -> {
                List<Integer> splitLine = Arrays.stream(line.split(" ")).map(Integer::parseInt).toList();
                int time = splitLine.get(0);
                int A = splitLine.get(1);
                int B = splitLine.get(2);
                if(unionFind.find(A)!= unionFind.find(B)){
                    unionFind.union(A,B);
                    num_components.addAndGet(-1);
                    if(num_components.intValue()==1){
                        return time;
                    }
                }
                return -1;
            }).filter(e -> e>=0).findFirst().orElse(-1);
        }catch (IOException e){
            StdOut.println("IOException "+e.getMessage());
        }
        return -1;
    }
}
