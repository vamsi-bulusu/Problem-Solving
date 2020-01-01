
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    private static String check(String[] str,int n){
        Map<String,Integer> hashMap = new HashMap<>();
        for (String val:str) {
            if(!hashMap.containsKey(val)){
                hashMap.put(val,1);
            }
            else{
                hashMap.put(val,hashMap.get(val)+1);
            }
        }
        return check_overs_bowled(hashMap,n);
    }
    private static String check_overs_bowled(Map<String, Integer> hashMap, int number_overs){
        if(number_overs%5==0){
            int max_overs = number_overs/5;
            for (Map.Entry<String, Integer> overs:hashMap.entrySet()) {
                if(overs.getValue() > max_overs) {
                    return "no";
                }
            }
            return "yes";
        }
        else{
            int max_overs = number_overs/5;
            int max_bowlers = number_overs%5;
            int count = 0;
            for (Map.Entry<String, Integer> overs:hashMap.entrySet()) {
                if(overs.getValue() > max_overs+1){
                    return "no";
                }
                if(overs.getValue() == max_overs+1) {
                    count++;
                }
            }
            if(count == max_bowlers && number_overs>=5){
                return "yes";
            }
            return "no";
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(bufferedReader.readLine());
        Map<String,Integer> hashMap = new HashMap<>();
        while(test-- != 0){
            int number_overs = Integer.parseInt(bufferedReader.readLine());
            for(int i = 0;i<number_overs;i++){
                String name = bufferedReader.readLine();
                if(!hashMap.containsKey(name)){
                    hashMap.put(name,1);
                }
                else{
                    hashMap.put(name,hashMap.get(name)+1);
                }
            }
            System.out.println(check_overs_bowled(hashMap,number_overs));
        }
    }
}
