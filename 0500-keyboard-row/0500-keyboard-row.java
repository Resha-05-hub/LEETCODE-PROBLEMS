class Solution {
    public String[] findWords(String[] words) {
        ArrayList<String>ans=new ArrayList<>();
        String first="qwertyuiop";
        String second="asdfghjkl";
        String third="zxcvbnm";
        for(String s:words){
            if(isinrow(s, first)||isinrow(s, second)||isinrow(s, third)){
                ans.add(s);
            }
        }
        return ans.toArray(new String[0]);
    }
    private boolean isinrow(String s, String row){
        for(char c: s.toCharArray()){
            if(row.indexOf(Character.toLowerCase(c))==-1){
                return false;
            }
        }
        return true;
    }
}