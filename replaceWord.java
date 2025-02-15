//https://leetcode.com/problems/replace-words/
/*
// Space complexity: O(Nk) + O(N)
//time complexity: O(Nk) + O(N), k replacements and N words
/*first add all the strings from dict. 
Then traverse through characters of each word in sentence. 
If for any character isWord is true 
then send the string from start to till that character. 
Else return whole word.
*/


 class Solution {
     public String replaceWords(List<String> dict, String sentence) {
         TrieNode root = new TrieNode('-');

         for(String word:dict){
            insert(root,word);
         }

         StringBuilder sb = new StringBuilder();
         String[] words = sentence.split(" ");
         for(String word:words){
             sb.append(getRoot(root,word));
             sb.append(" ");
         }

         int length = sb.length();
         sb.setLength(length-1);
         return new String(sb);
     }

    public void insert(TrieNode node,String word) {
         TrieNode temp = node;
         for(int i=0;i<word.length();i++){
             if(temp.map.containsKey(word.charAt(i))){
                 temp = temp.map.get(word.charAt(i));
             }
             else{
                 temp.map.put(word.charAt(i),new TrieNode(word.charAt(i)));
                 temp = temp.map.get(word.charAt(i));
             }
         }
       temp.isWord = true;
     }


     public String getRoot(TrieNode root,String word){
         TrieNode temp = root; StringBuilder sb = new StringBuilder();

         for(int i=0;i<word.length();i++){
             if(temp.map.containsKey(word.charAt(i)))
                 temp = temp.map.get(word.charAt(i));
                 sb.append(word.charAt(i));
                 if(temp.isWord){
                     return new String(sb);
                 }
             }
             else{
               break;
             }
         }

         return word;
     }
 }

 class TrieNode {
     char c;
     HashMap<Character,TrieNode> map;
     boolean isWord;
     
     TrieNode(char c){
         this.c = c;
         map = new HashMap<>();
         isWord = false;
     }
 }

