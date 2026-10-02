//problem1
class Solution {
    HashMap<Integer, Integer> map;
    public int confusingNumberII(int n) {
        this.map = new HashMap<>();

        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);

        int count = 0;

        Queue<Long> q = new LinkedList<>();
        q.add(0l);

        while(!q.isEmpty()){
            long currNum = q.poll();

            if(isConfusing((int)currNum)) count++;

            for(int key : map.keySet()){
                long newNum = currNum * 10 + key;

                if(newNum != 0 && newNum <= n){
                    q.add(newNum);
                }
            }
        }

        return count;
    }

    private boolean isConfusing(int num){ 
        int temp = num;
        int result = 0;
        while(num > 0){ 
            int lastDigit = num % 10;
            result = result * 10 + map.get(lastDigit);
            num = num / 10;
        }

        return result != temp;
    }
}

//problem2
class Solution {
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        int maxLength = 0;

        for(int num : matchsticks){
            sum += num;
            maxLength = Math.max(maxLength, num);
        }

        int lengthOfEachSide = sum / 4;

        if(sum % 4 != 0) return false;
        if(maxLength > lengthOfEachSide) return false;

        return helper(matchsticks, 0, new int[4], lengthOfEachSide);
    }

    private boolean helper(int[] matchsticks, int idx, int[] square, int lengthOfEachSide){

        if(idx == matchsticks.length) return true;

        for(int i=0; i<4; i++){
            if(square[i] + matchsticks[idx] <= lengthOfEachSide){

                square[i] += matchsticks[idx];

                if(helper(matchsticks, idx+1, square, lengthOfEachSide)) return true;

                square[i] -= matchsticks[idx];
            }
        }

        return false;
    }
}
