

/**
# 2. anagramsOf(word)
#
#    anagramsOf("post") --> ["pots", "spot", "stop", "tops"]
#    anagramsOf("dictionary") --> ["indicatory"]
#    anagramsOf("anagram") --> []
#
# Given a text file of English language words, and preprocessing time, how would you solve this?

# Anagrams: rearrange letters to form another word!
# https://en.wikipedia.org/wiki/Anagram
#
# Example: "spot" is an anagram of "stop".
#
# 1. isAnagram(word1, word2)
#
#    isAnagram("fired", "fried") --> true
#    isAnagram("alerting", "triangle") --> true
#    isAnagram("alerting", "triangles") --> false
#    isAnagram("cat", "dog") --> false

# Anagrams: rearrange letters to form another word!
# https://en.wikipedia.org/wiki/Anagram
#
# Example: "spot" is an anagram of "stop".
#
# 1. isAnagram(word1, word2)
#
#    isAnagram("fired", "fried") --> true
#    isAnagram("alerting", "triangle") --> true
#    isAnagram("alerting", "triangles") --> false
#    isAnagram("cat", "dog") --> false
public boolean isAnagram(String word1, String word2){
    boolean isAnagram = true;
    if(word1.length == word2.length){
        Map<char> charMap = HashMap<>();
        for(char in word1) {
            if(charMap.contains(char)){
                charMap.put(char, 1);
            }
            else {
                int freq = charMap.get(char);
                charMap.put(char, freq++);
            }
        }
        Map<car> charMap2 = MashMap<>();
        
        for(char in word2) {
            if(!charMap.contains(char){
                break!
                isAnagram = false;
            }
            else {
                int freq = charMap.get(char);
                if(charMap2.contains(char)){
                      int freq2 = charMap2.get(char);
                      if(fre2 > freq) {
                          break;
                          isAnagram = false;
                      }
                      else {
                          charMap2.put(char, freq2++); 
                      }
;                }
                else {
                    charMap2.put(char, 1);
                }
            }
        
        }
    
    }
    else {
        isAnagram = false;
    }
        
    
    return isAnagram;
}







# 2. anagramsOf(word)
#
#    anagramsOf("post") --> ["pots", "spot", "stop", "tops"]
#    anagramsOf("dictionary") --> ["indicatory"]
#    anagramsOf("anagram") --> []
#
# Given a text file of English language words, and preprocessing time, how would you solve this?

**/

